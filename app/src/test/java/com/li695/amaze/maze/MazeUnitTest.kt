package com.li695.amaze.maze

import com.li695.amaze.utils.Point
import org.junit.Assert.assertEquals
import org.junit.Test

class MazeUnitTest {
    @Test
    fun emptyMaze() {
        val maze = Maze.buildEmpty(width = 10, height = 15)
        assertEquals(maze.width, 10)
        assertEquals(maze.height, 15)
        (0 until maze.height).map { y: Int ->
            (0 until maze.width).map { x: Int ->
                val type = maze.getAreaType(x, y)
                assertEquals(type, AreaType.Wall)
            }
        }
    }

    @Test
    fun settersGetters() {
        val maze = Maze.buildEmpty(width = 10, height = 15)
        maze.setAreaType(1, 5, AreaType.Pass)
        assertEquals(maze.getAreaType(1, 5), AreaType.Pass)

        val point = Point(8, 10)
        maze.setAreaType(point, AreaType.Pass)
        assertEquals(maze.getAreaType(point), AreaType.Pass)
    }

    @Test
    fun enterExit() {
        val maze = Maze.buildEmpty(width = 10, height = 15)

        val enter = Point(8, 10)
        maze.enter = enter
        assertEquals(maze.getAreaType(enter), AreaType.Enter)

        val exit = Point(5, 6)
        maze.exit = exit
        assertEquals(maze.getAreaType(exit), AreaType.Exit)
    }
}

