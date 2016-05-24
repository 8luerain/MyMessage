package test.bluerain.youku.com.mymessage.entity;

import android.os.Parcel;
import android.os.Parcelable;
import android.support.annotation.NonNull;

/**
 * Project: MyMessage.
 * Data: 2016/5/24.
 * Created by 8luerain.
 * Contact:<a href="mailto:8luerain@gmail.com">Contact_me_now</a>
 */
public class Message implements Parcelable {

    /*
    _id：短信序号，如100
　　
　　thread_id：对话的序号，如100，与同一个手机号互发的短信，其序号是相同的
　　
　　address：发件人地址，即手机号，如+86138138000
　　
　　person：发件人，如果发件人在通讯录中则为具体姓名，陌生人为null
　　
　　date：日期，long型，如1346988516，可以对日期显示格式进行设置
　　
　　protocol：协议0SMS_RPOTO短信，1MMS_PROTO彩信
　　
　　read：是否阅读0未读，1已读
　　
　　status：短信状态-1接收，0complete,64pending,128failed
　　
　　type：短信类型1是接收到的，2是已发出
　　
　　body：短信具体内容
　　
　　service_center：短信服务中心号码编号，如+8613800755500
*/
    private String mId;
    private String mThread_id;
    private String mAddress;
    private String mPerson;
    private String mDate;
    private String mProtocol;
    private String mRead;
    private String mStatus;
    private String mType;
    private String mBody;
    private String mService_center;

    public String getId() {
        return mId;
    }

    public void setId(String id) {
        mId = id;
    }

    public String getThread_id() {
        return mThread_id;
    }

    public void setThread_id(String thread_id) {
        mThread_id = thread_id;
    }

    public String getAddress() {
        return mAddress;
    }

    public void setAddress(String address) {
        mAddress = address;
    }

    public String getPerson() {
        return mPerson;
    }

    public void setPerson(String person) {
        mPerson = person;
    }

    public String getDate() {
        return mDate;
    }

    public void setDate(String date) {
        mDate = date;
    }

    public String getProtocol() {
        return mProtocol;
    }

    public void setProtocol(String protocol) {
        mProtocol = protocol;
    }

    public String getRead() {
        return mRead;
    }

    public void setRead(String read) {
        mRead = read;
    }

    public String getStatus() {
        return mStatus;
    }

    public void setStatus(String status) {
        mStatus = status;
    }

    public String getType() {
        return mType;
    }

    public void setType(String type) {
        mType = type;
    }

    public String getBody() {
        return mBody;
    }

    public void setBody(String body) {
        mBody = body;
    }

    public String getService_center() {
        return mService_center;
    }

    public void setService_center(String service_center) {
        mService_center = service_center;
    }

    public Message() {
    }

    protected Message(Parcel in) {
        setId(in.readString());
        setThread_id(in.readString());
        setAddress(in.readString());
        setBody(in.readString());
        setDate(in.readString());
        setPerson(in.readString());
        setProtocol(in.readString());
        setRead(in.readString());
        setService_center(in.readString());
        setStatus(in.readString());
        setType(in.readString());
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(@NonNull Parcel dest, int flags) {
        dest.writeString(mId);
        dest.writeString(mThread_id);
        dest.writeString(mAddress);
        dest.writeString(mBody);
        dest.writeString(mDate);
        dest.writeString(mPerson);
        dest.writeString(mProtocol);
        dest.writeString(mRead);
        dest.writeString(mService_center);
        dest.writeString(mStatus);
        dest.writeString(mType);
    }

    public static final Creator<Message> CREATOR = new Creator<Message>() {
        @Override
        public Message createFromParcel(Parcel in) {
            return new Message(in);
        }

        @Override
        public Message[] newArray(int size) {
            return new Message[size];
        }
    };
}
