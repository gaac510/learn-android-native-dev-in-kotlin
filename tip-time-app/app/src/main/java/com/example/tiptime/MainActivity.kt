package com.example.tiptime

import android.os.Bundle
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
}