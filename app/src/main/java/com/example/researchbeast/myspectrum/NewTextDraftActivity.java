package com.example.researchbeast.myspectrum;

import android.content.ContentResolver;
import android.content.Intent;
import android.content.SharedPreferences;
import android.database.Cursor;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.provider.ContactsContract;
import android.provider.ContactsContract.CommonDataKinds.Phone;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.text.Html;
import android.text.method.LinkMovementMethod;
import android.view.MenuItem;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.example.researchbeast.myspectrum.models.NewDraftModel;
import com.google.gson.Gson;

import org.w3c.dom.Text;

import java.util.ArrayList;
import java.util.Collections;

public class NewTextDraftActivity extends AppCompatActivity {
    private Toolbar mToolbar;
    SharedPreferences mPrefs;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_new_text_draft);
        mToolbar = (Toolbar)findViewById(R.id.toolbar);
        setSupportActionBar(mToolbar);
        getSupportActionBar().setTitle("Send a Message");
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        // shared settings
        mPrefs = PreferenceManager.getDefaultSharedPreferences(this);

        // get contacts
        ArrayList<String> contactsList = getContacts();
        contactsList.add("--Select Contact--");
        Collections.sort(contactsList);
        String[] contacts = new String[contactsList.size()];

        contacts = contactsList.toArray(contacts);
        // set spinner to contacts
        Spinner s = (Spinner) findViewById(R.id.contactsSpinner);
        ArrayAdapter<String> adapter = new ArrayAdapter<>(this, android.R.layout.simple_spinner_item, contacts);
        s.setAdapter(adapter);

        TextView helpText = (TextView) findViewById(R.id.helpText2);

        helpText.setText(Html.fromHtml(
                "Other contacts for less pressing situations include the Asperger’s 24-Hour Hotline 877-723-3422, and these Autism-specific phone lines" +
                        "<a href=\"https://www.autismspeaks.org/family-services/crisis-intervention-resources \">https://www.autismspeaks.org/family-services/crisis-intervention-resources </a>"));
        helpText.setMovementMethod(LinkMovementMethod.getInstance());
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    private ArrayList<String> getContacts() {
        ArrayList<String> contacts = new ArrayList<>();
        ContentResolver cr = getContentResolver();
        Cursor cur = cr.query(ContactsContract.Contacts.CONTENT_URI,
                null, null, null, null);
        if (cur.getCount() > 0) {
            while (cur.moveToNext()) {
                String name = cur.getString(cur.getColumnIndex(ContactsContract.Contacts.DISPLAY_NAME));
                if (Integer.parseInt(cur.getString(cur.getColumnIndex(ContactsContract.Contacts.HAS_PHONE_NUMBER))) > 0) {
                    contacts.add(name);
                }
            }
        }
        return contacts;
    }

    private String getPhoneNumber(String name) {
    //  Find contact based on name.
    //
        ContentResolver cr = getContentResolver();
        Cursor cursor = cr.query(ContactsContract.Contacts.CONTENT_URI, null,
                "DISPLAY_NAME = '" + name + "'", null, null);
        if (cursor.moveToFirst()) {
            String contactId =
                    cursor.getString(cursor.getColumnIndex(ContactsContract.Contacts._ID));
            //
            //  Get all phone numbers.
            //
            Cursor phones = cr.query(Phone.CONTENT_URI, null,
                    Phone.CONTACT_ID + " = " + contactId, null, null);
            while (phones.moveToNext()) {
                String number = phones.getString(phones.getColumnIndex(Phone.NUMBER));
                int type = phones.getInt(phones.getColumnIndex(Phone.TYPE));
                if(type == Phone.TYPE_MOBILE) {
                    return number;
                }
            }
            phones.close();
        }
        cursor.close();

        // Should never get here
        return  "Number check issue";
    }

    public void onSaveClick(View view) {
        Spinner s = (Spinner) findViewById(R.id.contactsSpinner);
        EditText text = (EditText) findViewById(R.id.draftText);
        // check that use has selected a contact
        if (s.getSelectedItem().toString().equals("--Select Contact--")) {
            Toast.makeText(this, "Error: Select a contact", Toast.LENGTH_SHORT).show();
            return;
        }
        // check that text is not empty
        if (text.getText().length() == 0) {
            Toast.makeText(this, "Error: Enter a message", Toast.LENGTH_SHORT).show();
            return;
        }

        // get contact phone number
        String contactName = s.getSelectedItem().toString();
        String phoneNumber = getPhoneNumber(contactName);
        String message = text.getText().toString();

        // save to json
        SharedPreferences.Editor mPrefEdit = mPrefs.edit();
        Gson gson = new Gson();
        NewDraftModel eventModel = new NewDraftModel(contactName, phoneNumber, message);
        String json = gson.toJson(eventModel);
        mPrefEdit.putString(contactName + phoneNumber, json);
        mPrefEdit.commit();
        Toast.makeText(getApplicationContext(),"Saved",Toast.LENGTH_SHORT).show();
        goBack();
    }

    /**
     * Go back to the main activity
     */
    private void goBack() {
        onBackPressed();
        this.finish();
    }

    public void sendMessage(View view) {
        Spinner s = (Spinner) findViewById(R.id.contactsSpinner);
        EditText text = (EditText) findViewById(R.id.draftText);
        String contactName = s.getSelectedItem().toString();
        String phoneNumber = getPhoneNumber(contactName);
        String message = text.getText().toString();

        Intent smsIntent = new Intent(android.content.Intent.ACTION_VIEW);
        smsIntent.setType("vnd.android-dir/mms-sms");
        smsIntent.putExtra("address", phoneNumber);
        smsIntent.putExtra("sms_body",message);
        startActivity(smsIntent);
    }
}
