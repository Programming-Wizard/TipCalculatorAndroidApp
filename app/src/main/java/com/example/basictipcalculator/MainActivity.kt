package com.example.basictipcalculator

import android.graphics.Color
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.util.Log
import android.widget.EditText
import android.widget.SeekBar
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity

private const val TAG = "MainActivity"
private const val INITIAL_TIP_PERCENT = 15
class MainActivity : AppCompatActivity() {
    private lateinit var baseAmount : EditText
    private lateinit var seekBar : SeekBar
    private lateinit var tipPercentLabel : TextView
    private lateinit var totalTipLabel : TextView
    private lateinit var totalAmountLabel : TextView
    private lateinit var tipDescription : TextView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        baseAmount = findViewById(R.id.baseAmount)
        seekBar = findViewById(R.id.seekBar)
        tipPercentLabel = findViewById(R.id.tipPercentLabel)
        totalTipLabel = findViewById(R.id.totalTipLabel)
        totalAmountLabel = findViewById(R.id.totalAmountLabel)
        tipDescription = findViewById(R.id.tipDescription)
        seekBar.progress = 15
        tipPercentLabel.text = "15%"
        seekBar.setOnSeekBarChangeListener(object : SeekBar.OnSeekBarChangeListener{
            override fun onProgressChanged(seekBar: SeekBar?, progress: Int, fromUser: Boolean) {
//                Log.i(TAG, "onProgressChanged:$progress%")
                tipPercentLabel.text = "$progress%"
                computeTipAndTotal()
                computeTipDescription(progress)
            }

            override fun onStartTrackingTouch(seekBar: SeekBar?) {}
            override fun onStopTrackingTouch(seekBar: SeekBar?) {}
        })
        baseAmount.addTextChangedListener(object : TextWatcher{
            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {
            }

            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
            }

            override fun afterTextChanged(s: Editable?) {
                Log.i(TAG, "afterTextChanged $s")
                computeTipAndTotal()
            }

        })
    }
    private fun computeTipAndTotal(){
        try {
            val getBaseAmount = baseAmount.text.toString().toDouble()
            val getTipPercent = seekBar.progress
            val tipAmount = (getBaseAmount * getTipPercent) / 100
            val totalAmount = getBaseAmount + tipAmount
            totalTipLabel.text = "%.2f".format(tipAmount)
            totalAmountLabel.text = "%.2f".format(totalAmount)

        }catch (e: NumberFormatException){
            totalTipLabel.text = ""
            totalAmountLabel.text = ""
            println(e)
        }

    }
    private fun computeTipDescription(progress : Int){
//        val textColor: Int = when (progress) {
////            in 0..9 -> tipDescription.setTextColor(Color.red())
////            in 10..15 -> R.color.your_acceptable_color
////            in 15..20 -> R.color.your_good_color
////            in 20..25 -> R.color.your_great_color
////            else -> R.color.your_amazing_color
//        }

        // Set text color based on the condition
//        tipDescription.setTextColor(ContextCompat.getColor(this, textColor))

        val tipDesc = when(progress){
            in 0..9 -> { tipDescription.setTextColor(Color.RED)
            "Poor"}
            in 10..15 -> {tipDescription.setTextColor(Color.WHITE)
                "Acceptable"
            }
            in 15..20 -> {tipDescription.setTextColor(Color.GREEN)
                "Good"
            }
            in 20..25 -> {tipDescription.setTextColor(Color.CYAN)
                "Great"
            }
            else->{tipDescription.setTextColor(Color.YELLOW)
                "Amazing"
            }
        }

        tipDescription.text = tipDesc
    }

}