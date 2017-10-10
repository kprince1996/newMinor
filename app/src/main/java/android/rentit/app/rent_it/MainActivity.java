package android.rentit.app.rent_it;

import android.content.Intent;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity {

    private Button btnCreateAccount;
    private Button btnSkipSignIn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        btnCreateAccount = (Button)findViewById(R.id.btn_createaccount);
        btnSkipSignIn  = (Button)findViewById(R.id.btn_skip_signin);

        btnCreateAccount.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {


            }
        });


        btnSkipSignIn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent i = new Intent(MainActivity.this,  MainScreenActivity.class);
                startActivity(i);
            }
        });
    }


    public  void click (View view)
    {
        Intent i = new Intent(MainActivity.this, LoginActivity.class);
        startActivity(i);
    }



}
