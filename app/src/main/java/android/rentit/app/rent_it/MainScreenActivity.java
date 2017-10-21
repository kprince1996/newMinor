package android.rentit.app.rent_it;

import android.app.ProgressDialog;
import android.content.Intent;
import android.graphics.drawable.Drawable;
import android.support.annotation.IdRes;
import android.support.design.widget.NavigationView;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.MenuItem;
import android.widget.TextView;

import com.roughike.bottombar.BottomBar;
import com.roughike.bottombar.OnTabSelectListener;

import static android.rentit.app.rent_it.R.id.view;
import static android.rentit.app.rent_it.R.string.close;
import static android.rentit.app.rent_it.R.string.open;
import static android.rentit.app.rent_it.R.string.title_activity_main_screen;

public class MainScreenActivity extends AppCompatActivity {

    //for navigation drawer
    private ActionBarDrawerToggle actionBarDrawerToggle;
    private DrawerLayout drawerLayout;
    private NavigationView nv;
    private BottomBar bottomBar;
    //
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_screen);


        //bottom bar
        BottomBar bottomBar = (BottomBar) findViewById(R.id.bottomBar);

        // We're doing nothing with this listener here this time. Check example usage
        // from ThreeTabsActivity on how to use it.
        bottomBar.setOnTabSelectListener(new OnTabSelectListener() {
            @Override
            public void onTabSelected(@IdRes int tabId)
            {

                switch (tabId)
                {

                    case R.id.tab_discover:
                        Fragment frag;


                        Maindisplay frag1 = new Maindisplay();
                        frag = frag1;

                        FragmentManager fm = getSupportFragmentManager();

                        FragmentTransaction ft = fm.beginTransaction();
                        ft.replace(R.id.fragment_switch2, frag);

                        ft.addToBackStack("myscreen2");
                        ft.commit();
                        break;
                    case R.id.tab_chat:

                        Fragment frag22;


                        Chatfragment frag2 = new Chatfragment();
                        frag22 = frag2;

                        FragmentManager fm2= getSupportFragmentManager();

                        FragmentTransaction ft2 = fm2.beginTransaction();
                        ft2.replace(R.id.fragment_switch2, frag22);

                        ft2.addToBackStack("myscreen2");
                        ft2.commit();
                        break;

                    case R.id.tab_camera:

                        Intent i2 = new Intent(MainScreenActivity.this, NewAD.class);
                        startActivity(i2);


                        break;
                    case R.id.tab_myads:


                        Intent i3=new Intent(MainScreenActivity.this,Testing.class);
                        //i3.putExtra("json_data",Background_jsonresponce.Json_result);
                        startActivity(i3);





                        break;
                    case R.id.tab_profile:

                        Fragment frag44;


                        Profilefragment frag4 = new Profilefragment();
                        frag44 = frag4;

                        FragmentManager fm4= getSupportFragmentManager();

                        FragmentTransaction ft4 = fm4.beginTransaction();
                        ft4.replace(R.id.fragment_switch2, frag44);

                        ft4.addToBackStack("myscreen2");
                        ft4.commit();

                        break;


                }


            }
        });
      //

//// above code for bottom



}}



