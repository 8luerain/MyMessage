package test.bluerain.youku.com.mymessage.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.List;

import test.bluerain.youku.com.mymessage.R;
import test.bluerain.youku.com.mymessage.entity.Message;
import test.bluerain.youku.com.mymessage.utils.CommonUtils;
import test.bluerain.youku.com.mymessage.utils.MessageUtils;

/**
 * Project: MyMessage.
 * Data: 2016/5/24.
 * Created by 8luerain.
 * Contact:<a href="mailto:8luerain@gmail.com">Contact_me_now</a>
 */
public class MainRecycleAdapter extends RecyclerView.Adapter<MainRecycleAdapter.ItemViewHolder> {

    private List<Message> mMessages;
    private Context mContext;

    private OnRecycleItemClickListener mOnItemClickListener;


    public MainRecycleAdapter(Context context) {
        mContext = context;
    }

    @Override
    public ItemViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(mContext).inflate(R.layout.item_recycle_main_message, null);

        return new ItemViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(final ItemViewHolder holder, final int position) {
        Message message = mMessages.get(position);
        holder.mTextViewContectPhoneNum.setText(message.getAddress());
        holder.mTextViewContectBody.setText(message.getBody());
        holder.mTextViewContectServiceNum.setText(message.getService_center());
        holder.mTextViewContectDate.setText(CommonUtils.getLocalDate(message.getDate()));
        if (MessageUtils.isMessageNotRead(message)) {
            holder.mItemView.setBackgroundColor(0xFF87CEFA);
        } else {
            holder.mItemView.setBackgroundColor(0xFFFFFFFF);
        }
        if (null != mOnItemClickListener)
            holder.mItemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    mOnItemClickListener.OnItemClick(holder.mItemView, mMessages.get(position), position);
                }
            });
    }

    @Override
    public int getItemCount() {
        return mMessages.size();
    }


    public void setOnItemClickListener(OnRecycleItemClickListener onItemClickListener) {
        mOnItemClickListener = onItemClickListener;
    }

    public void setMessages(List<Message> messages) {
        mMessages = messages;
    }

    static class ItemViewHolder extends RecyclerView.ViewHolder {

        View mItemView;
        ImageView mImageViewContectImage;
        TextView mTextViewContectPhoneNum;
        TextView mTextViewContectBody;
        TextView mTextViewContectServiceNum;
        TextView mTextViewContectDate;

        public ItemViewHolder(View itemView) {
            super(itemView);
            mItemView = itemView;
            mImageViewContectImage = (ImageView) itemView.findViewById(R.id.id_igv_contact_image);
            mTextViewContectPhoneNum = (TextView) itemView.findViewById(R.id.id_txv_phone_num_item_recycle_main_message);
            mTextViewContectBody = (TextView) itemView.findViewById(R.id.id_txv_message_body_item_recycle_main_message);
            mTextViewContectServiceNum = (TextView) itemView.findViewById(R.id.id_txv_message_service_item_recycle_main_message);
            mTextViewContectDate = (TextView) itemView.findViewById(R.id.id_txv_message_date_item_recycle_main_message);
        }


    }


    public interface OnRecycleItemClickListener {

        void OnItemClick(View view, Object data, int position);

    }
}
