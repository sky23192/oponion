package com.app.oponion;

import android.content.Intent;
import android.graphics.Typeface;
import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import adapter.ViewPagerAdapter;
import extra.CircleTransform;
import fragment.CircleFragment;
import fragment.OponionFragment;
import fragment.SpotlightFragment;

public class MainActivity extends AppCompatActivity
{

    private TabLayout tabLayout;
    private ViewPager viewPager;

    final ViewPagerAdapter adapter = new ViewPagerAdapter(getSupportFragmentManager());

    View icTabCircleOn, icTabCircleOff, icTabOnionOn, icTabOnionOff, icTabSpotlightOn, icTabSpotlightOff;

    ImageView ivUserProfilePic;

    TextView tvFragmentTitle;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        getWindow().setLayout(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT);

        icTabCircleOn = LayoutInflater.from(this).inflate(R.layout.ic_tab_circle_on, null);
        icTabCircleOff = LayoutInflater.from(this).inflate(R.layout.ic_tab_circle_off, null);
        icTabOnionOn = LayoutInflater.from(this).inflate(R.layout.ic_tab_onion_on, null);
        icTabOnionOff = LayoutInflater.from(this).inflate(R.layout.ic_tab_onion_off, null);
        icTabSpotlightOn = LayoutInflater.from(this).inflate(R.layout.ic_tab_spotlight_on, null);
        icTabSpotlightOff = LayoutInflater.from(this).inflate(R.layout.ic_tab_spotlight_off, null);

        tvFragmentTitle = (TextView) findViewById(R.id.tv_fragmentTitle);
        tvFragmentTitle.setTypeface(Typeface.createFromAsset(getAssets(), "montserrat_regular.ttf"));

        ivUserProfilePic = (ImageView) findViewById(R.id.iv_userProfilePic);

        ivUserProfilePic.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View view)
            {
                startActivity(new Intent(MainActivity.this, ProfileActivity.class));
            }
        });

        findViewById(R.id.iv_search).setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View view)
            {
                startActivity(new Intent(MainActivity.this, SearchActivity.class));
            }
        });

        findViewById(R.id.iv_notification).setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View view)
            {
                startActivity(new Intent(MainActivity.this, NotificationsActivity.class));
            }
        });

        Picasso.with(this).load("https://lh3.googleusercontent.com/-8twv_aWLqtY/AAAAAAAAAAI/AAAAAAAAAQ8/K1r--rxdH3w/s100-p-rw-no/photo.jpg")
                .transform(new CircleTransform()).into(ivUserProfilePic);

        viewPager = (ViewPager) findViewById(R.id.viewPager);
        viewPager.setOffscreenPageLimit(2);
        setupViewPager(viewPager);

        tabLayout = (TabLayout) findViewById(R.id.tabs);
        tabLayout.setupWithViewPager(viewPager);
        setupTabIcons();
    }

    private void setupViewPager(ViewPager viewPager)
    {

        adapter.addFragment(CircleFragment.getInstance(this), "CIRCLE");
        adapter.addFragment(SpotlightFragment.getInstance(this), "SPOTLIGHT");
        adapter.addFragment(new OponionFragment(), "OPONION");

        viewPager.setAdapter(adapter);

        viewPager.addOnPageChangeListener(new ViewPager.OnPageChangeListener()
        {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels)
            {

            }

            @Override
            public void onPageSelected(int position)
            {
                if (position == 0)
                {
                    tvFragmentTitle.setText("circle");

                    tabLayout.getTabAt(0).setCustomView(null);
                    tabLayout.getTabAt(1).setCustomView(null);
                    tabLayout.getTabAt(2).setCustomView(null);

                    tabLayout.getTabAt(0).setCustomView(icTabCircleOn);
                    tabLayout.getTabAt(1).setCustomView(icTabSpotlightOff);
                    tabLayout.getTabAt(2).setCustomView(icTabOnionOff);
                } else if (position == 1)
                {
                    tvFragmentTitle.setText("shout");

                    tabLayout.getTabAt(0).setCustomView(null);
                    tabLayout.getTabAt(1).setCustomView(null);
                    tabLayout.getTabAt(2).setCustomView(null);

                    tabLayout.getTabAt(0).setCustomView(icTabCircleOff);
                    tabLayout.getTabAt(1).setCustomView(icTabSpotlightOn);
                    tabLayout.getTabAt(2).setCustomView(icTabOnionOff);
                } else if (position == 2)
                {
                    tvFragmentTitle.setText("oponion");

                    tabLayout.getTabAt(0).setCustomView(null);
                    tabLayout.getTabAt(1).setCustomView(null);
                    tabLayout.getTabAt(2).setCustomView(null);

                    tabLayout.getTabAt(0).setCustomView(icTabCircleOff);
                    tabLayout.getTabAt(1).setCustomView(icTabSpotlightOff);
                    tabLayout.getTabAt(2).setCustomView(icTabOnionOn);
                }

            }

            @Override
            public void onPageScrollStateChanged(int state)
            {

            }
        });
    }

    private void setupTabIcons()
    {

        tabLayout.getTabAt(0).setCustomView(icTabCircleOff);
        tabLayout.getTabAt(1).setCustomView(icTabSpotlightOff);
        tabLayout.getTabAt(2).setCustomView(icTabOnionOff);

        viewPager.setCurrentItem(1);

    }

}
