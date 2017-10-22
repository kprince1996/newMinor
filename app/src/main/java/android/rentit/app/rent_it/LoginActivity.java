package android.rentit.app.rent_it;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;

import com.google.android.gms.common.api.GoogleApiClient;


public class LoginActivity extends AppCompatActivity
{

      private Button Googlesignoutbutton;
    //    private  TextView name,email;
//    private  ImageView prof_pic;
//    private TextView status;
//    private String imgurl;
//    private String google_name,google_email;
//
      private GoogleApiClient googleApiClient;
    private static final String TAG="SignInActivity";
    private static final int RC_SIGN_IN=9001;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login2);

    }






 private void nextstep(Boolean nextstep)
   {
        if(nextstep==true)
        {
            Intent i = new Intent(LoginActivity.this, MainScreenActivity.class);

           // i.putExtra("key1",google_name);
            //i.putExtra("key2",google_email);
        // i.putExtra("key3",imgurl);
            startActivity(i);
       }


   }


     /////fragments button actions here



    public void createnewaccount_button (View view) {
        Intent createAccountIntent = new Intent(LoginActivity.this,CreateAccount.class);
        startActivity(createAccountIntent);
    }

    public void forgotpasswordbutton (View view) {
        Intent forgetPasswordIntent = new Intent(LoginActivity.this,ForgetPassword.class);
        startActivity(forgetPasswordIntent);
        }




}
