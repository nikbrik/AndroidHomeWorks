package com.nikbrik.battlesimulator

object Weapons {
    fun createPistol(): AbstractWeapon {
        return object : AbstractWeapon(18, FireType.SingleMode) {
            init {
                reload()
            }

            override fun createAmmo(): Ammo {
                return Ammo.PISTOL
            }
        }
    }

    fun createRifle(): AbstractWeapon {
        return object : AbstractWeapon(10, FireType.SingleMode) {
            init {
                reload()
            }

            override fun createAmmo() :Ammo {
                return Ammo.RIFLE}
        }
    }

    fun createAutoRifle(): AbstractWeapon {
        return object : AbstractWeapon(20, FireType.BurstMode(3)) {
            init {
                reload()
            }

            override fun createAmmo(): Ammo {
                return Ammo.RIFLE
            }
        }
    }

    fun createMachineGun(): AbstractWeapon {
        return object : AbstractWeapon(30, FireType.BurstMode(8)) {
            init {
                reload()
            }

            override fun createAmmo(): Ammo {
                return Ammo.MACHINEGUN
            }
        }
    }
}
