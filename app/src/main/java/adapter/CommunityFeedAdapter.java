package adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.ViewGroup;

import java.util.LinkedList;
import java.util.List;

import model.SingleCommunityCard;
import viewholder.CommunityPostVH;

/**
 * Created by rutvik on 11/27/2016 at 1:17 PM.
 */

public class CommunityFeedAdapter extends RecyclerView.Adapter
{

    final List<SingleCommunityCard> singleCommunityCardList;

    final Context context;

    public CommunityFeedAdapter(final Context context)
    {
        this.context = context;
        singleCommunityCardList = new LinkedList<>();
    }


    public void addSingleCommunityCard(final SingleCommunityCard singleCommunityCard)
    {
        singleCommunityCardList.add(singleCommunityCard);
        notifyItemInserted(singleCommunityCardList.size());
    }


    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType)
    {
        return CommunityPostVH.create(context, parent);
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position)
    {
        CommunityPostVH.bind((CommunityPostVH) holder,
                singleCommunityCardList.get(position));
    }

    @Override
    public int getItemCount()
    {
        return singleCommunityCardList.size();
    }
}
