package com.li695.amaze.walker

import com.li695.amaze.maze.AreaType
import com.li695.amaze.maze.Maze
import com.li695.amaze.utils.Point

abstract class BaseWalker(private val maze: Maze) {
    protected var position: Point = maze.enter
    protected var moveNum = 0

    abstract fun move()

    protected fun checkFinish() = availableTop(AreaType.Exit) ||
            availableRight(AreaType.Exit) ||
            availableBottom(AreaType.Exit) ||
            availableLeft(AreaType.Exit)

    protected fun availableTop(type: AreaType) = try {
        maze.getAreaType(position.x, position.y - 1) == type
    } catch (e: IndexOutOfBoundsException) {
        false
    }

    protected fun availableBottom(type: AreaType) = try {
        maze.getAreaType(position.x, position.y + 1) == type
    } catch (e: IndexOutOfBoundsException) {
        false
    }

    protected fun availableLeft(type: AreaType) = try {
        maze.getAreaType(position.x - 1, position.y) == type
    } catch (e: IndexOutOfBoundsException) {
        false
    }

    protected fun availableRight(type: AreaType) = try {
        maze.getAreaType(position.x + 1, position.y) == type
    } catch (e: IndexOutOfBoundsException) {
        false
    }

    protected fun availableTop() = try {
        maze.getAreaType(position.x, position.y - 1).isPassable
    } catch (e: IndexOutOfBoundsException) {
        false
    }

    protected fun availableBottom() = try {
        maze.getAreaType(position.x, position.y + 1).isPassable
    } catch (e: IndexOutOfBoundsException) {
        false
    }

    protected fun availableLeft() = try {
        maze.getAreaType(position.x - 1, position.y).isPassable
    } catch (e: IndexOutOfBoundsException) {
        false
    }

    protected fun availableRight() = try {
        maze.getAreaType(position.x + 1, position.y).isPassable
    } catch (e: IndexOutOfBoundsException) {
        false
    }
}