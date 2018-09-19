package com.li695.amaze

import com.li695.amaze.exceptions.BotLostError
import com.li695.amaze.exceptions.DeadEndError
import com.li695.amaze.maze.AreaType
import com.li695.amaze.maze.Maze
import com.li695.amaze.utils.Point

class PassBot(private val maze: Maze) {
    private var botPosition: Point = maze.enter
    private var moveCounts = 0

    fun passMaze() {
        val maxMoves = maze.width * maze.height - (maze.width * 2 + maze.height * 2)
        while (!checkFinish()) {
            move()
            println(maze)
            if (moveCounts >= maxMoves)
                throw BotLostError()
        }
    }

    private fun move() {
        when {
            availableTop(AreaType.Thread) -> botPosition.offsetY(-1)
            availableRight(AreaType.Thread) -> botPosition.offsetX(1)
            availableBottom(AreaType.Thread) -> botPosition.offsetY(1)
            availableLeft(AreaType.Thread) -> botPosition.offsetX(-1)
            else -> throw DeadEndError()
        }
        maze.setAreaType(botPosition, AreaType.Pass)
        moveCounts += 1
    }

    private fun checkFinish() = availableTop(AreaType.Exit) ||
            availableRight(AreaType.Exit) ||
            availableBottom(AreaType.Exit) ||
            availableLeft(AreaType.Exit)

    private fun availableTop(type: AreaType) = try {
        maze.getAreaType(botPosition.x, botPosition.y - 1) == type
    } catch (e: IndexOutOfBoundsException) {
        false
    }

    private fun availableBottom(type: AreaType) = try {
        maze.getAreaType(botPosition.x, botPosition.y + 1) == type
    } catch (e: IndexOutOfBoundsException) {
        false
    }

    private fun availableLeft(type: AreaType) = try {
        maze.getAreaType(botPosition.x - 1, botPosition.y) == type
    } catch (e: IndexOutOfBoundsException) {
        false
    }

    private fun availableRight(type: AreaType) = try {
        maze.getAreaType(botPosition.x + 1, botPosition.y) == type
    } catch (e: IndexOutOfBoundsException) {
        false
    }
}
