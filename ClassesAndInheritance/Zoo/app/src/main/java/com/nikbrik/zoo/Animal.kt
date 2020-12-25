package com.nikbrik.zoo

class Animal(name: String, maxAge: Int, weight: Int, energy: Int ) {
    var energy: Int = energy
        private set(energy: Int){field = energy}
        get(){return field}
    var weight: Int = weight
        private set(weight: Int){field = weight}
        get(){return field}
    private val maxAge: Int = maxAge
    val name: String = name
    var age: Int = 0
        private set(age: Int){field = age}
        get(){return field}
//    val isTooOld: Boolean
}