package com.night.staticlibrary;

import android.annotation.TargetApi;
import android.content.Context;
import android.os.Build;
import android.util.AttributeSet;
import android.widget.LinearLayout;

/**
 * Created by Night on 9/14/16.
 * Description:
 */
public class CustomView extends LinearLayout{
    public CustomView(Context context) {
        super(context);
        initialize(context);
    }

    public CustomView(Context context, AttributeSet attrs) {
        super(context, attrs);
        initialize(context);
    }

    public CustomView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        initialize(context);
    }

    @TargetApi(Build.VERSION_CODES.LOLLIPOP)
    public CustomView(Context context, AttributeSet attrs, int defStyleAttr, int defStyleRes) {
        super(context, attrs, defStyleAttr, defStyleRes);
        initialize(context);
    }

    private void initialize(Context context){
        inflate(context, R.layout.static_view, this);
    }
}
