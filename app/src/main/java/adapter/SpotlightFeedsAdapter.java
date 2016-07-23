package adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.app.oponion.R;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

import model.SpotlightFeed;
import viewholder.SpotlightFeedVH;

/**
 * Created by rutvik on 13-07-2016 at 06:19 PM.
 */

public class SpotlightFeedsAdapter extends RecyclerView.Adapter
{

    Context context;

    List<SpotlightFeed> spotlightFeedList=new LinkedList<>();

    public SpotlightFeedsAdapter(Context context){
        this.context=context;
    }

    public void addSpotlightFeed(SpotlightFeed feed){
        spotlightFeedList.add(feed);
        notifyItemInserted(spotlightFeedList.indexOf(feed));
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType)
    {
        return SpotlightFeedVH.create(context,parent);
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position)
    {
        SpotlightFeedVH.bind((SpotlightFeedVH)holder,spotlightFeedList.get(position));
    }

    @Override
    public int getItemCount()
    {
        return spotlightFeedList.size();
    }
}
