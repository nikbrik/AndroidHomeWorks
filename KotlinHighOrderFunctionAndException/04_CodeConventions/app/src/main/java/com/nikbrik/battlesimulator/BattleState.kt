package com.nikbrik.battlesimulator

sealed class BattleState {

    class Progress(private val battle: Battle) : BattleState() {
        override fun toString(): String {
            var returnString = "Состояние команды 1:" + System.lineSeparator()
            returnString += battle.getFirstTeamWarriorsState() + System.lineSeparator()
            returnString += "Состояние команды 2:" + System.lineSeparator()
            returnString += battle.getSecondTeamWarriorsState() + System.lineSeparator()
            return returnString
        }
    }

    class FirstTeamWin : BattleState() {
        override fun toString(): String {
            return "Команда 1 победила"
        }
    }

    class SecondTeamWin : BattleState() {
        override fun toString(): String {
            return "Команда 2 победила"
        }
    }

    class Draw : BattleState() {
        override fun toString(): String {
            return "Ничья. Все погибли."
        }
    }
}
