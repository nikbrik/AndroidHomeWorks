package com.nikbrik.zoo

import kotlin.random.Random

class Bird(name: String, weight: Int, energy: Int):Animal(name, weight, energy), Soundable {
    override val maxAge: Int = 5
    init {
        printChildInfo()
    }
    override fun move(moveType: String) {
        super.move("летит")
    }
    override fun makeChild(): Bird {
        return Bird(name,
                weight = Random.nextInt(5)+1,
                energy = Random.nextInt(10)+1)
    }
    override fun makeSound() {
        println("$name: Чик-чирик")
    }
}