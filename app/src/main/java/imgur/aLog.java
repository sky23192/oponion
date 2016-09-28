package imgur;

import android.util.Log;

/**
 * Created by mw on 27-09-2016.
 */
public class aLog {
    public static void w(String TAG, String msg) {
        if (Constants.LOGGING) {
            if (TAG != null && msg != null)
                Log.w(TAG, msg);
        }
    }
}
