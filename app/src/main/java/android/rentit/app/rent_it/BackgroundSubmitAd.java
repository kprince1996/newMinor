package android.rentit.app.rent_it;

import android.app.ProgressDialog;
import android.content.Context;
import android.graphics.Bitmap;
import android.os.AsyncTask;
import android.widget.Toast;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;

/**
 * Created by vipul dimri on 18-09-2017.
 */

public class BackgroundSubmitAd extends AsyncTask<String,Void,String>
{
    Context ctx;


    static String HHH="";
    static String register_responce;
    ProgressDialog loading;

    BackgroundSubmitAd(Context ctx)

    {
        this.ctx=ctx;

    }



    @Override
    protected void onPreExecute()
    {

        loading = ProgressDialog.show(ctx, "Posting AD", "Please wait...",true,true);

    }
    @Override
    protected String doInBackground(String... params)
    {

        String login_url="http://muscleuptk.000webhostapp.com/MinorProject/insert.php";


        String ad_c_name =params[0];
        String ad_c_email=params[1];
        String ad_c_address=params[2];
        String ad_c_pincode =params[3];
        String ad_c_phone=params[4];

//        String ad_c_privenego=params[5];
//      //  String ad_c_category=params[6];
        String ad_c_smalldesp=params[5];
        String ad_c_compdesp=params[6];
        String ad_c_price=params[7];
        String ad_c_privenego=params[8];
        String image=params[9];








        try {

            URL url = new URL(login_url);
            HttpURLConnection httpURLConnection = (HttpURLConnection) url.openConnection();
            httpURLConnection.setRequestMethod("POST");
            httpURLConnection.setDoOutput(true);

            httpURLConnection.setDoInput(true);
            OutputStream OS = httpURLConnection.getOutputStream();
            BufferedWriter bufferedWriter = new BufferedWriter(new OutputStreamWriter(OS, "UTF-8"));


            String data =
                    URLEncoder.encode("name", "UTF-8") + "=" + URLEncoder.encode(ad_c_name, "UTF-8")
                            +"&"+
                            URLEncoder.encode("email", "UTF-8") + "=" + URLEncoder.encode(ad_c_email, "UTF-8")
                            +"&"+
                            URLEncoder.encode("phone", "UTF-8") + "=" + URLEncoder.encode(ad_c_address, "UTF-8")
                            +"&"+
                            URLEncoder.encode("address", "UTF-8") + "=" + URLEncoder.encode(ad_c_pincode, "UTF-8")
                            +"&"+
                            URLEncoder.encode("pincode", "UTF-8") + "=" + URLEncoder.encode(ad_c_phone, "UTF-8")
                            +"&"+

//                            URLEncoder.encode("cate", "UTF-8") + "=" + URLEncoder.encode(ad_c_category, "UTF-8")
//                            +"&"+
                            URLEncoder.encode("smalldesp", "UTF-8") + "=" + URLEncoder.encode(ad_c_smalldesp, "UTF-8")
                            +"&"+
                            URLEncoder.encode("compdesp", "UTF-8") + "=" + URLEncoder.encode(ad_c_compdesp, "UTF-8")
                            +"&"+
                            URLEncoder.encode("price", "UTF-8") + "=" + URLEncoder.encode(ad_c_price, "UTF-8")
                            +"&"+
                            URLEncoder.encode("nego", "UTF-8") + "=" + URLEncoder.encode(ad_c_privenego, "UTF-8")

                             +"&"+
                           URLEncoder.encode("motive", "UTF-8") + "=" + URLEncoder.encode(image, "UTF-8")
                    ;

            bufferedWriter.write(data);
            bufferedWriter.flush();
            bufferedWriter.close();
            OS.close();

            // now responce getting above sending data
            InputStream inputStream = httpURLConnection.getInputStream();
            BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(inputStream, "iso-8859-1"));


            String line = "";

            while ((line = bufferedReader.readLine()) != null) {
                register_responce = line;
            }
            bufferedReader.close();
            inputStream.close();
            httpURLConnection.disconnect();




        } catch (Exception e)
        {


        }






        return register_responce;

    }

    @Override
    protected void onProgressUpdate(Void... values)
    {
        super.onProgressUpdate(values);

    }
    @Override
    protected void onPostExecute(String result)
    {


        //   if (result.equals(register_responce))
        // {
        if (result==null)
        {
            Toast.makeText(ctx,"NO INTERNET ACCESS",Toast.LENGTH_LONG).show();

        }
        else {

            Toast.makeText(ctx, result, Toast.LENGTH_LONG).show();


        }


        loading.dismiss();

        //result="";


    }



}
