package com.studiobethejustice.huhstagram.Utils;

import android.content.Context;
import android.support.annotation.NonNull;
import android.util.Log;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.studiobethejustice.huhstagram.R;
import com.studiobethejustice.huhstagram.models.User;
import com.studiobethejustice.huhstagram.models.UserAccountSettings;
import com.studiobethejustice.huhstagram.models.UserSettings;

public class FirebaseMethods {

    private static final String TAG = "FirebaseMethods";

    //Firebase
    private FirebaseAuth mAuth;
    private FirebaseAuth.AuthStateListener mAuthListener;
    private FirebaseDatabase mFirebaseDatabase;
    private DatabaseReference myRef;
    private String userId;

    private Context mContext;

    public FirebaseMethods(Context context) {
        this.mAuth = FirebaseAuth.getInstance();
        this.mFirebaseDatabase = FirebaseDatabase.getInstance();
        this.myRef = mFirebaseDatabase.getReference();
        this.mContext = context;

        if (mAuth.getCurrentUser() != null) {
            userId = mAuth.getCurrentUser().getUid();
        }
    }

    public int getImageCount(DataSnapshot dataSnapshot){
        int count = 0;
        for(DataSnapshot ds: dataSnapshot
                .child(mContext.getString(R.string.dbname_user_photos))
                .child(FirebaseAuth.getInstance().getCurrentUser().getUid())
                .getChildren()){
            count++;
        }
        return count;
    }


    /**
     * Update 'user_account_settings' node for the current user
     *
     * @param displayName
     * @param website
     * @param description
     * @param phoneNumber
     */
    public void updateUserAccoutSetting(String displayName, String website, String description, long phoneNumber) {

        Log.d(TAG, "updateUserAccountSetting: updating user account settings.");

        if (displayName != null) {
            myRef.child(mContext.getString(R.string.dbname_user_account_settings))
                    .child(userId)
                    .child(mContext.getString(R.string.field_display_name))
                    .setValue(displayName);
        }

        if (website != null) {
            myRef.child(mContext.getString(R.string.dbname_user_account_settings))
                    .child(userId)
                    .child(mContext.getString(R.string.field_website))
                    .setValue(website);
        }

        if (description != null) {
            myRef.child(mContext.getString(R.string.dbname_user_account_settings))
                    .child(userId)
                    .child(mContext.getString(R.string.field_description))
                    .setValue(description);
        }

        if (phoneNumber != 0) {
            myRef.child(mContext.getString(R.string.dbname_user_account_settings))
                    .child(userId)
                    .child(mContext.getString(R.string.field_phone_number))
                    .setValue(phoneNumber);
        }
    }

    /**
     * update username in the 'users' node and 'user_account-settings' node'
     *
     * @param username
     */
    public void updateUsername(String username) {
        Log.d(TAG, "updateUsername: updating username to: " + username);

        myRef.child(mContext.getString(R.string.dbname_users))
                .child(userId)
                .child(mContext.getString(R.string.field_username))
                .setValue(username);

        myRef.child(mContext.getString(R.string.dbname_user_account_settings))
                .child(userId)
                .child(mContext.getString(R.string.field_username))
                .setValue(username);
    }

    /**
     * update the email in the 'user's' node
     *
     * @param email
     */
    public void updateEmail(String email) {
        Log.d(TAG, "updateEmail: updating email to: " + email);

        myRef.child(mContext.getString(R.string.dbname_users))
                .child(userId)
                .child(mContext.getString(R.string.field_email))
                .setValue(email);
    }

//    public boolean checkIfUsernameExists(String username, DataSnapshot dataSnapshot) {
//        Log.d(TAG, "checkIfUsernameExists: checking if" + username + " already exists.");
//
//        User user = new User();
//
//        for (DataSnapshot ds : dataSnapshot.child(userId).getChildren()) {
//            Log.d(TAG, "checkIfUsernameExists: datasnapshot: " + ds);
//
//            user.setUsername(ds.getValue(User.class).getUsername());
//            Log.d(TAG, "checkIfUsernameExists: getUsername " + user.getUsername());
//
//            if (StringManipulation.expandUsername(user.getUsername()).equals(username)) {
//                Log.d(TAG, "checkIfUsernameExists: FOUND A MATCH" + user.getUsername());
//                return true;
//            }
//        }
//
//        return false;
//    }

    /**
     * Register a new email and password to Firebase Authentication
     *
     * @param email
     * @param password
     * @param username
     */
    public void registerNewEmail(final String email, String password, final String username) {
        mAuth.createUserWithEmailAndPassword(email, password)
                .addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (task.isSuccessful()) {
                            // Sign in success, update UI with the signed-in user's information
                            userId = mAuth.getCurrentUser().getUid();
                            Log.d(TAG, "createUserWithEmail:success" + userId);
                        } else {
                            // If sign in fails, display a message to the user.
                            Log.w(TAG, "createUserWithEmail:failure", task.getException());
                            Toast.makeText(mContext, "Authentication failed.",
                                    Toast.LENGTH_SHORT).show();

                            // 오류메세지에 따라 토스트메시지를 넣어주어야 할듯.
                            // 내생각엔 레지스터넣을때 userid를 체크해 주어야 하지 않을까?
                        }

                        // ...
                    }
                });
    }

    public void addNewUser(String email, String username, String description, String website, String profile_photo) {
        Log.d(TAG, "addNewUser: add new user " + userId);

        User user = new User(userId, 1, StringManipulation.condenseUsername(username), email);

        myRef.child(mContext.getString(R.string.dbname_users)).
                child(userId).
                setValue(user);

        UserAccountSettings settings = new UserAccountSettings(
                description,
                username,
                0,
                0,
                0,
                "",
                StringManipulation.condenseUsername(username),
                website
        );

        myRef.child(mContext.getString(R.string.dbname_user_account_settings)).
                child(userId).
                setValue(settings);

    }

    /**
     * Retrieves the account settings for tec user currently logged in
     * Datagase: user_account_Settings node
     *
     * @param dataSnapshot
     * @return
     */
    public UserSettings getUserSettings(DataSnapshot dataSnapshot) {
        Log.d(TAG, "getUserAccountSettings: retrieving user accoutn settings from firebase");

        UserAccountSettings settings = new UserAccountSettings();
        User user = new User();


        for (DataSnapshot ds : dataSnapshot.getChildren()) {

            // user_account_setting node;
            if (ds.getKey().equals(mContext.getString(R.string.dbname_user_account_settings))) {
                Log.d(TAG, "getUserAccountSettings: datasnapshot: " + ds);

                try {
                    settings.setDisplay_name(
                            ds.child(userId)
                                    .getValue(UserAccountSettings.class)
                                    .getDisplay_name());
                    settings.setUsername(
                            ds.child(userId)
                                    .getValue(UserAccountSettings.class)
                                    .getUsername());
                    settings.setWebsite(
                            ds.child(userId)
                                    .getValue(UserAccountSettings.class)
                                    .getWebsite());
                    settings.setDescription(
                            ds.child(userId)
                                    .getValue(UserAccountSettings.class)
                                    .getDescription());
                    settings.setProfile_photo(
                            ds.child(userId)
                                    .getValue(UserAccountSettings.class)
                                    .getProfile_photo());
                    settings.setPosts(
                            ds.child(userId)
                                    .getValue(UserAccountSettings.class)
                                    .getPosts());
                    settings.setFollowers(
                            ds.child(userId)
                                    .getValue(UserAccountSettings.class)
                                    .getFollowers());
                    settings.setFollowing(
                            ds.child(userId)
                                    .getValue(UserAccountSettings.class)
                                    .getFollowing());

                    Log.d(TAG, "getUserAccoutSettings: retrieve user_Account_Setting info" + settings.toString());
                } catch (NullPointerException e) {
                    Log.d(TAG, "getUserAccountSettings: NullPointerException" + e.getMessage());
                }
            }

            // user node
            if (ds.getKey().equals(mContext.getString(R.string.dbname_users))) {
                Log.d(TAG, "getUserAccountSettings: datasnapshot: " + ds);

                try {

                    user.setUsername(
                            ds.child(userId)
                                    .getValue(User.class)
                                    .getUsername());
                    user.setEmail(
                            ds.child(userId)
                                    .getValue(User.class)
                                    .getEmail());
                    user.setPhone_number(
                            ds.child(userId)
                                    .getValue(User.class)
                                    .getPhone_number());
                    user.setUser_id(
                            ds.child(userId)
                                    .getValue(User.class)
                                    .getUser_id());

                    Log.d(TAG, "getUserAccoutSettings: retrieve users info" + user.toString());
                } catch (NullPointerException e) {
                    Log.d(TAG, "getUserAccountSettings: NullPointerException" + e.getMessage());
                }
            }
        }


        return new UserSettings(user, settings);
    }

}
