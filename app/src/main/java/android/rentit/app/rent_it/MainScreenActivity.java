package android.rentit.app.rent_it;

import android.content.Intent;
import android.rentit.app.rent_it.chat.LoginActivity3;
import android.rentit.app.rent_it.chat.UserListingActivity;
import android.support.annotation.IdRes;
import android.support.design.widget.NavigationView;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.app.Fragment;

import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.MenuItem;
import android.widget.TextView;

import com.google.firebase.auth.FirebaseAuth;
import com.roughike.bottombar.BottomBar;
import com.roughike.bottombar.OnTabSelectListener;

public class MainScreenActivity extends AppCompatActivity {

    //for navigation drawer
    private ActionBarDrawerToggle actionBarDrawerToggle;
    private DrawerLayout drawerLayout;
    private NavigationView nv;
    private BottomBar bottomBar;
    TextView messageview;

    //
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_screen);

        drawerLayout=(DrawerLayout)findViewById(R.id.mainscreendrawer);

        nv = (NavigationView)findViewById(R.id.vipulnav);

//        //for responce from database
//        Background_jsonresponce backgroundTask=new Background_jsonresponce(this);
//        backgroundTask.execute();

        //
        //for navigation drawer
//        //
//        drawerLayout=(DrawerLayout)findViewById(R.id.mainscreendrawer);
//        actionBarDrawerToggle =new ActionBarDrawerToggle(this,drawerLayout,R.string.open,R.string.close);
//
//        drawerLayout.addDrawerListener(actionBarDrawerToggle);
//        actionBarDrawerToggle.syncState();
//        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
//        //navbar

        //bottom bar
      //  messageview = (TextView) findViewById(R.id.messageview);
        BottomBar bottomBar = (BottomBar) findViewById(R.id.bottomBar);

        // We're doing nothing with this listener here this time. Check example usage
        // from ThreeTabsActivity on how to use it.
        bottomBar.setOnTabSelectListener(new OnTabSelectListener()
                                         {
            @Override
            public void onTabSelected(@IdRes int tabId) {

                //messageview.setText(TabMessage.get(tabId, false));
                switch (tabId) {

                    case R.id.tab_discover:
                        Fragment frag22;


                        Maindisplay frag222 = new Maindisplay();
                        frag22 = frag222;

                        FragmentManager fm222 = getSupportFragmentManager();

                        FragmentTransaction ft222 = fm222.beginTransaction();
                        ft222.replace(R.id.fragment_switch2, frag22).commit();


                        break;
                    case R.id.tab_chat:


//                        getFragmentManager().popBackStack();
//
//                        Fragment frag11;
//
//
//                        Chatfragment frag111 = new Chatfragment();
//                        frag11 = frag111;
//
//                        FragmentManager fm111 = getSupportFragmentManager();
//                        FragmentTransaction ft111 = fm111.beginTransaction();
//                        ft111.replace(R.id.fragment_switch2, frag11).commit();


                       /* Fragment frag11;


                        Chatfragment frag111 = new Chatfragment();
                        frag11 = frag111;

                        FragmentManager fm111 = getSupportFragmentManager();

                        FragmentTransaction ft111 = fm111.beginTransaction();
                        ft111.replace(R.id.fragment_switch2, frag11).commit(); */

                        if (FirebaseAuth.getInstance().getCurrentUser() != null) {
                            // if logged in redirect the user to user listing activity
                         //   UserListingActivity.startActivity(Chatfragment.class);
                            Intent i2 = new Intent(MainScreenActivity.this, UserListingActivity.class);
                            startActivity(i2);
                        } else {
                            // otherwise redirect the user to login activity
                           // LoginActivity1.startActivity(Chatfragment.class);
                            Intent i2 = new Intent(MainScreenActivity.this,LoginActivity3.class);
                            startActivity(i2);
                        }


                        break;
                    case R.id.tab_camera:

                        Intent i2 = new Intent(MainScreenActivity.this, NewAD.class);
                        startActivity(i2);


                        break;
//                    case R.id.tab_myads:
//
//                        Fragment frag33;
//                        MyAds frag333 = new MyAds();
//                        frag33 = frag333;
//
//                        FragmentManager fm333 = getSupportFragmentManager();
//
//                        FragmentTransaction ft333 = fm333.beginTransaction();
//                        ft333.replace(R.id.fragment_switch2, frag33).commit();
//
//                        break;
                    case R.id.tab_profile:

                        startActivity(new Intent(getBaseContext(),ProfileView.class));
                        break;

                }


            }
        }

        //



        );
        //

// above code for bottom

//
//



        //loading data
        ///  LoadingData();
        //
        nv.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener()
        {
            @Override
            public boolean onNavigationItemSelected(MenuItem menuItem) {
                switch (menuItem.getItemId())
                {
                 //   case(R.id.Contact):
                      //  Intent in = new Intent(getApplicationContext(),Contactus.class);
                       // startActivity(in);
                }
                return true;
            }
        });
    }





}



