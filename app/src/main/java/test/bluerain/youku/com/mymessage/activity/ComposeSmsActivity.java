package test.bluerain.youku.com.mymessage.activity;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import java.util.List;

import test.bluerain.youku.com.mymessage.R;
import test.bluerain.youku.com.mymessage.entity.DisplayMessage;
import test.bluerain.youku.com.mymessage.entity.Message;
import test.bluerain.youku.com.mymessage.entity.Profile;
import test.bluerain.youku.com.mymessage.utils.CommonUtils;
import test.bluerain.youku.com.mymessage.utils.MessageManger;

/**
 * 发送短信界面
 * Project: MyMessage.
 * Data: 2016/5/24.
 * Created by 8luerain.
 * Contact:<a href="mailto:8luerain@gmail.com">Contact_me_now</a>
 */
public class ComposeSmsActivity extends AppBaseActivity {
    private static final String TAG = "ComposeSmsActivity";


    //由主页跳转过来的短信
    private String mAddress;
    private List<Message> mMessagesByAddress;

    //由第三方携带过来的参数
    private DisplayMessage mDisplayMessageOther;

    //头部
    private LinearLayout mLinearLayoutHeaderContainer;
    private EditText mEditTextSendPhoneNum;
    private ImageView mImageViewAddContact;
    //中间
    private LinearLayout mLinearLayoutMessageContainer;
    //尾部
    private RelativeLayout mRelativeLayoutFooterContainer;
    private EditText mEditTextMessageBody;
    private ImageView mImageViewSendBtn;


    @Override
    protected int setLayout() {
        return R.layout.activity_send_message;
    }

    @Override
    protected void initVariables() {

    }

    @Override
    protected void initData(Bundle savedInstanceState) {
        Intent intent = getIntent();
        String action = intent.getAction();
        if (TextUtils.equals(action, "android.intent.action.SENDTO")) {
            String mSmsTo = intent.getData().toString().split(":")[1];
            String mSmsBody = intent.getStringExtra("sms_body");
            mDisplayMessageOther = new DisplayMessage(mSmsBody, CommonUtils.getLocalDate(System.currentTimeMillis()));
            mDisplayMessageOther.setSendMessagePhoneNum(mSmsTo);
            Log.d(TAG, "initData: smsto[" + mSmsTo + "] smsbody[" + mSmsBody + "]");
        }
        initMessagesFromAddress();
    }

    private void initMessagesFromAddress() {
        mAddress = getIntent().getStringExtra(Profile.INTENT_MESSAGE_KEY);
        if (TextUtils.isEmpty(mAddress))
            return;
        mMessagesByAddress = MessageManger.getMessagesByAddress(mAddress);
        Log.d(TAG, "handleItemClick: [" + mMessagesByAddress + "]");
    }

    @Override
    protected void initView() {
        initHeader();
        initCenter();
        initFooter();
        attachMainPageDate2View(mMessagesByAddress);
        attachOtherDate2View(mDisplayMessageOther);

    }

    private void initHeader() {
        mLinearLayoutHeaderContainer = (LinearLayout) findViewById(R.id.id_layout_send_page_header);
        mEditTextSendPhoneNum = (EditText) findViewById(R.id.editText_send_message_header_phone);
        mImageViewAddContact = (ImageView) findViewById(R.id.imageView_send_message_header_add_contect);
        if (null != mAddress)
            mLinearLayoutHeaderContainer.setVisibility(View.GONE);
    }

    private void initCenter() {
        mLinearLayoutMessageContainer = (LinearLayout) findViewById(R.id.id_layout_send_page_container);
    }

    private void initFooter() {
        mRelativeLayoutFooterContainer = (RelativeLayout) findViewById(R.id.id_layout_send_page_footer);
        mEditTextMessageBody = (EditText) findViewById(R.id.editText_send_message_footer_message_body);
        mImageViewSendBtn = (ImageView) findViewById(R.id.imageView_send_message_footer_send);
        mImageViewSendBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String body = mEditTextMessageBody.getText().toString();
                if (TextUtils.isEmpty(body))
                    return;
                DisplayMessage displayMessage = new DisplayMessage(body, CommonUtils.getLocalDate(System.currentTimeMillis()));
                Log.d(TAG, "onClick: " + displayMessage + "");
                insertMessageBodyView(displayMessage);
                mEditTextMessageBody.setText("");
                checkIfNeedJumpToDidi(body);
            }
        });
    }

    private void checkIfNeedJumpToDidi(String messageBody) {
        if (messageBody.contains("滴滴"))
            jumpToGetMessageActivity(messageBody);
    }

    private void jumpToGetMessageActivity(String data) {
        Intent messageIntent = new Intent("test.bluerain.youku.com.xposedtest.getmessage");
        messageIntent.putExtra("DIDI", data);
        startActivityForResult(messageIntent, 1);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (requestCode == 1 && resultCode == RESULT_OK) {
            finish();
        }
        super.onActivityResult(requestCode, resultCode, data);
    }

    /**
     * 处理其他进程携带过来的数据
     *
     * @param displayMessageOhter
     */
    private void attachOtherDate2View(DisplayMessage displayMessageOhter) {
        if (null == displayMessageOhter)
            return;
        mEditTextSendPhoneNum.setText(displayMessageOhter.getSendMessagePhoneNum());
        mEditTextMessageBody.setText(displayMessageOhter.getSendMessageBody());
    }

    /**
     * 处理首页跳转过来的数据
     *
     * @param messagesByAddress
     */
    private void attachMainPageDate2View(List<Message> messagesByAddress) {
        if (null == messagesByAddress)
            return;

        setTitle(mAddress);

        for (int i = messagesByAddress.size() - 1; i >= 0; i--) {
            Message message = messagesByAddress.get(i);
            DisplayMessage displayMessage = new DisplayMessage(message.getBody(), CommonUtils.getLocalDate(message.getDate()));
            insertMessageBodyView(displayMessage);
        }

    }

    private void insertMessageBodyView(DisplayMessage displayMessage) {
        View messageBodyView = LayoutInflater.from(this).inflate(R.layout.layout_message_body, null);
        TextView textViewMessageBody = (TextView) messageBodyView.findViewById(R.id.id_txv_message_body);
        TextView textViewMessageDate = (TextView) messageBodyView.findViewById(R.id.id_txv_message_date);
        textViewMessageBody.setText(displayMessage.getSendMessageBody());
        textViewMessageDate.setText(displayMessage.getSendMessageDate());
        LinearLayout.LayoutParams layoutParams = new LinearLayout.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT,
                ViewGroup.LayoutParams.WRAP_CONTENT);
        if (displayMessage.getType() == DisplayMessage.TYPE_RECEIVE)
            layoutParams.gravity = Gravity.RIGHT;
        mLinearLayoutMessageContainer.addView(messageBodyView, layoutParams);
    }


}
