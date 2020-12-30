package com.nikbrik.zoo

import kotlin.random.Random

abstract class Animal(name: String, weight: Int, energy: Int ):AgedAnimal() {
    var energy: Int = energy
        private set(energy: Int){field = energy}
        get(){return field}
    var weight: Int = weight
        private set(weight: Int){field = weight}
        get(){return field}
    val name: String = name
    var age: Int = 0
        private set(age: Int){field = age}
        get(){return field}
    val isTooOld: Boolean
        get() {
            if (age>=maxAge){
                println("$name умерло")
                return true
            }
            return false
        }
    //Случайное увеличение возраста на 1
    private fun incrementAgeSometimes(){
        if(Random.nextBoolean() && !isTooOld) age++
    }
    //Иммитирует сон
    fun sleep(){
        if (!isTooOld) {
            energy+=5
            age++
            println("$name спит")
        }
    }
    //Иммитирует процесс потребления пищи
    fun eat(){
        if (!isTooOld) {
            energy += 3
            weight++
            incrementAgeSometimes()
        }
        println("$name ест")
    }
    //Иммитирует процесс движения
    open fun move(moveType:String = "двигается"){
        //Если животное не слишком старое, достаточно энергии и веса
        if (!isTooOld && energy>=5 && weight>0) {
            energy-=5
            weight--
            incrementAgeSometimes()
            println("$name $moveType")
        }
    }
    //Рождение нового животного
    open fun makeChild():Animal
    {
        return object:Animal(name,weight,energy) {
            override val maxAge: Int = this@Animal.maxAge
            init {
                printChildInfo()
            }
        }
    }
    protected fun printChildInfo(){
        println("Рождено ${name.toLowerCase()}. Срок жизни $maxAge, вес $weight, запас энергии $energy")
    }

}