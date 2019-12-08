package com.example.starchess;

import android.content.Context;
import android.content.DialogInterface;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Point;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AlertDialog;

import java.util.ArrayList;
import java.util.List;

public class ChessPanel extends View {
    @Override
    public void invalidate() {
        super.invalidate();
    }

    //行高
    private float lineHeight;

    //行数
    private int lineNumber = 12;

    //棋盘线定义
    private Paint panelLine;

    //棋盘线颜色 white
    private int panelLineColor = 0xFFFFFFFF;

    //ab user棋子
    private Bitmap userA;
    private Bitmap userB;
    static int aChoice;
    static int bChoice;

    //比例
    private float scale = 3 * 1.0f / 4;

    //输出语句
    private String outText;

    private String output;
    //private String bwins;
    //存储点击的位置
    protected static List<Point> useraArray = new ArrayList<>();
    protected static List<Point> userbArray = new ArrayList<>();
    //userA先手
    protected static boolean turnofa = true;
    public static boolean gameOver = false;

    //Constructor
    public ChessPanel(Context context) {
        this(context, null);
    }
    public ChessPanel(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        //定义格子线的颜色和style
        panelLine = new Paint();
        panelLine.setColor(panelLineColor);
        panelLine.setDither(true);
        panelLine.setStyle(Paint.Style.STROKE);



        //定义棋子的样式
//        aChoice = R.drawable.;
        //bChoice = R.drawable.b;
        userA = BitmapFactory.decodeResource(getResources(), aChoice);
        userB = BitmapFactory.decodeResource(getResources(), bChoice);
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
        lineHeight = h * 1.0f / (lineNumber + 1);
        //设置棋子大小
        int starSize = (int) (lineHeight * scale);
        userA = Bitmap.createScaledBitmap(userA, starSize, starSize, false);
        userB = Bitmap.createScaledBitmap(userB, starSize, starSize, false);
    }

    //绘制
    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        chessboard(canvas);
        drawStar(canvas);
        checkGameOver();
    }

    /**
     * 画棋盘
     * @param canvas
     */
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
            float left = (point.x + (1 - scale) / 2) * lineHeight;
            float top = (point.y + (1 - scale) / 2) * lineHeight;
            canvas.drawBitmap(userA, left, top, null);
        }

        // 绘制userb
        for (Point point : userbArray) {
            // 计算公式为: (x + 0.5) * h - ((ratio * h) / 2) = (x + (1 - ratio) / 2) * h
            float left = (point.x + (1 - scale) / 2) * lineHeight;
            float top = (point.y + (1 - scale) / 2) * lineHeight;
            canvas.drawBitmap(userB, left, top, null);
        }
    }
    //判断触屏位置
    @Override
    public boolean onTouchEvent(MotionEvent event) {
        if (gameOver) {
            return false;
        }
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
            checkGameOver();
        }
        return true;
    }

    //游戏结束检查
    private void checkGameOver() {
        boolean aWin = check(useraArray);
        boolean bWin = check(userbArray);
        if (aWin || bWin) {
            gameOver = true;
            if (aWin) {
                output = "User A is the winner!";
            } else {
                output = "User B is the winner!";
            }
            alertpop();
            //Toast.makeText(getContext(), outText, Toast.LENGTH_SHORT).show();
        }
    }

    //检查
    private boolean check(List<Point> list){
        for (Point point: list) {
            if(horizontalWin(list, point)) {
                return true;
            }
            if(verticalWin(list, point)) {
                return true;
            }
        }
        return false;
    }

    //horizontal
    private boolean horizontalWin(List<Point> list, Point point) {
        int x = point.x;
        int y = point.y;
        for (int i = 1; i <= 3; i++) {
            Point now = new Point(x, y + i);
            if (!list.contains(now)) {
                return false;
            }
        }
        return true;
    }

    //vertical
    private boolean verticalWin(List<Point> list, Point point) {
        int x = point.x;
        int y = point.y;
        for (int i = 1; i <= 3; i++) {
            Point now = new Point(x + i, y);
            if (!list.contains(now)) {
                return false;
            }
        }
        return true;
    }
    //有效的点
    private Point validPoint(int x, int y) {
        return new Point ((int) (x / lineHeight), (int) (y / lineHeight));
    }
    protected void restart() {
        useraArray.clear();
        userbArray.clear();
        gameOver = false;
        turnofa = true;
        invalidate();
    }
    private void alertpop() {
        if (ChessPanel.gameOver) {
            androidx.appcompat.app.AlertDialog.Builder alertcontent
                    = new androidx.appcompat.app.AlertDialog.Builder(getContext());
            alertcontent.setMessage(output).setCancelable(false)
                    .setPositiveButton("NewGame", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialogInterface, int i) {
                            restart();
                        }
                    })
                    .setNegativeButton("cancel", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialogInterface, int i) {
                            dialogInterface.cancel();
                        }
                    });
            AlertDialog alert = alertcontent.create();
            alert.setTitle("GameOver");
            alert.show();
        }
    }

}
