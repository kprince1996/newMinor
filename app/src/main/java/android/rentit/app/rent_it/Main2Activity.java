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
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.google.android.gms.auth.api.Auth;
import com.google.android.gms.auth.api.signin.GoogleSignInAccount;
import com.google.android.gms.auth.api.signin.GoogleSignInOptions;
import com.google.android.gms.auth.api.signin.GoogleSignInResult;
import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.SignInButton;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.common.api.ResultCallback;
import com.google.android.gms.common.api.Status;

import static android.R.attr.name;


public class Main2Activity extends AppCompatActivity implements View.OnClickListener ,GoogleApiClient.OnConnectionFailedListener
{
//    private LinearLayout prof_section;

      private SignInButton Googlesigninbutton;
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
        setContentView(R.layout.activity_main2);





        Googlesigninbutton=(SignInButton) findViewById(R.id.googlesigninbutton);
        Googlesignoutbutton=(Button)findViewById(R.id.logout);


        /*prof_section=(LinearLayout)findViewById(R.id.prof_section);


        name=(TextView)findViewById(R.id.username);
        email=(TextView)findViewById(R.id.emialid);
        prof_pic=(ImageView)findViewById(R.id.propic);



        status=(TextView)findViewById(R.id.status);*/



        // Configure Google Sign In

        GoogleSignInOptions gso = new GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN)
                .requestIdToken(getString(R.string.default_web_client_id))
                .requestEmail()
                .build();

        googleApiClient=new GoogleApiClient.Builder(this)
                .enableAutoManage(this,this)
                .addApi(Auth.GOOGLE_SIGN_IN_API,gso)
                .build();


        Googlesigninbutton.setOnClickListener(this);
        Googlesignoutbutton.setOnClickListener(this);




    }
    @Override
    public void onClick(View view) {


        switch (view.getId())
        {


            case R.id.googlesigninbutton :
                googlesignIn();
                break;

            case R.id.logout:
               googlesignOut();
                break;
      }
    }

    @Override
    public void onConnectionFailed(@NonNull ConnectionResult connectionResult) {

    }

    private void googlesignIn()
    {



       Intent signInIntent = Auth.GoogleSignInApi.getSignInIntent(googleApiClient);
      startActivityForResult(signInIntent, RC_SIGN_IN);
  }


   @Override
   public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        // Result returned from launching the Intent from GoogleSignInApi.getSignInIntent(...);
        if (requestCode == RC_SIGN_IN) {
            GoogleSignInResult result = Auth.GoogleSignInApi.getSignInResultFromIntent(data);
    handleSignInResult(result);
        }
    }


    private void handleSignInResult(GoogleSignInResult result)
   {

        Log.d(TAG,"handleSignInResult:"+result.isSuccess());
        if(result.isSuccess()){
            //success
            GoogleSignInAccount acct=result.getSignInAccount();

            //status.setText("hello ," +acct.getDisplayName());
           //google_name=acct.getDisplayName();     email.setText(acct.getEmail());     google_email=acct.getEmail();
           // imgurl=acct.getPhotoUrl().toString();
           //name.setText();
            //Glide.with(this).load(imgurl).into(prof_pic);

     /* Intent i = new Intent(Main2Activity.this, MainScreen.class);

              i.putExtra("key1",acct.getDisplayName());
              i.putExtra("key2",acct.getEmail());
              i.putExtra("key3",imgurl);
               startActivity(i);*/
               nextstep(true);



        }else{
           // nextstep(false);

        }

   }


   private void googlesignOut()
  {
       Auth.GoogleSignInApi.signOut(googleApiClient).setResultCallback(new ResultCallback<Status>() {
          @Override
            public void onResult(@NonNull Status status) {

               // status.setText("sign out");
               //name.setText("signout");
           }
       });
    }


 private void nextstep(Boolean nextstep)
   {
        if(nextstep==true)
        {
            Intent i = new Intent(Main2Activity.this, MainScreenActivity.class);

           // i.putExtra("key1",google_name);
            //i.putExtra("key2",google_email);
        // i.putExtra("key3",imgurl);
            startActivity(i);
       }


   }



     /////fragments button actions here

    public void createnewaccount_button (View view) {

        Fragment frag;
        if (view == findViewById(R.id.createnewaccount_button)) {

            CreateAccount frag1 = new CreateAccount();
            frag = frag1;

            FragmentManager fm = getSupportFragmentManager();

            FragmentTransaction ft = fm.beginTransaction();
            ft.replace(R.id.fragment_switch, frag);

            ft.addToBackStack("myscreen");
            ft.commit();
            //signin.setVisibility(View.GONE);






        }
    }
    public void forgotpasswordbutton (View view) {

        Fragment frag;
        if (view == findViewById(R.id.forgotpasswordbutton)) {

            ForgetPassword frag1 = new ForgetPassword();
            frag = frag1;

            FragmentManager fm = getSupportFragmentManager();

            FragmentTransaction ft = fm.beginTransaction();
            ft.replace(R.id.fragment_switch, frag);

            ft.addToBackStack("myscreen2");
            ft.commit();
            //signin.setVisibility(View.GONE);






        }
    }


    /////ends


}
