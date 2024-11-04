package com.example.investeasy

import android.os.Bundle
import android.view.inputmethod.InputMethodManager
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import com.google.android.material.snackbar.Snackbar


class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        // Check if no view has focus:
        val view = this.currentFocus
        if (view != null) {
            val imm = getSystemService(INPUT_METHOD_SERVICE) as InputMethodManager
            imm.hideSoftInputFromWindow(view.windowToken, 0)
        }

        val etMonthlyInvestment = findViewById<EditText>(R.id.et_monthly_investment)
        val etMonths = findViewById<EditText>(R.id.et_months)
        val etInterestRate = findViewById<EditText>(R.id.et_interest_rate)
        val btnRefresh = findViewById<Button>(R.id.btn_refresh)
        val tvReturn = findViewById<TextView>(R.id.tv_return)
        val tvInterest = findViewById<TextView>(R.id.tv_compound_interest)
        val tvPrincipalValue = findViewById<TextView>(R.id.tv_principal_value)

        btnRefresh.setOnClickListener {
            if (etMonthlyInvestment.text.isEmpty() || etMonths.text.isEmpty() || etInterestRate.text.isEmpty()) {
                Snackbar.make(it, "Fields cannot be empty", Snackbar.LENGTH_SHORT).show()
            } else {
                val monthlyInvestment = etMonthlyInvestment.text.toString().toDoubleOrNull() ?: 0.0
                val months = etMonths.text.toString().toIntOrNull() ?: 0
                val interestRate = etInterestRate.text.toString().toDoubleOrNull() ?: 0.0

                // Calculate the estimated return
                val principal = monthlyInvestment * months
                val totalInterest = principal * (interestRate / 100)
                val totalReturn = principal + totalInterest

                // Display the results
                tvReturn.text = String.format("$%.2f", totalReturn)
                tvPrincipalValue.text = String.format("$%.2f", principal)
                tvInterest.text = String.format("$%.2f", totalInterest)
            }
        }
    }
}