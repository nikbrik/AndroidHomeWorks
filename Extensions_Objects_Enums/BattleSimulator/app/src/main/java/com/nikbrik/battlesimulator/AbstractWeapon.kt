package com.nikbrik.battlesimulator

import kotlin.random.Random

abstract class AbstractWeapon(
    private val clipSize:Int,
    private val fireType: FireType) {

    lateinit var ammo: MutableList<Ammo>
    val clipIsEmpty:Boolean
        get(){return if (::ammo.isInitialized) ammo.isEmpty() else true}

    abstract fun createAmmo():Ammo
    fun reload(){
        ammo = mutableListOf()
        for(i in 1..clipSize) ammo.add(createAmmo())
    }
    fun getBullets(): List<Ammo>{
        val returnBullets = mutableListOf<Ammo>()
        return when(fireType){
            is FireType.SingleMode -> {
                    if (ammo.isNotEmpty()) {
                        val firstBullet = ammo.first()
                        returnBullets.add(firstBullet)
                        ammo.remove(firstBullet)
                }
                returnBullets
            }
            is FireType.BurstMode -> {
                for (i in 1..fireType.queueSize) {
                    if (ammo.isNotEmpty()) {
                        val firstBullet = ammo.first()
                        returnBullets.add(firstBullet)
                        ammo.remove(firstBullet)
                    }
                }
                returnBullets
            }
        }
    }
}