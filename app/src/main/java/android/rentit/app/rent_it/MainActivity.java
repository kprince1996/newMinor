package android.rentit.app.rent_it;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;

//import static android.rentit.app.rent_it.R.id.view;

public class MainActivity extends AppCompatActivity {

    private Button btnCreateAccount;
    private Button btnSkipSignIn;
    private Button btnLogin;

    private FirebaseAuth mAuth;
    private FirebaseAuth.AuthStateListener mAuthListener;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mAuth = FirebaseAuth.getInstance();

        mAuthListener = new FirebaseAuth.AuthStateListener() {
            @Override
            public void onAuthStateChanged(@NonNull FirebaseAuth firebaseAuth) {
                if(firebaseAuth.getCurrentUser()!=null)
                {
                    startActivity(new Intent(MainActivity.this,MainScreenActivity.class));
                }
            }
        };


        btnSkipSignIn  = (Button)findViewById(R.id.btn_skip_signin);
        btnLogin = (Button) findViewById(R.id.btn_signin);
        btnCreateAccount = (Button)findViewById(R.id.createaccount_button);



        btnSkipSignIn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(MainActivity.this,  MainScreenActivity.class);
          //    Intent i = new Intent(MainActivity.this,  ProfileView.class);
                startActivity(i);
            }
        });

        btnLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
          Intent i = new Intent(MainActivity.this, LoginActivity.class);
             //   Intent i = new Intent(MainActivity.this,  GoogleSignInActivity.class);
                startActivity(i);
            }
        });
    }

    public  void openit(View view)
    {
        Intent i = new Intent(MainActivity.this,CreateAccount.class);
        startActivity(i);

    }

    @Override
    public void onStart() {
        super.onStart();
        mAuth.addAuthStateListener(mAuthListener);
    }

    @Override
    public void onStop() {
        super.onStop();
        if (mAuthListener != null) {
            mAuth.removeAuthStateListener(mAuthListener);
        }
    }

}
