package com.example.coderem

import com.github.mikephil.charting.components.AxisBase
import com.github.mikephil.charting.formatter.IndexAxisValueFormatter
import java.text.SimpleDateFormat
import java.util.*

class MyCustomFormatter() : IndexAxisValueFormatter()
    {
        @Deprecated("Deprecated in Java")
        override fun getFormattedValue(value: Float, axis: AxisBase?): String
        {
            val dateInMillis = value.toLong()
            val date = Calendar.getInstance().apply {
                timeInMillis = dateInMillis
            }.time

            return SimpleDateFormat("dd MMM", Locale.getDefault()).format(date)
        }
    }
