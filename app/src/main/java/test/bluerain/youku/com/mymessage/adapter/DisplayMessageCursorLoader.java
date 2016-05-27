package test.bluerain.youku.com.mymessage.adapter;

import android.content.Context;
import android.content.CursorLoader;
import android.net.Uri;

/**
 * Project: MyMessage.
 * Data: 2016/5/27.
 * Created by 8luerain.
 * Contact:<a href="mailto:8luerain@gmail.com">Contact_me_now</a>
 */
public class DisplayMessageCursorLoader extends CursorLoader {
    /*     String thread_id = query.getString(query.getColumnIndex("thread_id"));
            String address = query.getString(query.getColumnIndex("address"));
            String person = query.getString(query.getColumnIndex("person"));
            String date = query.getString(query.getColumnIndex("date"));
            String protocol = query.getString(query.getColumnIndex("protocol"));
            String read = query.getString(query.getColumnIndex("read"));
            String status = query.getString(query.getColumnIndex("status"));
            String type = query.getString(query.getColumnIndex("type"));
            String body = query.getString(query.getColumnIndex("body"));
            String service_center = query.getString(query.getColumnIndex("service_center"));*/
    /*Uri uri, String[] projection, String selection, String[] selectionArgs, String sortOrder*/
    private final static Uri SMS_URI = Uri.parse("content://sms/");
    private final static String[] mProjection = {"_id", "thread_id", "address", "person", "date", "protocol",
            "read", "status", "type", "body", "service_center"};
    private final static String mSelection = "0==0) GROUP BY (address";
    //    private final static String[] mProjection = null;
    private final static String[] mSelectionArgs = null;
    private final static String mSortOrder = "_id desc";


    public DisplayMessageCursorLoader(Context context) {
        this(context, SMS_URI, mProjection, mSelection, mSelectionArgs, mSortOrder);
    }

    public DisplayMessageCursorLoader(Context context, Uri uri, String[] projection, String selection, String[] selectionArgs, String sortOrder) {
        super(context, uri, projection, selection, selectionArgs, sortOrder);
    }
}
