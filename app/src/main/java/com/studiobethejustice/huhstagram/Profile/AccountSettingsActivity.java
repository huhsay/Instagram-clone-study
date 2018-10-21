package com.studiobethejustice.huhstagram.Profile;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.RelativeLayout;

import com.studiobethejustice.huhstagram.R;
import com.studiobethejustice.huhstagram.Utils.SectionsStatePagerAdapter;

import java.util.ArrayList;

public class AccountSettingsActivity extends AppCompatActivity {

    private static final String TAG = "AccountSettingsActivity";

    private Context mContext;

    private SectionsStatePagerAdapter pagerAdapter;
    private ViewPager mViewPager;
    private RelativeLayout mRelativeLayout;


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_accountsettings);
        mContext = AccountSettingsActivity.this;
        Log.d(TAG, "onCreate: started.");
        mViewPager = findViewById(R.id.container);
        mRelativeLayout = findViewById(R.id.relLayout1);

        setUpSettingList();
        setUpFragments();
        
        //setup the backarrow for navigating back to "profileActivity"
        ImageView backArrow = findViewById(R.id.backArrow);
        backArrow.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.d(TAG, "onClick: navigating back to 'ProfileActivity'");
                finish();
            }
        });
    }

    private void setUpFragments(){
        pagerAdapter = new SectionsStatePagerAdapter(getSupportFragmentManager());
        pagerAdapter.addFragment(new EditProfileFragment(), getString(R.string.edit_profile));
        pagerAdapter.addFragment(new SignOutFragment(), getString(R.string.edit_profile));
    }

    private void setViewPager(int fragmentNumber){
        mRelativeLayout.setVisibility(View.GONE);
        Log.d(TAG, "setViewPager: Nevigating to fragment #: " + fragmentNumber);
        mViewPager.setAdapter(pagerAdapter);
        mViewPager.setCurrentItem(fragmentNumber);
    }

    private void setUpSettingList(){
        Log.d(TAG, "setUpSettingList: initiallizing 'Account Settings' list.");
        ListView listView = findViewById(R.id.lvAccountSettings);

        ArrayList<String> options = new ArrayList<>();
        options.add(getString(R.string.edit_profile));
        options.add(getString(R.string.sign_out));

        ArrayAdapter adapter = new ArrayAdapter(mContext, android.R.layout.simple_list_item_1, options);
        listView.setAdapter(adapter);

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Log.d(TAG, "onItemClick: navigating to fragment #: " + position);
                setViewPager(position);
            }
        });
    }
}
