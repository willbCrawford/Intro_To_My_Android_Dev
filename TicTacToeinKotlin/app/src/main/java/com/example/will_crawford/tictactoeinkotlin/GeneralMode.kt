package com.example.will_crawford.tictactoeinkotlin

import android.widget.Button
import java.util.ArrayList

abstract class GeneralMode(playerOneTurn : Boolean, playerButton : Button, val playerOneColor: Int, val playerTwoColor: Int, val playerOneButtonBackgroundColor: Int, val playerTwoButtonBackGroundColor: Int) {

    var playerOneTurn : Boolean = true

    var playerOneMoves : Int = 0
    var playerTwoMoves : Int = 0

    var playerButton: Button

    init {

        this.playerOneTurn = playerOneTurn

        this.playerButton = playerButton

        if(playerOneTurn){

            playerButton.setBackgroundColor(this.playerOneColor)

        } else{

            playerButton.setBackgroundColor(playerTwoColor)

        }

    }

    fun playerMove(buttonToMove: Button){

        if (playerOneTurn){

            playerOneMove(buttonToMove)

        } else {

            playerTwoMove(buttonToMove)

        }

    }

    abstract fun playerOneMove(buttonToMove: Button)


    abstract fun playerTwoMove(buttonToMove: Button)


}