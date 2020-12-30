package com.nikbrik.zoo

import kotlin.random.Random

class Dog(name: String, weight: Int, energy: Int, override val maxAge: Int = 10) : Animal(name, weight, energy), Soundable {
    init {
        printChildInfo()
    }
    override fun move(moveType: String) {
        super.move("бежит")
    }
    override fun makeChild(): Dog {
        return Dog(name,
                weight = Random.nextInt(5)+1,
                energy = Random.nextInt(10)+1)
    }
    override fun makeSound() {
        println("$name: Гав-гав-гав")
    }
}