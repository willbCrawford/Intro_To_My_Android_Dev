package com.example.will_crawford.tictactoeinkotlin

import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Button

const val EXTRA_MESSAGE = "com.example.will_crawford.tictactoeinkotlin.GAMEMODE"

class StartActivity : AppCompatActivity(), View.OnClickListener {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_start)

        val singlePlayerButton : Button = findViewById(R.id.SinglePlayer)
        singlePlayerButton.setOnClickListener(this)

        val twoPlayerButton : Button = findViewById(R.id.TwoPlayer)
        twoPlayerButton.setOnClickListener(this)

    }

    override fun onClick(v: View) {

        //val gameMode : String = (v as Button).text.toString()

        val gameMode : Int = if((v as? Button) == findViewById<Button>(R.id.SinglePlayer)) R.string.singlePlayerMode else R.string.twoPlayerMode

        val intent = Intent(this, TicTacToeGameActivity::class.java)

        intent.putExtra(EXTRA_MESSAGE, gameMode)

        startActivity(intent)

    }

}
