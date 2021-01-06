package com.nikbrik.battlesimulator

sealed class FireType{
    object SingleMode:FireType()
    data class BurstMode(val queueSize:Int):FireType()
}
