package adapter;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.ViewGroup;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import component.Component;
import model.CircleSimpleFeed;
import model.PollFeed;
import viewholder.CircleSimpleFeedVH;
import viewholder.PollFeedVH;

/**
 * Created by mw on 27-07-2016.
 */
public class CircleFeedAdapter extends RecyclerView.Adapter {

    //List<CircleSimpleFeed> circleSimpleFeedList = new ArrayList<>();

    Map<Integer, Component> componentMap = new HashMap<>();

    Context context;

    public CircleFeedAdapter(Context context) {
        this.context = context;
    }


/*    public void addCircleSimpleFeed(CircleSimpleFeed circleSimpleFeed) {
        circleSimpleFeedList.add(circleSimpleFeed);
        notifyItemInserted(circleSimpleFeedList.indexOf(circleSimpleFeed));
    }*/

    public void addSimpleFeed(int id, final CircleSimpleFeed circleSimpleFeed) {
        componentMap.put(id, new Component(id, Component.SIMPLE_FEED, circleSimpleFeed));
        notifyItemInserted(componentMap.size());
    }

    public void addPollFeed(int id, final PollFeed pollFeed) {
        componentMap.put(id, new Component(id, Component.POLL_FEED, pollFeed));
        notifyItemInserted(componentMap.size());
    }

    @Override
    public int getItemViewType(int position) {
        if(componentMap==null){
            Log.e("oponion","component map is null");
        }
        Log.i("oponion",componentMap.toString());
        return componentMap.get(position).getViewType();
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        switch (viewType) {

            case Component.SIMPLE_FEED:

                return CircleSimpleFeedVH.create(context, parent);

            case Component.POLL_FEED:

                return PollFeedVH.create(context, parent);

            default:

                return null;

        }
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {

        switch (getItemViewType(position)) {

            case Component.SIMPLE_FEED:
                CircleSimpleFeedVH.bind((CircleSimpleFeedVH) holder, (CircleSimpleFeed) componentMap.get(position).getObject());
                break;

            case Component.POLL_FEED:
                PollFeedVH.bind((PollFeedVH) holder, (PollFeed) componentMap.get(position).getObject());
                break;
        }

    }

    @Override
    public int getItemCount() {
        return componentMap.size();
    }
}
