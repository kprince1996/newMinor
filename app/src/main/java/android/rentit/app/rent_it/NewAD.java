package android.rentit.app.rent_it;

import android.content.Intent;
import android.graphics.Bitmap;
import android.net.Uri;
import android.provider.ContactsContract;
import android.provider.MediaStore;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.Toast;

import java.io.IOException;

import static android.rentit.app.rent_it.R.id.pricenego;

public class NewAD extends AppCompatActivity implements View.OnClickListener
{

    //spinner

    Spinner spinner;
    ArrayAdapter<CharSequence> adapter;

    //

    private ImageView Imgcamera,Imgselected,Imgstorage;
    private static final int IMG_REQUEST=777;
    private Bitmap bitmap;

    //for ad submit edit texts

    EditText Ad_c_name,Ad_c_phone,Ad_c_address,Ad_c_email,Ad_c_pincode;
    EditText Ad_princenego,Ad_category,Ad_smalldesp,Ad_compdesp,Ad_price,Ad_motive;
    String s1,s2,s3,s4,s5;
    String s6,s7,s8,s9,s10,s11;


    CheckBox nego,Rentcheck,Sellcheck;

    //
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_new_ad);

        nego=(CheckBox)findViewById(R.id.pricenego);
        Rentcheck=(CheckBox)findViewById(R.id.Rentcheck);

        Sellcheck=(CheckBox)findViewById(R.id.Sellcheck);


        spinner =(Spinner)findViewById(R.id.spinnerCategory);
        adapter = ArrayAdapter.createFromResource(this,R.array.category_array,android.R.layout.simple_spinner_item);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_item);
        spinner.setAdapter(adapter);
        spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {

                // An item was selected. You can retrieve the selected item using
                // parent.getItemAtPosition(pos
                Toast.makeText(getBaseContext(),"selected",Toast.LENGTH_LONG ).show();
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });
        //pic storage

        //camera=(Button)findViewById(R.id.camera);
        Imgselected=(ImageView)findViewById(R.id.selectedpic);
        Imgstorage=(ImageView)findViewById(R.id.storagepic);
        Imgcamera=(ImageView)findViewById(R.id.camerapic);
        Imgstorage.setOnClickListener(this);


        //edit text for submit ad

        Ad_c_name=(EditText)findViewById(R.id.ad_c_name);
        Ad_c_phone=(EditText)findViewById(R.id.ad_c_phone);
        Ad_c_address=(EditText)findViewById(R.id.ad_c_address);
        Ad_c_email=(EditText)findViewById(R.id.ad_c_email);
        Ad_c_pincode=(EditText)findViewById(R.id.ad_c_pincode);

        Ad_smalldesp=(EditText)findViewById(R.id.smalldesp);
        Ad_compdesp=(EditText)findViewById(R.id.comdesp);
        Ad_price=(EditText)findViewById(R.id.price);





        //
    }

    @Override
    public void onClick(View view) {

        switch (view.getId())
        {

            case R.id.storagepic:
                selectImage();
                break;



        }
    }
    private void selectImage()
    {
        Intent intent=new Intent();
        intent.setType("image/*");
        intent.setAction(Intent.ACTION_GET_CONTENT);
        startActivityForResult(intent,IMG_REQUEST);



    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if(requestCode==IMG_REQUEST && resultCode==RESULT_OK && data!=null)
        {
            Uri path= data.getData();


            try {
                bitmap= MediaStore.Images.Media.getBitmap(getContentResolver(),path);
                Imgselected.setImageBitmap(bitmap);
                Imgselected.setVisibility(View.VISIBLE);

                Imgcamera.setVisibility(View.GONE);
                Imgstorage.setVisibility(View.GONE);

            } catch (IOException e) {
                e.printStackTrace();
            }
        }



    }

    public void selectdiffpic(View view)
    {
        Imgselected.setVisibility(View.INVISIBLE);

        Imgcamera.setVisibility(View.VISIBLE);
        Imgstorage.setVisibility(View.VISIBLE);
        Toast.makeText(this, "please select new pic for your Ad", Toast.LENGTH_SHORT).show();


    }


    public void submitad(View view)
    {
        s1=Ad_c_name.getText().toString();
        s2=Ad_c_email.getText().toString();
        s3=Ad_c_address.getText().toString();
        s4=Ad_c_pincode.getText().toString();
        s5=Ad_c_phone.getText().toString();


//        if(nego.isChecked())
//        {
//            String s6="nego";
//        }else{
//            String s6="notnegotiable";
//        }

//        if(Rentcheck.isChecked() && Sellcheck.isChecked())
//        {
//            String s11="both";
//        }
//        else if(Rentcheck.isChecked())
//        {
//            String s11="rent";
//        }
//
//        else  if(Sellcheck.isChecked())
//        {
//            String s11="sell";
//        }
//
//
//       // String s7 = spinner.getSelectedItem().toString();
        s8=Ad_smalldesp.getText().toString();
        s9=Ad_compdesp.getText().toString();
        s10=Ad_price.getText().toString();


         s6="viiiiiii";
        BackgroundSubmitAd log2=new BackgroundSubmitAd(this);
        log2.execute(s1,s2,s3,s4,s5,s8,s9,s10,s6);
        //log2.execute(s1,s2,s3,s4,s5,s6,s8,s9,s10,s11);
    }

}
