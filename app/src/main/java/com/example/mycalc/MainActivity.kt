package com.example.mycalc

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.mycalc.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding

    private var displayView: String = "0"
    set(value) {
        field = value
        binding.displayView.text = displayView
    }

    private var currentOperation: String? = null
    private var shouldRestDisplay: Boolean = false
    private var firstNumber: String = "0"

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityMainBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)

        binding.buttonZero.setOnClickListener {onNumberClicked(0) }
        binding.buttonOne.setOnClickListener {onNumberClicked(1) }
        binding.buttonTwo.setOnClickListener {onNumberClicked(2) }
        binding.buttonThree.setOnClickListener {onNumberClicked(3) }
        binding.buttonFour.setOnClickListener {onNumberClicked(4) }
        binding.buttonFive.setOnClickListener {onNumberClicked(5) }
        binding.buttonSix.setOnClickListener {onNumberClicked(6) }
        binding.buttonSeven.setOnClickListener {onNumberClicked(7) }
        binding.buttonEight.setOnClickListener {onNumberClicked(8) }
        binding.buttonNine.setOnClickListener {onNumberClicked(9) }

        binding.buttonDivide.setOnClickListener { onOperationClicked("/") }
        binding.buttonTime.setOnClickListener { onOperationClicked("*") }
        binding.buttonPlus.setOnClickListener { onOperationClicked("+") }
        binding.buttonMinue.setOnClickListener { onOperationClicked("-") }


        binding.buttonDot.setOnClickListener{
            if (shouldRestDisplay) {
                displayView ="0."
                shouldRestDisplay = false
            } else if (!displayView.contains(".")){
                displayView += "."
            }
        }

        binding.buttonClear.setOnClickListener {
            displayView = "0"
            firstNumber = "0"
            currentOperation = null
        }

        binding.buttonEquals.setOnClickListener{
            calcCurrentOperation()
        }
    }

    private fun onNumberClicked(num: Int){
        displayView = if (displayView == "0" || shouldRestDisplay ) {
            num.toString()
        } else {
            displayView + num
        }
        shouldRestDisplay = false
    }

    private fun onOperationClicked(operation: String) {
        calcCurrentOperation()
        currentOperation = operation
        shouldRestDisplay = true
        firstNumber = displayView
    }

    private fun calcCurrentOperation () {
        displayView = when (currentOperation) {
            "/" -> (firstNumber.toFloat() / displayView.toFloat()).toString()
            "*" -> (firstNumber.toFloat() * displayView.toFloat()).toString()
            "+" -> (firstNumber.toFloat() + displayView.toFloat()).toString()
            "-" -> (firstNumber.toFloat() + displayView.toFloat()).toString()
            else -> displayView
        }

        currentOperation = null
    }

}