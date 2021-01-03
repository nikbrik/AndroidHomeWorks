package com.nikbrik.battlesimulator

sealed class BattleState{
    class Progress:BattleState()
    class FirstTeamWin:BattleState()
    class SecondTeamWin:BattleState()
    class Draw:BattleState()
}
