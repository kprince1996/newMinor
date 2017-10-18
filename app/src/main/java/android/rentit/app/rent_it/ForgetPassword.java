package android.rentit.app.rent_it;

import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;


public class ForgetPassword extends Fragment {

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
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_forget_password, container, false);

        editTextEmail = (EditText) view.findViewById(R.id.forgetpassword_emailid);
        btnContinue = (Button) view.findViewById(R.id.btn_contiue_forget_pwd);

        btnContinue.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String email = editTextEmail.getText().toString().trim();

                if(TextUtils.isEmpty(email) || validateEmailAddress(email)==false)
                {
                    Toast.makeText(getActivity(), "Please enter valid email address", Toast.LENGTH_SHORT).show();
                }
                else
                {
                    //todo code to fetch password
                }
            }
        });

        return view;
    }




}
