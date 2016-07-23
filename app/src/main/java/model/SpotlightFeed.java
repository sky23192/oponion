package model;

/**
 * Created by rutvik on 13-07-2016 at 06:22 PM.
 */

public class SpotlightFeed
{

    public String feedImageUrl,locationImage,feedTitle;

    public int totalVotes;

    public SpotlightFeed(String feedTitle,String locationImage, String feedImageUrl, int totalVotes)
    {
        this.feedTitle=feedTitle;
        this.locationImage=locationImage;
        this.feedImageUrl=feedImageUrl;
        this.totalVotes=totalVotes;
    }
}
