package com.example.starchess;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Point;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;

import androidx.annotation.Nullable;

import java.util.ArrayList;
import java.util.List;

public class ChessPanel extends View {

    //棋盘宽度
    private int panelSize;

    //行高
    private float lineHeight;

    //行数
    private int lineNumber = 10;

    //棋盘线定义
    private Paint panelLine;

    //棋盘线颜色
    private int panelLineColor = 0xFFFFFFFF;

    //ab user棋子
    private Bitmap userA;
    private Bitmap userB;

    //比例
    private float scale = 3 / 4;

    //存储点击位置
    private List<Point> useraArray = new ArrayList<>();
    private List<Point> userbArray = new ArrayList<>();
    //userA先手
    private boolean turnofa = true;

    //Constructor
    public ChessPanel(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        //setBackgroundColor(0xFFFFFFFF);
        //定义格子线的颜色和style
        panelLine = new Paint();
        panelLine.setColor(panelLineColor);
        panelLine.setDither(true);
        panelLine.setStyle(Paint.Style.STROKE);


        //定义棋子的样式
        userA = BitmapFactory.decodeResource(getResources(), android.R.drawable.btn_star_big_off);
        userB = BitmapFactory.decodeResource(getResources(), android.R.drawable.btn_star_big_on);
    }

    //测量需要的大小
    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        int widthSize = MeasureSpec.getSize(widthMeasureSpec);
        int widthMode = MeasureSpec.getMode(widthMeasureSpec);
        int heightSize = MeasureSpec.getSize(heightMeasureSpec);
        int heightMode = MeasureSpec.getMode(heightMeasureSpec);
        int length = Math.min(widthSize, heightSize);
        setMeasuredDimension(length, length);
    }

    //更改尺寸相关的量
    @Override
    protected void onSizeChanged(int w, int h, int oldw, int oldh) {
        super.onSizeChanged(w, h, oldw, oldh);
        //设置棋盘大小
//        panelSize = w;
//        lineHeight = panelSize * 1.0f / (lineNumber);
        lineHeight = h * 1.0f / (lineNumber + 1);
        //设置棋子大小
        //int starSize = (int) (lineHeight * scale);
        //userA = Bitmap.createScaledBitmap(userA, starSize, starSize, false);
        //userB = Bitmap.createScaledBitmap(userA, starSize, starSize, false);
    }

    //绘制
    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        chessboard(canvas);
        drawStar(canvas);
    }

    /**
     * 画棋盘
     * @param canvas
     */
//    private void chessboard(Canvas canvas) {
//        int w = panelSize;
//        float lHeight = lineHeight;
//        //横格子
//        for (int i = 0; i < lineNumber; i++) {
//            int xstart = (int) (lHeight / 2);
//            int xend = (int) (w - lHeight / 2);
//            int y = (int) ((0.5 + i) * lHeight);
//            canvas.drawLine(xstart, y, xend, y, panelLine);
//        }
//        //竖格子
//        for (int i = 0; i < lineNumber; i++) {
//            int ystart = (int) (lHeight / 2);
//            int yend = (int) (w - lHeight / 2);
//            int x = (int) ((0.5 + i) * lHeight);
//            canvas.drawLine(x, ystart, x, yend, panelLine);
//        }
//    }

    private void chessboard(Canvas canvas) {
        float startX = lineHeight / 2;
        float stopX = startX + lineNumber * lineHeight;
        float startY = lineHeight / 2;
        float stopY = startY;
        // 绘制横线
        for (int i = 0; i <= lineNumber; i++) {
            canvas.drawLine(startX, startY, stopX, stopY, panelLine);
            startY += lineHeight;
            stopY = startY;
        }

        // 绘制纵线
        startY = lineHeight / 2;
        stopY = startY + lineNumber * lineHeight;
        startX = lineHeight / 2;
        stopX = startX;
        for (int i = 0; i <= lineNumber; i++) {
            canvas.drawLine(startX, startY, stopX, stopY, panelLine);
            startX += lineHeight;
            stopX = startX;
        }
    }

    //画星星
    private void drawStar(Canvas canvas) {
        // 绘制usera
        for (Point point : useraArray) {
            // 计算公式为: (x + 0.5) * h - ((ratio * h) / 2) = (x + (1 - ratio) / 2) * h
            float left = (float) ((point.x + (1 - scale) / 2) * lineHeight);
            float top = (float) ((point.y + (1 - scale) / 2) * lineHeight);
            canvas.drawBitmap(userA, left, top, null);
        }

        // 绘制userb
        for (Point point : userbArray) {
            // 计算公式为: (x + 0.5) * h - ((ratio * h) / 2) = (x + (1 - ratio) / 2) * h
            float left = (float) ((point.x + (1 - scale) / 2) * lineHeight);
            float top = (float) ((point.y + (1 - scale) / 2) * lineHeight);
            canvas.drawBitmap(userB, left, top, null);
        }
    }
    @Override
    public boolean onTouchEvent(MotionEvent event) {
        int action = event.getAction();
        if (action == MotionEvent.ACTION_UP) {
            int x = (int) event.getX();
            int y = (int) event.getY();
            Point p = validPoint(x, y);

            //判断有没有下过
            if (useraArray.contains(p) || userbArray.contains(p)) {
                return false;
            }

            if (turnofa) {
                useraArray.add(p);
            } else {
                userbArray.add(p);
            }
            //invalidate 重新调ondraw
            invalidate();
            turnofa = !turnofa;
        }
        return true;
    }

    //有效的点
    private Point validPoint(int x, int y) {
        return new Point ((int) (x / lineHeight), (int) (y / lineHeight));
    }
}
