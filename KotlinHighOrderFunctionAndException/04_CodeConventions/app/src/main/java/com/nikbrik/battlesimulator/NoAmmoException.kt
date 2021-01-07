package com.nikbrik.battlesimulator

class NoAmmoException(private val ammo:MutableList<Ammo>):Exception() {
    fun getAmmo():MutableList<Ammo>{return ammo}
}