package android.rentit.app.rent_it;

import android.app.AlertDialog;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.os.AsyncTask;
import android.widget.Toast;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

/**
 * Created by vipul dimri on 26-07-2017.
 */

public class Background_jsonresponce extends AsyncTask<Void,Void,String>
{


    String jsonurl;
    String line;
    ProgressDialog progressDialog;
    AlertDialog alertDialog;
    Context ctx;
    Testing obj;
    //
    Maindisplay obj2;
    String sourece;

    static String Json_result;


//    Background_jsonresponce(Context ctx,Maindisplay obj)
//    {
//        this.ctx=ctx;
//        this.obj=obj;
//
//    }

//    Background_jsonresponce(Context ctx,Testing obj)
//{
//    this.ctx=ctx;
//    this.obj=obj;
//
//}

    Background_jsonresponce(Context ctx,Maindisplay obj2,String source)
    {
        this.ctx=ctx;
        this.obj2=obj2;
        this.sourece=source;

    }

    @Override
    protected void onPreExecute()
    {
      //  jsonurl="http://muscleuptk.000webhostapp.com/MinorProject/jsoncompletetable_complaint_info.php";
        //jsonurl="http://muscleuptk.000webhostapp.com/MinorProject/priceass.php";
        jsonurl=sourece;
        progressDialog=new ProgressDialog(ctx);
        progressDialog.setMessage("getting data");
        progressDialog.show();

    }

    @Override
    protected String doInBackground(Void... params) {



        try{
            URL url =  new URL(jsonurl);
            HttpURLConnection httpURLConnection=(HttpURLConnection)url.openConnection();


            InputStream inputStream=httpURLConnection.getInputStream();
            BufferedReader bufferedReader=new BufferedReader(new InputStreamReader(inputStream,"iso-8859-1"));


            StringBuilder builder=new StringBuilder();

            while ((line=bufferedReader.readLine())!=null)
            {


                builder.append(line+"/n");
            }
            bufferedReader.close();
            inputStream.close();
            httpURLConnection.disconnect();

            return builder.toString().trim();

        }
        catch (Exception e)
        {
            e.printStackTrace();
        }






        return null;
    }






    @Override
    protected void onProgressUpdate(Void... values)
    {
        super.onProgressUpdate(values);

    }
    @Override
    protected void onPostExecute(String result)
    {


//        if(result=="")
//        {
//            Toast.makeText(ctx, "NO INTERNET ACCES PLZ CONNECT TO INTERNET", Toast.LENGTH_LONG).show();
//
//
//        }else
//            {

        Toast.makeText(ctx, result, Toast.LENGTH_LONG).show();

       // Toast.makeText(ctx, "now click on get complaints lists", Toast.LENGTH_LONG).show();
            Json_result = result;

            progressDialog.dismiss();

            //Testing.helper(obj);


             Maindisplay.helper(obj2);
     //   }



//
////        Intent i3=new Intent(MainScreenActivity.this,Testing.class);
//        Intent i3=new Intent(new MainScreenActivity(),Testing.class);
//
//        //  i3.putExtra("json_data",Background_jsonresponce.Json_result);
////        startActivity(i3);





    }




}
