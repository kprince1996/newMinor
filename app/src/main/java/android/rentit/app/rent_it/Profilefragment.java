package android.rentit.app.rent_it;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.auth.UserInfo;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;


public class Profilefragment extends Fragment {

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
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment

        View view =inflater.inflate(R.layout.fragment_profilefragment, container, false);

        mAuth = FirebaseAuth.getInstance();
        mFirebaseDatabase = FirebaseDatabase.getInstance();
        myRef = mFirebaseDatabase.getReference();

        FirebaseUser user = mAuth.getCurrentUser();
        userID = user.getUid();


        editTextName = (EditText) view.findViewById(R.id.editText_nameDetails);
        editTextEmail = (EditText) view.findViewById(R.id.editText_emailDetails);
        editTextMobno = (EditText) view.findViewById(R.id.editText_contactDetails);

        // listView  = (ListView) view.findViewById(R.id.listview_userprofile);

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


                for(DataSnapshot ds:dataSnapshot.getChildren())
                {
                    UserInformation uInfo = new UserInformation();
                    uInfo.setName(ds.child(userID).getValue(UserInformation.class).getName());//set the name
                    uInfo.setEmail(ds.child(userID).getValue(UserInformation.class).getEmail());//set the email
                    uInfo.setMobNo((ds.child(userID).getValue(UserInformation.class).getMobNo()));//set the mob no

                    editTextName.setText(uInfo.getName());
                    editTextEmail.setText(uInfo.getEmail());
                    editTextMobno.setText(uInfo.getMobNo());

                }
                //showData(dataSnapshot);
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });


        return view;
    }


    /*private void showData(DataSnapshot dataSnapshot) {

        for(DataSnapshot ds:dataSnapshot.getChildren())
        {
            UserInformation uInfo = new UserInformation();
            uInfo.setName(ds.child(userID).getValue(UserInformation.class).getName());//set the name
            uInfo.setEmail(ds.child(userID).getValue(UserInformation.class).getEmail());//set the email
            uInfo.setMobNo((ds.child(userID).getValue(UserInformation.class).getMobNo()));//set the mob no

            editTextName.setText(uInfo.getName());
            editTextEmail.setText(uInfo.getEmail());
            editTextMobno.setText(uInfo.getMobNo());

        }

    }*/


    private boolean isNetworkAvailable() {
        ConnectivityManager connectivityManager
                = (ConnectivityManager) this.getContext().getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo activeNetworkInfo = connectivityManager.getActiveNetworkInfo();
        return activeNetworkInfo != null && activeNetworkInfo.isConnected();
    }

    @Override
    public void onStart() {
        super.onStart();

        if(isNetworkAvailable()==false)
        { Toast.makeText(this.getContext(), "Internet Connection is required", Toast.LENGTH_SHORT).show();

        }
        else
        {
        mAuth.addAuthStateListener(mAuthListener);
        }
    }

    @Override
    public void onStop() {
        super.onStop();
        if (mAuthListener != null) {
            mAuth.removeAuthStateListener(mAuthListener);
        }
    }


    private void toastMessage(String msg)
    {
        Toast.makeText(this.getContext(), msg, Toast.LENGTH_SHORT).show();
    }

}
