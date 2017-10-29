package android.rentit.app.rent_it;

import android.content.Context;
import android.content.Intent;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;


import com.google.android.gms.common.api.GoogleApiClient;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;



public class LoginActivity extends AppCompatActivity
{

    private Button Googlesignoutbutton;
    private EditText email,pass;
    private ImageView prof_pic;

    private TextView login;
    private String imgurl;
    private String google_name,google_email;

      private GoogleApiClient googleApiClient;
    private static final String TAG="SignInActivity";
    private static final int RC_SIGN_IN=9001;

    private FirebaseAuth mAuth;
    private FirebaseAuth.AuthStateListener mAuthListener;



    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login2);


        mAuth = FirebaseAuth.getInstance();

        email = (EditText) findViewById(R.id.editText_email);
        pass = (EditText) findViewById(R.id.editText_pass);
        login = (TextView) findViewById(R.id.textView_Login);

        mAuthListener = new FirebaseAuth.AuthStateListener() {
            @Override
            public void onAuthStateChanged(@NonNull FirebaseAuth firebaseAuth) {
                if(firebaseAuth.getCurrentUser()!=null)
                {
                    startActivity(new Intent(LoginActivity.this,MainScreenActivity.class));
                }
            }
        };


        login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startSignIn();

            }
        });

    }


    private boolean isNetworkAvailable() {
        ConnectivityManager connectivityManager
                = (ConnectivityManager) getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo activeNetworkInfo = connectivityManager.getActiveNetworkInfo();
        return activeNetworkInfo != null && activeNetworkInfo.isConnected();
    }


    private  void startSignIn()
    {
        String emailAddress = email.getText().toString().trim();
        String password = pass.getText().toString().trim();

        if(isNetworkAvailable()==false)
        {
            Toast.makeText(this, "Check Internet Connection", Toast.LENGTH_SHORT).show();
        }
        else {
            if (TextUtils.isEmpty(emailAddress) || TextUtils.isEmpty(password)) {
                    Toast.makeText(this, "Fill email address and password first", Toast.LENGTH_SHORT).show();
            } else {
                mAuth.signInWithEmailAndPassword(emailAddress, password).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (!(task.isSuccessful())) {
                            Toast.makeText(LoginActivity.this, "Login problem. Please try again", Toast.LENGTH_SHORT).show();

                        }
                    }
                });
            }
        }

    }

    @Override
    public void onStart() {
        super.onStart();

        if(isNetworkAvailable()==false)
        { Toast.makeText(this, "Internet Connection is required", Toast.LENGTH_SHORT).show();
            finish();
        }
        mAuth.addAuthStateListener(mAuthListener);
    }

    @Override
    public void onStop() {
        super.onStop();
        if (mAuthListener != null) {
            mAuth.removeAuthStateListener(mAuthListener);
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


        public void signOut()
        {
            mAuth.signOut();
            Toast.makeText(this, "signing out...", Toast.LENGTH_SHORT).show();

        }
}
