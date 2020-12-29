package com.nikbrik.zoo

import kotlin.random.Random

class Fish(name: String, weight: Int, energy: Int) : Animal(name, weight, energy) {
    override val maxAge: Int = 3
    override fun move(moveType: String) {
        super.move("плывет")
    }
    override fun makeChild(): Fish {
        return Fish(name,
                weight = Random.nextInt(1,5),
                energy = Random.nextInt(1,10))
    }
}