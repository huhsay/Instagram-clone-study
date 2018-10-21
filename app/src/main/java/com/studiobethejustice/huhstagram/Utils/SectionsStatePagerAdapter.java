package com.studiobethejustice.huhstagram.Utils;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class SectionsStatePagerAdapter extends FragmentStatePagerAdapter {

    private final List<Fragment> mFragmentList = new ArrayList<>();
    private final HashMap<Fragment, Integer> mFragment = new HashMap<>();
    private final HashMap<String, Integer> mFragmentNumbers = new HashMap<>();
    private final HashMap<Integer, String> mFragmentNames = new HashMap<>();

    public SectionsStatePagerAdapter(FragmentManager fm) {
        super(fm);
    }

    @Override
    public int getCount() {
        return mFragmentList.size();
    }

    @Override
    public Fragment getItem(int position) {
        return mFragmentList.get(position);
    }

    public void addFragment(Fragment fragment, String fragmentName){
        mFragmentList.add(fragment);
        mFragment.put(fragment, mFragmentList.size()-1);
        mFragmentNumbers.put(fragmentName, mFragmentList.size()-1);
        mFragmentNames.put(mFragmentList.size()-1, fragmentName);
    }

    /**
     * returns the fragment with the name @param
     * @param fragmentName
     * @return
     */
    public Integer getFragmentNumber(String fragmentName){
        if(mFragmentNumbers.containsKey(fragmentName)){
            return mFragmentNumbers.get(fragmentName);
        }

        return null;
    }

    /**
     * returns the fragment with the name @param
     * @param fragment
     * @return
     */
    public Integer getFragmentNumber(Fragment fragment){
        if(mFragment.containsKey(fragment)){
            return mFragmentNumbers.get(fragment);
        }

        return null;
    }

    /**
     * returns the fragment with the name @param
     * @param fragmentNumber
     * @return
     */
    public String getFragmentName(Integer fragmentNumber){
        if(mFragmentNames.containsKey(fragmentNumber)){
            return mFragmentNames.get(fragmentNumber);
        }

        return null;
    }


}
