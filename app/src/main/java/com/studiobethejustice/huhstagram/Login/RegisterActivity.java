package com.studiobethejustice.huhstagram.Login;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.studiobethejustice.huhstagram.R;

public class RegisterActivity extends AppCompatActivity {

    //Firebase
    private FirebaseAuth mAuth;
    private FirebaseAuth.AuthStateListener mAuthListener;

    private Context mContext;
    private String email, username, password;
    private EditText mEmail, mPassword, mUsername;
    private TextView loadingPleaseWait;
    private Button btnRegister;
    private ProgressBar mProgressBar;

    private static final String TAG = "RegisterActivity";

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);
        Log.d(TAG, "onCreate: started.");

        initWidgets();
        setupFirebaseAuth();
    }

    /**
     * Initialize the activity widgets
     */

    private void initWidgets() {
        Log.d(TAG, "initWidgets: Initializing Widgets.");

        setContentView(R.layout.activity_login);
        mProgressBar = findViewById(R.id.progressBar);
        //loadingPleaseWait = findViewById(R.id.pleaseWait);
        mEmail = findViewById(R.id.input_email);
        mPassword = findViewById(R.id.input_password);
        mUsername = findViewById(R.id.input_username);
        mContext = RegisterActivity.this;

        mProgressBar.setVisibility(View.GONE);
    }

    /**
     * setup the firebase auto object
     */

    private void setupFirebaseAuth() {
        Log.d(TAG, "setupFirebaseAuth: setting up firebase auth.");
        mAuth = FirebaseAuth.getInstance();
        mAuthListener = new FirebaseAuth.AuthStateListener() {
            @Override
            public void onAuthStateChanged(@NonNull FirebaseAuth firebaseAuth) {
                FirebaseUser user = firebaseAuth.getCurrentUser();

                if (user != null) {
                    Log.d(TAG, "onAuthStateChanged: signed_in" + user.getUid());
                } else {
                    Log.d(TAG, "onAuthStateChanged: signed_out");
                }
            }
        };
    }

    @Override
    public void onStart() {
        super.onStart();
        mAuth.addAuthStateListener(mAuthListener);
    }

    @Override
    protected void onStop() {
        super.onStop();
        if (mAuthListener != null) {
            mAuth.removeAuthStateListener(mAuthListener);
        }
    }

    private boolean isStringNull(String string) {
        Log.d(TAG, "isStringNull: checking string if null");

        if (string.equals("")) {
            return true;
        } else {
            return false;
        }
    }

}
