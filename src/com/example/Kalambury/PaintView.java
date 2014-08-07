package com.example.Kalambury;

import android.app.Activity;
import android.content.Context;
import android.graphics.*;
import android.util.AttributeSet;
import android.util.Log;
import android.view.MotionEvent;
import android.view.SurfaceHolder;
import android.view.SurfaceView;
import android.view.View;

import java.util.ArrayList;

/**
 * Created by macie_000 on 2014-07-27.
 */
public class PaintView extends SurfaceView implements SurfaceHolder.Callback{

    private int currentColor;
    private int currentSize;
    Path drawingPath = new Path();
    private ArrayList<DrawingObject> drawingPoints;
    private Paint paint = new Paint(Color.BLACK);


    public PaintView(Context context) {
        super(context);
        paint.setStyle(Paint.Style.STROKE);
        paint.setStrokeJoin(Paint.Join.ROUND);
        currentColor = Color.BLACK;
        currentSize = getResources().getInteger(R.integer.init_size) + 5;
        drawingPoints = new ArrayList<DrawingObject>();
    }

    public PaintView(Context context, AttributeSet attrs) {
        super(context, attrs);
        paint.setStyle(Paint.Style.STROKE);
        paint.setStrokeJoin(Paint.Join.ROUND);
        currentColor = Color.BLACK;
        currentSize = getResources().getInteger(R.integer.init_size) + 5;
        drawingPoints = new ArrayList<DrawingObject>();
    }

    public PaintView(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
        paint.setStyle(Paint.Style.STROKE);
        paint.setStrokeJoin(Paint.Join.ROUND);
        currentColor = Color.BLACK;
        currentSize = getResources().getInteger(R.integer.init_size) + 5;
        drawingPoints = new ArrayList<DrawingObject>();
    }


    public void surfaceCreated(SurfaceHolder holder) {

    }

    public void surfaceChanged(SurfaceHolder holder, int format, int width, int height) {

    }

    public void surfaceDestroyed(SurfaceHolder holder) {

    }

    public boolean onTouchEvent(MotionEvent event) {
        Log.d("dotyk", event.getX() + " " + event.getY());
        float x = event.getX();
        float y = event.getY();
        switch (event.getAction()) {
            case MotionEvent.ACTION_DOWN:
                byte hide = ((GameScreen)getContext()).getMenuVisibility();
                if(hide == 1) {
                    ((GameScreen)getContext()).findViewById(R.id.drawing_settings).setVisibility(View.GONE);
                    ((GameScreen) getContext()).setMenuVisibility((byte)0);
                } else if(hide == 2) {
                    //hide scores
                    ((GameScreen) getContext()).setMenuVisibility((byte)0);
                }

                drawingPath.moveTo(x, y);
                return true;
            case MotionEvent.ACTION_MOVE:
                drawingPath.lineTo(x, y);
                break;
            default:
                return false;

        }
        drawingPoints.add(new DrawingObject(drawingPath, currentColor, currentSize));
        invalidate();
        return false; //buduj ścieżkę
    }

    protected void onDraw(Canvas canvas) {

        /*paint.setColor(Color.BLUE);
        paint.setStrokeWidth(15);
        canvas.drawPath(drawingPath, paint);
       */
        for(DrawingObject d:drawingPoints) {
            paint.setColor(d.color);
            paint.setStrokeWidth(d.size);
            canvas.drawPath(d.path, paint);
        }
        /*
        for(DrawingObject d:drawingPoints) {
            paint.setColor(d.color);
            paint.setStrokeWidth(d.size);
            canvas.drawOval(new RectF(d.x - d.size, d.y - d.size, d.x + d.size, d.y + d.size), paint);
        }*/
        paint.setColor(currentColor);
    }

    public int getCurrentColor() {
        return currentColor;
    }

    public void setCurrentColor(int currentColor) {
        this.currentColor = currentColor;
        paint.setColor(currentColor);
        drawingPath = new Path();
    }

    public void clear()
    {
        drawingPoints.clear();
        drawingPath = new Path();
        invalidate();
    }

    public int getCurrentSize() {
        return currentSize;
    }

    public void setCurrentSize(int currentSize) {
        drawingPath = new Path();
        this.currentSize = currentSize;
    }
}
