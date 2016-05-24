package test.bluerain.youku.com.mymessage.utils;

import android.text.TextUtils;

import test.bluerain.youku.com.mymessage.entity.Message;

/**
 * Project: MyMessage.
 * Data: 2016/5/24.
 * Created by 8luerain.
 * Contact:<a href="mailto:8luerain@gmail.com">Contact_me_now</a>
 */
public class MessageUtils {
    public static boolean isMessageNotRead(Message message) {
        return TextUtils.equals(message.getRead(), "0");
    }
}
