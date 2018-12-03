package com.example.zachl.tcubed;

import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.support.annotation.Nullable;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.util.AttributeSet;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;

public class BoardView extends View {

    private static final int LINE_THICK = 5;
    private static final int ELT_MARGIN = 20;
    private static final int ELT_STROKE_WIDTH = 15;
    private int width, height, eltW, eltH;
    private Paint gridPaint, oPaint, xPaint;
    private GameEngine gameEngine;
    private GameBoard gameBoard;    //TODO: Trying to update scoreboard, by calling method in GameBoard, but don't have access to class from here
    private final static String TAG = "BoardView";

    public BoardView(Context context) {
        super(context);
        Log.d(TAG, "new BoardView constructed");
    }

    public BoardView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        gridPaint = new Paint();
        oPaint = new Paint(Paint.ANTI_ALIAS_FLAG);
        oPaint.setColor(Color.RED);
        oPaint.setStyle(Paint.Style.STROKE);
        oPaint.setStrokeWidth(ELT_STROKE_WIDTH);
        xPaint = new Paint(oPaint);
        xPaint.setColor(Color.BLUE);

        Log.d(TAG, "new BoardView constructed (+attrs)");
    }


    public void setGameBoard(GameEngine g) {
        gameEngine = g;
        Log.d(TAG, "GameEngine set in BoardView!");
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        height = View.MeasureSpec.getSize(heightMeasureSpec);
        width = View.MeasureSpec.getSize(widthMeasureSpec);
        eltW = (width - LINE_THICK) / 3;
        eltH = (height - LINE_THICK) / 3;

        setMeasuredDimension(width, height);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        Log.d(TAG, "Start onDraw()\nStart drawGrid()");
        drawGrid(canvas);
        Log.d(TAG, "Finish drawGrid()\nStart drawBoard()");
        drawBoard(canvas);
        Log.d(TAG, "Finish drawBoard()\nFinish onDraw()");
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        Log.d(TAG, "TouchEvent");
        if (!gameEngine.isEnded()  &&  event.getAction() == MotionEvent.ACTION_DOWN) {
            int x = (int) (event.getX() / eltW);
            int y = (int) (event.getY() / eltH);
            if (gameEngine.getElt(x, y) == ' ') {
                char win = gameEngine.play(x, y);
                invalidate();

                if (win != ' ') {   // If human wins after playing turn
                    gameEnded(win);
                } else {
                    // computer plays ...
                    win = gameEngine.computer();
                    invalidate();

                    if (win != ' ') {   // If computer wins after playing turn
                        gameEnded(win);
                    }
                }
            }
        }

        return super.onTouchEvent(event);
    }

    public void gameEnded(char c) {
        String msg = (c == 'T') ? "Game Ended: Tie!" : "Game Ended: " + c + " wins!";
        if (c == 'X')
            //xWins++;  TODO: Replace with call to GameBoard updateXScore()
        if (c == 'O')
            //oWins++;  TODO: Replace with call to GameBoard updateOScore()

        new AlertDialog.Builder(getContext()).setTitle("Tic Tac Toe").
                setMessage(msg).
                setOnDismissListener(new DialogInterface.OnDismissListener() {
                    @Override
                    public void onDismiss(DialogInterface dialogInterface) {
                        newGame();
                    }
                }).show();
    }

    public void newGame() {
        gameEngine.newGame();
        invalidate();
    }

    private void drawBoard(Canvas canvas) {
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                Log.d(TAG, "inner loop");
                drawElt(canvas, gameEngine.getElt(i, j), i, j);
            }
        }
    }

    private void drawGrid(Canvas canvas) {
        for (int i = 0; i < 2; i++) {
            // vertical lines
            float left = eltW * (i + 1);
            float right = left + LINE_THICK;
            float top = 0;
            float bottom = height;

            canvas.drawRect(left, top, right, bottom, gridPaint);

            // horizontal lines
            float left2 = 0;
            float right2 = width;
            float top2 = eltH * (i + 1);
            float bottom2 = top2 + LINE_THICK;

            canvas.drawRect(left2, top2, right2, bottom2, gridPaint);
        }
    }

    private void drawElt(Canvas canvas, char c, int x, int y) {
        if (c == 'O') { //Draw a circle game piece in selected position
            float cx = (eltW * x) + eltW / 2;
            float cy = (eltH * y) + eltH / 2;

            Log.d(TAG, "");

            canvas.drawCircle(cx, cy, Math.min(eltW, eltH) / 2 - ELT_MARGIN * 2, oPaint);

        } else if (c == 'X') {  //Draw an X game piece in selected position
            float startX = (eltW * x) + ELT_MARGIN;
            float startY = (eltH * y) + ELT_MARGIN;
            float endX = startX + eltW - ELT_MARGIN * 2;
            float endY = startY + eltH - ELT_MARGIN;

            canvas.drawLine(startX, startY, endX, endY, xPaint);

            float startX2 = (eltW * (x + 1)) - ELT_MARGIN;
            float startY2 = (eltH * y) + ELT_MARGIN;
            float endX2 = startX2 - eltW + ELT_MARGIN * 2;
            float endY2 = startY2 + eltH - ELT_MARGIN;

            canvas.drawLine(startX2, startY2, endX2, endY2, xPaint);
        }
    }
}