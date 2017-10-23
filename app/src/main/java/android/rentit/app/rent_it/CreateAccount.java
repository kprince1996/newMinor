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


public class CreateAccount extends AppCompatActivity {

    private EditText editTextName,editTextMobileNo,editTextEmail,editTextPassword;
    private Button btnSubmit;
    private CheckBox checkBoxShowPassword;
    private ProgressDialog progressDialog;

    private FirebaseAuth mAuth;
    private FirebaseAuth.AuthStateListener mAuthListener;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create_account);

        mAuth = FirebaseAuth.getInstance();

        progressDialog = new ProgressDialog(this);

        editTextName = (EditText) findViewById(R.id.edittext_name);
        editTextMobileNo = (EditText) findViewById(R.id.edittext_contact);
        editTextEmail = (EditText) findViewById(R.id.edittext_emailid);
        editTextPassword = (EditText) findViewById(R.id.edittext_password);
        btnSubmit = (Button) findViewById(R.id.btn_submit_signup_details);
        checkBoxShowPassword = (CheckBox) findViewById(R.id.checkbox_showpassword);

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

    private void registerUser(String name,String mobno,String emailAddress,String password)
    {
            progressDialog.setMessage("Registering User...");
            progressDialog.show();

        mAuth.createUserWithEmailAndPassword(emailAddress,password).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {
                if(task.isSuccessful()) {
                    Toast.makeText(CreateAccount.this, "Registration successful. Now login to continue", Toast.LENGTH_SHORT).show();
                    startActivity(new Intent(CreateAccount.this,LoginActivity.class));
                }
                else {
                    Log.e("Error",task.getException().toString());
                    Toast.makeText(CreateAccount.this, "Registration failed", Toast.LENGTH_SHORT).show();
                }
                progressDialog.dismiss();
            }
        });

    }

    private boolean isNetworkAvailable() {
        ConnectivityManager connectivityManager
                = (ConnectivityManager) getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo activeNetworkInfo = connectivityManager.getActiveNetworkInfo();
        return activeNetworkInfo != null && activeNetworkInfo.isConnected();
    }

    @Override
    protected void onStart() {
        super.onStart();
        if(isNetworkAvailable()==false)
        {
            Toast.makeText(this, "Internet connection is required", Toast.LENGTH_SHORT).show();
            finish();
        }
    }
}
