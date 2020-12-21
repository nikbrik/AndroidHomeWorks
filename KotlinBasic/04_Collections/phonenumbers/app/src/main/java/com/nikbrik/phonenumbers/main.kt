package com.nikbrik.phonenumbers

fun main() {
    println("Enter N:")
    readLine()?.toIntOrNull()?.let {
        val phoneNumberList = mutableListOf<String>()
        println("You enter number $it")
        for (i in 1..it) {
            println("Enter user phone number #$i:")
            phoneNumberList.add(readLine()?:"")
        }
    }?: run{println("Incorrect number")}
}