package com.nikbrik.zoo

import kotlin.random.Random

fun main() {
    println("Введите число итераций N для жизни зоопарка:")
    readLine()?.toIntOrNull()?.let {
        val zoo = Zoo()
        val animals = zoo.animals
        for (i in 0..it){
            val newAnimals = mutableListOf<Animal>()
            for (animal in animals){
                with(animal){
                    when(val j:Int = Random.nextInt(5)){
                        0 -> eat()
                        1 -> sleep()
                        2 -> move()
                        3 -> newAnimals.add(makeChild())
                        4 -> (this as? Soundable)?.makeSound()?: println("${this.name} молчит")
                        else -> println("Ничего не происходит")
                    }
                }
                makeDelay()
            }
            animals.removeAll{animal->animal.isTooOld}
            animals.addAll(newAnimals)
            if (animals.isEmpty()) {
                println("Зоопарк пуст. Выполнение программы завершено")
                break
            }
            println("-------------Общее число животных зоопарка: ${animals.count()}")
        }
    }?: run { println("Некорректное число. Программа завершена") }
}

fun makeDelay(){
    Thread.sleep(1000)
}