package com.li695.amaze.maze

import com.li695.amaze.utils.Point
import org.junit.Assert.assertEquals
import org.junit.Test

class MazeBuilderByMapUnitTest {
    @Test
    fun simpleMapParse() {
        val maze = MazeBuilderByMap.build(simpleMap)
        assertEquals(maze.width, 5)
        assertEquals(maze.height, 6)
        assertEquals(maze.enter, Point(2, 0))
        assertEquals(maze.exit, Point(2, 5))
        assertEquals(maze.toString(), simpleMap)
    }

    @Test
    fun simpleMapWithForkParse() {
        val maze = MazeBuilderByMap.build(simpleMapWithFork)
        assertEquals(maze.width, 11)
        assertEquals(maze.height, 7)
        assertEquals(maze.enter, Point(5, 0))
        assertEquals(maze.exit, Point(5, 6))
        assertEquals(maze.toString(), simpleMapWithFork)
    }

    @Test
    fun hardMapParse() {
        val maze = MazeBuilderByMap.build(hardMap)
        assertEquals(maze.width, 20)
        assertEquals(maze.height, 18)
        assertEquals(maze.enter, Point(5, 0))
        assertEquals(maze.exit, Point(0, 16))
        assertEquals(maze.toString(), hardMap)
    }

    private val simpleMap = """
XXiXX
XX XX
XX XX
XX XX
XX XX
XXoXX
""".trimIndent()

    private val simpleMapWithFork = """
XXXXXiXXXXX
XXXXX XXXXX
X         X
X XXXXXXX X
X     XXXXX
XXXXX XXXXX
XXXXXoXXXXX
""".trimIndent()

    private val hardMap = """
XXXXXiXXXXXXXXXXXXXX
X   X X   X   XX   X
X XX    X X X XX X X
X XX XXXX X X XX X X
X      XX   X XX X X
X XXXX XXXXXX    X X
X    X     XXXXXXXXX
XXXX XXXX XXX     XX
X       X   X XXX  X
X XXXXX  X XX XXXX X
XXXX  XXX  XX  XX  X
X         XXX X X XX
X XXXXXXX  XX   X XX
X   XXXXXXXXXXX X XX
XXX           X   XX
XXXXXXXXXXXXX  X  XX
o                  X
XXXXXXXXXXXXXXXXXXXX
""".trimIndent()
}