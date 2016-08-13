package component;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.app.oponion.R;

import progressviews.LineProgressBar;

/**
 * Created by mw on 13-08-2016.
 */
public class ProgressBarComponent extends LinearLayout {

    LinearLayout llProgressBarComponent;

    RelativeLayout rlProgressBarContainer;

    LineProgressBar lineProgressBar;

    TextView tvLabel, tvPollVotes;


    public ProgressBarComponent(Context context, String label, String percentage, OnClickListener onClickListener) {

        super(context);

        View view = LayoutInflater.from(context).inflate(R.layout.single_progressbar, null, false);

        this.setOrientation(VERTICAL);

        this.setLayoutParams(new ViewGroup.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT,
                ViewGroup.LayoutParams.WRAP_CONTENT));

        llProgressBarComponent = (LinearLayout) view.findViewById(R.id.ll_progressBarComponent);

        rlProgressBarContainer = (RelativeLayout) view.findViewById(R.id.rl_progressBarContainer);

        lineProgressBar = (LineProgressBar) view.findViewById(R.id.pb_poll);
        lineProgressBar.setRoundEdgeProgress(true);
        lineProgressBar.setProgress(Integer.valueOf(percentage));

        tvLabel = (TextView) view.findViewById(R.id.tv_progressLabel);
        tvLabel.setText(label);

        tvPollVotes = (TextView) view.findViewById(R.id.tv_pollVotes3);
        tvPollVotes.setText(percentage+"%");

        setOnClickListener(onClickListener);

        this.addView(view);

    }

}
