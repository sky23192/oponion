package model;

/**
 * Created by rutvik on 11/27/2016 at 9:51 AM.
 */

public class SingleCommunityCard
{

    String communityPostBg, communityPostLogo, communityPostContentText, communityPostRating,
            communityPostTitle;

    public SingleCommunityCard(String communityPostTitle, String communityPostContentText,
                               String communityPostLogo, String communityPostBg,
                               String communityPostRating)
    {

        this.communityPostBg = communityPostBg;
        this.communityPostLogo = communityPostLogo;
        this.communityPostContentText = communityPostContentText;
        this.communityPostRating = communityPostRating;
        this.communityPostTitle = communityPostTitle;

    }

    public String getCommunityPostBg()
    {
        return communityPostBg;
    }

    public String getCommunityPostLogo()
    {
        return communityPostLogo;
    }

    public String getCommunityPostContentText()
    {
        return communityPostContentText;
    }

    public String getCommunityPostRating()
    {
        return communityPostRating;
    }

    public String getCommunityPostTitle()
    {
        return communityPostTitle;
    }
}
