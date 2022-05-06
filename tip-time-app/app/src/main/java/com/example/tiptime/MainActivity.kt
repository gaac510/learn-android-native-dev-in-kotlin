package com.example.tiptime

import android.content.Context
import android.os.Bundle
import android.view.KeyEvent
import android.view.View
import android.view.inputmethod.InputMethodManager
import androidx.appcompat.app.AppCompatActivity
import com.example.tiptime.databinding.ActivityMainBinding
import java.text.NumberFormat
import kotlin.math.ceil

//private const val TAG = "MainActivity"

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        binding.calculateTipButton.setOnClickListener {
            calculateTip()
        }
        binding.costOfService.setOnKeyListener {
            view, keyCode, _ -> handleKeyEvent(view, keyCode)
        }
    }

    private fun calculateTip() {
        val cost: Double? = binding.costOfService.text.toString().toDoubleOrNull()
        if (cost == null) {
            binding.tipAmount.text = ""
            return
        }
        // Log.e(TAG, binding.qualityOfService.checkedRadioButtonId.toString())
        val tipPercentage: Double = when (binding.qualityOfService.checkedRadioButtonId) {
            R.id.excellent_service -> 0.25
            R.id.good_service -> 0.2
            else -> 0.15
        }
        var tipAmount: Double = cost * tipPercentage
        if(binding.roundupSwitch.isChecked) {
            tipAmount = ceil(tipAmount)
        }

        binding.tipAmount.text = getString(R.string.tip_amount, NumberFormat.getCurrencyInstance().format(tipAmount))
    }

    private fun handleKeyEvent(view: View, keyCode: Int): Boolean {
        if(keyCode == KeyEvent.KEYCODE_ENTER) {
            // Hide the keyboard
            val inputMethodManager = getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
            inputMethodManager.hideSoftInputFromWindow(view.windowToken, 0)
            return true
        }
        return false
    }
}