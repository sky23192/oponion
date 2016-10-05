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
import model.SpotlightFeed;

/**
 * Created by rutvik on 13-07-2016 at 06:26 PM.
 */

public class SpotlightFeedVH extends RecyclerView.ViewHolder implements FeedItem
{

    ImageView ivFeedImage, ivBtnUpVote, ivBtnDownVote, ivLocationImage;

    TextView tvTotalVotes, tvFeedTitle;

    Context context;

    SpotlightFeed model;

    public SpotlightFeedVH(Context context, View itemView)
    {
        super(itemView);
        this.context = context;
        tvFeedTitle = (TextView) itemView.findViewById(R.id.tv_title);
        ivFeedImage = (ImageView) itemView.findViewById(R.id.iv_contentPic);
        ivLocationImage = (ImageView) itemView.findViewById(R.id.iv_locationPic);
        tvTotalVotes = (TextView) itemView.findViewById(R.id.tv_totalVotes);

        ivBtnUpVote = (ImageView) itemView.findViewById(R.id.iv_upVote);
        ivBtnDownVote = (ImageView) itemView.findViewById(R.id.iv_downVote);

        ivBtnUpVote.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View view)
            {
                model.totalVotes = model.totalVotes + 1;
            }
        });

        ivBtnDownVote.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View view)
            {
                model.totalVotes = model.totalVotes - 1;
            }
        });
    }

    public static SpotlightFeedVH create(Context context, ViewGroup parent)
    {

        SpotlightFeedVH spotlightFeedVH = new SpotlightFeedVH(context,
                LayoutInflater.from(context).inflate(R.layout.single_spotlight_row_item, parent, false));

        return spotlightFeedVH;

    }

    public static void bind(SpotlightFeedVH holder, SpotlightFeed model)
    {
        if (holder.model == null)
        {
            holder.model = model;
            holder.tvFeedTitle.setText(holder.model.feedTitle);
            holder.tvTotalVotes.setText(String.valueOf(holder.model.totalVotes));
            if (model.feedImageUrl != null && model.locationImage != null)
            {
                Picasso.with(holder.context).load(holder.model.feedImageUrl).into(holder.ivFeedImage);
                Picasso.with(holder.context).load(holder.model.locationImage).into(holder.ivLocationImage);
            }
        }
    }

}
