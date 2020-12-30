package com.nikbrik.zoo

import kotlin.random.Random

class Fish(name: String, weight: Int, energy: Int, override val maxAge: Int = 3) : Animal(name, weight, energy) {
    init {
        printChildInfo()
    }
    override fun move(moveType: String) {
        super.move("плывет")
    }
    override fun makeChild(): Fish {
        return Fish(name,
                weight = Random.nextInt(5)+1,
                energy = Random.nextInt(10)+1)
    }
}