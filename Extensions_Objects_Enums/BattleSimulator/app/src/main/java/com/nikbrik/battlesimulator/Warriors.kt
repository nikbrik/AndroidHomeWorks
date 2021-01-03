package com.nikbrik.battlesimulator

import kotlin.random.Random

class CannonFodder(teamNumber:Int):AbstractWarrior(10,1,2,Weapons.createPistol()){
    override val name = "Команда №$teamNumber: Пушечное мясо №${Random.nextInt(100000)}(MHP=10)"
}

class Soldier(teamNumber:Int):AbstractWarrior(30,3,4,Weapons.createRifle()){
    override val name = "Команда №$teamNumber: Солдат №${Random.nextInt(100000)}(MHP=30)"
}

class Veteran(teamNumber:Int):AbstractWarrior(60,5,6,Weapons.createAutoRifle()){
    override val name = "Команда №$teamNumber: Ветеран №${Random.nextInt(100000)}(MHP=60)"
}

class KillingMachine(teamNumber:Int):AbstractWarrior(100,7,8,Weapons.createMachineGun()){
    override val name = "Команда №$teamNumber: Машина для убийства №${Random.nextInt(100000)}(MHP=100)"
}