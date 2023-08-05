package com.example.calculator

import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity

class MainActivity : AppCompatActivity() {
    private var tvInput: TextView? = null
    private var isNumerical: Boolean = false
    private var isDot: Boolean = false
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        tvInput = findViewById(R.id.tv_input)
    }

    fun onDigit(view: View) {
        tvInput?.append((view as Button).text)
        isNumerical = true
        isDot = false
    }

    fun onClear(view: View) {
        tvInput?.text = ""
    }

    fun onDecimalDot(view: View) {
        if (isNumerical && !isDot) {
            tvInput?.append(".")
            isNumerical = false
            isDot = true
        }
    }

    fun onOperator(view :View){
        tvInput?.text?.let {
            if (isNumerical && !checkOperators(it.toString())){
                tvInput?.append((view as Button).text)
                isNumerical = false
                isDot = false
            }
        }
    }

    private fun checkOperators(value: String): Boolean {
        return if (value.startsWith("-")) {
            false
        } else value.contains("/")
            || value.contains("-")
            || value.contains("+")
            || value.contains("*")
    }
    fun onEqual(view: View) {
        // If the last input is a number only, solution can be found.
        if (isNumerical) {
            var tvValue = tvInput?.text.toString()
            var prefix = ""
            try {

                // Here if the value starts with '-' then we will separate it and perform the calculation with value.
                if (tvValue.startsWith("-")) {
                    prefix = "-"
                    tvValue = tvValue.substring(1);
                }

                // If the inputValue contains the Division operator
                when {
                    tvValue.contains("/") -> {
                        // Will split the inputValue using Division operator
                        val splitedValue = tvValue.split("/")

                        var one = splitedValue[0]
                        val two = splitedValue[1]

                        if (prefix.isNotEmpty()) { // If the prefix is not empty then we will append it with first value.
                            one = prefix + one
                        }
                        tvInput?.text = (one.toDouble() / two.toDouble()).toString()
                    }
                    tvValue.contains("*") -> {
                        // If the inputValue contains the Multiplication operator
                        // Will split the inputValue using Multiplication operator
                        val splitedValue = tvValue.split("*")

                        var one = splitedValue[0]
                        val two = splitedValue[1]

                        if (prefix.isNotEmpty()) { // If the prefix is not empty then we will append it with first value.
                            one = prefix + one
                        }
                        tvInput?.text = (one.toDouble() * two.toDouble()).toString()
                    }
                    tvValue.contains("-") -> {

                        // If the inputValue contains the Subtraction operator
                        // Will split the inputValue using Subtraction operator
                        val splitedValue = tvValue.split("-")

                        var one = splitedValue[0]
                        val two = splitedValue[1]

                        if (prefix.isNotEmpty()) { // If the prefix is not empty then we will append it with first value.
                            one = prefix + one
                        }
                        tvInput?.text =(one.toDouble() - two.toDouble()).toString()
                    }
                    tvValue.contains("+") -> {
                        // If the inputValue contains the Addition operator
                        // Will split the inputValue using Addition operator
                        val splitedValue = tvValue.split("+")

                        var one = splitedValue[0]
                        val two = splitedValue[1]

                        if (prefix.isNotEmpty()) { // If the prefix is not empty then we will append it with first value.
                            one = prefix + one
                        }
                        tvInput?.text = (one.toDouble() + two.toDouble()).toString()
                    }
                }
            } catch (e: ArithmeticException) {
                e.printStackTrace()
            }
        }
    }

}