package com.example.will_crawford.tictactoeinkotlin

import android.widget.Button
import java.util.*

class SinglePlayerMode(playerOneTurn : Boolean, playerButton : Button, playerOneColor : Int, playerTwoColor : Int, playerOneButtonBackgroundColor: Int, playerTwoButtonBackGroundColor: Int, gameBoard : ArrayList<ArrayList<Button>>) : GeneralMode(playerOneTurn, playerButton, playerOneColor, playerTwoColor, playerOneButtonBackgroundColor, playerTwoButtonBackGroundColor) {

    lateinit var availablePosition : ArrayList<Button>

    init{

        for(row in gameBoard){

            for( position in row){

                availablePosition.add(position)

            }

        }

    }

    override fun playerOneMove(buttonToMove: Button) {

        buttonToMove.setBackgroundColor(playerOneColor)

        playerOneMoves++

        playerButton.setBackgroundColor(playerOneButtonBackgroundColor)

        //playerOneTurn = false

        availablePosition.remove(playerButton)


    }

    override fun playerTwoMove(buttonToMove: Button){

        availablePosition.get(Random().nextInt()).setBackgroundColor(playerTwoColor)

        playerTwoMoves++

        playerButton.setBackgroundColor(playerTwoButtonBackGroundColor)

        //playerOneTurn = true

    }

    fun resetAvailablePosition(gameBoard: ArrayList<ArrayList<Button>>){

        for (row in gameBoard){

            for(position in row){

                availablePosition.add(position)

            }

        }

    }

}