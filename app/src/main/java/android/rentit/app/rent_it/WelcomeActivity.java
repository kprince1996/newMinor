package android.rentit.app.rent_it;

import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.text.Html;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;

/**
 * Created by kprince on 10/10/17.
 */

public class WelcomeActivity extends AppCompatActivity {

    private ViewPager viewPager;
    private LinearLayout dotsLayout;
    private Button btnskip;
    private Button btnnext;
    protected static int[] layouts;
    private TextView[] dots;
    private MyViewPagerAdapater myViewPagerAdapater;


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        Boolean isFirstRun = getSharedPreferences("PERFERENCE",MODE_PRIVATE).getBoolean("isfirstrun",true);

        if(isFirstRun)
        {
            // Making notification bar transparent
            if (Build.VERSION.SDK_INT >= 21) {
                getWindow().getDecorView().setSystemUiVisibility(View.SYSTEM_UI_FLAG_LAYOUT_STABLE | View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN);
            }

            setContentView(R.layout.activity_welcome);

            viewPager = (ViewPager) findViewById(R.id.viewpager);
            dotsLayout = (LinearLayout) findViewById(R.id.layout_dots);
            btnnext = (Button) findViewById(R.id.btn_next);
            btnskip = (Button) findViewById(R.id.btn_skip);

            layouts = new int[]{
                    R.layout.welcome_slide1,
                    R.layout.welcome_slide2,
                    R.layout.welcome_slide3,
                    R.layout.welcome_slide4
            };


            // adding bottom dots
            addBottomDots(0);

            // making notification bar transparent
            changeStatusBarColor();

            myViewPagerAdapater = new MyViewPagerAdapater();
            viewPager.setAdapter(myViewPagerAdapater);

            viewPager.addOnPageChangeListener(viewPageChangeListener);



            btnskip.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    launchHomeScreen();
                }
            });

            btnnext.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    int current = getItem(+1);

                    if(current <layouts.length)
                        viewPager.setCurrentItem(current);
                    else {
                        launchHomeScreen();
                    }

                }
            });

        }

        else
        {

            launchHomeScreen();
        }

    }

    ViewPager.OnPageChangeListener viewPageChangeListener = new ViewPager.OnPageChangeListener() {
        @Override
        public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

        }

        @Override
        public void onPageSelected(int position) {
            addBottomDots(position);

            if(position == layouts.length-1)
            {
                btnnext.setText(R.string.start);
                btnskip.setVisibility(View.GONE);
            }
            else
            {
                btnnext.setText(R.string.next);
                btnskip.setVisibility(View.VISIBLE);
            }

        }

        @Override
        public void onPageScrollStateChanged(int state) {

        }
    };



    private void launchHomeScreen()
    {
        getSharedPreferences("PERFERENCE",MODE_PRIVATE).edit().putBoolean("isfirstrun",false).commit();
        startActivity(new Intent(WelcomeActivity.this,MainActivity.class));
        finish();
    }



    private int getItem(int i)
    {
        return viewPager.getCurrentItem() + i;
    }

    private void addBottomDots(int currentPage)
    {
        dots = new TextView[layouts.length];

        int[] colorsActive = getResources().getIntArray(R.array.array_dot_active);
        int[] colorsInactive = getResources().getIntArray(R.array.array_dot_inactive);

        dotsLayout.removeAllViews();

        for (int i = 0; i < dots.length; i++) {
            dots[i] = new TextView(this);
            dots[i].setText(Html.fromHtml("&#8226;"));
            dots[i].setTextSize(35);
            dots[i].setTextColor(colorsInactive[currentPage]);
            dotsLayout.addView(dots[i]);
        }

        if (dots.length > 0)
            dots[currentPage].setTextColor(colorsActive[currentPage]);

    }

    private void changeStatusBarColor()
    {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            Window window = getWindow();
            window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS);
            window.setStatusBarColor(Color.TRANSPARENT);
        }

    }


    public class MyViewPagerAdapater extends PagerAdapter {

        private LayoutInflater layoutInflater;

        @Override
        public Object instantiateItem(ViewGroup container, int position) {
            layoutInflater = (LayoutInflater) getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            View view = (View) layoutInflater.inflate(layouts[position],container,false);
            container.addView(view);
            return view;
        }

        @Override
        public int getCount() {
            return layouts.length;
        }

        @Override
        public boolean isViewFromObject(View view, Object object) {
            return view==object;
        }

        @Override
        public void destroyItem(ViewGroup container, int position, Object object) {
            View view =(View) object;
            container.removeView(view);
        }
    }

}




