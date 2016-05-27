package test.bluerain.youku.com.mymessage.adapter;

import android.content.Context;
import android.database.Cursor;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CursorAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import test.bluerain.youku.com.mymessage.R;
import test.bluerain.youku.com.mymessage.entity.Message;
import test.bluerain.youku.com.mymessage.utils.CommonUtils;
import test.bluerain.youku.com.mymessage.utils.MessageManger;
import test.bluerain.youku.com.mymessage.utils.MessageUtils;

/**
 * Project: MyMessage.
 * Data: 2016/5/27.
 * Created by 8luerain.
 * Contact:<a href="mailto:8luerain@gmail.com">Contact_me_now</a>
 */
public class MessageCursorAdapter extends CursorAdapter {


    public MessageCursorAdapter(Context context, Cursor c, boolean autoRequery) {
        super(context, c, autoRequery);
    }

    @Override
    public void bindView(View view, Context context, Cursor cursor) {
        ItemViewHolder holder = (ItemViewHolder) view.getTag();
        Message message = MessageManger.getMessageByCursor(cursor);
        holder.mTextViewContectPhoneNum.setText(message.getAddress());
        holder.mTextViewContectBody.setText(message.getBody());
        holder.mTextViewContectServiceNum.setText(message.getService_center());
        holder.mTextViewContectDate.setText(CommonUtils.getLocalDate(message.getDate()));
        if (MessageUtils.isMessageNotRead(message)) {
            holder.mItemView.setBackgroundColor(0xFF87CEFA);
        } else {
            holder.mItemView.setBackgroundColor(0xFFFFFFFF);
        }
    }

    @Override
    public View newView(Context context, Cursor cursor, ViewGroup parent) {
        View view = LayoutInflater.from(context).inflate(R.layout.item_recycle_main_message, null);
        ItemViewHolder itemViewHolder = new ItemViewHolder(view);
        view.setTag(itemViewHolder);
        return view;
    }


    public static class ItemViewHolder {

        View mItemView;
        ImageView mImageViewContectImage;
        TextView mTextViewContectPhoneNum;
        TextView mTextViewContectBody;
        TextView mTextViewContectServiceNum;
        TextView mTextViewContectDate;

        public ItemViewHolder(View itemView) {
            mItemView = itemView;
            mImageViewContectImage = (ImageView) itemView.findViewById(R.id.id_igv_contact_image);
            mTextViewContectPhoneNum = (TextView) itemView.findViewById(R.id.id_txv_phone_num_item_recycle_main_message);
            mTextViewContectBody = (TextView) itemView.findViewById(R.id.id_txv_message_body_item_recycle_main_message);
            mTextViewContectServiceNum = (TextView) itemView.findViewById(R.id.id_txv_message_service_item_recycle_main_message);
            mTextViewContectDate = (TextView) itemView.findViewById(R.id.id_txv_message_date_item_recycle_main_message);
        }

        public void resetBackground() {
            mItemView.setBackgroundColor(0xFFFFFFFF);
        }

    }
}
