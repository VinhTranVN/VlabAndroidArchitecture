package vlab.android.common.ui;

import android.content.Context;
import android.support.v4.content.ContextCompat;
import android.util.AttributeSet;
import android.widget.ProgressBar;

import vlab.android.common.R;


public class CommonProgressBar extends ProgressBar {

    public CommonProgressBar(Context context) {
        super(context);
        initView();
    }

    public CommonProgressBar(Context context, AttributeSet attrs) {
        super(context, attrs);
        initView();
    }

    public CommonProgressBar(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        initView();
    }

    private void initView() {

        // set progress color
        this.getIndeterminateDrawable().setColorFilter(ContextCompat.getColor(getContext(), R.color.colorProgressbar),
                android.graphics.PorterDuff.Mode.MULTIPLY);
    }
}
