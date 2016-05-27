package test.bluerain.youku.com.mymessage;

import android.app.Application;

/**
 * Project: MyMessage.
 * Data: 2016/5/27.
 * Created by 8luerain.
 * Contact:<a href="mailto:8luerain@gmail.com">Contact_me_now</a>
 */
public class AppApplication extends Application {
    public static AppApplication sApplicationContext;

    @Override
    public void onCreate() {
        super.onCreate();
        sApplicationContext = this;
    }

    public static AppApplication getAppContext() {
        return sApplicationContext;
    }
}
