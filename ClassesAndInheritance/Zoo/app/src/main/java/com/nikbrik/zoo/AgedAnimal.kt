package com.nikbrik.zoo

import kotlin.random.Random

abstract class AgedAnimal (){
    open val maxAge:Int = Random.nextInt(10)+1
}