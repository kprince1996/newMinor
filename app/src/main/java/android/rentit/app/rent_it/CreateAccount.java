package android.rentit.app.rent_it;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.text.TextUtils;
import android.text.method.HideReturnsTransformationMethod;
import android.text.method.PasswordTransformationMethod;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;


public class CreateAccount extends AppCompatActivity {

    private EditText editTextName,editTextMobileNo,editTextEmail,editTextPassword;
    private Button btnSubmit;
    private CheckBox checkBoxShowPassword;
    private ProgressDialog progressDialog;

    private FirebaseDatabase mFirebaseDatabase;
    private FirebaseAuth mAuth;
    private FirebaseAuth.AuthStateListener mAuthListener;
    private DatabaseReference myRef;

    private static String TAG  = "Create Account";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create_account);


        progressDialog = new ProgressDialog(this);

        editTextName = (EditText) findViewById(R.id.edittext_name);
        editTextMobileNo = (EditText) findViewById(R.id.edittext_contact);
        editTextEmail = (EditText) findViewById(R.id.edittext_emailid);
        editTextPassword = (EditText) findViewById(R.id.edittext_password);
        btnSubmit = (Button) findViewById(R.id.btn_submit_signup_details);
        checkBoxShowPassword = (CheckBox) findViewById(R.id.checkbox_showpassword);

        mAuth = FirebaseAuth.getInstance();


        //validate email & phone

                btnSubmit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String name = editTextName.getText().toString().trim();
                String mobno = editTextMobileNo.getText().toString().trim();
                String email = editTextEmail.getText().toString().trim();
                String password = editTextPassword.getText().toString().trim();

                if(isNetworkAvailable()==false)
                {
                    Toast.makeText(CreateAccount.this, "Check internet connection", Toast.LENGTH_SHORT).show();

                }
                else {
                    if (TextUtils.isEmpty(name) || TextUtils.isEmpty(password) || validatePassword(password) == false || validateMobileNumber(mobno) == false || validateEmailAddress(email) == false) {
                        Toast.makeText(CreateAccount.this, "Please fill all details correct", Toast.LENGTH_SHORT).show();
                    } else {

                        registerUser(name, mobno, email, password);

                    }
                }
            }
        });

        checkBoxShowPassword.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                // checkbox status is changed from uncheck to checked.
                if (!isChecked) {
                    // show password
                    editTextPassword.setTransformationMethod(PasswordTransformationMethod.getInstance());
                } else {
                    // hide password
                    editTextPassword.setTransformationMethod(HideReturnsTransformationMethod.getInstance());
                }
            }
        });


        mAuthListener = new FirebaseAuth.AuthStateListener() {
            @Override
            public void onAuthStateChanged(@NonNull FirebaseAuth firebaseAuth) {
                if(firebaseAuth.getCurrentUser()!=null)
                {
                    Toast.makeText(CreateAccount.this, "signing in..", Toast.LENGTH_SHORT).show();
                }
                else
                {
                    Toast.makeText(CreateAccount.this, "signing out..", Toast.LENGTH_SHORT).show();

                }
            }
        };




    }


    @Override
    public void onStart() {
        super.onStart();

        if(isNetworkAvailable()==false)
        { Toast.makeText(this, "Internet Connection is required", Toast.LENGTH_SHORT).show();
            finish();
        }
        else{
            mAuth.addAuthStateListener(mAuthListener);}
    }

    @Override
    public void onStop() {
        super.onStop();
        if (mAuthListener != null) {
            mAuth.removeAuthStateListener(mAuthListener);
        }
    }



    private boolean validateMobileNumber(String mob)
    {
        int len =mob.length();
        if(len==10)
            return true;
        else
            return false;
    }

    private boolean validateEmailAddress(String email)
    {

        return true;

    }

    private boolean validatePassword(String password)
    {

        return true;
    }

    private void registerUser(final String name, final String mobno, final String emailAddress, String password)
    {
            progressDialog.setTitle("Registering User");
            progressDialog.setMessage("Please wait while we create your account");
            progressDialog.setCanceledOnTouchOutside(false);
            progressDialog.show();

        mAuth.createUserWithEmailAndPassword(emailAddress,password).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {
                if(task.isSuccessful()) {
                    Toast.makeText(CreateAccount.this, "Registration successful. Now login to continue", Toast.LENGTH_SHORT).show();


                    FirebaseUser user = mAuth.getCurrentUser();
                    mFirebaseDatabase = FirebaseDatabase.getInstance();
                    myRef = mFirebaseDatabase.getReference();
                    String userID  = user.getUid();
                    UserInformation userInformation = new UserInformation(name,emailAddress,mobno);
                    myRef.child("users").child(userID).setValue(userInformation);
                    progressDialog.dismiss();

                    Intent intent = new Intent(CreateAccount.this,LoginActivity.class);
                    intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
                    startActivity(intent);

                }
                else {
                    progressDialog.dismiss();
                    Log.e("Error",task.getException().toString());
                    Toast.makeText(CreateAccount.this, "Registration failed", Toast.LENGTH_SHORT).show();
                }

            }
        });

    }

    private boolean isNetworkAvailable() {
        ConnectivityManager connectivityManager
                = (ConnectivityManager) getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo activeNetworkInfo = connectivityManager.getActiveNetworkInfo();
        return activeNetworkInfo != null && activeNetworkInfo.isConnected();
    }


}
