package com.app.oponion;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import adapter.ViewAdapter;
import viewmodel.ProfileView;

public class ProfileActivity extends AppCompatActivity
{

    RecyclerView rvProfileView;

    ViewAdapter adapter;

    ProfileView profileView;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile);

        rvProfileView = (RecyclerView) findViewById(R.id.rv_profileView);
        rvProfileView.setHasFixedSize(true);
        rvProfileView.setLayoutManager(new LinearLayoutManager(this));

        adapter = new ViewAdapter(this);

        rvProfileView.setAdapter(adapter);

        profileView =
                new ProfileView("https://lh3.googleusercontent.com/-8twv_aWLqtY/AAAAAAAAAAI/AAAAAAAAAQ8/K1r--rxdH3w/s200-p-rw-no/photo.jpg",
                        "https://profalbrecht.files.wordpress.com/2009/07/road01.jpg",
                        "Rutvik Mehta", "55", "104");

        profileView.addTags("DEVELOPER");
        profileView.addTags("PHOTOGRAPHER");

        adapter.addProfileView(profileView);

        adapter.notifyDataSetChanged();

    }
}
