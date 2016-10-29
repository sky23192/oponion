package model;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * Created by rutvik on 13-07-2016 at 06:22 PM.
 */

public class SpotlightFeed implements Parcelable
{

    public String feedImageUrl="",locationImage="",feedTitle="";

    public int totalVotes=0;

    public SpotlightFeed(){

    }

    public SpotlightFeed(String feedTitle,String locationImage, String feedImageUrl, int totalVotes)
    {
        this.feedTitle=feedTitle;
        this.locationImage=locationImage;
        this.feedImageUrl=feedImageUrl;
        this.totalVotes=totalVotes;
    }

    @Override
    public int describeContents()
    {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags)
    {
        dest.writeString(this.feedImageUrl);
        dest.writeString(this.locationImage);
        dest.writeString(this.feedTitle);
        dest.writeInt(this.totalVotes);
    }

    protected SpotlightFeed(Parcel in)
    {
        this.feedImageUrl = in.readString();
        this.locationImage = in.readString();
        this.feedTitle = in.readString();
        this.totalVotes = in.readInt();
    }

    public static final Parcelable.Creator<SpotlightFeed> CREATOR = new Parcelable.Creator<SpotlightFeed>()
    {
        @Override
        public SpotlightFeed createFromParcel(Parcel source)
        {
            return new SpotlightFeed(source);
        }

        @Override
        public SpotlightFeed[] newArray(int size)
        {
            return new SpotlightFeed[size];
        }
    };
}
