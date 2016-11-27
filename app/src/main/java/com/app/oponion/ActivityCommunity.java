package com.app.oponion;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.widget.ScrollView;

import com.squareup.picasso.Picasso;

import adapter.CommunityFeedAdapter;
import model.SingleCommunityCard;

import static android.support.v7.widget.RecyclerView.SCROLL_STATE_IDLE;

public class ActivityCommunity extends AppCompatActivity
{

    RecyclerView rvYoutubers, rvPoliticians;

    CommunityFeedAdapter youtubeAdapter, politiciansAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_community);

        rvYoutubers = (RecyclerView) findViewById(R.id.rv_youtubers);
        rvPoliticians = (RecyclerView) findViewById(R.id.rv_politicians);


        rvYoutubers.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false));
        rvYoutubers.setHasFixedSize(true);

        rvYoutubers.addOnScrollListener(new RecyclerView.OnScrollListener()
        {
            @Override
            public void onScrollStateChanged(RecyclerView recyclerView, int newState)
            {
                final Picasso picasso = Picasso.with(ActivityCommunity.this);
                if (newState == SCROLL_STATE_IDLE)
                {
                    picasso.resumeTag("COMMUNITY");
                } else
                {
                    picasso.pauseTag("COMMUNITY");
                }
            }
        });

        youtubeAdapter = new CommunityFeedAdapter(this);
        rvYoutubers.setAdapter(youtubeAdapter);
        addYoutubeCommunity();

        rvPoliticians.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false));
        rvPoliticians.setHasFixedSize(true);

        rvPoliticians.addOnScrollListener(new RecyclerView.OnScrollListener()
        {
            @Override
            public void onScrollStateChanged(RecyclerView recyclerView, int newState)
            {
                final Picasso picasso = Picasso.with(ActivityCommunity.this);
                if (newState == SCROLL_STATE_IDLE)
                {
                    picasso.resumeTag("COMMUNITY");
                } else
                {
                    picasso.pauseTag("COMMUNITY");
                }
            }
        });

        politiciansAdapter = new CommunityFeedAdapter(this);
        rvPoliticians.setAdapter(politiciansAdapter);
        addPoliticiansCommunity();

    }

    private void addPoliticiansCommunity()
    {

        for (int i = 0; i < 5; i++)
        {
            SingleCommunityCard modi =
                    new SingleCommunityCard("Narendra Modi", "I am just a guy from bla bla who loves to play" +
                            " video games and i run my channel on youtube. spread love not war. catch all" +
                            " my activity and rate my performance here. PewDiePie <3",
                            "http://downloads.andyroid.net/website/v2//wp-content/uploads/2015/07/NarendraModi-icon.png",
                            "http://cdn.narendramodi.in/cmsuploads/0.34592100-1450848819-indian-pm-narendra-modi-russia-remains-our-principal-partner.jpg",
                            "3.9");
            politiciansAdapter.addSingleCommunityCard(modi);

            SingleCommunityCard trump =
                    new SingleCommunityCard("Donald Trump", "I am just a guy from bla bla who loves to play" +
                            " video games and i run my channel on youtube. spread love not war. catch all" +
                            " my activity and rate my performance here. PewDiePie <3",
                            "https://upload.wikimedia.org/wikipedia/commons/thumb/d/d2/Donald_Trump_August_19,_2015_(cropped).jpg/220px-Donald_Trump_August_19,_2015_(cropped).jpg",
                            "https://cdn.theatlantic.com/assets/media/img/mt/2016/11/RTX2SPX6/hero_wide_640.jpg?1478717366",
                            "3.9");
            politiciansAdapter.addSingleCommunityCard(trump);
        }

        politiciansAdapter.notifyDataSetChanged();

    }

    private void addYoutubeCommunity()
    {

        for (int i = 0; i < 5; i++)
        {
            SingleCommunityCard pewdiepie =
                    new SingleCommunityCard("Pewdiepie", "I am just a guy from bla bla who loves to play" +
                            " video games and i run my channel on youtube. spread love not war. catch all" +
                            " my activity and rate my performance here. PewDiePie <3",
                            "http://vignette1.wikia.nocookie.net/spudroosi/images/9/9b/PewDiePie.png",
                            "https://peopledotcom.files.wordpress.com/2016/08/pewdiepie-800.jpg",
                            "3.9");
            youtubeAdapter.addSingleCommunityCard(pewdiepie);

            SingleCommunityCard unboxing =
                    new SingleCommunityCard("Unboxing Therapy", "I am just a guy from bla bla who loves to play" +
                            " video games and i run my channel on youtube. spread love not war. catch all" +
                            " my activity and rate my performance here. PewDiePie <3",
                            "http://www.boldlist.net/img/unbox-therapy-logo.jpg",
                            "https://cdn.shopify.com/s/files/1/0677/8927/files/Mighty_Mug_Unbox_Therapy_World_Greatest_Coffee_Travel_Mug_1024x1024.png",
                            "3.9");
            youtubeAdapter.addSingleCommunityCard(unboxing);

        }

        youtubeAdapter.notifyDataSetChanged();

    }
}
