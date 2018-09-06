package com.example.will_crawford.tictactoeinkotlin

import android.content.Context
import android.graphics.drawable.ColorDrawable
import android.util.Log
import android.widget.Button

class GameLogic(val gameBoard: ArrayList<ArrayList<Button>>) {

    fun checkColumns(player : Int, context : Context) : Boolean{
        var gameBoardChecked = true
        var columnPosition = 0;
        var correctMoves = 0
        while (gameBoardChecked) {
            for (Row in gameBoard) {
                if ( (Row[columnPosition].background as ColorDrawable).color.equals(context.getColor(player))) {
                    correctMoves++
                }
            }
            columnPosition++

            if (correctMoves >= 3)
                return true
            else
                correctMoves = 0

            if(columnPosition >= 2) {
                gameBoardChecked = false
            }
        }

        return false
    }

    fun checkRows(player : Int, context : Context) : Boolean{
        var correctMoves = 0
        for (Row in gameBoard){
            for (rowPosition in Row) {
                if ((rowPosition.background as ColorDrawable).color.equals(context.getColor(player))){
                    correctMoves++
                }
            }
            if(correctMoves >= 3){
                return true
            }
            else {
                correctMoves = 0
            }
        }

        return false
    }

    fun checkDiagonal(player : Int, context : Context) : Boolean {
        var correctMoves = 0
        var rowPosition = 0
        for (Row in gameBoard){
            if ((Row[rowPosition].background as ColorDrawable).color.equals(context.getColor(player))){
                correctMoves++
            }
            rowPosition++
        }

        if (correctMoves >= 3){
            return true
        }
        else
            correctMoves = 0

        for (Row in gameBoard){
            rowPosition--
            if ( (Row[rowPosition].background as ColorDrawable).color.equals(context.getColor(player))) {
                correctMoves++
            }
        }
        return correctMoves >= 3
    }
}