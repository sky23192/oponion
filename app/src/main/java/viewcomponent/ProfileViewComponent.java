package viewcomponent;

import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.app.oponion.R;
import com.squareup.picasso.Picasso;

import extra.CircleTransform;
import viewmodel.ProfileView;

/**
 * Created by rutvik on 10/10/2016 at 1:45 PM.
 */

public class ProfileViewComponent extends LinearLayout
{

    ImageView ivProfileUserPic, ivProfileCover;

    TextView tvProfileUserName, tvProfileFollowers, tvProfileFollowing;

    ProfileView profileView;

    Context context;

    public ProfileViewComponent(Context context)
    {
        super(context);

        this.context = context;

        setLayoutParams(new LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT,
                ViewGroup.LayoutParams.WRAP_CONTENT));

        LayoutInflater.from(context).inflate(R.layout.profile_view_component, this, true);

        ivProfileCover = (ImageView) findViewById(R.id.iv_profileCover);
        ivProfileUserPic = (ImageView) findViewById(R.id.iv_profileUserPic);

        tvProfileUserName = (TextView) findViewById(R.id.tv_profileUserName);
        tvProfileFollowers = (TextView) findViewById(R.id.tv_profileFollowers);
        tvProfileFollowing = (TextView) findViewById(R.id.tv_profileFollowing);

    }

    public void setProfileViewModel(ProfileView profileView)
    {
        this.profileView = profileView;

        Picasso.with(context).load(profileView.getCoverPicUrl()).into(ivProfileCover);
        Picasso.with(context).load(profileView.getProfilePicUrl()).transform(new CircleTransform())
                .into(ivProfileUserPic);

        tvProfileUserName.setText(profileView.getName());
        tvProfileFollowing.setText(profileView.getFollowing());
        tvProfileFollowers.setText(profileView.getFollowers());

        findViewById(R.id.iv_back).setOnClickListener(new OnClickListener()
        {
            @Override
            public void onClick(View view)
            {

            }
        });

    }


}
