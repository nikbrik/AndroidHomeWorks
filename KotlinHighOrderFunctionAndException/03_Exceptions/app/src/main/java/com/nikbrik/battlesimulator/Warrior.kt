package com.nikbrik.battlesimulator

interface Warrior {
    val isKilled:Boolean
    val chanceToAvoidBeingHit:Int
    fun attack(warrior: Warrior)
    fun takeDamage(damage:Int)
}