package com.example.researchbeast.myspectrum.activities;

import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.TransitionDrawable;
import android.os.Bundle;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.animation.AlphaAnimation;
import android.view.animation.Animation;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.example.researchbeast.myspectrum.R;
import com.example.researchbeast.myspectrum.svg.AnimatedSvgView;
import com.example.researchbeast.myspectrum.svg.LogoPaths;

import butterknife.Bind;
import butterknife.ButterKnife;

public class MainActivity extends AppCompatActivity {
    @Bind(R.id.next_btn) Button mNextButton;
    @Bind(R.id.welcomeTextView) TextView mWelcomeText;
    @Bind(R.id.intro_main_textview) TextView mIntroText;
    @Bind(R.id.app_name_textview) TextView mAppNameText;
    @Bind(R.id.anim_container) LinearLayout mAnimLayout;
    @Bind(R.id.animated_svg_view) AnimatedSvgView mSvgView;
    AppCompatActivity context = this;

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
                mAppNameText.setVisibility(View.VISIBLE);
                mAppNameText.startAnimation(in);
                mSvgView.start();
            }

            @Override
            public void onAnimationRepeat(Animation animation) {
            }

        });
        in.setAnimationListener(new Animation.AnimationListener() {
            @Override
            public void onAnimationStart(Animation animation) {}

            @Override
            public void onAnimationEnd(Animation animation) {
                new Handler().postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        startActivity(new Intent(context, CardViewActivity.class));
                        overridePendingTransition(R.anim.fade_in, R.anim.fade_out);
                        context.finish();
                    }
                }, 2500);
            }

            @Override
            public void onAnimationRepeat(Animation animation) {

            }
        });
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);
        mSvgView.setGlyphStrings(LogoPaths.BOUY_GLYPHS);
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

}
