package com.li695.amaze.utils

import org.junit.Assert.*
import org.junit.Test

class PointUnitTest {
    @Test
    fun constructors() {
        val pointA = Point(11, 50)
        assertEquals(pointA.x, 11)
        assertEquals(pointA.y, 50)

        val pointB = Point()
        assertEquals(pointB.x, 0)
        assertEquals(pointB.y, 0)

        val pointC = Point(pointA)
        assertEquals(pointC.x, 11)
        assertEquals(pointC.y, 50)
    }

    @Test
    fun offsetX() {
        val point = Point(10, 10)
        point.offsetX(1)
        assertEquals(point.x, 11)
        assertEquals(point.y, 10)

        point.offsetX(-1)
        assertEquals(point.x, 10)
        assertEquals(point.y, 10)
    }

    @Test
    fun offsetY() {
        val point = Point(10, 10)
        point.offsetY(1)
        assertEquals(point.x, 10)
        assertEquals(point.y, 11)

        point.offsetY(-1)
        assertEquals(point.x, 10)
        assertEquals(point.y, 10)
    }

    @Test
    fun offset() {
        val point = Point(10, 10)
        point.offset(1, 1)
        assertEquals(point.x, 11)
        assertEquals(point.y, 11)

        point.offset(-1, -1)
        assertEquals(point.x, 10)
        assertEquals(point.y, 10)

        point.offset(1, 0)
        assertEquals(point.x, 11)
        assertEquals(point.y, 10)
    }

    @Test
    fun offsetsWithDirections() {
        val point = Point(10, 10)
        point.toTop()
        assertEquals(point.x, 10)
        assertEquals(point.y, 9)

        point.toRight()
        assertEquals(point.x, 11)
        assertEquals(point.y, 9)

        point.toBottom()
        assertEquals(point.x, 11)
        assertEquals(point.y, 10)

        point.toLeft()
        assertEquals(point.x, 10)
        assertEquals(point.y, 10)

        point.toTop().toRight().toBottom().toLeft()
        assertEquals(point.x, 10)
        assertEquals(point.y, 10)
    }

    @Test
    fun equalsIsCorrect() {
        val pointA = Point(10, 10)
        val pointB = Point(10, 10)
        val pointC = Point(10, 11)
        assertTrue(pointA == pointB)
        assertTrue(pointA != pointC)
        assertFalse(pointA == pointC)
    }

    @Test
    fun toStringIsCorrect() {
        val point = Point(10, 11)
        val expected = point.toString()
        assertEquals(expected, "x:10, y:11")
    }
}

