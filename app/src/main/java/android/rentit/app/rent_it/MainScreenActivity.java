package android.rentit.app.rent_it;

import android.app.ProgressDialog;
import android.content.Intent;
import android.graphics.drawable.Drawable;
import android.support.annotation.IdRes;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.MenuItem;
import android.widget.TextView;

import com.roughike.bottombar.BottomBar;
import com.roughike.bottombar.OnTabSelectListener;

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
         drawerLayout=(DrawerLayout)findViewById(R.id.mainscreendrawer);
        actionBarDrawerToggle =new ActionBarDrawerToggle(this,drawerLayout,R.string.open,R.string.close);

        drawerLayout.addDrawerListener(actionBarDrawerToggle);
        actionBarDrawerToggle.syncState();
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        //navbar

        //bottom bar
        messageview = (TextView) findViewById(R.id.messageview);
        BottomBar bottomBar = (BottomBar) findViewById(R.id.bottomBar);

        // We're doing nothing with this listener here this time. Check example usage
        // from ThreeTabsActivity on how to use it.
        bottomBar.setOnTabSelectListener(new OnTabSelectListener() {
            @Override
            public void onTabSelected(@IdRes int tabId)
            {

                //messageview.setText(TabMessage.get(tabId, false));
                switch (tabId)
                {

                    case R.id.tab_discover:

                        break;
                    case R.id.tab_chat:
                        break;
                    case R.id.tab_camera:

                        Intent i2 = new Intent(MainScreenActivity.this, NewAD.class);
                        startActivity(i2);


                        break;
                    case R.id.tab_myads:


                        Intent i3=new Intent(MainScreenActivity.this,Testing.class);
                      //  i3.putExtra("json_data",Background_jsonresponce.Json_result);
                        startActivity(i3);

                        break;
                    case R.id.tab_profile:


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

    //navbar
    @Override
    public boolean onOptionsItemSelected(MenuItem item)
    {

if(actionBarDrawerToggle.onOptionsItemSelected(item))
{

    return true;

}

        return super.onOptionsItemSelected(item);
    }
    //



}



