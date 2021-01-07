package com.nikbrik.battlesimulator

import java.lang.Thread.sleep

fun main() {
    println("Введите количество войнов в команде:")
    readLine()?.toIntOrNull()?.let {
        val battle = Battle(it)
        while(!battle.isCompleted){
            battle.nextIteration()
            println(battle.getBattleState())
            makeDelay()
        }
    }?:run{ println("Введено некорректное число")}
}

fun makeDelay(){
    sleep(1)
}