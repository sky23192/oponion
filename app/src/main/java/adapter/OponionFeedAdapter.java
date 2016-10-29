package adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.ViewGroup;

import java.util.ArrayList;
import java.util.List;

import model.OponionFeed;
import viewholder.OponionFeedVH;

/**
 * Created by rutvik on 10/29/2016 at 8:54 PM.
 */

public class OponionFeedAdapter extends RecyclerView.Adapter
{

    final List<OponionFeed> oponionFeedList;

    final Context context;

    public OponionFeedAdapter(final Context context)
    {
        this.context = context;
        oponionFeedList = new ArrayList<>();
    }

    public void addNewOponionFeed(final OponionFeed oponionFeed)
    {
        oponionFeedList.add(oponionFeed);
        notifyItemInserted(oponionFeedList.indexOf(oponionFeed));
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType)
    {
        return OponionFeedVH.create(context, parent);
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position)
    {
        OponionFeedVH.bind((OponionFeedVH) holder, oponionFeedList.get(position));
    }

    @Override
    public int getItemCount()
    {
        return oponionFeedList.size();
    }
}
