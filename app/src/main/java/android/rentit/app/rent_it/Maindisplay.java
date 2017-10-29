package android.rentit.app.rent_it;

import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.ImageLoader;
import com.android.volley.toolbox.ImageRequest;
import com.android.volley.toolbox.NetworkImageView;

import org.json.JSONArray;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.Collections;


import static android.R.attr.data;
import static android.R.attr.fingerprintAuthDrawable;
import static android.rentit.app.rent_it.R.id.Ad_list2;
import static android.rentit.app.rent_it.R.id.fill;
import static android.rentit.app.rent_it.R.id.list_item;


public class Maindisplay extends Fragment  //implements View.OnClickListener
{


    Button ass;
    Button desc;

    TextView t;
    String Json_string;
    JSONObject jsonObject;
    JSONArray jsonArray;

    String price;
    String smalldesp;
    String name;
    static Context ctx;
    static Maindisplay thishai;
    String email,phone,pincode,address,compdesp,status,cat,negotiable,motive;
    String imagelink;


    Bitmap image;
    private ImageLoader imageLoader;
    private NetworkImageView imageView;
ImageView hai;


    String text="testting";
    int count=0;
    ListView listView;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState)
    {


//
//        ass=(Button)getView().findViewById(R.id.asss);
//        desc=(Button)getView().findViewById(R.id.descc);
//
//        ass.setOnClickListener(this);
//        desc.setOnClickListener(this);
        ctx=getContext();
       // thishai=this;
        String source="http://muscleuptk.000webhostapp.com/MinorProject/jsoncompletetable_complaint_info.php";
        Background_jsonresponce backgroundTask = new Background_jsonresponce(ctx,this,source);
        backgroundTask.execute();

        return inflater.inflate(R.layout.fragment_maindisplay, container, false);

    }



    public static void helper(Maindisplay obj2)
    {
        obj2.updategui();
    }

    public void updategui()
    {
        Json_string= Background_jsonresponce.Json_result;
        final ArrayList<Ad_details> com = new ArrayList<>();

        try{

            jsonObject=new JSONObject(Json_string);
            jsonArray=jsonObject.getJSONArray("server_responce");


            while(count<jsonArray.length())
            {


                JSONObject ob = jsonArray.getJSONObject(count);


                price = ob.getString("price");
                smalldesp=ob.getString("shortdesp");
                name=ob.getString("name");
                email=ob.getString("email");
                phone=ob.getString("phone");
                pincode=ob.getString("pincode");
                address=ob.getString("address");
                compdesp=ob.getString("completedesp");
                status=ob.getString("status");
                cat=ob.getString("category");
                negotiable=ob.getString("negotiable");
                motive=ob.getString("motive");
                imagelink=ob.getString("path");



//image ka



//
//                ImageRequest imageRequest= new ImageRequest(imagelink, new Response.Listener<Bitmap>() {
//                    @Override
//                    public void onResponse(Bitmap response) {
//
//                        image=response;
//                    }
//                }, 0, 0, ImageView.ScaleType.CENTER_CROP, null, new Response.ErrorListener() {
//                    @Override
//                    public void onErrorResponse(VolleyError error) {
//
//
//                    }
//                });
//
//                Singleton.getmImstance(getActivity()).addRequestque(imageRequest);
//


                /////



                com.add(new Ad_details(price,name,smalldesp,email,phone,pincode,motive,negotiable,compdesp,imagelink));



                listView = (ListView) getView().findViewById(R.id.mainlist);
                Adapter adapter = new Adapter(ctx, com);
                listView.setAdapter(adapter);


                count++;


            }
        }
        catch (Exception e)
        {
            e.printStackTrace();
        }




    }






//        public static void assclick(View view)
//        {
//
//            String source="http://muscleuptk.000webhostapp.com/MinorProject/jsoncompletetable_complaint_info.php";
//
//
//            backgroundTask.execute();

          //  }
//    public void pricedesc(View view)
//    {
//
//
//        String source="http://muscleuptk.000webhostapp.com/MinorProject/pricedesc.php";
//        Background_jsonresponce backgroundTask = new Background_jsonresponce(ctx,this,source);
//        backgroundTask.execute();
//

//    }


//    @Override
//    public void onClick(View view) {
//
//
//        switch (view.getId())
//        {
//
//            case R.id.asss:
//                Toast.makeText(getActivity(), "vipul",
//                        Toast.LENGTH_LONG).show();
//                break;
//
//            case R.id.descc:
//
//                break;
//
//
//
//        }
//    }
}




