package model;

import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

/**
 * Created by mw on 09-08-2016.
 */
public class PollFeed {

    List<SinglePollOption> pollOptionList = new LinkedList<>();

    String profilePicUrl;

    public String getProfilePicUrl() {
        return profilePicUrl;
    }

    public String getUserName() {
        return userName;
    }

    public String getContentBody() {
        return contentBody;
    }

    public int getTotalVotes() {
        return totalVotes;
    }

    public List<String> getTags() {
        return tags;
    }

    public List<SinglePollOption> getPollOptionList() {
        return pollOptionList;
    }

    String userName;
    String contentBody;

    List<String> tags;

    int totalVotes;

    public PollFeed(String profilePicUrl, String userName, String contentBody, List<SinglePollOption> pollOptionList, int totalVotes) {
        this.profilePicUrl = profilePicUrl;
        this.userName = userName;
        this.contentBody = contentBody;
        this.pollOptionList = pollOptionList;
        this.totalVotes = totalVotes;
        tags = new ArrayList<>();
    }

    public void setTags(List<String> tags) {
        this.tags = tags;
    }

}
