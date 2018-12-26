package com.studiobethejustice.huhstagram.Share;

import android.content.Context;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.TabLayout;
import android.support.v4.app.ActivityCompat;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;

import com.ittianyu.bottomnavigationviewex.BottomNavigationViewEx;
import com.studiobethejustice.huhstagram.R;
import com.studiobethejustice.huhstagram.Utils.BottomNavigationViewHelper;
import com.studiobethejustice.huhstagram.Utils.Permissions;
import com.studiobethejustice.huhstagram.Utils.SectionPagerAdapter;

public class ShareActivity extends AppCompatActivity {
    private static final String TAG = "ShareActivity";

    //constants
    private static final int ACTIVITY_NUM = 2;
    private static final int VERIFY_PERMISSIONS_REQUEST = 1;

    private ViewPager mViewPager;

    private Context mContext = ShareActivity.this;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_share);
        Log.d(TAG, "onCreate: started.");
        
        if(checkPermissionsArray(Permissions.PERMISSIONS)){

            setupViewPager();

        }else{
            verifyPermissions(Permissions.PERMISSIONS);
        }
    }

    /**
     * return the current tab number
     * 0 = Gallery fragment
     * 1 = PhotoFragment
     * @return
     */
    public int getCurrentTabNumber(){
        return mViewPager.getCurrentItem();
    }

    
    public int getTask(){
        Log.d(TAG, "getTask: Task: " + getIntent().getFlags());
        return getIntent().getFlags();
    }
    /**
     * setup viewpager for manager the tabs
     */
    private void setupViewPager(){
        Log.d(TAG, "setupViewPager: settingup viewPager");
        SectionPagerAdapter adapter = new SectionPagerAdapter(getSupportFragmentManager());
        adapter.addFragment(new GalleryFragment());
        adapter.addFragment(new PhotoFragment());

        mViewPager = findViewById(R.id.container);
        mViewPager.setAdapter(adapter);

        TabLayout tabLayout = findViewById(R.id.tabsBottom);
        tabLayout.setupWithViewPager(mViewPager);

        tabLayout.getTabAt(0).setText(getString(R.string.gallery));
        tabLayout.getTabAt(1).setText(getString(R.string.photo));
    }

    /**
     * Verifiy all the permissions passed to the array
     * @param permissions
     */
    public void verifyPermissions(String[] permissions){
        Log.d(TAG, "verifyPermissions: verifying permissions.");

        ActivityCompat.requestPermissions(
                ShareActivity.this,
                permissions,
                VERIFY_PERMISSIONS_REQUEST
        );
    }

    /**
     * check an array of permissions
     * @param permissions
     * @return
     */
    public boolean checkPermissionsArray(String[] permissions){
        Log.d(TAG, "checkPermissionsArray: checking permissions array.");

        for (int i = 0; i < permissions.length; i++) {
            String check = permissions[i];
            if(!checkPermission(check)){
                return false;
            }
        }
        return true;
    }

    /**
     * Check a single permission is it has been verified
     * @param permission
     * @return
     */
    public boolean checkPermission(String permission){
        Log.d(TAG, "checkPermission: checking permissions" + permission);
        
        int permissionsRequest = ActivityCompat.checkSelfPermission(ShareActivity.this, permission);
        
        if(permissionsRequest != PackageManager.PERMISSION_GRANTED){
            Log.d(TAG, "checkPermission: \n Permission was not granted for: " + permission);
            return false;
        }
        else{
            Log.d(TAG, "checkPermission: \n Permission was granted for: " + permission);
            return true;
        }

    }
}
