package com.li695.amaze.maze

import com.li695.amaze.utils.Point
import java.util.*

class Maze internal constructor(val width: Int, val height: Int) {
    internal val areas = ArrayList<Area>()
    var enter = Point()
        internal set(value) {
            setAreaType(value.x, value.y, AreaType.Enter)
            field = value
        }
    var exit = Point()
        internal set(value) {
            setAreaType(value.x, value.y, AreaType.Exit)
            field = value
        }

    fun getAreaType(x: Int, y: Int): AreaType = areas[getFlat(x, y)].type

    fun getAreaType(point: Point): AreaType = getAreaType(point.x, point.y)

    fun setAreaType(point: Point, type: AreaType) {
        setAreaType(point.x, point.y, type)
    }

    fun setAreaType(x: Int, y: Int, type: AreaType) {
        val area = Area(x, y, type)
        areas[getFlat(x, y)] = area
    }

    private fun getFlat(x: Int, y: Int): Int = y * width + x

    internal fun getX(flat: Int): Int = flat % width

    internal fun getY(flat: Int) = Math.floor(flat.toDouble() / width.toDouble()).toInt()

    override fun toString(): String {
        var map = ""
        areas.map {
            if (it.x == 0) map += "\n"
            map += it.type
        }
        return map.trimIndent()
    }

    companion object {
        fun buildEmpty(width: Int, height: Int): Maze {
            val maze = Maze(width, height)
            (0 until maze.width * maze.height).map {
                val area = Area(type = AreaType.Wall, x = maze.getX(it), y = maze.getY(it))
                maze.areas.add(it, area)
            }
            return maze
        }
    }
}