package com.example.zachl.tcubed;

import android.util.Log;

import java.util.Random;


public class GameEngine {

    private static final String TAG = "GameEngine";
    private static final Random RANDOM = new Random();
    private char[] elts;
    private char currentPlayer;
    private boolean ended;
    private boolean playerVCPU;
    private boolean xGoesFirst;

    //Constructs game engine, instantiates game board grid
    public GameEngine()
    {
        elts = new char[9];
    }

    //What does this do?
    public boolean isEnded() {
        return ended;
    }


    public void setXGoesFirst(boolean xGoesFirstinc)
    {
        xGoesFirst = xGoesFirstinc;
    }

    public void setPlayerVCPU(boolean playerVCPUinc)
    {
        playerVCPU = playerVCPUinc;
    }

    public char play(int x, int y) {
        if (!ended  &&  elts[3 * y + x] == ' ') {
            elts[3 * y + x] = currentPlayer;
            changePlayer();
        }

        return checkEnd();
    }

    public void changePlayer() {
        if(xGoesFirst == true){
            currentPlayer = (currentPlayer == 'X' ? 'O' : 'X');
        }
        else{
            currentPlayer = (currentPlayer == 'O' ? 'X' : 'O');
        }
    }

    public char getElt(int x, int y) {
        return elts[3 * y + x];
    }

    public void newGame() {
        Log.d(TAG, "Start newGame()");
        for (int i = 0; i  < elts.length; i++) {
            elts[i] = ' ';
        }
        if (xGoesFirst)
            currentPlayer = 'X';
        else
            currentPlayer = 'O';
        ended = false;
        Log.d(TAG, "End newGame()");
    }

    // Function checks whether game is over: either a player has 3 in a row or the board is full
    // Returns char indicating winner: X, O, or T for tie, sets 'ended' flag if game over
    public char checkEnd() {
        for (int i = 0; i < 3; i++) {
            if (getElt(i, 0) != ' ' &&
                    getElt(i, 0) == getElt(i, 1)  &&
                    getElt(i, 1) == getElt(i, 2)) {
                ended = true;
                return getElt(i, 0);
            }

            if (getElt(0, i) != ' ' &&
                    getElt(0, i) == getElt(1, i)  &&
                    getElt(1, i) == getElt(2, i)) {
                ended = true;
                return getElt(0, i);
            }
        }

        if (getElt(0, 0) != ' '  &&
                getElt(0, 0) == getElt(1, 1)  &&
                getElt(1, 1) == getElt(2, 2)) {
            ended = true;
            return getElt(0, 0);
        }

        if (getElt(2, 0) != ' '  &&
                getElt(2, 0) == getElt(1, 1)  &&
                getElt(1, 1) == getElt(0, 2)) {
            ended = true;
            return getElt(2, 0);
        }

        for (int i = 0; i < 9; i++) {
            if (elts[i] == ' ')
                return ' ';
        }

        return 'T';
    }

    // Computer plays a turn
    public char computer() {

        if (playerVCPU) {

            if (!ended) {   // If game hasn't ended
                int position;

                do {    // Find empty spot randomly
                    position = RANDOM.nextInt(9);
                } while (elts[position] != ' ');

                elts[position] = currentPlayer; // "Play" computer choice on game board
                changePlayer();
            }

            return checkEnd();
        }
        else {
            return checkEnd();
        }
    }

}