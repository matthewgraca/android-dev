package com.example.diceroller

import android.os.Bundle
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity

/**
 * This activity lets the user roll a dice and view the result
 */
class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        // creates a reference to the button object
        val rollButton: Button = findViewById(R.id.button)
        // instructions for button when clicked
        rollButton.setOnClickListener {
            rollDice()
        } // lambda function
    }

    /**
     * Rolls a dice and updates the screen with the result
     */
    private fun rollDice() {
        // create dice and roll
        val dice = Dice(6)
        var diceRoll = dice.roll()

        // reference the imageView object and display the results
        var diceImage: ImageView = findViewById(R.id.imageView)

        // kotlin's switch statement can actually return values
        val drawableResource = when (diceRoll) {
            1 -> R.drawable.dice_1
            2 -> R.drawable.dice_2
            3 -> R.drawable.dice_3
            4 -> R.drawable.dice_4
            5 -> R.drawable.dice_5
            else -> R.drawable.dice_6
        }
        // update image with correct drawable
        diceImage.setImageResource(drawableResource)
        // update content description for accessibility
        diceImage.contentDescription = diceRoll.toString()
    }
}

class Dice(private val numSides: Int) {
    fun roll(): Int {
        return (1..numSides).random()
    }
}