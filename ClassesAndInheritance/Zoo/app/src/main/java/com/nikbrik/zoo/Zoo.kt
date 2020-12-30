package com.nikbrik.zoo

import kotlin.random.Random

class Zoo {
    val animals = mutableListOf<Animal>()
    init {
        for (i in 1..5){
            animals.add(Bird("Животное птица",
                    weight = Random.nextInt(5)+1,
                    energy = Random.nextInt(10)+1))
        }
        for (i in 1..3){
            animals.add(Fish("Животное рыба",
                    weight = Random.nextInt(5)+1,
                    energy = Random.nextInt(10)+1))
        }
        for (i in 1..2){
            animals.add(Dog("Животное собака",
                    weight = Random.nextInt(5)+1,
                    energy = Random.nextInt(10)+1))
        }
        for (i in 0..Random.nextInt(10)+1){
            animals.add(
                object :Animal("Обычное животное",
                    weight = Random.nextInt(5)+1,
                    energy = Random.nextInt(10)+1){
                    init {
                        printChildInfo()
                    }
                }
            )
        }
    }
}