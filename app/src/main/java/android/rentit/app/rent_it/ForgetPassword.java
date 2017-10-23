package android.rentit.app.rent_it;

import android.content.Context;
import android.content.Intent;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;
import android.text.TextUtils;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;


public class ForgetPassword extends AppCompatActivity {

        private EditText editTextEmail;
        private Button btnContinue;



    private boolean validateEmailAddress(String email)
    {
        //todo code to validate email address
        return true;
    }

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_forget_password);

        editTextEmail = (EditText) findViewById(R.id.forgetpassword_emailid);
        btnContinue = (Button) findViewById(R.id.btn_contiue_forget_pwd);

        btnContinue.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String email = editTextEmail.getText().toString().trim();

                if(isNetworkAvailable()==false)
                {
                    Toast.makeText(ForgetPassword.this, "Check internet connection", Toast.LENGTH_SHORT).show();
                }
                else
                    {
                    if (TextUtils.isEmpty(email) || validateEmailAddress(email) == false) {
                        Toast.makeText(ForgetPassword.this, "Please enter valid email address", Toast.LENGTH_SHORT).show();

                    } else {

                        sendResetLink(email);
                    }
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

    @Override
    protected void onStart() {
        super.onStart();

        if(isNetworkAvailable()==false)
        {
            Toast.makeText(this, "Internet connection is required", Toast.LENGTH_SHORT).show();
            finish();
        }

    }

    private void sendResetLink(String emailAddress)
    {

        FirebaseAuth auth = FirebaseAuth.getInstance();

        auth.sendPasswordResetEmail(emailAddress)
                .addOnCompleteListener(new OnCompleteListener<Void>() {
                    @Override
                    public void onComplete(@NonNull Task<Void> task) {
                        if (task.isSuccessful()) {
                            Toast.makeText(ForgetPassword.this, "Email sent to entered email address", Toast.LENGTH_SHORT).show();
                            startActivity(new Intent(ForgetPassword.this,LoginActivity.class));
                        }
                        else
                        {
                            Toast.makeText(ForgetPassword.this, "Email address does not exist or wrong", Toast.LENGTH_SHORT).show();
                        }
                    }
                });
    }

}
