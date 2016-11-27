package fragment;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.app.oponion.R;
import com.squareup.picasso.Picasso;

import adapter.OponionFeedAdapter;
import model.OponionFeed;

/**
 * Created by rutvik on 02-07-2016 at 06:12 PM.
 */

public class OponionFragment extends Fragment
{

    private Context context;

    private RecyclerView rvOponionFeeds;

    private OponionFeedAdapter adapter;

    public OponionFragment()
    {

    }

    public static OponionFragment getInstance(Context context)
    {
        OponionFragment fragment = new OponionFragment();
        fragment.context = context;
        return fragment;
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState)
    {
        View v = inflater.inflate(R.layout.oponion_fragment, container, false);

        rvOponionFeeds = (RecyclerView) v.findViewById(R.id.rv_oponionFeeds);

        rvOponionFeeds.setLayoutManager(new LinearLayoutManager(context));

        rvOponionFeeds.setHasFixedSize(true);

        rvOponionFeeds.setNestedScrollingEnabled(false);

        adapter = new OponionFeedAdapter(context);

        rvOponionFeeds.setAdapter(adapter);

        rvOponionFeeds.addOnScrollListener(new RecyclerView.OnScrollListener()
        {
            @Override
            public void onScrollStateChanged(RecyclerView recyclerView, int newState)
            {
                if (newState == RecyclerView.SCROLL_STATE_IDLE)
                {
                    Picasso.with(getActivity()).resumeTag("OPONION");
                } else
                {
                    Picasso.with(getActivity()).pauseTag("OPONION");
                }
            }
        });

        addTestOponionFeeds();

        return v;
    }


    public void addTestOponionFeeds()
    {

        for (int i = 0; i < 5; i++)
        {
            OponionFeed of1
                    = new OponionFeed("http://www.elderlytech.com/wp-content/uploads/2015/09/Apple-logo-black.jpg",
                    "Apple Inc.", "Apple Launches New MacBook Pro", "5h ago", "462");
            of1.setFeedContentImage("http://tech.firstpost.com/wp-content/uploads/2014/01/MacBook-Air.jpg");

            adapter.addNewOponionFeed(of1);

            OponionFeed of3
                    = new OponionFeed("http://www.samsung.com/common/img/logo-square-letter.png",
                    "Samsung", "Samsung has something exciting to showoff", "1d ago", "231");
            of3.setFeedContentImage("http://androidspin.com/wp-content/uploads/2013/02/Samsung-Galaxy-S-III-Unpacked.png");

            adapter.addNewOponionFeed(of3);

            OponionFeed of4
                    = new OponionFeed("http://www.oracle.com/us/oracle-social-share-fb-480-2516041.jpg",
                    "Oracle", "Oracle,s new database system cluster", "12h ago", "251");
            of4.setFeedContentImage("http://electricthumb.in/wp-content/uploads/2016/04/oracle1.jpg");

            adapter.addNewOponionFeed(of4);
        }

        adapter.notifyDataSetChanged();

    }

}
