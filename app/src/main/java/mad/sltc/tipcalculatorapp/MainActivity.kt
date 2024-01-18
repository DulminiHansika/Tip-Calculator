package mad.sltc.tipcalculatorapp

import android.annotation.SuppressLint
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.core.text.isDigitsOnly
import mad.sltc.tipcalculatorapp.databinding.ActivityMainBinding
import java.text.NumberFormat
import java.util.Locale
import kotlin.math.ceil

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding
    private var countperson = 1
    private var billamount = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        binding.buttonOption.setOnClickListener { CalculateTip() }
        onViewClick()

    }


    private fun onViewClick() {

            binding.btnPlus.setOnClickListener {
                countperson++
                binding.personCount.text = countperson.toString()
            }

            binding.btnMinus.setOnClickListener {
                if (countperson > 1) {
                    countperson--
                    binding.personCount.text = countperson.toString()
                } else
                    Toast.makeText(
                        this@MainActivity,
                        "Minimum Person count is 1.",
                        Toast.LENGTH_SHORT
                    ).show()
            }
        }

        private fun CalculateTip() {

            val totalAmount = binding.costOfServiceEditText.text.toString()
            val cost= totalAmount.toDoubleOrNull()

            if (totalAmount.isEmpty()) {
                Toast.makeText(this@MainActivity, "Enter your Bill Amount", Toast.LENGTH_SHORT)
                    .show()
            } else {
                val selectedId = binding.tipOptions.checkedRadioButtonId

                val tipPercentage = when (selectedId) {
                    R.id.option1 -> 0.2
                    R.id.option2 -> 0.15

                    else -> 0.1
                }

                var tip = cost!!.times(tipPercentage)

                var billPlusAmount= cost.plus(tip)
                showTotalAmount(tip)

                var totalperson = (billPlusAmount/countperson)
                showTotalPersonResult(totalperson)

                val roundUp = binding.tipRound.isChecked
                if (roundUp) {
                    tip = ceil(tip)

                val currencyTip = NumberFormat.getCurrencyInstance(Locale("en","in")).format(tip)
                    binding.resultOfTip.text = "Tip Amount: $currencyTip"
                    binding.resultOfTotal.text = "Total Amount : $currencyTip"


                }
            }


        }

    private fun showTotalAmount(tip:Double){
        binding.resultOfTotal.text = "Total Amount(Bill+Tip) : $tip"

    }
    private fun showTotalPersonResult(tip: Double) {
        binding.resultOfTotalperson.text = "Total Person Amount : $tip"

    }

}






