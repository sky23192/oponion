package viewmodel;

import android.app.Activity;

import java.util.ArrayList;

/**
 * Created by rutvik on 10/10/2016 at 3:37 PM.
 */

public class ProfileView
{

    String profilePicUrl, coverPicUrl, name, followers, following;
    final ArrayList<String> tags;

    public Activity activity;

    public ProfileView(Activity activity, String profilePicUrl, String coverPicUrl, String name, String followers,
                       String following)
    {
        this.activity = activity;
        this.coverPicUrl = coverPicUrl;
        this.profilePicUrl = profilePicUrl;
        this.name = name;
        this.followers = followers;
        this.following = following;
        tags = new ArrayList<>();
    }

    public void addTags(String tag)
    {
        tags.add(tag);
    }

    public String getProfilePicUrl()
    {
        return profilePicUrl;
    }

    public String getCoverPicUrl()
    {
        return coverPicUrl;
    }

    public String getName()
    {
        return name;
    }

    public String getFollowers()
    {
        return followers;
    }

    public String getFollowing()
    {
        return following;
    }

    public ArrayList<String> getTags()
    {
        return tags;
    }
}
