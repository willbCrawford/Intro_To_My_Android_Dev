package com.example.will_crawford.tictactoeinkotlin

import android.content.DialogInterface
import android.content.res.Resources
import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.graphics.drawable.Drawable
import android.net.sip.SipSession
import android.os.Bundle
import android.support.v7.app.AlertDialog
import android.support.v7.app.AppCompatActivity
import android.view.View
import android.widget.Button
import java.util.*
import kotlin.collections.ArrayList

const val MaxNUmberOfMoves = 9
const val PlayerOne = android.R.color.holo_orange_light
const val PlayerTwo = android.R.color.holo_blue_light
const val PlayerOneButton = android.R.color.holo_orange_dark
const val PlayerTwoButton = android.R.color.holo_blue_dark

class TicTacToeGameActivity : AppCompatActivity(), View.OnClickListener {

    var playerOneTurn :  Boolean = Random().nextBoolean()

    private val topRow : ArrayList<Button> = ArrayList<Button>()
    private val middleRow : ArrayList<Button> = ArrayList<Button>()
    private val bottomRow : ArrayList<Button> = ArrayList<Button>()

    val gameBoard : ArrayList<ArrayList<Button>> = ArrayList<ArrayList<Button>>()
    lateinit var playerButton : Button
    lateinit var presenterImpl: PresenterImpl
    //private var gameMode : Int = 0

    override fun onCreate(savedInstanceState: Bundle?) {

        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_tic_tac_toe_game)

        val buttonTopLeft = findViewById<Button>(R.id.buttonTopLeft)
        val buttonTopCenter = findViewById<Button>(R.id.buttonTopCenter)
        val buttonTopRight = findViewById<Button>(R.id.buttonTopRight)

        buttonTopLeft.setOnClickListener(this)
        buttonTopCenter.setOnClickListener(this)
        buttonTopRight.setOnClickListener(this)

        topRow.add(buttonTopLeft)
        topRow.add(buttonTopCenter)
        topRow.add(buttonTopRight)

        gameBoard.add(topRow)

        val buttonMiddleLeft : Button = findViewById<Button>(R.id.buttonMiddleLeft)
        val buttonCenter = findViewById<Button>(R.id.buttonCenter)
        val buttonMiddleRight = findViewById<Button>(R.id.buttonMiddleRight)

        buttonMiddleLeft.setOnClickListener(this)
        buttonCenter.setOnClickListener(this)
        buttonMiddleRight.setOnClickListener(this)

        middleRow.add(buttonMiddleLeft)
        middleRow.add(buttonCenter)
        middleRow.add(buttonMiddleRight)

        gameBoard.add(middleRow)

        val buttonBotLeft = findViewById<Button>(R.id.buttonBotLeft)
        val buttonBotCenter = findViewById<Button>(R.id.buttonBotCenter)
        val buttonBotRight = findViewById<Button>(R.id.buttonBotRight)

        buttonBotLeft.setOnClickListener(this)
        buttonBotCenter.setOnClickListener(this)
        buttonBotRight.setOnClickListener(this)

        bottomRow.add(buttonBotLeft)
        bottomRow.add(buttonBotCenter)
        bottomRow.add(buttonBotRight)

        gameBoard.add(bottomRow)

        val resetButton = findViewById<Button>(R.id.reset_button)
        resetButton.setOnClickListener {
            resetGame()
        }

        playerButton = findViewById(R.id.playerMove)

        presenterImpl = PresenterImpl(playerButton, gameBoard, this)
    }

    override fun onClick(v: View?) {
        val winner = presenterImpl.onClick(v as Button, this)

        when (winner){
            PlayerOne -> {
                showWinner("Player One")
            }

            PlayerTwo -> {
                showWinner("Player Two")
            }

            R.string.resultTie -> {
                showTie()
            }
        }
    }

    private fun showWinner(winner : String){
        AlertDialog.Builder(this)
                .setMessage("The winner is: $winner")
                .setPositiveButton("Play Again?") { _, _ ->  resetGame()}
                .create().show()
    }

    private fun showTie(){
        AlertDialog.Builder(this)
                .setMessage("The game is a Tie!")
                .setPositiveButton("Play Again?") { _, _ ->  resetGame()}
                .create().show()
    }

    fun resetGame() {
        for (Row in gameBoard){
            for (button in Row){
                button.setBackgroundColor(resources.getColor(android.R.color.black))
            }
        }
        presenterImpl.playerOneMoves = 0
        presenterImpl.playerTwoMoves = 0
    }
}
