package test.bluerain.youku.com.mymessage.entity;

/**
 * Project: MyMessage.
 * Data: 2016/5/25.
 * Created by 8luerain.
 * Contact:<a href="mailto:8luerain@gmail.com">Contact_me_now</a>
 */
public class DisplayMessage {
    public static final int TYPE_SEND = 0;
    public static final int TYPE_RECEIVE = 1;

    //用于标识发送方还是接受方
    private int mType = TYPE_SEND;
    private String mSendMessagePhoneNum;
    private String mSendMessageBody;
    private String mSendMessageDate;

    public DisplayMessage() {
    }

    public DisplayMessage(String sendMessageBody, String sendMessageDate) {
        mSendMessageBody = sendMessageBody;
        mSendMessageDate = sendMessageDate;
    }

    public String getSendMessagePhoneNum() {
        return mSendMessagePhoneNum;
    }

    public void setSendMessagePhoneNum(String sendMessagePhoneNum) {
        mSendMessagePhoneNum = sendMessagePhoneNum;
    }

    public void setType(int type) {
        mType = type;
    }

    public int getType() {
        return mType;
    }

    public String getSendMessageBody() {
        return mSendMessageBody;
    }

    public void setSendMessageBody(String sendMessageBody) {
        mSendMessageBody = sendMessageBody;
    }

    public String getSendMessageDate() {
        return mSendMessageDate;
    }

    public void setSendMessageDate(String sendMessageDate) {
        mSendMessageDate = sendMessageDate;
    }

    @Override
    public String toString() {
        return "message body[" + mSendMessageBody + "] date[" + mSendMessageDate + "]";
    }
}
