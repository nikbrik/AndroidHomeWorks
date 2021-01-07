package com.nikbrik.battlesimulator

import kotlin.random.Random

class Team (private val teamNumber:Int, private val warriorCount:Int){

    private val warriors:MutableList<AbstractWarrior> = mutableListOf()
    var turnCount:Int = 0

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
    fun getAliveWarriors():List<AbstractWarrior>{
        return warriors.filter{!it.isKilled}
    }
    fun getAliveWarriorsCount():Int = warriors.filter{!it.isKilled}.count()
    fun shuffleWarriors(){warriors.shuffle()}
    fun getWarriorsState():String{
        var returnString = ""
        warriors.forEach(){
            returnString+="$it HP=${it.hitPoints}; Магазин = ${it.getAmmoCount()} ${if(it.isKilled) "Мёртв" else "Жив"}"+System.lineSeparator()
        }
        return returnString
    }
    fun turnsOver() = turnCount>warriors.filter{!it.isKilled}.count()-1
    fun randomAliveWarrior():AbstractWarrior = getAliveWarriors()[Random.nextInt(getAliveWarriorsCount())]
}