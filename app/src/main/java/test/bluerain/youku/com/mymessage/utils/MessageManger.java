package test.bluerain.youku.com.mymessage.utils;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.net.Uri;
import android.telephony.SmsMessage;
import android.util.Log;

import java.util.ArrayList;
import java.util.List;

import test.bluerain.youku.com.mymessage.AppApplication;
import test.bluerain.youku.com.mymessage.entity.Message;

/**
 * Project: MyMessage.
 * Data: 2016/5/24.
 * Created by 8luerain.
 * Contact:<a href="mailto:8luerain@gmail.com">Contact_me_now</a>
 */
public class MessageManger {
    private static final String TAG = "MessageManger";
    private static Context mContext = AppApplication.getAppContext();
    public static final Uri SMS_URI = Uri.parse("content://sms/");
    public static final Uri SMS_URI_INPUT = Uri.parse("content://sms/inbox");

    private static MessageManger ourInstance = new MessageManger();

    public static MessageManger getInstance(Context context) {
        mContext = context;
        return ourInstance;
    }

    private MessageManger() {

    }

    public static Message getMessageById(int id) {
        String selection = "_id = " + id;
        Cursor query = mContext.getContentResolver().query(SMS_URI, null, selection, null, "date desc");
        return getMessageByCursor(query);
    }


    public static Message getMessageByCursor(Cursor query) {
        Message itemMessage = null;
        if (null == query)
            return itemMessage;
        itemMessage = new Message();
        String _id = query.getString(query.getColumnIndex("_id"));
        String thread_id = query.getString(query.getColumnIndex("thread_id"));
        String address = query.getString(query.getColumnIndex("address"));
        String person = query.getString(query.getColumnIndex("person"));
        String date = query.getString(query.getColumnIndex("date"));
        String protocol = query.getString(query.getColumnIndex("protocol"));
        String read = query.getString(query.getColumnIndex("read"));
        String status = query.getString(query.getColumnIndex("status"));
        String type = query.getString(query.getColumnIndex("type"));
        String body = query.getString(query.getColumnIndex("body"));
        String service_center = query.getString(query.getColumnIndex("service_center"));
        itemMessage.setId(_id);
        itemMessage.setThread_id(thread_id);
        itemMessage.setAddress(address);
        itemMessage.setPerson(person);
        itemMessage.setDate(date);
        itemMessage.setProtocol(protocol);
        itemMessage.setRead(read);
        itemMessage.setStatus(status);
        itemMessage.setType(type);
        itemMessage.setBody(body);
        itemMessage.setService_center(service_center);
        return itemMessage;
    }

    public static List<Message> getAllMessage() {
        List<Message> messages = new ArrayList<>();
        Cursor query = mContext.getContentResolver().query(SMS_URI, null, null, null, "date desc");
        while (query != null && query.moveToNext()) {
            messages.add(getMessageByCursor(query));
        }
        return messages;
    }

    public static List<Message> getMessagesByAddress(String address) {
        String selection = "address = " + address;
        List<Message> messages = new ArrayList<>();
        Cursor query = mContext.getContentResolver().query(SMS_URI, null, selection, null, "_id desc");
        while (query != null && query.moveToNext()) {
            messages.add(getMessageByCursor(query));
        }
        return messages;
    }


    public static void setMessageReaded(String messageId) {
        String where = "_id=" + messageId;
        String[] args = {messageId};
        ContentValues values = new ContentValues();
        values.put("read", "1");
        int num = mContext.getContentResolver().update(SMS_URI_INPUT, values, where, null);
        Log.d(TAG, "setMessageReaded: messageId[" + messageId + "] updateMun[" + num + "]");
    }

    public static void insertMessageToSystemDB(SmsMessage smsMessage) {

        ContentValues values = new ContentValues();
        int indexOnIcc = smsMessage.getIndexOnIcc();
        int statusOnIcc = smsMessage.getStatusOnIcc();
        String originatingAddress = smsMessage.getOriginatingAddress();
        long timestampMillis = smsMessage.getTimestampMillis();
        int protocolIdentifier = smsMessage.getProtocolIdentifier();
        int status = smsMessage.getStatus();
        String messageBody = smsMessage.getMessageBody();
        String serviceCenterAddress = smsMessage.getServiceCenterAddress();

        Log.d(TAG, "insertMessageToSystemDB: indexOnIcc[" + indexOnIcc + "]statusOnIcc[" + statusOnIcc + "]" +
                "originatingAddress[" + originatingAddress + "]timestampMillis[" + timestampMillis + "]" +
                "protocolIdentifier[" + protocolIdentifier + "]status[" + status + "]messageBody[" + messageBody + "]" +
                "serviceCenterAddress[" + serviceCenterAddress + "]");

        values.put("address", originatingAddress);
        values.put("date", timestampMillis);
        values.put("protocol", protocolIdentifier);
        values.put("read", "0");
        values.put("status", status);
        values.put("body", messageBody);
        values.put("service_center", serviceCenterAddress);
        mContext.getContentResolver().insert(SMS_URI_INPUT, values);
    }
}
