package android.rentit.app.rent_it;

import com.google.gson.annotations.SerializedName;

/**
 * Created by vipul dimri on 17-09-2017.
 */

public class ImageClass {


    @SerializedName("title")
    private String Title;

    @SerializedName("image")
    private String Image;

    @SerializedName("response")
    private String Responce;



//from here new oner
//
//    @SerializedName("name")
//    private String name;
//    @SerializedName("email")
//    private String email;
//    @SerializedName("phone")
//    private String phone;
//    @SerializedName("address")
//    private String address;
//    @SerializedName("pincode")
//    private String pincode;
//    @SerializedName("price")
//    private String prive;
//    @SerializedName("completedesp")
//    private String completedesp;
//    @SerializedName("shortdesp")
//    private String shortdesp;
//    @SerializedName("category")
//    private String category;
//    @SerializedName("status")
//    private String status;
//    @SerializedName("negotiable")
//    private String negotiable;
//    @SerializedName("motive")
//    private String motive;





    public String getResponce() {
        return Responce;
    }
}
