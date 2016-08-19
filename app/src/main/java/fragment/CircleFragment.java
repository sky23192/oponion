package fragment;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.StaggeredGridLayoutManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.app.oponion.R;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

import adapter.CircleFeedAdapter;
import adapter.SpotlightFeedsAdapter;
import model.CircleSimpleFeed;
import model.PollFeed;
import model.SinglePollOption;
import viewholder.PollFeedVH;

/**
 * Created by rutvik on 02-07-2016 at 06:10 PM.
 */

public class CircleFragment extends Fragment {

    public static final String TAG = "oponion " + CircleFragment.class.getSimpleName();

    RecyclerView rvCircleFeeds;

    Context context;

    CircleFeedAdapter adapter;

    private int count = -1;

    public CircleFragment() {

    }

    public static CircleFragment getInstance(Context context) {
        CircleFragment fragment = new CircleFragment();
        fragment.context = context;
        return fragment;
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.circle_fragment, container, false);

        rvCircleFeeds = (RecyclerView) view.findViewById(R.id.rv_circleFeeds);

        rvCircleFeeds.setLayoutManager(new LinearLayoutManager(context));

        rvCircleFeeds.setHasFixedSize(true);

        adapter = new CircleFeedAdapter(context);

        rvCircleFeeds.setAdapter(adapter);

        setupAdapterData();

        return view;
    }


    public void setupAdapterData() {

        List<String> tags = new ArrayList<>();
        tags.add("@rutvik ");
        tags.add("@umang ");
        tags.add("@abbas ");


        for (int i = 0; i < 10; i++) {

            CircleSimpleFeed csf1 = new CircleSimpleFeed("Akash Patel",
                    "https://lh3.googleusercontent.com/-4XHgL5Blj-I/AAAAAAAAAAI/AAAAAAAAC3Y/uZd5aDp2DdI/s120-c/photo.jpg",
                    "Finally they named it, Android Nougat!",
                    177);

            csf1.setTags(tags);

            csf1.setContentImageUrl("http://www.digitalintervention.com/wp-content/uploads/2016/07/Android-Nougat-banner.jpg");

            adapter.addSimpleFeed(++count, csf1);


            CircleSimpleFeed csf2 = new CircleSimpleFeed("Rutvik Mehta",
                    "https://lh3.googleusercontent.com/-8twv_aWLqtY/AAAAAAAAAAI/AAAAAAAAAQ8/K1r--rxdH3w/s120-c/photo.jpg",
                    "I think the internet should be free to the world",
                    3324);

            csf2.setTags(tags);

            csf2.setContentImageUrl("http://s1.thingpic.com/images/Vg/vPTFgMp2SJ8QQCMiNbdGLBGg.jpeg");

            adapter.addSimpleFeed(++count, csf2);

            CircleSimpleFeed csf3 = new CircleSimpleFeed("Abbas kharodawala",
                    "https://lh3.googleusercontent.com/-FffqJSvWgoM/AAAAAAAAAAI/AAAAAAAAAFc/O-PfLgisbgQ/s120-c/photo.jpg",
                    "Aston martin vs Lambo, OFC lambo!!",
                    435);

            csf3.setContentImageUrl("http://www.motorward.com/wp-content/images/2014/03/Lamborghini-Huracan-Geneva-0.jpg");

            csf3.setTags(tags);

            adapter.addSimpleFeed(++count, csf3);


            final List<SinglePollOption> pollOptionList = new LinkedList<>();

            pollOptionList.add(new SinglePollOption("1", "moto g4", "80"));
            pollOptionList.add(new SinglePollOption("2", "iphone", "10"));
            pollOptionList.add(new SinglePollOption("3", "oppo", "10"));

            PollFeed pf1 = new PollFeed("https://lh3.googleusercontent.com/-FffqJSvWgoM/AAAAAAAAAAI/AAAAAAAAAFc/O-PfLgisbgQ/s120-c/photo.jpg",
                    "Rutvik Mehta",
                    "which is the best phone?",
                    pollOptionList,
                    122);
            pf1.setTags(tags);

            adapter.addPollFeed(++count, pf1);

        }

        adapter.notifyDataSetChanged();
    }

}
