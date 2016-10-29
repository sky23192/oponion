package viewholder;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import viewcomponent.ProfileViewComponent;
import viewmodel.ProfileView;

/**
 * Created by rutvik on 10/10/2016 at 4:29 PM.
 */

public class ProfileViewVH extends RecyclerView.ViewHolder
{

    ProfileView model;

    ProfileViewComponent profileViewComponent;

    private ProfileViewVH(ProfileViewComponent itemView)
    {
        super(itemView);
        profileViewComponent = itemView;
    }

    public static ProfileViewVH create(final Context context, ViewGroup parent)
    {
        return new ProfileViewVH(new ProfileViewComponent(context));
    }

    public static void bind(final ProfileViewVH vh, final ProfileView model)
    {
        vh.model = model;
        vh.profileViewComponent.setProfileViewModel(vh.model);
        vh.profileViewComponent.ivBack.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View view)
            {
                vh.model.activity.finish();
            }
        });
    }

}
