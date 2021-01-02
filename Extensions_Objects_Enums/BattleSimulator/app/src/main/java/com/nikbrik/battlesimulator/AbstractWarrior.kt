package com.nikbrik.battlesimulator

abstract class AbstractWarrior(
    private val maxHitPoints:Int,
    override val chanceToAvoidBeingHit: Int,
    private val accuracy:Int,
    private val weapon: AbstractWeapon,

):Warrior {
    private var hitPoints = maxHitPoints
    override fun attack(warrior: Warrior) {
        with(weapon) {
            if (clipIsEmpty) reload()
            else getBullets().forEach(){
                if ((accuracy-warrior.chanceToAvoidBeingHit).isProbably())
                    warrior.takeDamage(it.getCurrentDamage())
            }
        }
    }

    override fun takeDamage(damage: Int) {
        hitPoints-=damage
    }
}