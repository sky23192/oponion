package adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.ViewGroup;

import java.util.ArrayList;

import component.ViewComponent;
import viewcomponent.ProfileViewComponent;
import viewholder.ProfileViewVH;
import viewmodel.ProfileView;

/**
 * Created by rutvik on 10/10/2016 at 3:54 PM.
 */

public class ViewAdapter extends RecyclerView.Adapter
{

    final Context context;

    final ArrayList<ViewComponent> viewComponents;

    public ViewAdapter(final Context context)
    {
        this.context = context;
        viewComponents = new ArrayList<>();
    }


    public void addProfileView(ProfileView profileView)
    {
        viewComponents.add(new ViewComponent(ViewComponent.PROFILE_VIEW, profileView));
        notifyItemInserted(viewComponents.size());
    }


    @Override
    public int getItemViewType(int position)
    {
        return viewComponents.get(position).getViewType();
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType)
    {

        switch (viewType)
        {
            case ViewComponent.PROFILE_VIEW:
                return ProfileViewVH.create(context, parent);
        }

        return null;
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position)
    {
        switch (getItemViewType(position))
        {

            case ViewComponent.PROFILE_VIEW:
                ProfileViewVH.bind((ProfileViewVH) holder,
                        (ProfileView) viewComponents.get(position).getViewModel());

                break;

        }
    }

    @Override
    public int getItemCount()
    {
        return viewComponents.size();
    }
}
