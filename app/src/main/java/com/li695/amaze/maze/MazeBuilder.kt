package com.li695.amaze.maze

import com.li695.amaze.utils.Point
import java.util.*

class MazeBuilder(private val maze: Maze) {
    fun generateInOutPoints(): MazeBuilder {
        val exit = generateBorderPoint()
        var enter = Point()
        var needToRegenerate = true
        while (needToRegenerate) {
            enter = generateBorderPoint()
            val horizontalDistanceIsCorrect = Math.abs(enter.x - exit.x) < maze.height / 3
            val verticalDistanceIsCorrect = Math.abs(enter.y - exit.y) < maze.width / 3
            val distanceIsCorrect = horizontalDistanceIsCorrect && verticalDistanceIsCorrect
            val isSame = enter.x == exit.x || enter.y == exit.y
            needToRegenerate = isSame || distanceIsCorrect
        }
        maze.enter = enter
        maze.exit = exit
        return this
    }

    fun generateMainPath(): MazeBuilder {
        val enterPort = getPortPoint(maze.enter)
        val exitPort = getPortPoint(maze.exit)
        maze.setAreaType(enterPort, AreaType.Thread)
        maze.setAreaType(exitPort, AreaType.Thread)
        generatePath(enterPort, exitPort, AreaType.Thread, calcMinMoves())
        return this
    }

    private fun generateBorderPoint(): Point {
        val point = Point()
        val random = Random()
        val isHorizontal = random.nextBoolean()
        if (isHorizontal) {
            point.y = random.nextInt(maze.width - 3) + 2
            val isTop = random.nextBoolean()
            point.x = if (isTop) 0 else maze.height - 1
        } else {
            point.x = random.nextInt(maze.height - 3) + 2
            val isRight = random.nextBoolean()
            point.y = if (isRight) 0 else maze.width - 1
        }
        return point
    }

    private fun getPortPoint(point: Point): Point = when {
        point.x == 0 -> Point(1, point.y)
        point.y == 0 -> Point(point.x, 1)
        point.x == maze.height - 1 -> Point(point.x - 1, point.y)
        point.y == maze.width - 1 -> Point(point.x, point.y - 1)
        else -> Point(point)
    }

    private fun calcMinMoves(): Int {
        val minMoves = Math.abs(maze.enter.x - maze.exit.x) + Math.abs(maze.enter.y - maze.exit.y)
        val someVar = Random().nextInt(maze.height) + 1
        val someRule = (maze.width * (someVar / 2))
        return someRule + minMoves
    }

    private fun generatePath(current: Point, end: Point, type: AreaType, stepsCount: Int) {
        val distance = Math.abs(end.y - current.y) + Math.abs(end.x - current.x)
        if (stepsCount > distance) {
            val chance = Random().nextInt(105)
            when (chance) {
                in 0..25 -> initBottomArea(current, type)
                in 26..50 -> initTopArea(current, type)
                in 51..75 -> initLeftArea(current, type)
                else -> initRightArea(current, type)
            }
        } else {
            initNextArea(current, end, type)
        }
        if (stepsCount > 0)
            generatePath(current, end, type, stepsCount - 1)
    }

    private fun initNextArea(current: Point, end: Point, type: AreaType) {
        when {
            current.y > end.y -> maze.setAreaType(current.toTop(), type)
            current.y < end.y -> maze.setAreaType(current.toBottom(), type)
            current.x < end.x -> maze.setAreaType(current.toRight(), type)
            current.x > end.x -> maze.setAreaType(current.toLeft(), type)
        }
    }

    private fun initBottomArea(current: Point, type: AreaType) {
        val isBorder = current.y + 1 >= maze.width - 1 ||
                current.x + 1 >= maze.height - 1 ||
                current.x - 1 < 1
        if (isBorder) return
        val notSameBottomRight = maze.getAreaType(current.x + 1, current.y + 1) != type
        val notSameBottomLeft = maze.getAreaType(current.x - 1, current.y + 1) != type
        if (notSameBottomRight && notSameBottomLeft)
            maze.setAreaType(current.toBottom(), type)
    }

    private fun initTopArea(current: Point, type: AreaType) {
        val isBorder = current.y - 1 < 1 ||
                current.x + 1 >= maze.height - 1 ||
                current.x - 1 < 1
        if (isBorder) return
        val notSameTopRight = maze.getAreaType(current.x + 1, current.y - 1) != type
        val notSameTopLeft = maze.getAreaType(current.x - 1, current.y - 1) != type
        if (notSameTopRight && notSameTopLeft)
            maze.setAreaType(current.toTop(), type)
    }

    private fun initLeftArea(current: Point, type: AreaType) {
        val isBorder = current.x - 1 < 1 ||
                current.y + 1 >= maze.width - 1 ||
                current.y - 1 < 1
        if (isBorder) return
        val notSameTopLeft = maze.getAreaType(current.x - 1, current.y - 1) != type
        val notSameBottomLeft = maze.getAreaType(current.x - 1, current.y + 1) != type
        if (notSameTopLeft && notSameBottomLeft)
            maze.setAreaType(current.toLeft(), type)
    }

    private fun initRightArea(current: Point, type: AreaType) {
        val isBorder = current.x + 1 >= maze.height - 1 ||
                current.y + 1 >= maze.width - 1 ||
                current.y - 1 < 1
        if (isBorder) return
        val notSameTopRight = maze.getAreaType(current.x + 1, current.y - 1) != type
        val notSameBottomRight = maze.getAreaType(current.x + 1, current.y + 1) != type
        if (notSameTopRight && notSameBottomRight)
            maze.setAreaType(current.toRight(), type)
    }

    companion object {
        fun build(width: Int, height: Int): Maze {
            val maze = Maze.buildEmpty(width, height)
            MazeBuilder(maze).apply {
                generateInOutPoints()
                generateMainPath()
            }
            println(maze)
            return maze
        }
    }
}