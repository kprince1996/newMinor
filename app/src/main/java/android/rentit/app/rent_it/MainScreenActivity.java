package android.rentit.app.rent_it;

import android.app.ProgressDialog;
import android.content.Intent;
import android.graphics.drawable.Drawable;
import android.support.annotation.IdRes;
import android.support.design.widget.NavigationView;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;

import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.roughike.bottombar.BottomBar;
import com.roughike.bottombar.OnTabSelectListener;

import static android.rentit.app.rent_it.Maindisplay.ctx;
import static android.rentit.app.rent_it.Maindisplay.thishai;
import static android.rentit.app.rent_it.R.string.close;
import static android.rentit.app.rent_it.R.string.open;
import static android.rentit.app.rent_it.R.string.title_activity_main_screen;

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



//        //for responce from database
//        Background_jsonresponce backgroundTask=new Background_jsonresponce(this);
//        backgroundTask.execute();

        //
        //for navigation drawer
        //
       // drawerLayout = (DrawerLayout) findViewById(R.id.mainscreendrawer);
       // actionBarDrawerToggle = new ActionBarDrawerToggle(this, drawerLayout,R.string.open, R.string.close);

        //drawerLayout.addDrawerListener(actionBarDrawerToggle);
        //actionBarDrawerToggle.syncState();
        //getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        //navbar

        //bottom bar
      //  messageview = (TextView) findViewById(R.id.messageview);
        BottomBar bottomBar = (BottomBar) findViewById(R.id.bottomBar);

        // We're doing nothing with this listener here this time. Check example usage
        // from ThreeTabsActivity on how to use it.
        bottomBar.setOnTabSelectListener(new OnTabSelectListener() {
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


                        Fragment frag11;


                        Chatfragment frag111 = new Chatfragment();
                        frag11 = frag111;

                        FragmentManager fm111 = getSupportFragmentManager();

                        FragmentTransaction ft111 = fm111.beginTransaction();
                        ft111.replace(R.id.fragment_switch2, frag11).commit();

                        break;
                    case R.id.tab_camera:

                        Intent i2 = new Intent(MainScreenActivity.this, NewAD.class);
                        startActivity(i2);


                        break;
                    case R.id.tab_myads:

                        Fragment frag33;
                        MyAds frag333 = new MyAds();
                        frag33 = frag333;

                        FragmentManager fm333 = getSupportFragmentManager();

                        FragmentTransaction ft333 = fm333.beginTransaction();
                        ft333.replace(R.id.fragment_switch2, frag33).commit();

                        break;
                    case R.id.tab_profile:
                        Fragment frag;
                        Profilefragment frag1 = new Profilefragment();
                        frag = frag1;

                        FragmentManager fm = getSupportFragmentManager();

                        FragmentTransaction ft = fm.beginTransaction();
                        ft.replace(R.id.fragment_switch2, frag).commit();

                        // ft.addToBackStack(null).commit();

                      //  startActivity(new Intent(getBaseContext(),ProfileView.class));
                        break;

                }


            }
        });
        //

// above code for bottom

        //nv = (NavigationView)findViewById(R.id.mainscreendrawer);
        /*
        nv.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(MenuItem menuItem) {
                switch (menuItem.getItemId()) {
                    case(R.id.action_settings):
                        Intent in = new Intent(getApplicationContext(),MainActivity.class);
                        startActivity(in);
                }
                return true;
            }
        });*/


        //loading data
        ///  LoadingData();
        //
    }




}



