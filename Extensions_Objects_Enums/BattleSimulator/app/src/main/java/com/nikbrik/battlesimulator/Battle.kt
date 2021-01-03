package com.nikbrik.battlesimulator

import kotlin.random.Random

class Battle(warriorCount:Int){
    val firstTeam:Team = Team(1,warriorCount)
    val secondTeam:Team = Team(2,warriorCount)
    val isCompleted:Boolean
        get() = firstTeam.getWarriorCount()<=0 || secondTeam.getWarriorCount()<=0

    fun getBattleState():BattleState {
        return if(firstTeam.getWarriorCount()<=0 && secondTeam.getWarriorCount()<=0)
            BattleState.Draw()
        else if(firstTeam.getWarriorCount()<=0) BattleState.SecondTeamWin()
        else if(secondTeam.getWarriorCount()<=0) BattleState.FirstTeamWin()
        else BattleState.Progress(this)
    }
    fun nextIteration(){
        firstTeam.warriors.shuffle()
        secondTeam.warriors.shuffle()
        val aliveWarriorsOfFirstTeam = firstTeam.warriors.filter{!it.isKilled}
        val aliveWarriorsOfSecondTeam = secondTeam.warriors.filter{!it.isKilled}
        var firstTeamTurnCount = 0
        var secondTeamTurnCount = 0
        while (!(firstTeamTurnCount>aliveWarriorsOfFirstTeam.count()-1
        && secondTeamTurnCount>aliveWarriorsOfSecondTeam.count()-1)){

            with(aliveWarriorsOfFirstTeam){
                if(firstTeamTurnCount<count())
                    get(firstTeamTurnCount).run {
                        if (!isKilled) {
                            val attackedEnemy = aliveWarriorsOfSecondTeam[Random.nextInt(aliveWarriorsOfSecondTeam.count())]
                            println("$this атакует $attackedEnemy")
                            makeDelay()
                            attack(attackedEnemy)
                        }
                    }
            }

            with(aliveWarriorsOfSecondTeam){
                if(secondTeamTurnCount<count())
                    get(secondTeamTurnCount).run {
                        if (!isKilled) {
                            val attackedEnemy = aliveWarriorsOfFirstTeam[Random.nextInt(aliveWarriorsOfFirstTeam.count())]
                            println("$this атакует $attackedEnemy")
                            makeDelay()
                            attack(attackedEnemy)
                        }
                    }
            }

            firstTeamTurnCount++
            secondTeamTurnCount++
        }
    }
}