package com.example.introslider;

import androidx.appcompat.app.AppCompatActivity;
import androidx.viewpager.widget.ViewPager;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;

import com.example.introslider.pagerAdapters.IntroViewPager;
import com.google.android.material.tabs.TabLayout;


public class IntroSlider extends AppCompatActivity {

    private ViewPager viewPager;
    private TabLayout tabIndicator;
    private Button btnNext, btnBack;
    int[] slides = {R.layout.intro_slide1, R.layout.intro_slide2};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getWindow().addFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
        setContentView(R.layout.activity_intro_slider);

//       <--  get state of IntroPrefManager   -->
        IntroPrefManager introPrefManager = new IntroPrefManager(this);
        if (introPrefManager.getState()) {
            introPrefManager.setIntro(true);
            goToMainActivity();
        }

        setupViews();
    }

    private void setupViews() {
        viewPager = findViewById(R.id.intro_viewPager);
        btnBack = findViewById(R.id.btn_intro_back);
        btnNext = findViewById(R.id.btn_intro_next);
        tabIndicator = findViewById(R.id.tabIndicator_intro);
        viewPager.setAdapter(new IntroViewPager(this, slides));

//      <--    setup viewpager with tabLayout -->
        tabIndicator.setupWithViewPager(viewPager);

//        ---------------
        viewPager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

            }

            @Override
            public void onPageSelected(int position) {
                if (position==slides.length-1){
                    btnNext.setText(getString(R.string.BTN_START));
                    btnBack.setText(getString(R.string.BTN_BACK));

                }else if (position==0){
                    btnBack.setText(getString(R.string.BTN_SKIP));
                    btnNext.setText(getString(R.string.BTN_NEXT));
                }else {
                    btnNext.setText(getString(R.string.BTN_NEXT));
                    btnBack.setText(getString(R.string.BTN_BACK));
                }
            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });
//        --------------------------
        //      <-- click Button methods  -->

        btnNext.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                int nextSlide=viewPager.getCurrentItem()+1;
                if (nextSlide<slides.length){
                    viewPager.setCurrentItem(nextSlide);
                }else {
                    IntroPrefManager introPrefManager = new IntroPrefManager(IntroSlider.this);
                    introPrefManager.setIntro(true);
                    goToMainActivity();
                }
            }
        });

        btnBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (viewPager.getCurrentItem()==0){
                    IntroPrefManager introPrefManager = new IntroPrefManager(IntroSlider.this);
                    introPrefManager.setIntro(true);
                    goToMainActivity();

                }else{
                    viewPager.setCurrentItem(viewPager.getCurrentItem()-1);
                }
            }
        });

    }

    private void goToMainActivity() {
        Intent intent = new Intent(IntroSlider.this, MainActivity.class);
        startActivity(intent);
        finish();
    }

}
