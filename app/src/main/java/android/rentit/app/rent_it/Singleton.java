package android.rentit.app.rent_it;

import android.app.DownloadManager;
import android.content.Context;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.toolbox.Volley;

/**
 * Created by vipul dimri on 29-10-2017.
 */

public class Singleton
{

    private static Singleton mImstance;
    private RequestQueue requestQueue;
    private static  Context mctx;

    public  Singleton(Context context)
    {
               mctx=context;
        requestQueue=mRequestQueue();


    }

    public RequestQueue mRequestQueue()
    {
        if(requestQueue ==null)
        {
            requestQueue= Volley.newRequestQueue(mctx.getApplicationContext());
        }
        return  requestQueue;
    }
public static synchronized  Singleton getmImstance(Context context)
{

    if(mImstance==null)
    {

        mImstance=new Singleton(context);
    }

    return mImstance;

}

    public <T> void addRequestque(Request<T> request)
    {
            requestQueue.add(request);
    }


}
