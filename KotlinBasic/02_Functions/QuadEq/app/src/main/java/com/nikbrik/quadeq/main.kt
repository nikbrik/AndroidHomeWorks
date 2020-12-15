package com.nikbrik.qe
import kotlin.math.pow
import kotlin.math.sqrt

fun main() {
    var a:Int = 1
    var b:Int = 2
    var c:Int = 3
    var solutionSum:Double = solveEqual(a = a, b = b, c = c)
    println(if(solutionSum.isNaN()) "" else "solutionSum = $solutionSum"+System.lineSeparator())

    a = 99; b = 152; c = 56
    solutionSum = solveEqual(a = a, b = b, c = c)
    println(if(solutionSum.isNaN()) "" else "solutionSum = $solutionSum"+System.lineSeparator())

    //Здесь я тестирую возможность перестановки аргументов, которая для меня является чем-то необычным
    solutionSum = solveEqual(a = a, c = c, b = b)
    println(if(solutionSum.isNaN()) "" else "solutionSum = $solutionSum"+System.lineSeparator())

    a = 0; b = 152; c = 56
    solutionSum = solveEqual(a = a, b = b, c = c)
    println(if(solutionSum.isNaN()) "" else "solutionSum = $solutionSum"+System.lineSeparator())

    a = 99; b = 0; c = 0
    solutionSum = solveEqual(a = a, b = b, c = c)
    println(if(solutionSum.isNaN()) "" else "solutionSum = $solutionSum"+System.lineSeparator())

    a = 99; b = 0; c = 56
    solutionSum = solveEqual(a = a, b = b, c = c)
    println(if(solutionSum.isNaN()) "" else "solutionSum = $solutionSum"+System.lineSeparator())

    a = 99; b = 0; c = -56
    solutionSum = solveEqual(a = a, b = b, c = c)
    println(if(solutionSum.isNaN()) "" else "solutionSum = $solutionSum"+System.lineSeparator())

    a = 99; b = 152; c = 0
    solutionSum = solveEqual(a = a, b = b, c = c)
    println(if(solutionSum.isNaN()) "" else "solutionSum = $solutionSum"+System.lineSeparator())
}

fun solveEqual(a:Int,b:Int,c:Int):Double {
    println("a = $a, b = $b, c = $c")
    if(a==0){
        println("Если аргумент A равен 0, уравнение нельзя считать квадратным")
        return Double.NaN
    }

    //Здесь рассчитывается дискриминант
    val discriminant:Double = b.toDouble().pow(2)-4*a*c

    if (discriminant<0){
        println("Нет корней")
        return Double.NaN
    }
    val x1:Double = (-b+sqrt(discriminant))/(2*a)  //Расчет корня уравнения x1
    val x2:Double = (-b-sqrt(discriminant))/(2*a)  //Расчет корня уравнения x2
    println("x1 = $x1")
    println("x2 = $x2")
    return x1+x2
}