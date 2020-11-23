package kr.co.douchgosum.android.coinradar.utils

import androidx.databinding.BindingConversion


@BindingConversion
fun convertDoubleToString(double: Double): String = double.toString()