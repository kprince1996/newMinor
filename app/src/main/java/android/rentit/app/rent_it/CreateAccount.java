package android.rentit.app.rent_it;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.text.TextUtils;
import android.text.method.HideReturnsTransformationMethod;
import android.text.method.PasswordTransformationMethod;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.Toast;


public class CreateAccount extends AppCompatActivity {

    private EditText editTextName,editTextMobileNo,editTextEmail,editTextPassword;
    private Button btnSubmit;
    private CheckBox checkBoxShowPassword;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create_account);


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
                String email = editTextMobileNo.getText().toString().trim();
                String password = editTextPassword.getText().toString().trim();

                if( TextUtils.isEmpty(name) || TextUtils.isEmpty(password) || validatePassword(password)==false || validateMobileNumber(mobno)==false || validateEmailAddress(email)==false)
                {
                    Toast.makeText(CreateAccount.this, "Please fill all details correct", Toast.LENGTH_SHORT).show();
                }
                else
                {

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

        return false;

    }

    private boolean validatePassword(String password)
    {


        return false;
    }

}
