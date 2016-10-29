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

        addTestOponionFeeds();

        return v;
    }


    public void addTestOponionFeeds()
    {

        for (int i = 0; i < 5; i++)
        {
            OponionFeed of1
                    = new OponionFeed("http://xojsmn.com/xojsmnwp/wp-content/uploads/2015/01/apple-logo-grey-300x300.jpg",
                    "Apple", "Apple Launches New MacBook Pro", "5h ago", "462");

            adapter.addNewOponionFeed(of1);

            OponionFeed of3
                    = new OponionFeed("http://icons.iconarchive.com/icons/alecive/flatwoken/512/Apps-Samsung-icon.png",
                    "Samsung", "Samsung has something exciting to showoff", "1d ago", "231");
            of3.setFeedContentImage("http://careace.net/wp-content/uploads/2010/07/android-samsung-icon.jpg");

            adapter.addNewOponionFeed(of3);

            OponionFeed of4
                    = new OponionFeed("http://www.oracle.com/us/oracle-social-share-fb-480-2516041.jpg",
                    "Oracle", "Oracle,s new database system cluster", "5h ago", "462");

            adapter.addNewOponionFeed(of4);
        }

        adapter.notifyDataSetChanged();

    }

}
