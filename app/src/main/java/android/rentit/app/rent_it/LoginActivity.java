package android.rentit.app.rent_it;

import android.content.Context;
import android.content.Intent;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;


import com.google.android.gms.auth.api.Auth;
import com.google.android.gms.auth.api.signin.GoogleSignInAccount;
import com.google.android.gms.auth.api.signin.GoogleSignInOptions;
import com.google.android.gms.auth.api.signin.GoogleSignInResult;
import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.api.GoogleApiClient;

import com.google.android.gms.common.api.ResultCallback;
import com.google.android.gms.common.api.Status;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthCredential;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.auth.GoogleAuthProvider;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.HashMap;


public class LoginActivity extends BaseActivity implements  GoogleApiClient.OnConnectionFailedListener,
        View.OnClickListener {

    private Button Googlesignoutbutton;
    private EditText email,pass;
    private ImageView prof_pic;

    private TextView login;
    private String imgurl;
    private String google_name,google_email;


    private static final String TAG="SignInActivity";
    private static final int RC_SIGN_IN=9001;

    private FirebaseAuth mAuth;
    private FirebaseAuth.AuthStateListener mAuthListener;

    private GoogleApiClient mGoogleApiClient;

    private static String DEFAULT_IMAGE_PATH =
            "https://firebasestorage.googleapis.com/v0/b/rent-it-322e1.appspot.com/o/Profile_images%2Fimages.png?alt=media&token=39ed2d42-f783-4ab6-a27b-969b3f3d5060";



    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login2);




        email = (EditText) findViewById(R.id.editText_email);
        pass = (EditText) findViewById(R.id.editText_pass);
        login = (TextView) findViewById(R.id.textView_Login);
        findViewById(R.id.sign_in_button).setOnClickListener(this);

        // [START config_signin]
        // Configure Google Sign In
        GoogleSignInOptions gso = new GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN)
                .requestIdToken(getString(R.string.default_web_client_id))
                .requestEmail()
                .build();
        // [END config_signin]

        mGoogleApiClient = new GoogleApiClient.Builder(this)
                .enableAutoManage(this /* FragmentActivity */, this /* OnConnectionFailedListener */)
                .addApi(Auth.GOOGLE_SIGN_IN_API, gso)
                .build();

        mAuth = FirebaseAuth.getInstance();

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
                        else
                        {
                            Intent intent = new Intent(LoginActivity.this,MainScreenActivity.class);
                            intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
                            startActivity(intent);

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

        //FirebaseUser currentUser = mAuth.getCurrentUser();
        //updateUI(currentUser);
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


    // [START onactivityresult]
    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        // Result returned from launching the Intent from GoogleSignInApi.getSignInIntent(...);
        if (requestCode == RC_SIGN_IN) {
            GoogleSignInResult result = Auth.GoogleSignInApi.getSignInResultFromIntent(data);
            if (result.isSuccess()) {
                // Google Sign In was successful, authenticate with Firebase
                GoogleSignInAccount account = result.getSignInAccount();
                firebaseAuthWithGoogle(account);
            } else {
                // Google Sign In failed, update UI appropriately
                // [START_EXCLUDE]
                Toast.makeText(this, "google signin failed", Toast.LENGTH_SHORT).show();
                // [END_EXCLUDE]
            }
        }
    }
    // [END onactivityresult]




    // [START auth_with_google]
    private void firebaseAuthWithGoogle(GoogleSignInAccount acct) {
        Log.d(TAG, "firebaseAuthWithGoogle:" + acct.getId());
        // [START_EXCLUDE silent]
        showProgressDialog();
        // [END_EXCLUDE]

        AuthCredential credential = GoogleAuthProvider.getCredential(acct.getIdToken(), null);
        mAuth.signInWithCredential(credential)
                .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (task.isSuccessful()) {
                            // Sign in success, update UI with the signed-in user's information
                            Log.d(TAG, "signInWithCredential:success");
                            FirebaseUser user = mAuth.getCurrentUser();
                            Toast.makeText(LoginActivity.this, "name : "+user.getDisplayName()+"\nemail"+user.getEmail()+"\nphone"+user.getPhoneNumber() , Toast.LENGTH_SHORT).show();
                            registerUser(user);


                        } else {
                            // If sign in fails, display a message to the user.
                            Log.w(TAG, "signInWithCredential:failure", task.getException());
                            Toast.makeText(LoginActivity.this, "Authentication failed.",
                                    Toast.LENGTH_SHORT).show();
                        }

                        // [START_EXCLUDE]
                        hideProgressDialog();
                        // [END_EXCLUDE]
                    }
                });
    }
    // [END auth_with_google]

    private void registerUser(FirebaseUser user)
    {
                    String userID  = user.getUid();
                    //UserInformation userInformation = new UserInformation(user.getDisplayName(),user.getEmail(),user.getPhoneNumber(),"default");

                    ///if(user.getPhoneNumber()==null)
                       // userInformation.setMobNo(""+0);
                    //FirebaseDatabase mFirebaseDatabase = FirebaseDatabase.getInstance();
                    //DatabaseReference myRef = mFirebaseDatabase.getReference();

                    DatabaseReference mDatabase = FirebaseDatabase.getInstance().getReference().child("users").child(userID);

                    HashMap<String,String> userDetails = new HashMap<>();
                    userDetails.put("name",user.getDisplayName());
                    userDetails.put("email",user.getEmail());
                    userDetails.put("mobNo","0");
                    userDetails.put("image",DEFAULT_IMAGE_PATH);


                    mDatabase.setValue(userDetails).addOnCompleteListener(new OnCompleteListener<Void>() {
                        @Override
                        public void onComplete(@NonNull Task<Void> task) {
                            if(task.isSuccessful())
                            {
                                startActivity(new Intent(LoginActivity.this,MainScreenActivity.class));
                            }
                            else
                                {
                                    Toast.makeText(LoginActivity.this, "Failed...!!!", Toast.LENGTH_SHORT).show();

                                }
                        }
                    });
        //myRef.child("users").child(userID).setValue(userInformation);



        //            startActivity(new Intent(CreateAccount.this,LoginActivity.class));


    }

    // [START signin]
    private void signIn() {
        Intent signInIntent = Auth.GoogleSignInApi.getSignInIntent(mGoogleApiClient);
        startActivityForResult(signInIntent, RC_SIGN_IN);
    }
    // [END signin]

    /*private void updateUI(FirebaseUser user) {
        hideProgressDialog();
        if (user != null) {
            //mStatusTextView.setText(getString(R.string.google_status_fmt, user.getEmail()));
            //mDetailTextView.setText(getString(R.string.firebase_status_fmt, user.getUid()));
            startActivity(new Intent(LoginActivity.this,MainScreenActivity.class));
            Toast.makeText(this, "Welcome!" + user.getDisplayName(), Toast.LENGTH_SHORT).show();
            //findViewById(R.id.sign_in_button).setVisibility(View.GONE);
            //findViewById(R.id.sign_out_and_disconnect).setVisibility(View.VISIBLE);
        }
    }*/

    @Override
    public void onConnectionFailed(@NonNull ConnectionResult connectionResult) {
        // An unresolvable error has occurred and Google APIs (including Sign-In) will not
        // be available.
        Log.d(TAG, "onConnectionFailed:" + connectionResult);
        Toast.makeText(this, "Google Play Services error.", Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onClick(View v) {
        int i = v.getId();
        if (i == R.id.sign_in_button) {
            signIn();
        } //else if (i == R.id.sign_out_button) {
        //signOut();
        //} else if (i == R.id.disconnect_button) {
        //  revokeAccess();
        // }
    }




}
