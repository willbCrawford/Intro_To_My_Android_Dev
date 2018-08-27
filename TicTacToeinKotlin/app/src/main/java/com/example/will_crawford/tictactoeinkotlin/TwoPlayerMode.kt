package com.example.will_crawford.tictactoeinkotlin

import android.widget.Button

class TwoPlayerMode(playerOneTurn : Boolean, playerButton : Button, playerOneColor : Int, playerTwoColor : Int, playerOneButtonBackgroundColor: Int, playerTwoButtonBackGroundColor: Int) : GeneralMode(playerOneTurn, playerButton, playerOneColor, playerTwoColor, playerOneButtonBackgroundColor, playerTwoButtonBackGroundColor) {

    override fun playerOneMove(buttonToMove: Button ) {

        buttonToMove.setBackgroundColor(playerOneColor)

        playerOneMoves++

        playerButton.setBackgroundColor(playerOneButtonBackgroundColor)

        playerOneTurn = false


    }

    override fun playerTwoMove(buttonToMove: Button ) {

        buttonToMove.setBackgroundColor(playerTwoColor)

        playerTwoMoves++

        playerButton.setBackgroundColor(playerTwoButtonBackGroundColor)

        playerOneTurn = true

    }


}