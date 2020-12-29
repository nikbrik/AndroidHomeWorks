package com.nikbrik.zoo

import kotlin.random.Random

class Zoo {
    private val animals = mutableListOf<Animal>()
    init {
        for (i in 1..5){
            animals.add(Bird("Птица",
                    weight = Random.nextInt(1,5),
                    energy = Random.nextInt(1,10)))
        }
        for (i in 1..3){
            animals.add(Fish("Рыба",
                    weight = Random.nextInt(1,5),
                    energy = Random.nextInt(1,10)))
        }
        for (i in 1..2){
            animals.add(Dog("Собака",
                    weight = Random.nextInt(1,5),
                    energy = Random.nextInt(1,10)))
        }
        for (i in 0..Random.nextInt(10)){
            animals.add(
                    object :Animal("Обычное животное",
                        weight = Random.nextInt(1,5),
                        energy = Random.nextInt(1,10)){
                        override val maxAge: Int=Random.nextInt(9)+1
            })
        }
    }
}