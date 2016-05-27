package test.bluerain.youku.com.mymessage.utils;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Project: MyMessage.
 * Data: 2016/5/24.
 * Created by 8luerain.
 * Contact:<a href="mailto:8luerain@gmail.com">Contact_me_now</a>
 */
public class CommonUtils {

    public static long getLongOfString(String string) {
        try {
            return Long.valueOf(string);
        } catch (NumberFormatException e) {
            e.printStackTrace();
            return -1;
        }
    }

    public static long getIntOfString(String string) {
        try {
            Integer.valueOf(string);
        } catch (NumberFormatException e) {
            e.printStackTrace();
            return -1;
        }
        return -1;
    }


    public static String getLocalDate(String utc) {
        String formatedTime;
        try {
            DateFormat dateInstance = SimpleDateFormat.getDateInstance();
            formatedTime = dateInstance.format(new Date(getLongOfString(utc)));
        } catch (Exception e) {
            e.printStackTrace();
            return "";
        }
        return formatedTime;
    }

    public static String getLocalDate(long utc) {
        return SimpleDateFormat.getDateInstance().format(utc);
    }

}
