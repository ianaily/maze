package com.li695.amaze.walker

import com.li695.amaze.exceptions.BotLostError
import com.li695.amaze.exceptions.DeadEndError
import com.li695.amaze.maze.AreaType
import com.li695.amaze.maze.Maze
import com.li695.amaze.utils.Point

class BotWalker(private val maze: Maze) : BaseWalker(maze) {
    private val forks = ArrayList<Fork>()

    fun passMaze() {
        val maxMoves = maze.width * maze.height - (maze.width * 2 + maze.height * 2)
        while (!checkFinish()) {
            move()
            render()
            if (moveNum >= maxMoves)
                throw BotLostError()
        }
        println(" - forks (${forks.size})")
        println(" - moves ($moveNum)")
    }

    fun render(): String {
        var a = ""
        maze.areas.map { area ->
            if (area.x == 0) a += "\n"
            a += if (position == area.point) "*"
            else {
                val hasFork = forks.firstOrNull { it.point == area.point }
                if (hasFork != null) "+" else area.type
            }
        }
        println("\n--- Step ($moveNum) ---")
        println(a)
        return a
    }

    override fun move() {
        val fork = getFork()
        position = getPosition(fork)
        maze.setAreaType(position, AreaType.Pass)
        moveNum += 1
        if (fork.count > 0) {
            this.forks.add(fork)
            render()
            println(" - fork (${fork.count})")
            println(" - fork ($fork)")
        }
        removeEmptyForks()
    }

    private fun removeEmptyForks() {
        forks.removeIf {
            val copy = it.copy()
            initForkDirections(copy)
            copy.isEmpty()
        }
    }

    private fun getPosition(fork: Fork) =
            if (fork.isEmpty()) {
                toLastFork()
                if (forks.isEmpty())
                    throw DeadEndError()
                val newPosition = forks.last().pop()
                if (newPosition == null)
                    throw DeadEndError()
                else Point(newPosition)
            } else {
                val newPosition = fork.pop()
                if (newPosition == null)
                    throw DeadEndError()
                else Point(newPosition)
            }

    private fun getFork(): Fork {
        val fork = Fork(point = position)
        initForkDirections(fork)
        return fork
    }

    private fun initForkDirections(fork: Fork) {
        if (availableTop(AreaType.Thread)) fork.initTop()
        if (availableRight(AreaType.Thread)) fork.initRight()
        if (availableBottom(AreaType.Thread)) fork.initBottom()
        if (availableLeft(AreaType.Thread)) fork.initLeft()
    }

    private var targetPoint = maze.exit

    private fun toLastFork() {
        initTargetPoint()
        println(" - back to ($targetPoint)")
        toTarget()
        println(" - out from dead end")
        targetPoint = maze.exit
    }

    private fun toTarget() {
        var attempts = (maze.width + maze.height) * 2
        while (position != targetPoint) {
            when {
                position.x < targetPoint.x && availableRight() ->
                    maze.setAreaType(position.toRight(), AreaType.Pass)
                position.x > targetPoint.x && availableLeft() ->
                    maze.setAreaType(position.toLeft(), AreaType.Pass)
                position.y < targetPoint.y && availableBottom() ->
                    maze.setAreaType(position.toBottom(), AreaType.Pass)
                position.y > targetPoint.y && availableTop() ->
                    maze.setAreaType(position.toTop(), AreaType.Pass)
            }
            attempts -= 1
            render()
            println(" - step back (${100 - attempts})")
            if (attempts < 0)
                throw BotLostError()
        }
    }

    private fun initTargetPoint() {
        if (forks.isEmpty()) {
            println("on move $moveNum")
            render()
            throw DeadEndError()
        }
        targetPoint = forks.last().point
    }
}
