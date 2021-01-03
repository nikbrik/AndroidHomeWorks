package com.nikbrik.battlesimulator

fun main() {
    println("Введите количество войнов в команде:")
    readLine()?.toIntOrNull()?.let {
        val battle = Battle(it)
        while(!battle.isCompleted){
            battle.nextIteration()
        }
    }?:run{ println("Введено некорректное число")}
}