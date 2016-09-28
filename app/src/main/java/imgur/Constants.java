package imgur;

/**
 * Created by mw on 27-09-2016.
 */
public class Constants {

    /*
      Logging flag
     */
    public static final boolean LOGGING = false;

    /*
      Your imgur client id. You need this to upload to imgur.
      More here: https://api.imgur.com/
     */
    public static final String MY_IMGUR_CLIENT_ID = "4d0542cbfcd1cb4";
    public static final String MY_IMGUR_CLIENT_SECRET = "480dc95bd11ff1cba3a016b799a6b5db12354eab";

    /*
      Redirect URL for android.
     */
    public static final String MY_IMGUR_REDIRECT_URL = "http://android";

    /*
      Client Auth
     */
    public static String getClientAuth() {
        return "Client-ID " + MY_IMGUR_CLIENT_ID;
    }

}
