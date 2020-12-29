package com.nikbrik.zoo

import kotlin.random.Random

class Bird(name: String, weight: Int, energy: Int) :Animal(name, weight, energy), Soundable {
    override val maxAge: Int = 5
    override fun move(moveType: String) {
        super.move("летит")
    }
    override fun makeChild(): Bird {
        return Bird(name,
                weight = Random.nextInt(1,5),
                energy = Random.nextInt(1,10))
    }
    override fun makeSound() {
        println("Чик-чирик")
    }
}