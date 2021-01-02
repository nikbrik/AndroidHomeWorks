package com.nikbrik.battlesimulator

import kotlin.random.Random

enum class Ammo (
    private val damage:Int,
    private val criticalStrikeChance:Int,
    private val criticalStrikeRatio:Int){

    PISTOL(1,2,2),
    RIFLE(3,4,3),
    MACHINEGUN(7,1,5);

    fun getCurrentDamage():Int {
        return if(criticalStrikeChance.isProbably()) damage*criticalStrikeRatio else damage
    }

}

fun Int.isProbably():Boolean{
    return Random.nextInt(10)+1 <= this
}