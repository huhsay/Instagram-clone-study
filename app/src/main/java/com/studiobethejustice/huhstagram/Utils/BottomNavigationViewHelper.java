package com.studiobethejustice.huhstagram.Utils;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.util.Log;
import android.view.MenuItem;

import com.ittianyu.bottomnavigationviewex.BottomNavigationViewEx;
import com.studiobethejustice.huhstagram.Home.HomeActivity;
import com.studiobethejustice.huhstagram.Likes.LikesActivity;
import com.studiobethejustice.huhstagram.Profile.ProfileActivity;
import com.studiobethejustice.huhstagram.R;
import com.studiobethejustice.huhstagram.Search.SearchActivity;
import com.studiobethejustice.huhstagram.Share.ShareActivity;

public class BottomNavigationViewHelper {

    private static final String TAG = "BottomNavigationViewHel";

    public static void setUpBottomNavigationView(BottomNavigationViewEx bottomNavigationView){
        Log.d(TAG, "setUpBottomNavigationView: Setting up BottomNavigationView");
        bottomNavigationView.enableAnimation(false);
        bottomNavigationView.enableShiftingMode(false);
        bottomNavigationView.enableShiftingMode(false);
        bottomNavigationView.setTextVisibility(false);
    }

    public static void enableNavigation(final Context context, final Activity callingActivity, BottomNavigationViewEx view){
        view.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                switch(item.getItemId()){
                    case R.id.ic_house:
                        Intent intent1 = new Intent(context, HomeActivity.class); // ACTIVITY_NUM = 0;
                        context.startActivity(intent1);
                        callingActivity.overridePendingTransition(R.anim.fade_in, R.anim.fade_out);
                        break;
                    case R.id.ic_search:
                        Intent intent2 = new Intent(context, SearchActivity.class); // ACTIVITY_NUM = 1;
                        context.startActivity(intent2);
                        callingActivity.overridePendingTransition(R.anim.fade_in, R.anim.fade_out);
                        break;
                    case R.id.ic_circle:
                        Intent intent3 = new Intent(context, ShareActivity.class); // ACTIVITY_NUM = 2;
                        context.startActivity(intent3);
                        callingActivity.overridePendingTransition(R.anim.slide_up, R.anim.slide_down);

                        break;
                    case R.id.ic_alert:
                        Intent intent4 = new Intent(context, LikesActivity.class); // ACTIVITY_NUM = 3;
                        context.startActivity(intent4);
                        callingActivity.overridePendingTransition(R.anim.fade_in, R.anim.fade_out);
                        break;
                    case R.id.ic_android:
                        Intent intent5 = new Intent(context, ProfileActivity.class); // ACTIVITY_NUM = 4;
                        context.startActivity(intent5);
                        callingActivity.overridePendingTransition(R.anim.fade_in, R.anim.fade_out);
                        break;
                }
                return false;
            }
        });
    }
}
