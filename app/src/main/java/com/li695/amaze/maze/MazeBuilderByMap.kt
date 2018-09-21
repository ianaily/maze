package com.li695.amaze.maze

import com.li695.amaze.utils.Point

class MazeBuilderByMap(map: String) {
    private val lines = map.split("\n")
    private val maze: Maze = Maze.buildEmpty(calcWidth(), calcHeight())

    private fun calcWidth() = lines.first().length
    private fun calcHeight() = lines.count()

    private fun defineMaze(): MazeBuilderByMap {
        (0 until maze.width * maze.height).map {
            val x = maze.getX(it)
            val y = maze.getY(it)
            val char = lines[y][x].toString()
            val type = AreaType.from(char)
            if (type == AreaType.Enter)
                maze.enter = Point(x, y)
            if (type == AreaType.Exit)
                maze.exit = Point(x, y)
            maze.setAreaType(x, y, type)
        }
        return this
    }

    companion object {
        fun build(map: String): Maze {
            val builder = MazeBuilderByMap(map)
                    .defineMaze()
            return builder.maze
        }
    }
}
