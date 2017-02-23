package com.night.customproject.common.extension;

import android.annotation.TargetApi;
import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.drawable.Drawable;
import android.os.Build;
import android.util.AttributeSet;
import android.widget.TextView;

/**
 * Created by Night on 9/18/16.
 * Description:自定义TextView，附带图片的按钮
 */
public class DrawableCenterTextView extends TextView{

    private Paint mPaint, mFramePaint;

    public DrawableCenterTextView(Context context) {
        super(context);
        initView();
    }

    public DrawableCenterTextView(Context context, AttributeSet attrs) {
        super(context, attrs);
        initView();
    }

    public DrawableCenterTextView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        initView();
    }

    @TargetApi(Build.VERSION_CODES.LOLLIPOP)
    public DrawableCenterTextView(Context context, AttributeSet attrs, int defStyleAttr, int defStyleRes) {
        super(context, attrs, defStyleAttr, defStyleRes);
        initView();
    }

    private void initView() {
        mPaint = new Paint();

        mFramePaint = new Paint();
    }

    @Override
    protected void onDraw(Canvas canvas) {

        //配置背景色
        mPaint.setColor(getResources().getColor(android.R.color.holo_blue_light));
        mPaint.setStyle(Paint.Style.FILL);
        canvas.drawRect(0,0,getMeasuredWidth(), getMeasuredHeight(), mPaint);

        //内置矩形加颜色
        mFramePaint.setColor(getResources().getColor(android.R.color.holo_red_light));
        canvas.drawRect(10, 10, getMeasuredWidth() - 10, getMeasuredHeight() - 10, mFramePaint);
        canvas.translate(10, 0);
        canvas.save();
        //配置位移
        Drawable[] drawables = getCompoundDrawables();
        Drawable drawableLeft = drawables[0];
        if (drawableLeft != null) {
            float textWidth = getPaint().measureText(getText().toString());
            int drawablePadding = getCompoundDrawablePadding();
            int drawableWidth = drawableLeft.getIntrinsicWidth();
            float bodyWidth = textWidth + drawableWidth + drawablePadding;
            canvas.translate((getWidth() - bodyWidth) / 2, 0);
        }


        super.onDraw(canvas);
        canvas.restore();
    }
}
