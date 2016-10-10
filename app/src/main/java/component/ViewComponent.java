package component;

/**
 * Created by rutvik on 10/10/2016 at 4:12 PM.
 */

public class ViewComponent<T>
{

    public static final int PROFILE_VIEW = 0;

    final int viewType;

    final T viewModel;

    public ViewComponent(final int viewType, final T viewModel)
    {
        this.viewType = viewType;
        this.viewModel = viewModel;
    }

    public int getViewType()
    {
        return viewType;
    }

    public T getViewModel()
    {
        return viewModel;
    }
}
