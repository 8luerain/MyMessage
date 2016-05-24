package test.bluerain.youku.com.mymessage.activity;

import android.app.NotificationManager;
import android.content.Intent;
import android.database.ContentObserver;
import android.os.Bundle;
import android.os.Handler;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;

import java.util.List;

import test.bluerain.youku.com.mymessage.R;
import test.bluerain.youku.com.mymessage.adapter.MainRecycleAdapter;
import test.bluerain.youku.com.mymessage.entity.Message;
import test.bluerain.youku.com.mymessage.entity.Profile;
import test.bluerain.youku.com.mymessage.utils.MessageManger;

public class MainActivity extends AppBaseActivity {
    private static final String TAG = "MainActivity";

    private RecyclerView mRecyclerView;
    private MainRecycleAdapter mMainRecycleAdapter;
    private List<Message> mMessageData;

    private NotificationManager mNotificationManager;
    private Handler mHandler;

    private Toolbar mToolbar;
    private FloatingActionButton mFloatingActionButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        MessageManger.getInstance(this).getMessageFromContent();
    }

    @Override
    protected int setLayout() {
        return R.layout.activity_main;
    }

    protected void initVariables() {
        mNotificationManager = (NotificationManager) getSystemService(NOTIFICATION_SERVICE);
        mHandler = new Handler() {
            @Override
            public void handleMessage(android.os.Message msg) {
                super.handleMessage(msg);

            }
        };
        getContentResolver().registerContentObserver(MessageManger.SMS_URI, true, new SMSObserver(mHandler));
    }

    @Override
    protected void initData(Bundle savedInstanceState) {
        mMessageData = MessageManger.getInstance(this).getMessageFromContent();
        mMainRecycleAdapter = new MainRecycleAdapter(this);
        mMainRecycleAdapter.setMessages(mMessageData);
    }

    protected void initView() {
        mRecyclerView = (RecyclerView) findViewById(R.id.id_recycle_view_main);
        mRecyclerView.setLayoutManager(new LinearLayoutManager(this));
        mRecyclerView.setAdapter(mMainRecycleAdapter);
        mMainRecycleAdapter.setOnItemClickListener(new MainRecycleAdapter.OnRecycleItemClickListener() {
            @Override
            public void OnItemClick(View view, Object data, int position) {
                Message clickMessage = (Message) data;
                MessageManger.setMessageReaded(clickMessage.getId());
                Intent intent = new Intent(MainActivity.this, MessageDetailActivity.class);
                intent.putExtra(Profile.INTENT_MESSAGE_KEY, clickMessage);
//                startActivity(intent);
            }
        });
        mToolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(mToolbar);
        mFloatingActionButton = (FloatingActionButton) findViewById(R.id.fab);
        mFloatingActionButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });
    }


    class SMSObserver extends ContentObserver {

        public SMSObserver(Handler handler) {
            super(handler);
        }

        @Override
        public void onChange(boolean selfChange) {
            super.onChange(selfChange);
            Log.d(TAG, "onChange: message change.... ");

//            mMessageData = MessageManger.getInstance(MainActivity.this).getMessageFromContent();
//            mMainRecycleAdapter.notifyDataSetChanged();
        }
    }


    private void loadMessagePanel() {
        Intent intent = new Intent(Intent.ACTION_VIEW);


        intent.putExtra("address", "186...");


        intent.putExtra("sms_body", "短信内容");


        intent.setType("vnd.android-dir/mms-sms");


        startActivity(intent);
    }
    @Override
    protected void onDestroy() {
        super.onDestroy();
        mHandler.removeCallbacksAndMessages(null);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

}
