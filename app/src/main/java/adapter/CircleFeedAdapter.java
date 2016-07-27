package adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.ViewGroup;

import java.util.ArrayList;
import java.util.List;

import model.CircleSimpleFeed;
import viewholder.CircleSimpleFeedVH;

/**
 * Created by mw on 27-07-2016.
 */
public class CircleFeedAdapter extends RecyclerView.Adapter {


    List<CircleSimpleFeed> circleSimpleFeedList=new ArrayList<>();

    Context context;

    public CircleFeedAdapter(Context context){
        this.context=context;
    }


    public void addCircleSimpleFeed(CircleSimpleFeed circleSimpleFeed){
        circleSimpleFeedList.add(circleSimpleFeed);
        notifyItemInserted(circleSimpleFeedList.indexOf(circleSimpleFeed));
    }


    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return CircleSimpleFeedVH.create(context,parent);
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        CircleSimpleFeedVH.bind((CircleSimpleFeedVH) holder,circleSimpleFeedList.get(position));
    }

    @Override
    public int getItemCount() {
        return circleSimpleFeedList.size();
    }
}
