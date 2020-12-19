package com.nikbrik.gcd

import kotlin.math.abs

fun main() {
    println("Введите N:")
    var sum = 0
    var sumPositiveNumber = 0
    readLine()?.toIntOrNull()?.let{
        println("Необходимо ввести $it чисел.")
        var count = 0
        while (count<it){
            println("Введите число ${count+1}:")
            val number = readLine()?.toIntOrNull()?:continue
            sum += number
            sumPositiveNumber += if (number>0) 1 else 0
            count++
            println("Число $count принято.")
        }
        println("Количество введенных положительных чисел: $sumPositiveNumber"
                +System.lineSeparator())
        println("Сумма введенных положительных чисел: $sum")
        println("Введите второе число для вычисления НОД между ним и суммой")
        println(readLine()?.toIntOrNull()?.let{"НОД: ${gcd(sum,it)}"}?:"Введено некорректное число")
    }
}

tailrec fun gcd(a:Int, b:Int):Int = if (b == 0) abs(a) else gcd(b, a % b)
