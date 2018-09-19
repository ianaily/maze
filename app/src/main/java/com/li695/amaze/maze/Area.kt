package com.li695.amaze.maze

import com.li695.amaze.utils.Point


data class Area(val x: Int, val y: Int, val type: AreaType) {
    constructor(point: Point, type: AreaType) : this(point.x, point.y, type)

    val point: Point
        get() = Point(x, y)
}
