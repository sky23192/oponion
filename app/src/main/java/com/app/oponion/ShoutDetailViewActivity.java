package com.app.oponion;

import android.content.Intent;
import android.graphics.Typeface;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.squareup.picasso.Picasso;

import extra.CircleTransform;

public class ShoutDetailViewActivity extends AppCompatActivity
{

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_shout_detail_view);

        setSupportActionBar((Toolbar) findViewById(R.id.toolbar));

        Toast.makeText(this, getIntent().getStringExtra("feed_title"), Toast.LENGTH_SHORT).show();

        if (getSupportActionBar() != null)
        {
            getSupportActionBar().setTitle("");
            getSupportActionBar().setHomeAsUpIndicator(R.drawable.ic_arrow_back_purple_24dp);
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
            getSupportActionBar().setDisplayShowHomeEnabled(true);
            getSupportActionBar().setDisplayShowTitleEnabled(false);
        }

        ((TextView) findViewById(R.id.tv_feedTitle)).setText("Google Pixel and Pixel XL");

        Picasso.with(this)
                .load(R.drawable.sample_user)
                .transform(new CircleTransform())
                .into((ImageView) findViewById(R.id.iv_feedDetailProfilePic));

        Picasso.with(this)
                .load("http://www.mobilekart.co.uk/uploads/product/Google-Pixel-Black-Deals1.jpg")
                .into((ImageView) findViewById(R.id.iv_feedCoverPic));

        ((TextView) findViewById(R.id.tv_feedContent))
                .setText("It seems as if it is raining smartphones from the heaven, if Samsung, Apple. HTC, HuwaeI were not enough that Google launched Pixel and what a launch we must say. Google Pixel 32 GB black is a bang on smartphone that features an awesome design, amazing camera and Google Assistant and a host of new features. Here is a rundown of all the specs and the features that make the Google Pixel 32 GB black a powerful smartphone of the year 2016. We will also look at the best deals on Google Pixel 32 GB black by leading UK mobile operators.");
        ((TextView) findViewById(R.id.tv_feedContent))
                .setTypeface(Typeface.createFromAsset(getAssets(), "montserrat_regular.ttf"));

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu)
    {
        getMenuInflater().inflate(R.menu.shout_detail_activity_menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item)
    {
        if (item.getItemId() == android.R.id.home)
        {
            finish();
        }
        return super.onOptionsItemSelected(item);
    }
}
