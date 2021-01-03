package com.nikbrik.battlesimulator

import kotlin.random.Random

class Team (private val warriorCount:Int){
    private val warriors:MutableList<AbstractWarrior> = mutableListOf()
    private fun fill(){
        for (i in 1..warriorCount){
            warriors.add(when(Random.nextInt(10)+1){
                in 1..4 -> CannonFodder()
                in 5..7 -> Soldier()
                8,9 -> Veteran()
                else -> KillingMachine()
            })
        }
    }
    fun getWarriorCount():Int{return warriors.count()}
    init {
        fill()
    }
}