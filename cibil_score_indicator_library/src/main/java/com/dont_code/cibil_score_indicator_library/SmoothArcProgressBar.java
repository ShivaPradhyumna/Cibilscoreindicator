package com.dont_code.cibil_score_indicator_library;

import android.animation.ValueAnimator;
import android.content.Context;
import android.util.AttributeSet;

public class SmoothArcProgressBar extends ArcProgressBar {
    public interface OnProgressUpdateListener {
        void onProgressUpdate(float progress);
    }

    private OnProgressUpdateListener progressUpdateListener;

    public SmoothArcProgressBar(Context context) {
        super(context);
    }

    public SmoothArcProgressBar(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public SmoothArcProgressBar(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    public void setSmoothProgress(float targetProgress, long duration, OnProgressUpdateListener listener) {
        float currentProgress = getProgress();
        ValueAnimator animator = ValueAnimator.ofFloat(currentProgress, targetProgress);
        animator.setDuration(duration);
        animator.addUpdateListener(animation -> {
            float animatedValue = (float) animation.getAnimatedValue();
            setProgress(animatedValue);
            if (listener != null) {
                listener.onProgressUpdate(animatedValue);
            }
        });
        animator.start();
    }
}
