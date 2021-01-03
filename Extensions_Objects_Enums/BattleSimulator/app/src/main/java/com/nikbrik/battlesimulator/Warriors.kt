package com.nikbrik.battlesimulator

class CannonFodder:AbstractWarrior(10,1,2,Weapons.createPistol()){

}

class Soldier:AbstractWarrior(30,3,4,Weapons.createRifle()){

}

class Veteran:AbstractWarrior(60,5,6,Weapons.createAutoRifle()){

}

class KillingMachine:AbstractWarrior(100,7,8,Weapons.createMachineGun()){

}