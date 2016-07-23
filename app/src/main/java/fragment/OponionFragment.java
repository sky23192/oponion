package fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.app.oponion.R;

/**
 * Created by rutvik on 02-07-2016 at 06:12 PM.
 */

public class OponionFragment extends Fragment
{
    public OponionFragment()
    {

    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState)
    {
        View v = inflater.inflate(R.layout.circle_fragment, container, false);

        return v;
    }

}
