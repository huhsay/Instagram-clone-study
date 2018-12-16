package com.studiobethejustice.huhstagram.Profile;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.widget.ImageView;
import android.widget.ProgressBar;

import com.studiobethejustice.huhstagram.R;

public class ProfileActivity extends AppCompatActivity {
    private static final String TAG = "ProfileActivity";
    private static final int ACTIVITY_NUM = 4;
    private static final int NUM_GRID_COLUMNS = 3;

    private Context mContext = ProfileActivity.this;
    private ProgressBar mProgressBar;
    private ImageView profilePhoto;


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile);
        Log.d(TAG, "onCreate: started.");

        init();

//        setupBottomNavigationView();
//        setupToolbar();
//        setupActivityWidgets();
//        setProfileImage();
//
//        tempGridSetup();
    }

    private void init() {
        Log.d(TAG, "init: inflating " + getString(R.string.profile_fragment));

        ProfileFragment fragment = new ProfileFragment();
        android.support.v4.app.FragmentTransaction transaction = ProfileActivity.this.getSupportFragmentManager().beginTransaction();
        transaction.replace(R.id.container, fragment);
        transaction.addToBackStack(getString(R.string.profile_fragment));
        transaction.commit();
    }

//    private void tempGridSetup(){
//        ArrayList<String> imgURLs = new ArrayList<>();
//        imgURLs.add("https://www.androidcentral.com/sites/androidcentral.com/files/styles/xlarge_wm_brw/public/article_images/2018/10/lg-v40-thinq-black-back-standing.jpg?itok=EoOByKfO");
//        imgURLs.add("https://www.androidcentral.com/sites/androidcentral.com/files/styles/xlarge_wm_brw/public/article_images/2018/10/lg-v40-thinq-black-back-standing.jpg?itok=EoOByKfO");
//        imgURLs.add("https://www.androidcentral.com/sites/androidcentral.com/files/styles/xlarge_wm_brw/public/article_images/2018/10/lg-v40-thinq-black-back-standing.jpg?itok=EoOByKfO");
//        imgURLs.add("https://www.androidcentral.com/sites/androidcentral.com/files/styles/xlarge_wm_brw/public/article_images/2018/10/lg-v40-thinq-black-back-standing.jpg?itok=EoOByKfO");
//        imgURLs.add("https://www.androidcentral.com/sites/androidcentral.com/files/styles/xlarge_wm_brw/public/article_images/2018/10/lg-v40-thinq-black-back-standing.jpg?itok=EoOByKfO");
//
//        setupImageGrid(imgURLs);
//    }

//    private void setupImageGrid(ArrayList<String> imgURLs){
//        GridView gridView = findViewById(R.id.gridView);
//
//        int gridWidth = getResources().getDisplayMetrics().widthPixels;
//        int imageWidth = gridWidth/ NUM_GRID_COLUMNS;
//        gridView.setColumnWidth(imageWidth);
//
//        GridImageAdapter adapter = new GridImageAdapter(mContext, R.layout.layout_grid_imageview,"", imgURLs);
//        gridView.setAdapter(adapter);
//    }
//
//    private void setProfileImage(){
//        Log.d(TAG, "setProfileImage: setting profile photo.");
//        String imgURL = "developer.android.com/static/images/home/jetpack-promo.svg?hl=ko";
//        UniversalImageLoader.setImage(imgURL, profilePhoto, mProgressBar, "https://");
//
//    }
//
//    private void setupActivityWidgets(){
//        mProgressBar = findViewById(R.id.profileProgressBar);
//        mProgressBar.setVisibility(View.GONE);
//        profilePhoto = findViewById(R.id.profile_photo);
//    }
//
//    private void setupToolbar(){
//        Toolbar toolbar = findViewById(R.id.profileToolBar);
//        setSupportActionBar(toolbar);
//
//        ImageView profileMenu = findViewById(R.id.profileMenu);
//        profileMenu.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                Log.d(TAG, "onClick: navigating to account settings. ");
//                Intent intent = new Intent(mContext, AccountSettingsActivity.class);
//                startActivity(intent);
//            }
//        });
//    }
//
}

