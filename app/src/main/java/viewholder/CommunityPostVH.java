package viewholder;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.app.oponion.R;
import com.github.siyamed.shapeimageview.mask.PorterShapeImageView;
import com.squareup.picasso.Picasso;

import de.hdodenhof.circleimageview.CircleImageView;
import model.SingleCommunityCard;

/**
 * Created by rutvik on 11/27/2016 at 9:50 AM.
 */

public class CommunityPostVH extends RecyclerView.ViewHolder
{

    final Context context;

    SingleCommunityCard model;

    CircleImageView ivCommunityLogo;
    PorterShapeImageView ivCommunityBg;

    TextView tvCommunityName, tvCommunityContentText, tvCommunityRating;

    public CommunityPostVH(Context context, View itemView)
    {
        super(itemView);
        this.context = context;

        tvCommunityRating = (TextView) itemView.findViewById(R.id.tv_communityPostRating);
        tvCommunityName = (TextView) itemView.findViewById(R.id.tv_communityName);
        tvCommunityContentText = (TextView) itemView.findViewById(R.id.tv_communityContentText);

        ivCommunityBg = (PorterShapeImageView) itemView.findViewById(R.id.iv_communityPostBg);
        ivCommunityLogo = (CircleImageView) itemView.findViewById(R.id.iv_communityLogo);

    }

    public static CommunityPostVH create(final Context context, final ViewGroup parent)
    {
        return new CommunityPostVH(context, LayoutInflater.from(context)
                .inflate(R.layout.single_community_card, parent, false));
    }

    public static void bind(final CommunityPostVH vh, SingleCommunityCard model)
    {

        vh.model = model;

        vh.tvCommunityContentText.setText(model.getCommunityPostContentText());
        vh.tvCommunityName.setText(model.getCommunityPostTitle());
        vh.tvCommunityRating.setText(model.getCommunityPostRating());

        Picasso.with(vh.context)
                .load(model.getCommunityPostLogo())
                .tag("COMMUNITY")
                .into(vh.ivCommunityLogo);

        Picasso.with(vh.context)
                .load(model.getCommunityPostBg())
                .tag("COMMUNITY")
                .into(vh.ivCommunityBg);

    }


}
