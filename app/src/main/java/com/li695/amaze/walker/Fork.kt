package com.li695.amaze.walker

import com.li695.amaze.utils.Point

data class Fork(val point: Point,
                var top: Point? = null,
                var right: Point? = null,
                var bottom: Point? = null,
                var left: Point? = null) {
    val count: Int
        get() {
            var value = 0
            if (top != null) value += 1
            if (right != null) value += 1
            if (bottom != null) value += 1
            if (left != null) value += 1
            return value
        }

    fun isEmpty() = count == 0

    fun pop(): Point? {
        var value: Point? = null
        when {
            top != null -> {
                value = top
                top = null
            }
            right != null -> {
                value = right
                right = null
            }
            bottom != null -> {
                value = bottom
                bottom = null
            }
            left != null -> {
                value = left
                left = null
            }
        }
        return value
    }

    fun initTop() {
        top = Point(point).toTop()
    }

    fun initRight() {
        right = Point(point).toRight()
    }

    fun initBottom() {
        bottom = Point(point).toBottom()
    }

    fun initLeft() {
        left = Point(point).toLeft()
    }

    override fun toString(): String {
        return "(point: $point, top: $top, right: $right, bottom: $bottom, left: $left)"
    }
}
