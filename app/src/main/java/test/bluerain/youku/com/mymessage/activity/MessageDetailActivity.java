package test.bluerain.youku.com.mymessage.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import test.bluerain.youku.com.mymessage.R;
import test.bluerain.youku.com.mymessage.entity.Message;
import test.bluerain.youku.com.mymessage.entity.Profile;

/**
 * Project: MyMessage.
 * Data: 2016/5/24.
 * Created by 8luerain.
 * Contact:<a href="mailto:8luerain@gmail.com">Contact_me_now</a>
 */
public class MessageDetailActivity extends AppBaseActivity {
    private LinearLayout mLinearLayoutMessageContainer;
    private Message mMessage;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    protected int setLayout() {
        return R.layout.avtivity_message_detail;
    }

    @Override
    protected void initView() {
        mLinearLayoutMessageContainer = (LinearLayout) findViewById(R.id.id_layout_message_detail_container);
        addMessageDetailView();
    }

    private void addMessageDetailView() {
        if (null == mMessage)
            return;
        TextView messageDetailView =   new TextView(this);
        messageDetailView.setText(mMessage.getBody());
        mLinearLayoutMessageContainer.addView(messageDetailView, new LinearLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup
                .LayoutParams.WRAP_CONTENT));
    }

    @Override
    protected void initData(Bundle savedInstanceState) {
        Intent intent = getIntent();
        mMessage = intent.getParcelableExtra(Profile.INTENT_MESSAGE_KEY);
    }

    @Override
    protected void initVariables() {

    }
}
