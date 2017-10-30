package android.rentit.app.rent_it;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

/**
 * Created by kprince on 29/10/17.
 */

public class ProfileView extends AppCompatActivity {

    private FirebaseDatabase mFirebaseDatabase;
    private FirebaseAuth mAuth;
    private FirebaseAuth.AuthStateListener mAuthListener;
    private DatabaseReference myRef;
    private String userID;

    private ListView listView;

    private EditText editTextName;
    private  EditText editTextEmail;
    private  EditText editTextMobno;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.fragment_profilefragment);


        mAuth = FirebaseAuth.getInstance();
        mFirebaseDatabase = FirebaseDatabase.getInstance();
        myRef = mFirebaseDatabase.getReference();

        editTextName = (EditText) findViewById(R.id.editText_nameDetails);
        editTextEmail = (EditText) findViewById(R.id.editText_emailDetails);
        editTextMobno = (EditText) findViewById(R.id.editText_contactDetails);


        FirebaseUser user = mAuth.getCurrentUser();
        userID = user.getUid();

        //listView  = (ListView) findViewById(R.id.listview_userprofile);

        mAuthListener = new FirebaseAuth.AuthStateListener() {
            @Override
            public void onAuthStateChanged(@NonNull FirebaseAuth firebaseAuth) {
                if(firebaseAuth.getCurrentUser()!=null)
                {
                    toastMessage("signed in..");
                }
                else
                {
                    toastMessage("Signing out...");

                }
            }
        };


        myRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                showData(dataSnapshot);
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });


    }

    private void showData(DataSnapshot dataSnapshot) {

        for(DataSnapshot ds:dataSnapshot.getChildren())
        {
            UserInformation uInfo = new UserInformation();
            uInfo.setName(ds.child(userID).getValue(UserInformation.class).getName());//set the name
            uInfo.setEmail(ds.child(userID).getValue(UserInformation.class).getEmail());//set the email
            uInfo.setMobNo((ds.child(userID).getValue(UserInformation.class).getMobNo()));//set the mob no

           // ArrayList<String> arrayList = new ArrayList<>();

            //arrayList.add(uInfo.getName());
            //arrayList.add(uInfo.getEmail());
            //arrayList.add(uInfo.getMobNo());

              //  ArrayAdapter adapter = new ArrayAdapter(this,R.layout.fragment_profilefragment,arrayList);
              //listView.setAdapter(adapter);

            editTextName.setText(uInfo.getName());
            editTextEmail.setText(uInfo.getEmail());
            editTextMobno.setText(uInfo.getMobNo());
        }

    }


    private boolean isNetworkAvailable() {
        ConnectivityManager connectivityManager
                = (ConnectivityManager) getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo activeNetworkInfo = connectivityManager.getActiveNetworkInfo();
        return activeNetworkInfo != null && activeNetworkInfo.isConnected();
    }

    @Override
    public void onStart() {
        super.onStart();

        if(isNetworkAvailable()==false)
        { Toast.makeText(this, "Internet Connection is required", Toast.LENGTH_SHORT).show();

        }
        else
        {
  //          mAuth.addAuthStateListener(mAuthListener);
        }
    }

    @Override
    public void onStop() {
        super.onStop();
        if (mAuthListener != null) {
    //        mAuth.removeAuthStateListener(mAuthListener);
        }
    }


    private void toastMessage(String msg)
    {
        Toast.makeText(this, msg, Toast.LENGTH_SHORT).show();
    }

}
