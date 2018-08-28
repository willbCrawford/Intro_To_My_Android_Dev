package com.example.will_crawford.tictactoeinkotlin

import android.graphics.drawable.ColorDrawable
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

class TicTacToeGameActivity : AppCompatActivity(), View.OnClickListener {

    var playerOneTurn :  Boolean = Random().nextBoolean()

    var playerOneMoves : Int = 0
    var playerTwoMoves : Int = 0

    lateinit var gameMode : GeneralMode

    private val topRow : ArrayList<Button> = ArrayList<Button>()
    private val middleRow : ArrayList<Button> = ArrayList<Button>()
    private val bottomRow : ArrayList<Button> = ArrayList<Button>()

    val gameBoard : ArrayList<ArrayList<Button>> = ArrayList<ArrayList<Button>>()
    lateinit var playerButton : Button
    //private var gameMode : Int = 0

    override fun onCreate(savedInstanceState: Bundle?) {

        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_tic_tac_toe_game)

        val buttonTopLeft = findViewById<Button>(R.id.buttonTopLeft)
        val buttonTopCenter = findViewById<Button>(R.id.buttonTopCenter)
        val buttonTopRight = findViewById<Button>(R.id.buttonTopRight)

        topRow.add(buttonTopLeft)
        topRow.add(buttonTopCenter)
        topRow.add(buttonTopRight)

        gameBoard.add(topRow)

        buttonTopLeft.setOnClickListener(this)
        buttonTopCenter.setOnClickListener(this)
        buttonTopRight.setOnClickListener(this)

        val buttonMiddleLeft = findViewById<Button>(R.id.buttonMiddleLeft)
        val buttonCenter = findViewById<Button>(R.id.buttonCenter)
        val buttonMiddleRight = findViewById<Button>(R.id.buttonMiddleRight)

        middleRow.add(buttonMiddleLeft)
        middleRow.add(buttonCenter)
        middleRow.add(buttonMiddleRight)

        gameBoard.add(middleRow)

        buttonMiddleLeft.setOnClickListener(this)
        buttonCenter.setOnClickListener(this)
        buttonMiddleRight.setOnClickListener(this)

        val buttonBotLeft = findViewById<Button>(R.id.buttonBotLeft)
        val buttonBotCenter = findViewById<Button>(R.id.buttonBotCenter)
        val buttonBotRight = findViewById<Button>(R.id.buttonBotRight)

        bottomRow.add(buttonBotLeft)
        bottomRow.add(buttonBotCenter)
        bottomRow.add(buttonBotRight)

        gameBoard.add(bottomRow)

        buttonBotLeft.setOnClickListener(this)
        buttonBotCenter.setOnClickListener(this)
        buttonBotRight.setOnClickListener(this)

        val resetButton = findViewById<Button>(R.id.reset_button)

        playerButton = findViewById(R.id.playerMove)

        if(intent.getIntExtra(EXTRA_MESSAGE, R.string.twoPlayerMode) == R.string.twoPlayerMode){

            gameMode = TwoPlayerMode(playerOneTurn, playerButton,
                    resources.getColor(android.R.color.holo_orange_light), resources.getColor(android.R.color.holo_blue_light),
                    resources.getColor(android.R.color.holo_orange_dark), resources.getColor(android.R.color.holo_blue_dark) )

        }
        else {

            gameMode = SinglePlayerMode(playerOneTurn, playerButton,
                    resources.getColor(android.R.color.holo_orange_light), resources.getColor(android.R.color.holo_blue_light),
                    resources.getColor(android.R.color.holo_orange_dark), resources.getColor(android.R.color.holo_blue_dark),
                    gameBoard )

        }

        resetButton.setOnClickListener {
            resetBoard()
        }

    }

    private fun resetBoard() {

        for(row : ArrayList<Button> in gameBoard){

            for (buttonPosition : Button in row){

                buttonPosition.setBackgroundColor(resources.getColor(android.R.color.black))

            }

        }

        if(gameMode is SinglePlayerMode){

            (gameMode as SinglePlayerMode).resetAvailablePosition(gameBoard)

        }

        gameMode.playerOneMoves = 0
        gameMode.playerTwoMoves = 0
        gameMode.playerOneTurn = true

    }

    override fun onClick(v: View) {

        if(checkForAvailableSpace(v as Button)){

            gameMode.playerMove(v)

        }

        checkWinConditions()

    }

    private fun checkForAvailableSpace(button: Button): Boolean {

        return button.background.equals( resources.getColor( android.R.color.black ) )

    }

    private fun checkWinConditions() {

        if (gameMode.playerOneMoves >= 3) {

            checkGameForWin(PlayerOne)

        }

        if (gameMode.playerTwoMoves >= 3) {

            checkGameForWin(PlayerTwo)

        }

        if (gameMode.playerOneMoves + gameMode.playerTwoMoves >= MaxNUmberOfMoves) {

            displayTie()

        }

    }

    private fun checkGameForWin(player: Int) {

        if (checkCols(player) || checkRows(player) || checkDiagonal(player)) {

            displayWinner(player)

        }

    }

    private fun displayWinner(winner: Int) {

        AlertDialog.Builder(this)
                .setMessage(winner.toString() + " is the Winner!")
                .setPositiveButton("Reset"
                ) { _, _ -> resetBoard() }
                .create()
                .show()

    }

    private fun displayTie() {

        AlertDialog.Builder(this)
                .setMessage("It's A Tie!!")
                .setPositiveButton("Reset"
                ) { _, _ -> resetBoard() }
                .create()
                .show()

    }

    private fun checkCols(player: Int): Boolean {

        var correctMoves = 0
        var i = 0
        var gameBoardIsNotChecked = true

        while (gameBoardIsNotChecked) {

            var rowCounter = 0
            var columnCounter = 0

            for (Row in gameBoard) {

                if (player.equals((Row[columnCounter].background as ColorDrawable).color)) {

                    correctMoves++

                }

                rowCounter++

                if (correctMoves >= 3) {

                    return true

                }

            }

            i++

            correctMoves = 0

            if (rowCounter == 2) {

                rowCounter = 0

                columnCounter++

            }

            if (i >= 3) {

                gameBoardIsNotChecked = false

            }

        }

        return false

    }

    private fun checkRows(player: Int): Boolean {

        var correctPosition: Int

        for (row in gameBoard) {

            correctPosition = 0

            for (position in row) {

                if (player == (position.background as ColorDrawable).color) {

                    correctPosition++

                }

            }

            if (correctPosition >= 3) {

                return true
            }

        }

        return false

    }

    private fun checkDiagonal(player: Int): Boolean {

        var i = 0
        var correctMoves = 0

        for (row in gameBoard) {

            if (player == (row[i].background as ColorDrawable).color) {

                correctMoves++

            }

            i++

            if (correctMoves >= 3) {

                return true
            }

        }

        correctMoves = 0

        i = 2

        for (row in gameBoard) {

            if (player == (row[i].background as ColorDrawable).color) {

                correctMoves++

            }

            i--

            if (correctMoves >= 3) {

                return true

            }

        }

        return false

    }


}
