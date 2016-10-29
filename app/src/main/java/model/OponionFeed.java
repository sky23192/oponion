package model;

/**
 * Created by rutvik on 10/29/2016 at 8:09 PM.
 */

public class OponionFeed
{

    final String feedByImage, feedByName, feedTitle, feedExtra, feedTotalVotes;

    String feedContentImage;

    public OponionFeed(String feedByImage, String feedByName,
                       String feedTitle, String feedExtra,
                       String feedTotalVotes)
    {
        this.feedByImage = feedByImage;
        this.feedByName = feedByName;
        this.feedTitle = feedTitle;
        this.feedExtra = feedExtra;
        this.feedTotalVotes = feedTotalVotes;
    }


    public String getFeedByImage()
    {
        return feedByImage;
    }

    public String getFeedByName()
    {
        return feedByName;
    }

    public String getFeedTitle()
    {
        return feedTitle;
    }

    public String getFeedExtra()
    {
        return feedExtra;
    }

    public String getFeedTotalVotes()
    {
        return feedTotalVotes;
    }


    public String getFeedContentImage()
    {
        return feedContentImage;
    }

    public void setFeedContentImage(String feedContentImage)
    {
        this.feedContentImage = feedContentImage;
    }
}
