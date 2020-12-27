package kr.co.douchgosum.android.coinradar

import kotlinx.coroutines.*
import org.junit.Test

import org.junit.Assert.*
import java.text.DecimalFormat
import java.text.SimpleDateFormat
import java.util.*
import kotlin.collections.ArrayList

import java.util.Scanner

fun main() = with(Scanner(System.`in`)) {
    val word = nextLine()
    var count = 0
    var tmp = ""
    for ((index, char) in word.withIndex()) {
        tmp += char
        if (tmp == tmp.reversed()) {
            tmp = ""
            count++
        }
    }


}

