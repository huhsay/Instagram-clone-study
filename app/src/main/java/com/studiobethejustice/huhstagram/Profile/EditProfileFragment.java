package com.studiobethejustice.huhstagram.Profile;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.nostra13.universalimageloader.core.ImageLoader;
import com.studiobethejustice.huhstagram.R;
import com.studiobethejustice.huhstagram.Utils.UniversalImageLoader;

public class EditProfileFragment extends Fragment{

    private static final String TAG = "EditProfileFragment";

    private ImageView mProfilePhoto;
    private ImageView backButton;

    private ProfileActivity mActivity;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_editprofile, container, false);
        mProfilePhoto = view.findViewById(R.id.profile_photo);
        backButton = view.findViewById(R.id.backArrow);


        initImageLoader();
        setProfileImage();

        return view;
    }

    private void initImageLoader(){
        UniversalImageLoader universalImageLoader = new UniversalImageLoader(getActivity());
        ImageLoader.getInstance().init(universalImageLoader.getConfig());
    }

    private void setProfileImage(){
        Log.d(TAG, "setProfileImage: setting profile image.");
        String imgURL = "https://developer.android.com/static/images/home/jetpack-promo.svg?hl=ko";
        UniversalImageLoader.setImage(imgURL, mProfilePhoto, null, "");
    }
}