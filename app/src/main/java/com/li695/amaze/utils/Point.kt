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

    override fun toString() = "x:$x, y:$y"
}