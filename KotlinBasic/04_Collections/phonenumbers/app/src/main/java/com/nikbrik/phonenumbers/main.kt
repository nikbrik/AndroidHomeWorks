package com.nikbrik.phonenumbers

fun main() {
    println("Enter N:")
    readLine()?.toIntOrNull()?.let {
        println("You enter number $it")

        val phoneNumberList = getPhoneNumberList(it)
        printListOfRussianPhoneNumbers(phoneNumberList)
        printUniquePhoneNumbers(phoneNumberList)
        printSumOfTheLengthOfAllStrings(phoneNumberList)
        printUserData(phoneNumberList)

    }?: run{println("Incorrect number")}
}

fun printListOfRussianPhoneNumbers(phoneNumberList: MutableList<String>){
    println("List of russian phone numbers:")
    phoneNumberList.filter {it.startsWith("+7")}.forEach(){println(it)}
}

fun printUniquePhoneNumbers(phoneNumberList: MutableList<String>){
    println("Unique phone numbers: ${phoneNumberList.toSet().size}")
}

fun printSumOfTheLengthOfAllStrings(phoneNumberList: MutableList<String>){
    println("Sum of the length of all strings: ${phoneNumberList.sumBy {it.length}}")
}

fun printUserData(phoneNumberList: MutableList<String>){
    val mapOfPhoneNumbers = mutableMapOf<String,String>()
    phoneNumberList.forEach(){
        mapOfPhoneNumbers.put(
                it.substringBefore(":").trim(), //Строка до двоеточия - номер телефона
                it.substringAfter(":").trim()) //После двоеточия - имя пользователя
    }
    for (phoneNumber in mapOfPhoneNumbers) {
        println("Человек: ${phoneNumber.value}. Номер телефона: ${phoneNumber.key}")
    }
}

fun getPhoneNumberList(N: Int): MutableList<String>{
    val phoneNumberList = mutableListOf<String>()
    for (i in 1..N) {
        print("Введите имя человека с номером телефона ")
        phoneNumberList.add(readLine()?:"")
    }
    return phoneNumberList
}