package com.li695.amaze.utils

class Point(var x: Int, var y: Int) {
    constructor() : this(0, 0)
    constructor(point: Point) : this(point.x, point.y)

    fun offsetX(deltaX: Int): Point {
        x += deltaX
        return this
    }

    fun offsetY(deltaY: Int): Point {
        y += deltaY
        return this
    }

    fun offset(deltaX: Int, deltaY: Int) = offsetX(deltaX).offsetY(deltaY)

    fun toTop() = offsetY(-1)
    fun toRight() = offsetX(1)
    fun toBottom() = offsetY(1)
    fun toLeft() = offsetX(-1)

    override operator fun equals(other: Any?) = when (other) {
        other == null -> false
        other !is Point -> false
        else -> x == (other as Point).x && y == other.y
    }

    override fun toString() = "x:$x, y:$y"
    override fun hashCode() = 31 * x + y
}