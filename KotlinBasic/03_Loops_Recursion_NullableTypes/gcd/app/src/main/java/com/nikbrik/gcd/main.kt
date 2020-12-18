package com.nikbrik.gcd

fun main() {
    println("Введите N:")
    val n = readLine()?.toIntOrNull()
    if (n==null){
        println("N не является числом")
    }else{
        for (i in 1..n){
            readLine()?.toIntOrNull()
        }
    }
}