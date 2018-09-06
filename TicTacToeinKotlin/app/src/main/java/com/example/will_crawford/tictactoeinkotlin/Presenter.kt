package com.example.will_crawford.tictactoeinkotlin

import android.content.Context
import android.content.res.Resources
import android.widget.Button

interface Presenter {

    abstract fun onClick(buttonToMove : Button, context: Context) : Int

    abstract fun checkForAvailableSpace(buttonToMove : Button, context: Context) : Boolean
}