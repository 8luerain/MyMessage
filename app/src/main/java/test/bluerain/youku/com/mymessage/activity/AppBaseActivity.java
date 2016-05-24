package test.bluerain.youku.com.mymessage.activity;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

/**
 * Project: MyMessage.
 * Data: 2016/5/24.
 * Created by 8luerain.
 * Contact:<a href="mailto:8luerain@gmail.com">Contact_me_now</a>
 */
public abstract class AppBaseActivity extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(setLayout());
        initVariables();
        initData(savedInstanceState);
        initView();
    }

    protected abstract int setLayout();

    protected abstract void initView();

    protected abstract void initData(Bundle savedInstanceState);

    protected abstract void initVariables();


}
