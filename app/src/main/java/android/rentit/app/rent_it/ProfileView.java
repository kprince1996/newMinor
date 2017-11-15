package android.rentit.app.rent_it;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;
import com.google.firebase.storage.UploadTask;
import com.squareup.picasso.Picasso;
import com.theartofdev.edmodo.cropper.CropImage;

import java.util.ArrayList;

import de.hdodenhof.circleimageview.CircleImageView;

/**
 * Created by kprince on 29/10/17.
 */

public class ProfileView extends AppCompatActivity {


    private static final int GALLERY_PIC = 1;
    private TextView editTextName;
    private  TextView editTextEmail;
    private  TextView editTextMobno;
    private Button btn_changeimg;

    private DatabaseReference mUserDatabase;
    private FirebaseUser mCurrentUser;

    private Toolbar mToolbar;
    private StorageReference mImageStorage;
    private CircleImageView imageView;
    private ProgressDialog mProgressDialog;


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile_setting);

        mToolbar = (Toolbar) findViewById(R.id.main_page_toolbar);

        setSupportActionBar(mToolbar);
        getSupportActionBar().setTitle("Rent it Chat");

        mProgressDialog = new ProgressDialog(this);
        mProgressDialog.setTitle("Fetching information");
        mProgressDialog.setMessage("Please wait while we are processing your profile information");
        mProgressDialog.setCanceledOnTouchOutside(false);
        mProgressDialog.show();

        editTextName = (TextView) findViewById(R.id.editText_nameDetails);
        editTextEmail = (TextView) findViewById(R.id.editText_emailDetails);
        editTextMobno = (TextView) findViewById(R.id.editText_contactDetails);
        imageView = (CircleImageView) findViewById(R.id.userProfileImgView);

        btn_changeimg = (Button) findViewById(R.id.settings_change_Image);

        mImageStorage = FirebaseStorage.getInstance().getReference();

        mCurrentUser = FirebaseAuth.getInstance().getCurrentUser();
        String current_uid = mCurrentUser.getUid();

        mUserDatabase = FirebaseDatabase.getInstance().getReference().child("users").child(current_uid);

        mUserDatabase.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                String name = dataSnapshot.child("name").getValue().toString();
                String email = dataSnapshot.child("email").getValue().toString();
                String mobNo = dataSnapshot.child("mobNo").getValue().toString();
                String image = dataSnapshot.child("image").getValue().toString();

                editTextName.setText(name);
                editTextEmail.setText(email);
                if(mobNo.equals("0"))
                {editTextMobno.setText("NOT FOUND!");}
                else{
                    editTextMobno.setText(mobNo);}


                mProgressDialog.dismiss();

                Picasso.with(ProfileView.this).load(image).into(imageView);

            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });

        btn_changeimg.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent gallery = new Intent();
                gallery.setType("image/*");
                gallery.setAction(Intent.ACTION_GET_CONTENT);
                startActivityForResult(Intent.createChooser(gallery,"Select Image"),GALLERY_PIC);
            }
        });
    }



    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if(requestCode==GALLERY_PIC && resultCode == RESULT_OK)
        {
            Uri imageUri = data.getData();
            // start cropping activity for pre-acquired image saved on the device
            CropImage.activity(imageUri)
                    .setAspectRatio(1,1)
                    .start(ProfileView.this);
        }

        if (requestCode == CropImage.CROP_IMAGE_ACTIVITY_REQUEST_CODE) {

            CropImage.ActivityResult result = CropImage.getActivityResult(data);

            if (resultCode == RESULT_OK) {

                mProgressDialog = new ProgressDialog(ProfileView.this);
                mProgressDialog.setTitle("Uploading image");
                mProgressDialog.setMessage("Please wait while we upload and process the image...");
                mProgressDialog.setCanceledOnTouchOutside(false);
                mProgressDialog.show();

                Uri resultUri = result.getUri();

                String current_uid = mCurrentUser.getUid();

                //StorageReference filepath = mImageStorage.child("Profile_images").child(current_uid+".jpeg");
                StorageReference filepath = mImageStorage.child("Profile_images").child(current_uid+".jpeg");

                filepath.putFile(resultUri).addOnCompleteListener(new OnCompleteListener<UploadTask.TaskSnapshot>() {
                    @Override
                    public void onComplete(@NonNull Task<UploadTask.TaskSnapshot> task) {
                        if (task.isSuccessful()) {
                            String download_url = task.getResult().getDownloadUrl().toString();

                            mUserDatabase.child("image").setValue(download_url).addOnCompleteListener(new OnCompleteListener<Void>() {
                                @Override
                                public void onComplete(@NonNull Task<Void> task) {
                                    if(task.isSuccessful())
                                    {
                                        mProgressDialog.dismiss();

                                    }
                                }
                            });

                        } else {
                            Toast.makeText(ProfileView.this, "Error in uploading", Toast.LENGTH_SHORT).show();
                            mProgressDialog.dismiss();
                        }
                    }
                });


            } else if (resultCode == CropImage.CROP_IMAGE_ACTIVITY_RESULT_ERROR_CODE) {
                Exception error = result.getError();
            }
        }


    }


    private boolean isNetworkAvailable() {
        ConnectivityManager connectivityManager
                = (ConnectivityManager) getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo activeNetworkInfo = connectivityManager.getActiveNetworkInfo();
        return activeNetworkInfo != null && activeNetworkInfo.isConnected();
    }




    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        super.onCreateOptionsMenu(menu);

        getMenuInflater().inflate(R.menu.menu_user_listing,menu);

        return  true;
    }


    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        super.onOptionsItemSelected(item);

        if(item.getItemId()==R.id.action_logout)
        {
            FirebaseAuth.getInstance().signOut();
            startActivity(new Intent(ProfileView.this,LoginActivity.class));
        }
        return true;
    }
}
