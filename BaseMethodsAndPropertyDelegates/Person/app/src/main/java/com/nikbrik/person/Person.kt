package com.nikbrik.person

import kotlin.properties.ReadOnlyProperty
import kotlin.random.Random
import kotlin.reflect.KProperty

class PersonAnimalsReader : ReadOnlyProperty<Person, HashSet<Animal>>{
    private val animals = hashSetOf<Animal>()
    override fun getValue(thisRef: Person, property: KProperty<*>): HashSet<Animal> {
            var info = ""
            info+="Person ${thisRef.name} and pets:"+System.lineSeparator()
                animals.forEach(){
                info+=it.toString()+System.lineSeparator()
            }
            println(info)
            return animals
    }

}

class Person(val height:Int, val weight:Int, val name:String) {

    private val pets by PersonAnimalsReader()

    fun buyPet(){
        pets.add(
            Animal(Random.nextInt(10)+1,
            Random.nextInt(15)+1,
            name = when(Random.nextInt(5)){
                0->"Dog"
                1->"Cat"
                2->"Turtle"
                3->"Fish"
                else->"Rabbit"
            })
        )
    }

    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (javaClass != other?.javaClass) return false

        other as Person

        if (height != other.height) return false
        if (weight != other.weight) return false
        if (name != other.name) return false
        if (pets != other.pets) return false

        return true
    }

    override fun hashCode(): Int {
        var result = height
        result = 31 * result + weight
        result = 31 * result + name.hashCode()
        result = 31 * result + pets.hashCode()
        return result
    }

    override fun toString() = name
    //override fun toString(): String {
    //        return "Person(name='$name', height=$height, weight=$weight, pets=$pets)"
    //}
}
