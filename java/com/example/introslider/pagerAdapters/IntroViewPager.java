package com.example.introslider.pagerAdapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.viewpager.widget.PagerAdapter;


public class IntroViewPager extends PagerAdapter {
    Context context;
    int[] slides;

    public IntroViewPager(Context context, int[] slides) {
        this.context = context;
        this.slides = slides;
    }

    @NonNull
    @Override
    public Object instantiateItem(@NonNull ViewGroup container, int position) {
        View view=LayoutInflater.from(context).inflate(slides[position],container,false);
        container.addView(view);
        return view;
    }

    @Override
    public int getCount() {
        return slides.length;
    }

    @Override
    public boolean isViewFromObject(@NonNull View view, @NonNull Object object) {
        return view==object;
    }

    @Override
    public void destroyItem(@NonNull ViewGroup container, int position, @NonNull Object object) {
        container.removeView((View) object);
    }
}
