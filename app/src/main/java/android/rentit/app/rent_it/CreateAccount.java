package android.rentit.app.rent_it;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.text.TextUtils;
import android.text.method.HideReturnsTransformationMethod;
import android.text.method.PasswordTransformationMethod;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.Toast;


/**
 * A simple {@link Fragment} subclass.
 */
public class CreateAccount extends Fragment {


    private EditText editTextName,editTextMobileNo,editTextEmail,editTextPassword;
    private Button btnSubmit;
    private CheckBox checkBoxShowPassword;

    public CreateAccount() {
        // Required empty public constructor
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
        //todo code to validate email address
        return false;

    }

    private boolean validatePassword(String password)
    {

        //todo code to validate password
        return false;
    }




    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view =  inflater.inflate(R.layout.fragment_create_account, container, false);

        editTextName = (EditText) view.findViewById(R.id.edittext_name);
        editTextMobileNo = (EditText) view.findViewById(R.id.edittext_contact);
        editTextEmail = (EditText) view.findViewById(R.id.edittext_emailid);
        editTextPassword = (EditText) view.findViewById(R.id.edittext_password);
        btnSubmit = (Button) view.findViewById(R.id.btn_submit_signup_details);
        checkBoxShowPassword = (CheckBox) view.findViewById(R.id.checkbox_showpassword);

        //validate email & phone

        btnSubmit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String name = editTextName.getText().toString().trim();
                String mobno = editTextMobileNo.getText().toString().trim();
                String email = editTextMobileNo.getText().toString().trim();
                String password = editTextPassword.getText().toString().trim();

                if(TextUtils.isEmpty(name) || TextUtils.isEmpty(password) || validatePassword(password)==false || validateMobileNumber(mobno)==false || validateEmailAddress(email)==false)
                {
                    Toast.makeText(getActivity(), "Please fill all details correct", Toast.LENGTH_SHORT).show();
                }
                else
                {
                    //todo code to save details to database
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

        return  view;
    }





}
