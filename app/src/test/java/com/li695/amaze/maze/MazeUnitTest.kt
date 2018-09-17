package com.li695.amaze.maze

import org.junit.Assert.assertEquals
import org.junit.Test

class MazeUnitTest {
    @Test
    fun emptyMaze() {
        val maze = Maze(width = 10, height = 15)
        maze.buildEmpty()
        assertEquals(maze.width, 10)
        assertEquals(maze.height, 15)
        (0 until maze.height).map { y: Int ->
            (0 until maze.width).map { x: Int ->
                val area = maze.getArea(x, y)
                println("-----")
                println(" x,  y: $x, $y\nax, ay: ${area.x}, ${area.y}")
                assertEquals(area.type, AreaType.Wall)
                assertEquals(area.x, x)
                assertEquals(area.y, y)
            }
        }
    }
}

