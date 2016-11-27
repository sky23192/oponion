package viewholder;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.app.oponion.R;
import com.github.siyamed.shapeimageview.mask.PorterShapeImageView;
import com.squareup.picasso.Picasso;

import de.hdodenhof.circleimageview.CircleImageView;
import extra.CircleTransform;
import model.OponionFeed;

/**
 * Created by rutvik on 10/29/2016 at 8:04 PM.
 */

public class OponionFeedVH extends RecyclerView.ViewHolder
{

    OponionFeed model;

    final Context context;

    CircleImageView ivOponionFeedBy;

    PorterShapeImageView ivOponionFeedImage;

    TextView tvOponionFeedBy, tvOponionFeedExtra,
            tvOponionFeedTitle, tvOponionFeedTotalVotes;

    public OponionFeedVH(Context context, View itemView)
    {
        super(itemView);
        this.context = context;

        ivOponionFeedBy = (CircleImageView) itemView.findViewById(R.id.iv_oponionFeedBy);
        ivOponionFeedImage = (PorterShapeImageView) itemView.findViewById(R.id.iv_oponionFeedImage);

        tvOponionFeedBy = (TextView) itemView.findViewById(R.id.tv_oponionFeedBy);
        tvOponionFeedExtra = (TextView) itemView.findViewById(R.id.tv_oponionFeedExtra);
        tvOponionFeedTitle = (TextView) itemView.findViewById(R.id.tv_oponionFeedTitle);
        tvOponionFeedTotalVotes = (TextView) itemView.findViewById(R.id.tv_oponionFeedTotalVotes);

    }

    public static OponionFeedVH create(Context context, ViewGroup parent)
    {
        if (context == null)
        {
            Log.e("OPONINO", "CONTEXT IS NULLLL");
            return null;
        }
        return new OponionFeedVH(context,
                LayoutInflater.from(context).inflate(R.layout.single_oponion_post_row_item, parent, false));
    }

    public static void bind(final OponionFeedVH holder, final OponionFeed model)
    {
        if (holder.model == null)
        {
            holder.model = model;
        }

        Picasso.with(holder.context).load(holder.model.getFeedByImage())
                .transform(new CircleTransform())
                .into(holder.ivOponionFeedBy);

        if (holder.model.getFeedContentImage() != null)
        {
            Picasso.with(holder.context).load(holder.model.getFeedContentImage())
                    .into(holder.ivOponionFeedImage);
        }

        holder.tvOponionFeedTotalVotes.setText(holder.model.getFeedTotalVotes());
        holder.tvOponionFeedExtra.setText(holder.model.getFeedExtra());
        holder.tvOponionFeedTitle.setText(holder.model.getFeedTitle());
        holder.tvOponionFeedBy.setText(holder.model.getFeedByName());

    }

}
