package android.rentit.app.rent_it;

import android.app.ProgressDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ListView;

import org.json.JSONArray;
import org.json.JSONObject;

import java.util.ArrayList;

import static android.R.attr.description;
import static android.rentit.app.rent_it.R.id.Ad_list2;


public class Testing extends AppCompatActivity
{


    String Json_string;
    JSONObject jsonObject;
    JSONArray jsonArray;

    String price;
    String smalldesp;
    String name;
    String email,phone,pincode,address,compdesp,status,cat,negotiable,motive;

    String text="testting";
    int count=0;
    @Override
    protected void onCreate(Bundle savedInstanceState) {


        Background_jsonresponce backgroundTask = new Background_jsonresponce(this,this);
        backgroundTask.execute();
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_testing);


    }


    public static void helper(Testing obj)
    {
        obj.updategui();
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

                com.add(new Ad_details(price,name,smalldesp,email,phone,pincode,motive,negotiable,compdesp));



                ListView listView = (ListView) findViewById(Ad_list2);
                Adapter adapter = new Adapter(this, com);
                listView.setAdapter(adapter);


                count++;


            }
        }
        catch (Exception e)
        {
            e.printStackTrace();
        }




    }



}

