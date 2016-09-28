package firebasemodels;

import android.support.annotation.NonNull;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.Calendar;
import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

import model.CircleSimpleFeed;

/**
 * Created by mw on 14-09-2016.
 */
public class Feed {

    public static void postFeed(String userId, String location, String coverImageUrl, String title, String contentBody, final PostFeedListener listener) {

        final DatabaseReference dbRef = FirebaseDatabase.getInstance().getReference();
        dbRef.getRoot();
        final String key = dbRef.child("feeds").push().getKey();

        final Map<String, String> singleFeed = new HashMap<>();
        singleFeed.put("user-id", userId);
        singleFeed.put("location", location);
        singleFeed.put("cover-image-url", coverImageUrl);
        singleFeed.put("title", title);
        singleFeed.put("body", contentBody);
        singleFeed.put("timestamp", Calendar.getInstance().getTimeInMillis() + "");

        final Map<String, Object> feedPost = new HashMap<>();
        feedPost.put("/feeds/" + key + "/", singleFeed);
        feedPost.put("/user-feeds/" + userId + "/" + key + "/", key);

        dbRef.updateChildren(feedPost).addOnCompleteListener(new OnCompleteListener<Void>() {
            @Override
            public void onComplete(@NonNull Task<Void> task) {
                if (task.isSuccessful()) {
                    listener.onPostSuccess();
                } else {
                    listener.onPostFailed();
                }
            }
        });

    }

    public static void getFeeds(final FeedListener listener) {
        final DatabaseReference dbRef = FirebaseDatabase.getInstance().getReference();
        dbRef.getRoot();

        dbRef.child("feeds").addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {

                listener.onGetFeed((Map<String, Object>) dataSnapshot.getValue());
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });

    }

    public interface FeedListener {
        void onGetFeed(Map<String, Object> feedMap);
    }

    public interface PostFeedListener {
        void onPostSuccess();

        void onPostFailed();
    }

}
