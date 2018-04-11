package com.epam.androidlab.task10_customview;


import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.RectF;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.View;

public class SmileView extends View {

    private Paint smilingEyesPaint ;
    private Paint sadEyesPaint ;
    private Paint mouthPaint ;
    private Paint facePaint ;

    private int eyesColor;
    private int mouthColor;
    private int faceColor;

    private int faceX;
    private int faceY;
    private int faceRadius;

    private RectF mouth;
    private RectF leftEye;
    private RectF rightEye;

    public static final int HAPPY = 0;
    public static final int SAD = 1;

    private int state;

    public void setState(int state){
        this.state = state;
        invalidate();
    }

    public int getState() {
        return state;
    }

    public SmileView(Context context) {
        super(context);
        init();
    }

    public SmileView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        TypedArray a = context.getTheme().obtainStyledAttributes(
                attrs,
                R.styleable.SmileView,
                0, 0);
        try {
            state = a.getInt(R.styleable.SmileView_state,HAPPY);
            eyesColor = a.getColor(R.styleable.SmileView_eyesColor, Color.BLACK);
            mouthColor = a.getColor(R.styleable.SmileView_mouthColor, Color.WHITE);
            faceColor = a.getColor(R.styleable.SmileView_faceColor, Color.GRAY);
        } finally {
            a.recycle();
        }
        init();
    }

    private void init() {
        initFace();
        initMouth();
        initEyes();
    }

    private void initFace(){
        facePaint = new Paint();
        facePaint.setColor(faceColor);
        facePaint.setStyle(Paint.Style.FILL);

        int screenHeight = getHeight();
        int screenWidth = getWidth();
        int size = Math.min(screenHeight, screenWidth);
        int facePadding = 50;
        faceRadius = size /2 - facePadding;
        faceX = screenWidth / 2 ;
        faceY = screenHeight / 2 ;
    }

    private void initMouth(){
        mouthPaint = new Paint();
        mouthPaint.setColor(mouthColor);
        mouthPaint.setStyle(Paint.Style.STROKE);
        mouthPaint.setStrokeWidth(15f);
    }

    private void initEyes(){
        smilingEyesPaint = new Paint();
        smilingEyesPaint.setColor(eyesColor);
        smilingEyesPaint.setStyle(Paint.Style.FILL_AND_STROKE);
        smilingEyesPaint.setStrokeWidth(15f);

        sadEyesPaint = new Paint();
        sadEyesPaint.setColor(Color.BLACK);
        sadEyesPaint.setStyle(Paint.Style.STROKE);
        sadEyesPaint.setStrokeWidth(15f);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        init();
        drawFace(canvas);

        switch (state){
            case HAPPY://happy
                drawSmilingEyes(canvas);
                drawSmilingMouth(canvas);
                break;
            case SAD: //sad
                drawSadEyes(canvas);
                drawSadMouth(canvas);
                break;
            default: break;
        }
    }

    private void drawFace(Canvas canvas){
        canvas.drawCircle(faceX, faceY, faceRadius, facePaint);
    }

    private void drawSmilingMouth(Canvas canvas){
        float lineX = getWidth()/2 - faceRadius/2;
        float lineY = getHeight()/2;
        float mouthLength = faceRadius;
        float mouthHeight = faceRadius/2;

        mouth = new RectF(lineX, lineY, lineX+mouthLength, lineY+mouthHeight);
        canvas.drawArc(mouth, 20, 140, false, mouthPaint );
    }

    private void drawSmilingEyes(Canvas canvas){
        float leftLineX = getWidth()/2-faceRadius/2;
        float lineY = getHeight()/2-faceRadius/3;
        float eyeWidth = faceRadius/3;
        float eyeHeight = faceRadius/2;

        leftEye = new RectF(leftLineX,
                            lineY,
                            leftLineX+eyeWidth,
                            lineY+eyeHeight);

        float rightLineX = getWidth()/2+faceRadius/2;

        rightEye = new RectF(rightLineX-eyeWidth,
                             lineY,
                             rightLineX,
                             lineY+eyeHeight);
        canvas.drawArc(leftEye,180,180,true,smilingEyesPaint);
        canvas.drawArc(rightEye,180,180,true,smilingEyesPaint);
    }

    private void drawSadMouth(Canvas canvas){

        float lineX = getWidth()/2 - faceRadius/2;
        float lineY = getHeight()/2 + faceRadius/3;
        float mouthLength = faceRadius;
        float mouthHeight = faceRadius/2;

        mouth = new RectF(lineX, lineY, lineX+mouthLength, lineY+mouthHeight);
        canvas.drawArc(mouth, 200, 140, false, mouthPaint );
    }

    private void drawSadEyes(Canvas canvas){
        float leftLineX = getWidth()/2-faceRadius/2;
        float lineY = (int) (getHeight()/2-faceRadius/3);
        float eyeWidth = faceRadius/3;
        float eyeHeight = faceRadius/4;

        leftEye = new RectF(leftLineX,
                            lineY,
                            leftLineX+eyeWidth,
                            lineY+eyeHeight);

        float rightLineX = getWidth()/2+faceRadius/2;

        rightEye = new RectF(rightLineX-eyeWidth,
                             lineY,
                             rightLineX,
                             lineY+eyeHeight);
        canvas.drawArc(leftEye,0,180,false,sadEyesPaint);
        canvas.drawArc(rightEye,0,180,false,sadEyesPaint);
    }
}
