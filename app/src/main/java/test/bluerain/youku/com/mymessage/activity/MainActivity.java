package test.bluerain.youku.com.mymessage.activity;

import android.app.LoaderManager;
import android.content.Intent;
import android.content.Loader;
import android.database.Cursor;
import android.net.Uri;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.TextView;

import test.bluerain.youku.com.mymessage.R;
import test.bluerain.youku.com.mymessage.adapter.DisplayMessageCursorLoader;
import test.bluerain.youku.com.mymessage.adapter.MessageCursorAdapter;
import test.bluerain.youku.com.mymessage.entity.Profile;

public class MainActivity extends AppBaseActivity implements LoaderManager.LoaderCallbacks<Cursor> {
    private static final String TAG = "MainActivity";

    private ListView mListView;
    private MessageCursorAdapter mMessageCursorAdapter;

    private Toolbar mToolbar;
    private FloatingActionButton mFloatingActionButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        initEvent();
    }

    @Override
    protected int setLayout() {
        return R.layout.activity_main;
    }

    protected void initVariables() {

    }

    @Override
    protected void initData(Bundle savedInstanceState) {
        mMessageCursorAdapter = new MessageCursorAdapter(this, null, false);
        getLoaderManager().initLoader(0, null, this);
    }

    protected void initView() {
        mListView = (ListView) findViewById(R.id.id_list_view_view_main);
        mListView.setAdapter(mMessageCursorAdapter);
        mToolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(mToolbar);
        mFloatingActionButton = (FloatingActionButton) findViewById(R.id.fab);
    }

    private void initEvent() {
        mListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                handleItemClick(view);
            }
        });
        mFloatingActionButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this, ComposeSmsActivity.class);
                startActivity(intent);
//                testSendMessage();
            }
        });
    }


    @Override
    public Loader<Cursor> onCreateLoader(int id, Bundle args) {
        return new DisplayMessageCursorLoader(this);
    }

    @Override
    public void onLoadFinished(Loader<Cursor> loader, Cursor data) {
        mMessageCursorAdapter.swapCursor(data);
    }

    @Override
    public void onLoaderReset(Loader<Cursor> loader) {
        mMessageCursorAdapter.swapCursor(null);
    }


    private String getStringFromViewId(View view, int id) {
        String s = null;
        if (view != null) {
            View viewById = view.findViewById(id);
            if (viewById instanceof TextView)
                s = ((TextView) viewById).getText().toString();
        }
        return s;
    }

    private void handleItemClick(View view) {
        jumpToDetailPage(view);
    }

    private void jumpToDetailPage(View view) {
        String phoneNum = getStringFromViewId(view, R.id.id_txv_phone_num_item_recycle_main_message);
        Intent intent = new Intent(this, ComposeSmsActivity.class);
        intent.putExtra(Profile.INTENT_MESSAGE_KEY, phoneNum);
        startActivity(intent);
    }

    public void testSendMessage() {
        Intent intent = new Intent(Intent.ACTION_SENDTO, Uri.parse("smsto:" + "1234556"));
        intent.putExtra("sms_body", "tttttttttttt");
        startActivity(intent);
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
