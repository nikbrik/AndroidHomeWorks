package com.nikbrik.battlesimulator

abstract class AbstractWarrior(
    private val maxHitPoints: Int,
    override val chanceToAvoidBeingHit: Int,
    private val accuracy: Int,
    private val weapon: AbstractWeapon,
) : Warrior {
    abstract val name: String

    var hitPoints: Int = maxHitPoints
        get() {
            return field
        }
        private set(value) {
            field = value
        }

    override fun attack(warrior: Warrior) {
        with(weapon) {
            try {
                useAmmoOnWarrior(warrior, getBullets())
            } catch (e: NoAmmoException) {
                useAmmoOnWarrior(warrior, e.getAmmo())
                reload()
                println("Перезарядка")
            }
            makeDelay()
        }
    }

    private fun useAmmoOnWarrior(warrior: Warrior, ammo: List<Ammo>) {
        ammo.forEach() {
            if ((accuracy - warrior.chanceToAvoidBeingHit).isProbably())
                warrior.takeDamage(
                    it.getCurrentDamage().also { damage: Int ->
                        println("Нанесен урон $damage")
                    }
                )
            else println("Промах")
        }
    }

    override val isKilled: Boolean get() = hitPoints <= 0

    override fun takeDamage(damage: Int) {
        hitPoints -= damage
    }

    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (javaClass != other?.javaClass) return false

        other as AbstractWarrior

        if (maxHitPoints != other.maxHitPoints) return false
        if (chanceToAvoidBeingHit != other.chanceToAvoidBeingHit) return false
        if (accuracy != other.accuracy) return false
        if (weapon != other.weapon) return false
        if (name != other.name) return false

        return true
    }

    override fun hashCode(): Int {
        var result = maxHitPoints
        result = 31 * result + chanceToAvoidBeingHit
        result = 31 * result + accuracy
        result = 31 * result + weapon.hashCode()
        result = 31 * result + name.hashCode()
        return result
    }

    override fun toString() = name

    fun getAmmoCount(): Int = weapon.ammo.count()
}
