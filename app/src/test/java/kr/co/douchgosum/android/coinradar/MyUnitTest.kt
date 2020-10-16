package kr.co.douchgosum.android.coinradar

import kotlinx.coroutines.flow.asFlow
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.runBlocking
import kr.co.douchgosum.android.coinradar.utils.dateToMillis
import org.junit.Test

class MyUnitTest {
    @Test
    fun test() {
        runBlocking {
            (1..10).asFlow().collect {
                println("flow: $it")
            }
            println("End!")
        }
    }

    data class Human(
        val sex: String,
        val age: Int
    ): Animalizable {
        override fun toAnimal(): Animal = Animal(this.age)
    }


    interface Animalizable {
        fun toAnimal(): Animal
    }

    class Animal(
        age: Int
    ) {
        val realAge = age*10
    }
}