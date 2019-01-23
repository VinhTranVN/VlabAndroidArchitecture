package vlab.android.architecture.ui;

import android.content.Context;
import android.support.constraint.motion.MotionLayout;
import android.support.design.widget.AppBarLayout;
import android.util.AttributeSet;

import vlab.android.common.util.LogUtils;

/**
 * Created by Vinh.Tran on 1/23/19.
 **/
public class CollapsingToolbarMotion extends MotionLayout implements AppBarLayout.OnOffsetChangedListener {

    public CollapsingToolbarMotion(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public CollapsingToolbarMotion(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    @Override
    public void onOffsetChanged(AppBarLayout appBarLayout, int verticalOffset) {
        int progress = -verticalOffset / appBarLayout.getTotalScrollRange();
        setProgress(progress);
        LogUtils.d("TEST", ">>> onOffsetChanged verticalOffset " + verticalOffset + " ; progress " + progress);
    }

    @Override
    protected void onAttachedToWindow() {
        super.onAttachedToWindow();
        if(getParent() instanceof AppBarLayout){
            ((AppBarLayout)getParent()).addOnOffsetChangedListener(this);
        }
    }

    @Override
    protected void onDetachedFromWindow() {
        super.onDetachedFromWindow();
        if(getParent() instanceof AppBarLayout){
            ((AppBarLayout)getParent()).removeOnOffsetChangedListener(this);
        }
    }
}
