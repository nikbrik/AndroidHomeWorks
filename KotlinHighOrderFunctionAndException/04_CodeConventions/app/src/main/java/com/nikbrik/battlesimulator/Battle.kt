package com.nikbrik.battlesimulator

class Battle(warriorCount:Int)

{

    private val firstTeam: Team = Team(1, warriorCount)
    private val secondTeam: Team = Team(2, warriorCount)

    val isCompleted: Boolean
        get() = firstTeam.getWarriorCount() <= 0 || secondTeam.getWarriorCount() <= 0

    fun getBattleState(): BattleState {
        return if (firstTeam.getWarriorCount() <= 0 && secondTeam.getWarriorCount() <= 0)
            BattleState.Draw()
        else if (firstTeam.getWarriorCount() <= 0) BattleState.SecondTeamWin()
        else if (secondTeam.getWarriorCount() <= 0) BattleState.FirstTeamWin()
        else BattleState.Progress(this)
    }

    fun getFirstTeamWarriorsState() = firstTeam.getWarriorsState()
    fun getSecondTeamWarriorsState() = secondTeam.getWarriorsState()

    fun nextIteration() {
        firstTeam.shuffleWarriors()
        secondTeam.shuffleWarriors()
        firstTeam.turnCount = 0
        secondTeam.turnCount = 0
        while (!(firstTeam.turnsOver() && secondTeam.turnsOver())) {
            teamAttack(firstTeam, secondTeam)
            teamAttack(secondTeam, firstTeam)
            firstTeam.turnCount++
            secondTeam.turnCount++}
    }

    private fun teamAttack(attackTeam: Team, defenderTeam: Team) {
        with(attackTeam.getAliveWarriors()) {
            if (!attackTeam.turnsOver() && defenderTeam.getAliveWarriorsCount()> 0)
                get(attackTeam.turnCount).run { // живой воин, у которого находится текущий ход
                    if (!isKilled) {
                        val enemyWarrior = defenderTeam.randomAliveWarrior()
                        println("$this атакует $enemyWarrior")
                        makeDelay()
                        attack(enemyWarrior)
                    }
                }
        }
    }
}
