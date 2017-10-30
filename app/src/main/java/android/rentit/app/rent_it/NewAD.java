package android.rentit.app.rent_it;

import android.content.Intent;
import android.content.pm.PackageManager;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.net.Uri;
import android.provider.ContactsContract;
import android.provider.MediaStore;
import android.support.annotation.NonNull;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v4.text.ICUCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Base64;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.Toast;

import net.gotev.uploadservice.MultipartUploadRequest;
import net.gotev.uploadservice.UploadNotificationConfig;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.IOException;
import java.util.UUID;


import static android.R.attr.data;
import static android.icu.lang.UCharacter.GraphemeClusterBreak.V;
import static android.rentit.app.rent_it.R.id.pricenego;

public class NewAD extends AppCompatActivity implements View.OnClickListener
{

    //spinner


    Spinner spinner;
    ArrayAdapter<CharSequence> adapter;

    //
    static int imagekhase;
    private ImageView Imgcamera,Imgselected,Imgstorage;
    private static final int IMG_REQUEST=777;

    //for ad submit edit texts

    EditText Ad_c_name,Ad_c_phone,Ad_c_address,Ad_c_email,Ad_c_pincode;
    EditText Ad_princenego,Ad_category,Ad_smalldesp,Ad_compdesp,Ad_price,Ad_motive;
    String s1,s2,s3,s4,s5;
    String s6,s7,s8,s9,s10,s11;
    String image;


    CheckBox nego,Rentcheck,Sellcheck;

    //

    File file;
    Uri fileUri;
    //Image request code
    private int PICK_IMAGE_REQUEST = 1;
    private static final int CAM_REQUEST = 1313;

    //storage permission code
    private static final int STORAGE_PERMISSION_CODE = 123;

    //Bitmap to get image from gallery
    private Bitmap bitmap;

    //Uri to store the image uri
    private Uri filePath,filepath2;
    public static final String UPLOAD_URL = "http://muscleuptk.000webhostapp.com/MinorProject/upload.php";


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_new_ad);


       requestStoragePermission();

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
        Imgcamera.setOnClickListener(this);


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
                imagekhase=1;
                showFileChooser();
                break;

            case R.id.camerapic:
                 imagekhase=0;
                Intent intent=new Intent(MediaStore.ACTION_IMAGE_CAPTURE);

                intent.putExtra(MediaStore.EXTRA_OUTPUT, fileUri);
                startActivityForResult(intent,CAM_REQUEST);

                break;



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
        s8=Ad_smalldesp.getText().toString();
        s9=Ad_compdesp.getText().toString();
        s10=Ad_price.getText().toString();
        s6="1";

        //getting name for the image

        //getting the actual path of the image
        String path = "";
        if(imagekhase==1)
        {


            path=getPath(filePath);
        }
        else if(imagekhase==0){
           path= getRealPathFromURI(filepath2);

        }



        //Uploading code
        try {
            String uploadId = UUID.randomUUID().toString();

            //Creating a multi part request
            new MultipartUploadRequest(this, uploadId, UPLOAD_URL)
                    .addFileToUpload(path, "image") //Adding file
                    .addParameter("name", s1)
                    .addParameter("email", s2)
                    .addParameter("phone", s5)
                    .addParameter("address", s3)
                    .addParameter("pincode", s4)
                    .addParameter("nego", s6)
                    .addParameter("smalldesp", s8)
                    .addParameter("compdesp", s9)

                    .addParameter("price", s10)


                    //Adding text parameter to the request
                    .setNotificationConfig(new UploadNotificationConfig())
                    .setMaxRetries(20)
                    .startUpload(); //Starting the upload

        } catch (Exception exc) {
            Toast.makeText(this, exc.getMessage(), Toast.LENGTH_SHORT).show();
        }







       /*
        //uploadimage();
        Toast.makeText(this,image+"vipul", Toast.LENGTH_SHORT).show();
        BackgroundSubmitAd log2=new BackgroundSubmitAd(this);
        log2.execute(s1,s2,s3,s4,s5,s8,s9,s10,s6,image);
        //log2.execute();
        //log2.execute(s1,s2,s3,s4,s5,s6,s8,s9,s10,s11);
        */
    }







    //method to show file chooser
    private void showFileChooser() {
        Intent intent = new Intent();
        intent.setType("image/*");
        intent.setAction(Intent.ACTION_GET_CONTENT);
        startActivityForResult(Intent.createChooser(intent, "Select Picture"), PICK_IMAGE_REQUEST);
    }

    //handling the image chooser activity result
    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (requestCode == PICK_IMAGE_REQUEST && resultCode == RESULT_OK && data != null && data.getData() != null)
        {
            filePath = data.getData();
            try {
                bitmap = MediaStore.Images.Media.getBitmap(getContentResolver(), filePath);
                Imgselected.setImageBitmap(bitmap);
                Imgselected.setVisibility(View.VISIBLE);
                Imgstorage.setVisibility(View.GONE);
                Imgcamera.setVisibility(View.GONE);


            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        if(requestCode==CAM_REQUEST  )
        {
            filepath2 = data.getData();
            bitmap=(Bitmap)data.getExtras().get("data");
            Imgselected.setImageBitmap(bitmap);
            Imgselected.setVisibility(View.VISIBLE);
            Imgstorage.setVisibility(View.GONE);
            Imgcamera.setVisibility(View.GONE);
        }


        }


    //method to get the file path from uri
    public String getPath(Uri uri) {
        Cursor cursor = getContentResolver().query(uri, null, null, null, null);
        cursor.moveToFirst();
        String document_id = cursor.getString(0);
        document_id = document_id.substring(document_id.lastIndexOf(":") + 1);
        cursor.close();

        cursor = getContentResolver().query(
                android.provider.MediaStore.Images.Media.EXTERNAL_CONTENT_URI,
                null, MediaStore.Images.Media._ID + " = ? ", new String[]{document_id}, null);
        cursor.moveToFirst();
        String path = cursor.getString(cursor.getColumnIndex(MediaStore.Images.Media.DATA));
        cursor.close();

        return path;
    }



    //Requesting permission
    private void requestStoragePermission() {
        if (ContextCompat.checkSelfPermission(this, Manifest.permission.READ_EXTERNAL_STORAGE) == PackageManager.PERMISSION_GRANTED)
            return;

        if (ActivityCompat.shouldShowRequestPermissionRationale(this, Manifest.permission.READ_EXTERNAL_STORAGE)) {
            //If the user has denied the permission previously your code will come to this block
            //Here you can explain why you need this permission
            //Explain here why you need this permission
        }
        //And finally ask for the permission
        ActivityCompat.requestPermissions(this, new String[]{Manifest.permission.READ_EXTERNAL_STORAGE}, STORAGE_PERMISSION_CODE);
    }

    public String getRealPathFromURI(Uri contentUri)
    {
        try
        {
            String[] proj = {MediaStore.Images.Media.DATA};
            Cursor cursor = managedQuery(contentUri, proj, null, null, null);
            int column_index = cursor.getColumnIndexOrThrow(MediaStore.Images.Media.DATA);
            cursor.moveToFirst();
            return cursor.getString(column_index);
        }
        catch (Exception e)
        {
            return contentUri.getPath();
        }
    }
}
