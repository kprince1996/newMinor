package android.rentit.app.rent_it;

import static android.R.attr.description;

/**
 * Created by vipul dimri on 18-09-2017.
 */

public class Ad_details
{
    private String price;
    private String smalldesp;
    private String name;
    private String email,phone,pincode,address,compdesp,status,cat,negotiable,motive;
    private String imagelink;


    public Ad_details(String price,String name,String smalldesp,String email,String phone,String pincode,String motive,String negotiable,String compdesp,String imagelink)
    {
        this.price = price;
        this.name=name;
        this.smalldesp=smalldesp;
        this.email=email;
        this.phone=phone;
        this.pincode=pincode;
        this.motive=motive;
        this.negotiable=negotiable;
        this.compdesp=compdesp;
        this.imagelink=imagelink;

    }


    public String getprice()
    {
        return price;
    }

    public String getname()
    {
        return name;
    }
    public String getsdes()
    {
        return smalldesp;
    }
    public String getemail()
    {
        return email;
    }

    public String getphone()
    {
        return phone;
    }
    public String getPincode()
    {
        return pincode;
    }

    public String getMotive()
    {
        return motive;
    }
    public String getNegotiable()
    {
        return negotiable;
    }
    public String getCompdesp()
    {
        return compdesp;
    }

    public String getImagelink()
    {
        return imagelink;
    }








}
