package com.nikbrik.battlesimulator

import kotlin.random.Random

class Team (private val teamNumber:Int, private val warriorCount:Int){
    val warriors:MutableList<AbstractWarrior> = mutableListOf()
    private fun fill(){
        for (i in 1..warriorCount){
            warriors.add(when(Random.nextInt(10)+1){
                in 1..4 -> CannonFodder(teamNumber)
                in 5..7 -> Soldier(teamNumber)
                8,9 -> Veteran(teamNumber)
                else -> KillingMachine(teamNumber)
            })
        }
    }
    fun getWarriorCount():Int{return warriors.filter{!it.isKilled}.count()}
    init {
        fill()
    }
}