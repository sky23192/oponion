package viewholder;

import android.content.Context;
import android.graphics.Color;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.app.oponion.R;

import component.FeedItem;
import model.PollFeed;
import progressviews.LineProgressBar;

/**
 * Created by mw on 09-08-2016.
 */
public class PollFeedVH extends RecyclerView.ViewHolder implements FeedItem {

    PollFeed model;

    LineProgressBar lineProgressBar;

    public PollFeedVH(View itemView) {
        super(itemView);
        lineProgressBar = (LineProgressBar) itemView.findViewById(R.id.pb_linear);
        lineProgressBar.setProgress(65);
/*        lineProgressBar.setWidth(200);
        lineProgressBar.setWidthProgressBackground(25);
        lineProgressBar.setWidthProgressBarLine(25);*/
        lineProgressBar.setBackgroundColor(Color.parseColor("#d4d1d1"));
        lineProgressBar.setProgressColor(Color.parseColor("#5949a3"));
        lineProgressBar.setRoundEdgeProgress(true);
    }

    public static PollFeedVH create(final Context context, ViewGroup parent) {
        View v = LayoutInflater.from(context).inflate(R.layout.single_poll_row_item, parent,false);
        PollFeedVH vh = new PollFeedVH(v);
        return vh;
    }

    public static void bind(final PollFeedVH vh, final PollFeed model) {
        vh.model = model;
    }


}
