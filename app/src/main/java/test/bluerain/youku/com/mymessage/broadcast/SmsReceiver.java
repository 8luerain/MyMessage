package test.bluerain.youku.com.mymessage.broadcast;

import android.app.Notification;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.media.RingtoneManager;
import android.telephony.SmsMessage;
import android.util.Log;

import test.bluerain.youku.com.mymessage.activity.MainActivity;
import test.bluerain.youku.com.mymessage.utils.MessageManger;

/**
 * Project: MyMessage.
 * Data: 2016/5/24.
 * Created by 8luerain.
 * Contact:<a href="mailto:8luerain@gmail.com">Contact_me_now</a>
 */
public class SmsReceiver extends BroadcastReceiver {
    private static final String TAG = "SmsReceiver";
    public static final String TOKEN_SMS = "pdus";
    private Context mContext;
    private NotificationManager mNotificationManager;

    @Override
    public void onReceive(Context context, Intent intent) {
        Log.d(TAG, "onReceive: sms receive.....");
        mContext = context;
        mNotificationManager = (NotificationManager) mContext.getSystemService(Context.NOTIFICATION_SERVICE);
        Object[] puds = (Object[]) intent.getExtras().get(TOKEN_SMS);
        SmsMessage[] smsMessages = new SmsMessage[puds.length];

        for (int i = 0; i < smsMessages.length; i++) {
            SmsMessage messageFromPdu = SmsMessage.createFromPdu((byte[]) puds[i]);
            MessageManger.insertMessageToSystemDB(messageFromPdu);
            setNotification(messageFromPdu);
        }
    }

    private void setNotification(SmsMessage message) {
        Intent intent = new Intent(mContext, MainActivity.class);
        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        PendingIntent pendingIntent = PendingIntent.getActivity(mContext, 0, intent, PendingIntent.FLAG_UPDATE_CURRENT);
        Notification.Builder builder = new Notification.Builder(mContext);
        //基本参数
        builder.setSmallIcon(android.R.mipmap.sym_def_app_icon);
        builder.setContentTitle(message.getOriginatingAddress());
        builder.setContentText(message.getMessageBody());
        //设定优先级
        builder.setPriority(Notification.PRIORITY_MAX);
        builder.setSound(RingtoneManager.getDefaultUri(RingtoneManager.TYPE_NOTIFICATION));
        builder.setAutoCancel(true);
        builder.setContentIntent(pendingIntent);
        Notification notification = builder.build();
        mNotificationManager.notify((int) (Math.random() * 1000), notification);
    }
}
