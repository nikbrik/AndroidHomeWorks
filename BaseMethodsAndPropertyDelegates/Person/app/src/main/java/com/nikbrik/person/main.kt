package com.nikbrik.person

fun main() {
    val person1 = Person(180, 80, "Joe")
    val person2 = Person(180, 80, "Joe")
    val person3 = Person(190,90,"Kevin")

    for (i in 1..3) person1.buyPet()
    person2.buyPet()
    person3.buyPet()

    val hashSet = hashSetOf<Person>(person1, person2, person3)
    println("hashSet count = ${hashSet.size}")
    hashSet.forEach() {person ->  println(person)}
}