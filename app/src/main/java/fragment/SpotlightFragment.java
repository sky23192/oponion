package fragment;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.StaggeredGridLayoutManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.app.oponion.R;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import adapter.SpotlightFeedsAdapter;
import firebasemodels.Feed;
import model.CircleSimpleFeed;
import model.SpotlightFeed;

/**
 * Created by rutvik on 02-07-2016 at 06:13 PM.
 */

public class SpotlightFragment extends Fragment
{

    RecyclerView rvSpotlightFeeds;

    Context context;

    SpotlightFeedsAdapter adapter;

    public SpotlightFragment()
    {

    }

    public static SpotlightFragment getInstance(Context context)
    {
        SpotlightFragment fragment = new SpotlightFragment();
        fragment.context = context;
        return fragment;
    }


    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState)
    {
        View view = inflater.inflate(R.layout.spotlight_fragment, container, false);

        rvSpotlightFeeds = (RecyclerView) view.findViewById(R.id.rv_spotlightFeeds);

        final StaggeredGridLayoutManager sgm = new StaggeredGridLayoutManager(2, StaggeredGridLayoutManager.VERTICAL);

        sgm.setGapStrategy(StaggeredGridLayoutManager.GAP_HANDLING_MOVE_ITEMS_BETWEEN_SPANS);

        rvSpotlightFeeds.setLayoutManager(sgm);

        rvSpotlightFeeds.setNestedScrollingEnabled(false);

        rvSpotlightFeeds.setHasFixedSize(true);
        /*rvSpotlightFeeds.setItemViewCacheSize(20);
        rvSpotlightFeeds.setDrawingCacheEnabled(true);
        rvSpotlightFeeds.setDrawingCacheQuality(View.DRAWING_CACHE_QUALITY_HIGH);*/

        rvSpotlightFeeds.addOnScrollListener(new RecyclerView.OnScrollListener()
        {
            @Override
            public void onScrollStateChanged(RecyclerView recyclerView, int newState)
            {
                super.onScrollStateChanged(recyclerView, newState);
                final Picasso picasso = Picasso.with(context);
                if (newState == RecyclerView.SCROLL_STATE_IDLE)
                {
                    picasso.resumeTag("resume_tag");
                } else
                {
                    picasso.pauseTag("resume_tag");
                }
            }
        });

        adapter = new SpotlightFeedsAdapter(context);

        rvSpotlightFeeds.setAdapter(adapter);

        //tryLoadingFeedsFromFirebase();

        return view;
    }

    private void tryLoadingFeedsFromFirebase()
    {

        try
        {
            Feed.getFeeds(new Feed.FeedListener()
            {
                @Override
                public void onGetFeed(Map<String, Object> feedMap)
                {

                    adapter.spotlightFeedList.clear();

                    adapter.notifyDataSetChanged();

                    List<String> tags = new ArrayList<>();
                    tags.add("@rutvik ");
                    tags.add("@umang ");
                    tags.add("@abbas ");

                    if (feedMap != null)
                    {

                        Iterator<Object> iterator = feedMap.values().iterator();

                        while (iterator.hasNext())
                        {
                            Map<String, String> singleFeed = (Map<String, String>) iterator.next();
                            adapter.addSpotlightFeed(new SpotlightFeed(singleFeed.get("title"),
                                    "http://icons.iconarchive.com/icons/custom-icon-design/round-world-flags/256/India-icon.png",
                                    singleFeed.get("cover-image-url"),
                                    835));
                        }
                    }

                }
            });
        } catch (Exception e)
        {
            e.printStackTrace();
        }

        for (int i = 11; i <= 10; i++)
        {

            adapter.addSpotlightFeed(new SpotlightFeed("Indian PM Narendra Modi",
                    "http://icons.iconarchive.com/icons/custom-icon-design/round-world-flags/256/India-icon.png",
                    "http://img-9gag-fun.9cache.com/photo/aj68AER_700b.jpg",
                    835));

            adapter.addSpotlightFeed(new SpotlightFeed("FOX news is just hilarious!",
                    "http://www.greatexpectationstheplay.com/images/flags/USA.png",
                    "http://i2.cdn.turner.com/money/dam/assets/150116013259-megyn-kelly-780x439.jpg",
                    1337));

            adapter.addSpotlightFeed(new SpotlightFeed("Tesla Model 3",
                    "http://img.freeflagicons.com/thumb/round_icon/canada/canada_640.png",
                    "http://st.motortrend.com/uploads/sites/5/2016/05/2017-Tesla-Model-3-front-three-quarter-03.jpg",
                    9200));

            adapter.addSpotlightFeed(new SpotlightFeed("Pokemon GO - Big news update",
                    "https://images-eu.ssl-images-amazon.com/images/I/41GTY%2BVLyOL._AC_UL320_SR306,320_.jpg",
                    "https://i.ytimg.com/vi/QIypLsv-3FQ/maxresdefault.jpg",
                    1200));

            adapter.addSpotlightFeed(new SpotlightFeed("Big rockets!",
                    "http://img.freeflagicons.com/thumb/round_icon/canada/canada_640.png",
                    "http://starshipnivan.com/blog/wp-content/uploads/2009/09/Apollo-1.jpg",
                    1212));

            adapter.addSpotlightFeed(new SpotlightFeed("Snow in Australia",
                    "http://img.freeflagicons.com/thumb/round_icon/france/france_640.png",
                    "http://img-9gag-fun.9cache.com/photo/ajDEbGq_460s.jpg",
                    300));


        }


        adapter.notifyDataSetChanged();


    }

}
