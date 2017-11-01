package android.rentit.app.rent_it;

/**
 * Created by Ankit on 31-10-2017.
 */

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.widget.Toast;

import android.rentit.app.rent_it.FirebaseChatMainApp;
import android.rentit.app.rent_it.ChatFragment;
import android.rentit.app.rent_it.Constants;

public class ChatActivity extends AppCompatActivity {
    private Toolbar mToolbar;

    public static void startActivity(Context context,
                                     String receiver,
                                     String receiverUid,
                                     String firebaseToken) {
        Intent intent = new Intent(context, ChatActivity.class);
        intent.putExtra(Constants.ARG_RECEIVER, receiver);
        intent.putExtra(Constants.ARG_RECEIVER_UID, receiverUid);
        intent.putExtra(Constants.ARG_FIREBASE_TOKEN, firebaseToken);
        context.startActivity(intent);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_chat);
        bindViews();
        init();
    }

    private void bindViews() {
        mToolbar = (Toolbar) findViewById(R.id.toolbar);
    }

    private void init() {
        // set the toolbar
        setSupportActionBar(mToolbar);

        // set toolbar title
        mToolbar.setTitle(getIntent().getExtras().getString(Constants.ARG_RECEIVER));

        // set the register screen fragment
        Toast.makeText(this, "9", Toast.LENGTH_SHORT).show();
        FragmentTransaction fragmentTransaction = getSupportFragmentManager().beginTransaction();
        fragmentTransaction.replace(R.id.frame_layout_content_chat,
                ChatFragment.newInstance(getIntent().getExtras().getString(Constants.ARG_RECEIVER),
                        getIntent().getExtras().getString(Constants.ARG_RECEIVER_UID),
                        getIntent().getExtras().getString(Constants.ARG_FIREBASE_TOKEN)),
                ChatFragment.class.getSimpleName());
        Toast.makeText(this, "10", Toast.LENGTH_SHORT).show();
        fragmentTransaction.commit();
    }

    @Override
    protected void onResume() {
        super.onResume();
        Toast.makeText(this, "11", Toast.LENGTH_SHORT).show();
        FirebaseChatMainApp.setChatActivityOpen(true);
    }

    @Override
    protected void onPause() {
        super.onPause();
        Toast.makeText(this, "12", Toast.LENGTH_SHORT).show();
        FirebaseChatMainApp.setChatActivityOpen(false);
    }
}
