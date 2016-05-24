package test.bluerain.youku.com.mymessage.utils;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.net.Uri;
import android.util.Log;

import java.util.ArrayList;
import java.util.List;

import test.bluerain.youku.com.mymessage.entity.Message;

/**
 * Project: MyMessage.
 * Data: 2016/5/24.
 * Created by 8luerain.
 * Contact:<a href="mailto:8luerain@gmail.com">Contact_me_now</a>
 */
public class MessageManger {
    private static final String TAG = "MessageManger";
    private static Context mContext;
    public static final Uri SMS_URI = Uri.parse("content://sms/");
    public static final Uri SMS_URI_INPUT = Uri.parse("content://sms/inbox");

    private static MessageManger ourInstance = new MessageManger();

    public static MessageManger getInstance(Context context) {
        mContext = context;
        return ourInstance;
    }

    private MessageManger() {
    }

    public List<Message> getMessageFromContent() {
        List<Message> messages = new ArrayList<>();
        Cursor query = mContext.getContentResolver().query(SMS_URI, null, null, null, "date desc");
        if (null == query)
            return messages;
        while (query.moveToNext()) {
            Message itemMessage = new Message();
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
            messages.add(itemMessage);
//            Log.d(TAG, "getMessageFromContent: _id[" + _id + "] thread_id[" + thread_id + "]" +
//                    " address[" + address + "] person[" + person + "]date[" + date + "]" +
//                    "protocol[" + protocol + "]read[" + read + "]status[" + status + "]" +
//                    "type[" + type + "]body[" + body + "]service_center[" + service_center + "]");
        }
        return messages;
    }


    public static void setMessageReaded(String messageId) {
        String where = "_id="+messageId;
        String[] args = {messageId};
        ContentValues values = new ContentValues();
        values.put("read", 1);
        int num = mContext.getContentResolver().update(SMS_URI_INPUT, values, where, null);
//        int del = mContext.getContentResolver().delete(SMS_URI, where, null);
//        Log.d(TAG, "setMessageReaded: messageId[" + messageId + "] updateMun[" + num + "] delNum[" + del + "]");
        Log.d(TAG, "setMessageReaded: messageId[" + messageId + "] updateMun[" + num + "]");
    }
}
