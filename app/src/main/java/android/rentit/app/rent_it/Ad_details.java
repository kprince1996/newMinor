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


    public Ad_details(String price,String name,String smalldesp)
    {
        this.price = price;
        this.name=name;
        this.smalldesp=smalldesp;

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

}
