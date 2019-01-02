package com.studiobethejustice.huhstagram.Utils;

import android.animation.AnimatorSet;
import android.animation.ObjectAnimator;
import android.util.Log;
import android.view.View;
import android.view.animation.AccelerateInterpolator;
import android.view.animation.DecelerateInterpolator;
import android.widget.ImageView;

public class Heart {

    private static final String TAG = "Heart";

    private static final DecelerateInterpolator DECELERATE_INTERPOLATOR = new DecelerateInterpolator();
    private static final AccelerateInterpolator ACCELERATE_INTERPOLATOR = new AccelerateInterpolator();

    public ImageView heartWhite, heartRead;

    public Heart(ImageView heartWhite, ImageView heartRead) {
        this.heartWhite = heartWhite;
        this.heartRead = heartRead;
    }

    public void toggleLike() {
        Log.d(TAG, "toggleLike: toggling heart");

        AnimatorSet animatorSet = new AnimatorSet();

        if (heartRead.getVisibility() == View.VISIBLE) {
            Log.d(TAG, "toggleLike: toggling red heart off.");

            heartRead.setScaleX(0.1f);
            heartRead.setScaleY(0.1f);

            ObjectAnimator scaleDownY = ObjectAnimator.ofFloat(heartRead, "scaleY", 1f, 0f);
            scaleDownY.setDuration(300);
            scaleDownY.setInterpolator(ACCELERATE_INTERPOLATOR);

            ObjectAnimator scaleDownX = ObjectAnimator.ofFloat(heartRead, "scaleX", 1f, 0f);
            scaleDownX.setDuration(300);
            scaleDownX.setInterpolator(ACCELERATE_INTERPOLATOR);

            heartRead.setVisibility(View.GONE);
            heartWhite.setVisibility(View.VISIBLE);

            animatorSet.playTogether(scaleDownY, scaleDownX);

        }else if (heartRead.getVisibility() == View.GONE) {
            Log.d(TAG, "toggleLike: toggling red heart on.");

            heartRead.setScaleX(0.1f);
            heartRead.setScaleY(0.1f);

            ObjectAnimator scaleDownY = ObjectAnimator.ofFloat(heartRead, "scaleY", 0.1f, 1f);
            scaleDownY.setDuration(300);
            scaleDownY.setInterpolator(DECELERATE_INTERPOLATOR);

            ObjectAnimator scaleDownX = ObjectAnimator.ofFloat(heartRead, "scaleX", 0.1f, 1f);
            scaleDownX.setDuration(300);
            scaleDownX.setInterpolator(DECELERATE_INTERPOLATOR);

            heartRead.setVisibility(View.VISIBLE);
            heartWhite.setVisibility(View.GONE);

            animatorSet.playTogether(scaleDownY, scaleDownX);
        }

        animatorSet.start();
    }

}
