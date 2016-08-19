package viewholder;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.app.oponion.R;
import com.squareup.picasso.Picasso;

import component.FeedItem;
import extra.CircleTransform;
import model.CircleSimpleFeed;

/**
 * Created by mw on 27-07-2016.
 */
public class CircleSimpleFeedVH extends RecyclerView.ViewHolder implements FeedItem {


    CircleSimpleFeed model;

    TextView tvUserName, tvTags, tvContentBody, tvTotalVotes;

    ImageView ivProfilePic, ivContentImage;

    Context context;

    public CircleSimpleFeedVH(View itemView) {

        super(itemView);

        tvUserName = (TextView) itemView.findViewById(R.id.tv_userName);
        tvTags = (TextView) itemView.findViewById(R.id.tv_tags);
        tvTotalVotes = (TextView) itemView.findViewById(R.id.tv_totalVotes);
        ivContentImage = (ImageView) itemView.findViewById(R.id.iv_contentImage);
        ivProfilePic = (ImageView) itemView.findViewById(R.id.iv_profilePic);
        tvTags = (TextView) itemView.findViewById(R.id.tv_tags);
        tvContentBody = (TextView) itemView.findViewById(R.id.tv_contentBody);

    }

    public static CircleSimpleFeedVH create(Context context, ViewGroup parent) {

        CircleSimpleFeedVH holder = new CircleSimpleFeedVH(LayoutInflater.from(context).inflate(R.layout.single_circle_row_item, parent, false));
        holder.context = context;

        return holder;
    }


    public static void bind(CircleSimpleFeedVH holder, CircleSimpleFeed model) {

        holder.model = model;

        holder.tvContentBody.setText(model.getContentBody());
        holder.tvTotalVotes.setText(String.valueOf(model.getTotalVotes()));
        if (model.getTags().size() > 0) {
            holder.tvTags.setText(model.getTags().toString());
        }
        holder.tvUserName.setText(model.getUserName());
        Picasso.with(holder.context).load(model.getProfilePicUrl()).transform(new CircleTransform()).into(holder.ivProfilePic);
        if (model.getContentImageUrl() != null) {

            Picasso.with(holder.context).load(model.getContentImageUrl()).into(holder.ivContentImage);
        }

    }

}
