package com.nikbrik.zoo

import kotlin.random.Random

fun main() {
    var newAnimal = Animal("Лошадь", 10, 4, 10)
    while (true){
        for (i in 0..Random.nextInt(2)) {
            newAnimal.move()
            makeDelay()
            if(newAnimal.isTooOld)
                newAnimal = Animal("Лошадь", 10, 4, 10)
        }
        newAnimal.eat()
        if(newAnimal.isTooOld)
            newAnimal = Animal("Лошадь", 10, 4, 10)
        makeDelay()
        newAnimal.sleep()
        if(newAnimal.isTooOld)
            newAnimal = Animal("Лошадь", 10, 4, 10)
        makeDelay()
    }
}

fun makeDelay(){
    Thread.sleep(1000)
}