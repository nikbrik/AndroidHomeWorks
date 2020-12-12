package com.nikbrik.owninfo

fun main() {
    val firstName = "Nikita"
    val lastName = "Shuteev"
    var height = 181
    val weight = 76.5F
    var isChild = height<150 || weight<40
    var info = "My name is $firstName $lastName. My height is $height cm., my weight is $weight kg. and it means that I am ${if(isChild) "a child" else "an adult"}"
    println(info)
    height = 120
    isChild = height<150 || weight<40
    info = "My name is $firstName $lastName. My height is $height cm., my weight is $weight kg. and it means that I am ${if(isChild) "a child" else "an adult"}"
    println(info)
}