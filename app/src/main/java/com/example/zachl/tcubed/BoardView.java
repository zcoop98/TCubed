package com.example.zachl.tcubed;

import android.content.Context;
import android.content.DialogInterface;
import android.content.SharedPreferences;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Rect;
import android.support.annotation.Nullable;
import android.support.v7.app.AlertDialog;
import android.util.AttributeSet;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;

import java.lang.reflect.Field;

public class BoardView extends View {

    private static final int LINE_THICK = 5;
    private static final int ELT_MARGIN = 20;
    private static final int ELT_STROKE_WIDTH = 15;
    private int width, height, eltW, eltH;
    private Paint gridPaint, oPaint, xPaint;
    private GameEngine gameEngine;
    private GameBoard gameBoard;
    private Bitmap oBitmp;
    private Bitmap xBitmp;
    private static final String sharedPrefFile = "com.example.zachl.tcubed.sharedPrefs";
    private SharedPreferences mPreferences;
    private static final String ICON_KEY = "com.example.zachl.tcubed.iconKey";
    String[] xFileArr = getResources().getStringArray(R.array.iconsX);
    String[] oFileArr = getResources().getStringArray(R.array.iconsO);
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

        mPreferences = context.getApplicationContext().getSharedPreferences(sharedPrefFile, Context.MODE_PRIVATE);

        int fileIndex = mPreferences.getInt(ICON_KEY, 0);

        Log.d(TAG, "Retrieved file index: " + fileIndex);

        String xIconName = xFileArr[fileIndex];
        String oIconName = oFileArr[fileIndex];

        Log.d(TAG, "Retrieved file names from arrs- X: " + xIconName + " O: " + oIconName);

        int xID = getResId(xIconName, R.drawable.class);   //Use pref value to access array to get file name to retrieve icon
        int oID = getResId(oIconName, R.drawable.class);

        Log.d(TAG, "Retrieved file IDs- X: " + xID + " O: " + oID);

        if (xID != 0 && oID != 0) {
            xBitmp = BitmapFactory.decodeResource(getResources(), xID);
            oBitmp = BitmapFactory.decodeResource(getResources(), oID);
        }
        else {
            xBitmp = null;
            oBitmp = null;
        }

        Log.d(TAG, "new BoardView constructed (+attrs)");
    }

    public static int getResId(String resName, Class<?> c) {

        try {
            Field idField = c.getDeclaredField(resName);
            return idField.getInt(idField);
        } catch (Exception e) {
            e.printStackTrace();
            Log.d(TAG, "[ERROR: GetResId ERROR]");
            return -1;
        }
    }

    public void setGameEngine(GameEngine g) {
        gameEngine = g;
        Log.d(TAG, "GameEngine set in BoardView!");
    }

    public void setGameBoard(GameBoard b)
    {
        gameBoard = b;
        Log.d(TAG, "GameBoard is set in BoardView");
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

    public void gameEnded(char c) { //Calls score increment and displays end game dialog
        String msg;

        if (c == 'T')
            msg = "Game Ended: Tie!";
        else if (c == 'X') {
            gameBoard.incrementXScore();
            msg = "Game Ended: " + gameBoard.getPlayerXName() + " wins!";
        }
        else if (c == 'O') {
            gameBoard.incrementOScore();
            msg = "Game Ended: " + gameBoard.getPlayerOName() + " wins!";
        }
        else
            msg = "[[ERROR: You shouldn\'t be seeing this!]]";
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

    //x = row [0,2]
    //y = col [0,2]
    private void drawElt(Canvas canvas, char c, int x, int y) {
        if (c == 'O' && oBitmp == null) { //Draw a circle game piece in selected position
            float cx = (eltW * x) + eltW / 2;
            float cy = (eltH * y) + eltH / 2;   //calculating center point of circle

            canvas.drawCircle(cx, cy, Math.min(eltW, eltH) / 2 - ELT_MARGIN * 2, oPaint);

        }
        else if (c == 'O') {
            Rect rectangle = new Rect(eltW * x, eltH * y, eltW * x + eltW, eltH * y + eltH);
            canvas.drawBitmap(oBitmp, null, rectangle, null);
        }
        else if (c == 'X' && xBitmp == null) {  //Draw an X game piece in selected position
            float startX = (eltW * x) + ELT_MARGIN;
            float startY = (eltH * y) + ELT_MARGIN / 4 * 3;
            float endX = startX + eltW - ELT_MARGIN * 2;
            float endY = startY + eltH - ELT_MARGIN;

            canvas.drawLine(startX, startY, endX, endY, xPaint);

            float startX2 = (eltW * (x + 1)) - ELT_MARGIN;
            float startY2 = (eltH * y) + ELT_MARGIN / 4 * 3;
            float endX2 = startX2 - eltW + ELT_MARGIN * 2;
            float endY2 = startY2 + eltH - ELT_MARGIN;

            canvas.drawLine(startX2, startY2, endX2, endY2, xPaint);
        }
        else if (c == 'X') {
            Rect rectangle = new Rect(eltW * x, eltH * y, eltW * x + eltW, eltH * y + eltH);
            canvas.drawBitmap(xBitmp, null, rectangle, null);
        }
    }
}