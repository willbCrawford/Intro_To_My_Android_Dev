package com.example.will_crawford.tictactoeinkotlin

import android.content.Context
import android.graphics.drawable.ColorDrawable
import android.graphics.drawable.Drawable
import android.widget.Button

class PresenterImpl(private val playerButton : Button, val gameBoard : ArrayList<ArrayList<Button>>, val context: Context) : Presenter {

    val gameLogic = GameLogic(gameBoard)

    var playerOneTurn : Boolean = true
    var playerOneMoves : Int = 0
    var playerTwoMoves : Int = 0

    override fun onClick(buttonToMove: Button, context: Context) : Int {
        if (playerOneTurn) {
            buttonToMove.setBackgroundColor(context.getColor(PlayerOne))
            playerButton.setBackgroundColor(context.getColor(PlayerTwoButton))
            playerButton.setText(R.string.playerTwoTurn)
            playerOneMoves++
            playerOneTurn = false
        }
        else {
            buttonToMove.setBackgroundColor(context.getColor(PlayerTwo))
            playerButton.setBackgroundColor(context.getColor(PlayerOneButton))
            playerButton.setText(R.string.playerOneTurn)
            playerTwoMoves++
            playerOneTurn = true
        }

        if( playerOneMoves + playerTwoMoves >= 9)
            return R.string.resultTie

        if (playerOneMoves >= 3 || playerTwoMoves >= 3)
            return checkWinConditions()

        return 0
    }

    fun checkWinConditions() : Int{
        if (gameLogic.checkDiagonal(PlayerOne, context))
            return PlayerOne

        if (gameLogic.checkRows(PlayerOne, context))
            return PlayerOne

        if (gameLogic.checkColumns(PlayerOne, context))
            return PlayerOne

        if (gameLogic.checkColumns(PlayerTwo, context))
            return PlayerTwo

        if (gameLogic.checkRows(PlayerTwo, context))
            return PlayerTwo

        if (gameLogic.checkDiagonal(PlayerTwo, context))
            return PlayerTwo

        return -1
    }

    override fun checkForAvailableSpace(buttonToMove: Button, context: Context) : Boolean {
        return context.getColor(android.R.color.black).equals((buttonToMove.background as ColorDrawable).color)
    }
}