package com.li695.amaze.maze

class Maze(val width: Int, val height: Int) {
    private val areas = ArrayList<Area>()

    fun buildEmpty(): Maze {
        (0 until width * height).map {
            val area = Area(type = AreaType.Wall, x = getX(it), y = getY(it))
            areas.add(it, area)
        }
        return this
    }

    fun getArea(x: Int, y: Int): Area {
        return areas[getFlat(x, y)]
    }

    private fun getFlat(x: Int, y: Int): Int {
        return y * width + x
    }

    private fun getX(flat: Int): Int {
        return flat % width
    }

    private fun getY(flat: Int): Int {
        val fl: Double = flat.toDouble() / width.toDouble()
        return Math.floor(fl).toInt()
    }
}