package com.nikbrik.zoo

import kotlin.random.Random

class Dog(name: String, weight: Int, energy: Int) : Animal(name, weight, energy) {
    override val maxAge: Int = 10
    override fun move(moveType: String) {
        super.move("бежит")
    }
    override fun makeChild(): Dog {
        return Dog(name,
                weight = Random.nextInt(1,5),
                energy = Random.nextInt(1,10))
    }
}