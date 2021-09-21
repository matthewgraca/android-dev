package com.example.tiptime

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.tiptime.databinding.ActivityMainBinding
import java.text.NumberFormat

class MainActivity : AppCompatActivity() {

    // binding basically unifies all the activity's UI elements into one accessible object
    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.calculateButton.setOnClickListener{calculateTip()}
    }

    private fun calculateTip(){
        /* uses binding to get the cost of service UI element, turns it into text (Editable object),
        then turns it into a String, then to a Double */
        // grab cost and selected tip amount
        /* if you use just toDouble() and it returns null, cost will reject it and throw an
            exception. this is kotlin's null safety at work
         */
        val cost = binding.costOfService.text.toString().toDoubleOrNull()
        // handle empty cost
        if (cost == null || cost == 0.0){
            displayTip(0.0)
            return
        }
        val tipPercentage = when (binding.tipOptions.checkedRadioButtonId){
            R.id.twenty_percent_option -> 0.2
            R.id.eighteen_percent_option -> 0.18
            else -> 0.15
        }

        // calculate tip
        var tip = tipPercentage * cost
        if (binding.roundUpSwitch.isChecked){
            tip = kotlin.math.ceil(tip)
        }

        // display tip
        displayTip(tip)
    }

    private fun displayTip(tip: Double){
        // adjust tip to particular currency, then display
        val formattedTip = NumberFormat.getCurrencyInstance().format(tip)
        binding.tipResult.text = getString(R.string.tip_amount, formattedTip)
    }
}