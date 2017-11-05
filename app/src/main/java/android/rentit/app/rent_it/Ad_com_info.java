package android.rentit.app.rent_it;

import android.content.Intent;
import android.graphics.Bitmap;
import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.ImageRequest;
import com.bumptech.glide.RequestManager;
import com.google.firebase.auth.FirebaseAuth;
import com.squareup.picasso.Picasso;

import static android.rentit.app.rent_it.Maindisplay.ctx;

public class Ad_com_info extends AppCompatActivity {

    Button call,chat;
    String price,sdesp,name,email,phone,pincode,motive,nego,comdesp;


    TextView displaypri,dispsdesp,disname,disemail,disphone,dispincode,dispmotive,dispnego,dispcomdesp;
    TextView linkimage;

    String imageurl;
   ImageView imageView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ad_com_info);

        call = (Button) findViewById(R.id.callingbutton);
        chat=(Button)findViewById(R.id.chatseller);

        displaypri = (TextView) findViewById(R.id.displayprice);
        dispsdesp = (TextView) findViewById(R.id.displaysdesp);
        disname = (TextView) findViewById(R.id.displayname);

        disemail = (TextView) findViewById(R.id.disemail);
        disphone = (TextView) findViewById(R.id.disphone);
        dispincode = (TextView) findViewById(R.id.dispincode);
        dispmotive = (TextView) findViewById(R.id.dispmotive);
        dispnego = (TextView) findViewById(R.id.dispnego);
        dispcomdesp = (TextView) findViewById(R.id.dispcomdesp);
         imageView = (ImageView) findViewById(R.id.imageView4);
        // linkimage=(TextView)findViewById(link);


        price = getIntent().getExtras().getString("key1");
        price = " ₹ " + price;
        sdesp = getIntent().getExtras().getString("key2");
        name = getIntent().getExtras().getString("key3");
        email = getIntent().getExtras().getString("key4");
        phone = getIntent().getExtras().getString("key5");

        pincode = getIntent().getExtras().getString("key6");
        motive = getIntent().getExtras().getString("key7");
        nego = getIntent().getExtras().getString("key8");
        if (nego.equals("0")) {
            nego = "NOT negotiable";
        } else {

            nego = "Negotiable";
        }
        comdesp = getIntent().getExtras().getString("key9");
        imageurl = getIntent().getExtras().getString("key10");


        displaypri.setText(price);
        dispsdesp.setText(sdesp);
        disname.setText(name);


        disemail.setText(email);
        disphone.setText(phone);


        dispincode.setText(pincode);
        dispmotive.setText(motive);
        dispnego.setText(nego);
        dispcomdesp.setText(comdesp);
        // linkimage.setText(imageurl);

        Picasso.with(ctx)
                .load(imageurl)
                .into(imageView);
//    }


    }

    public void calling(View view)
    {
        Intent intent =new Intent(Intent.ACTION_DIAL);
        String c="tel:"+phone;
        intent.setData(Uri.parse(c));
        startActivity(intent);
    }
    public void chatting(View view)
    {
        if (FirebaseAuth.getInstance().getCurrentUser() != null) {
            // if logged in redirect the user to user listing activity
            //   UserListingActivity.startActivity(Chatfragment.class);
            Intent i2 = new Intent(Ad_com_info.this, UserListingActivity.class);
            startActivity(i2);
        } else {
            // otherwise redirect the user to login activity
            // LoginActivity1.startActivity(Chatfragment.class);
            Intent i2 = new Intent(Ad_com_info.this,LoginActivity3.class);
            startActivity(i2);
           // Intent i2 = new Intent(Ad_com_info.this,LoginActivity3.class);
           // startActivity(i2);
        }



    }


}
