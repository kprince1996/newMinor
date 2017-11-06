package android.rentit.app.rent_it;

import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import static android.rentit.app.rent_it.R.id.container;



public class MyAds extends Fragment {

    public static MyAds newInstance()
    {
        MyAds myAds =new MyAds();
        return  myAds;
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_my_ads, container, false);
    }



}
