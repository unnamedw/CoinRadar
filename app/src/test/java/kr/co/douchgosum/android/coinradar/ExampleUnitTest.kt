package kr.co.douchgosum.android.coinradar

import kotlinx.coroutines.*
import org.junit.Test

import org.junit.Assert.*

@Test
fun main() {
    val regex1 = Regex("""^010[-]\d{4}[-]\d{4}""")
    val regex2 = Regex("""^010\d{8}""")
    val regex3 = Regex("""/+82-10-/\d{4}[-]d{4}""")
    val input = ""
    println(regex1.matches("01040546279"))
}

fun solution(phone_number: String): Int {
    var answer = 0
    val regex1 = Regex("""^010[-]\d{4}[-]\d{4}""")
    val regex2 = Regex("""^010\d{8}""")
    val regex3 = Regex("""/+82-10-/\d{4}[-]d{4}""")


    return answer
}
