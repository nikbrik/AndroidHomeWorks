package com.nikbrik.person

data class Animal(val energy:Int, val weight:Int, val name:String) {
    override fun toString(): String {
        return "$name(energy=$energy, weight=$weight)"
    }
}
