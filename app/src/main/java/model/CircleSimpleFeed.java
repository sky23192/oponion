package model;

import android.support.v7.widget.RecyclerView;
import android.view.View;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by mw on 27-07-2016.
 */
public class CircleSimpleFeed {

    String profilePicUrl,userName,contentImageUrl,contentBody;

    List<String> tags=new ArrayList<>();

    int totalVotes;

    public CircleSimpleFeed(String userName, String profilePictureUrl, String contentBody, int totalVotes){

        this.profilePicUrl=profilePictureUrl;
        this.userName=userName;
        this.contentBody=contentBody;
        this.totalVotes=totalVotes;

    }

    public void setTags(List<String> tags){
        this.tags=tags;
    }

    public void setContentImageUrl(String contentImageUrl){
        this.contentImageUrl=contentImageUrl;
    }

    public String getProfilePicUrl() {
        return profilePicUrl;
    }

    public String getUserName() {
        return userName;
    }

    public String getContentImageUrl() {
        return contentImageUrl;
    }

    public List<String> getTags() {
        return tags;
    }

    public String getContentBody() {
        return contentBody;
    }

    public int getTotalVotes() {
        return totalVotes;
    }


}
