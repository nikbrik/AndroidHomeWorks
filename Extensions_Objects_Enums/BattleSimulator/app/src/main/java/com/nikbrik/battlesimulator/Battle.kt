package com.nikbrik.battlesimulator

class Battle(warriorCount:Int){
    private val firstTeam:Team = Team(warriorCount)
    private val secondTeam:Team = Team(warriorCount)
    val isCompleted:Boolean
        get() = firstTeam.getWarriorCount()<=0 || secondTeam.getWarriorCount()<=0

    private fun getBattleState():BattleState {return if(firstTeam.getWarriorCount()<=0 && secondTeam.getWarriorCount()<=0) BattleState.Draw()
        else if(firstTeam.getWarriorCount()<=0) BattleState.SecondTeamWin()
        else if(secondTeam.getWarriorCount()<=0) BattleState.FirstTeamWin()
        else BattleState.Progress()
    }
    fun nextIteration(){}
}