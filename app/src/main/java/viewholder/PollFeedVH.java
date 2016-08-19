package viewholder;

import android.content.Context;
import android.graphics.Color;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.app.oponion.R;
import com.squareup.picasso.Picasso;

import java.util.List;

import component.FeedItem;
import component.ProgressBarComponent;
import extra.CircleTransform;
import model.PollFeed;
import model.SinglePollOption;
import progressviews.LineProgressBar;

/**
 * Created by mw on 09-08-2016.
 */
public class PollFeedVH extends RecyclerView.ViewHolder implements FeedItem {

    PollFeed model;

    LinearLayout llProgressContainer;

    ImageView ivProfilePic;

    TextView tvUserName, tvTags, tvContentBody, tvTotalVotes;

    Context context;

    public PollFeedVH(final Context context, View itemView) {
        super(itemView);
        llProgressContainer = (LinearLayout) itemView.findViewById(R.id.ll_progressContainer);
        ivProfilePic = (ImageView) itemView.findViewById(R.id.iv_profilePic);
        tvUserName = (TextView) itemView.findViewById(R.id.tv_userName);
        tvTags = (TextView) itemView.findViewById(R.id.tv_tags);
        tvContentBody = (TextView) itemView.findViewById(R.id.tv_contentBody);
        tvTotalVotes = (TextView) itemView.findViewById(R.id.tv_totalVotes);
        this.context = context;
    }

    public static PollFeedVH create(final Context context, ViewGroup parent) {
        View v = LayoutInflater.from(context).inflate(R.layout.single_poll_row_item, parent, false);
        PollFeedVH vh = new PollFeedVH(context, v);
        return vh;
    }

    public static void bind(final PollFeedVH holder, final PollFeed model) {
        holder.model = model;

        holder.tvContentBody.setText(model.getContentBody());
        holder.tvTotalVotes.setText(String.valueOf(model.getTotalVotes()));
        if (model.getTags().size() > 0) {
            holder.tvTags.setText(model.getTags().toString());
        }
        holder.tvUserName.setText(model.getUserName());
        Picasso.with(holder.context).load(model.getProfilePicUrl()).transform(new CircleTransform()).into(holder.ivProfilePic);
        if (model.getProfilePicUrl() != null) {
            Picasso.with(holder.context).load(model.getProfilePicUrl()).transform(new CircleTransform()).into(holder.ivProfilePic);
        }
        if (holder.llProgressContainer.getChildCount() == 0) {
            for (final SinglePollOption poll : model.getPollOptionList()) {
                holder.llProgressContainer.addView(new ProgressBarComponent(holder.context, poll.getLabel(), poll.getTotalPercentage(), new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Toast.makeText(holder.context, "pool model id: " + poll.getId(), Toast.LENGTH_SHORT).show();
                    }
                }));
            }
        }


    }


}
