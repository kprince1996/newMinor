package android.rentit.app.rent_it;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;


public class ForgetPassword extends AppCompatActivity {

        private EditText editTextEmail;
        private Button btnContinue;

    public ForgetPassword() {
        // Required empty public constructor
    }

    private boolean validateEmailAddress(String email)
    {
        //todo code to validate email address
        return false;
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

                if(TextUtils.isEmpty(email) || validateEmailAddress(email)==false)
                {
                    Toast.makeText(ForgetPassword.this, "Please enter valid email address", Toast.LENGTH_SHORT).show();

                }
                else
                {
                    //todo code to fetch password
                }
            }
        });
    }


}
