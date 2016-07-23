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

import adapter.SpotlightFeedsAdapter;
import model.SpotlightFeed;

/**
 * Created by rutvik on 02-07-2016 at 06:13 PM.
 */

public class SpotlightFragment extends Fragment
{

    RecyclerView rvSpotlightFeeds;

    Context context;

    SpotlightFeedsAdapter adapter;

    public SpotlightFragment(){

    }

    public static SpotlightFragment getInstance(Context context){
        SpotlightFragment fragment=new SpotlightFragment();
        fragment.context=context;
        return fragment;
    }


    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState)
    {
        View view=inflater.inflate(R.layout.spotlight_fragment,container,false);

        rvSpotlightFeeds=(RecyclerView) view.findViewById(R.id.rv_spotlightFeeds);

        rvSpotlightFeeds.setLayoutManager(new LinearLayoutManager(context));

        rvSpotlightFeeds.setHasFixedSize(true);

        adapter=new SpotlightFeedsAdapter(context);

        rvSpotlightFeeds.setAdapter(adapter);

        for(int i=0;i<=10;i++)
        {

            adapter.addSpotlightFeed(new SpotlightFeed("Indian PM Narendra Modi be like",
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
        }


        adapter.notifyDataSetChanged();

        return view;
    }

}
