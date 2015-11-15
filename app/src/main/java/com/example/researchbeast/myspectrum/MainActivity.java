package com.example.researchbeast.myspectrum;

import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.TransitionDrawable;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.animation.AlphaAnimation;
import android.view.animation.Animation;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.example.researchbeast.myspectrum.svg.AndroidLogoPaths;
import com.example.researchbeast.myspectrum.svg.AnimatedSvgView;

import butterknife.Bind;
import butterknife.ButterKnife;

public class MainActivity extends AppCompatActivity {
    @Bind(R.id.newEventBtn) Button mNewEventButton;
    @Bind(R.id.viewHistoryBtn) Button mViewHistoryButton;
    @Bind(R.id.next_btn) Button mNextButton;
    @Bind(R.id.welcomeTextView) TextView mWelcomeText;
    @Bind(R.id.intro_main_textview) TextView mIntroText;
    @Bind(R.id.app_name_textview) TextView mAppNameText;
    @Bind(R.id.anim_container) LinearLayout mAnimLayout;
    @Bind(R.id.animated_svg_view) AnimatedSvgView mSvgView;

    final Animation in = new AlphaAnimation(0.0f, 1.0f);

    final Animation out = new AlphaAnimation(1.0f, 0.0f);

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        in.setDuration(800);
        out.setDuration(800);

        out.setAnimationListener(new Animation.AnimationListener() {
            @Override
            public void onAnimationStart(Animation animation) {
            }

            @Override
            public void onAnimationEnd(Animation animation) {
                mNextButton.setVisibility(View.GONE);
                mWelcomeText.setVisibility(View.GONE);
                mIntroText.setVisibility(View.GONE);
                mViewHistoryButton.setVisibility(View.VISIBLE);
                mViewHistoryButton.startAnimation(in);
                mNewEventButton.setVisibility(View.VISIBLE);
                mNewEventButton.startAnimation(in);
                mAppNameText.setVisibility(View.VISIBLE);
                mAppNameText.startAnimation(in);
                mSvgView.start();
            }

            @Override
            public void onAnimationRepeat(Animation animation) {
            }

        });
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);
        mSvgView.setGlyphStrings(AndroidLogoPaths.BOUY_GLYPHS);
        mSvgView.setFillPaints(
                new int[]{255, 255, 255, 255},
                new int[]{255, 255, 255, 255},
                new int[]{255, 255, 255, 255},
                new int[]{255, 255, 255, 255});
        int traceColor = Color.argb(255, 0, 0, 0);
        int[] traceColors = new int[4]; // 4 glyphs
        int residueColor = Color.argb(50, 0, 0, 0);
        int[] residueColors = new int[4]; // 4 glyphs

        // Every glyph will have the same trace/residue
        for (int i = 0; i < traceColors.length; i++) {
            traceColors[i] = traceColor;
            residueColors[i] = residueColor;
        }
        mSvgView.setTraceColors(traceColors);
        mSvgView.setTraceResidueColors(residueColors);
        final TransitionDrawable transition = (TransitionDrawable) getWindow().findViewById(R.id.rootView).getBackground();

        mNextButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mAnimLayout.startAnimation(out);
                transition.startTransition(800);
            }
        });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
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

    public void viewHistory(View v){
        startActivity(new Intent(MainActivity.this, ViewHistoryActivity.class));
    }

    public void onNewEventClick(View view) {
        startActivity(new Intent(this, NewEventActivity.class));
    }
}
