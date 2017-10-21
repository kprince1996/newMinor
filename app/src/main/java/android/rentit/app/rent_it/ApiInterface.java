package android.rentit.app.rent_it;

import com.google.gson.annotations.SerializedName;

import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.POST;

/**
 * Created by vipul dimri on 17-09-2017.
 */

public interface ApiInterface
{

            @FormUrlEncoded
            @POST("upload.php")
            Call<ImageClass> uploadImage(@Field("title") String title, @Field("image") String image);
//            Call<ImageClass> uploadImage(@Field("title") String title,
//
//                                    @Field("image") String image,
//                                    @Field("name") String name,
//                                    @Field("email") String email,
//                                    @Field("phone") String phone,
//                                    @Field("address") String address,
//                                    @Field("pincode") String pincode,
//                                    @Field("price") String price,
//                                    @Field("completedesp") String completedesp,
//                                    @Field("shortdesp") String shortdesp,
//                                    @Field("category") String category,
//                                    @Field("status") String status,
//                                    @Field("negotiable") String negotiable,
//                                    @Field("motive") String motive);

}
