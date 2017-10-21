package android.rentit.app.rent_it;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by vipul dimri on 17-09-2017.
 */

public class ApiClient
{


  //  private static final String BaseUrl="http://10.0.2.2/imageupload/";

    private static final String BaseUrl="http://muscleuptk.000webhostapp.com/MinorProject/";
private static Retrofit retrofit;

    public static Retrofit getApiClient()
    {
        if(retrofit==null)
        {
            retrofit =new Retrofit.Builder().baseUrl(BaseUrl).
                addConverterFactory(GsonConverterFactory.create()).build();
        }

        return retrofit;
    }

}
