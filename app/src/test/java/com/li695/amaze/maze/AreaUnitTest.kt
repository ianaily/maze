package com.li695.amaze.maze

import com.li695.amaze.utils.Point
import org.junit.Assert.assertEquals
import org.junit.Test

class AreaUnitTest {
    @Test
    fun constructors() {
        val areaA = Area(1, 10, AreaType.Wall)
        assertEquals(areaA.x, 1)
        assertEquals(areaA.y, 10)
        assertEquals(areaA.type, AreaType.Wall)

        val areaB = Area(Point(1, 10), AreaType.Wall)
        assertEquals(areaB.x, 1)
        assertEquals(areaB.y, 10)
        assertEquals(areaB.type, AreaType.Wall)
    }

    @Test
    fun point() {
        val point = Point(1, 10)
        val area = Area(point, AreaType.Wall)
        assertEquals(area.point.x, point.x)
        assertEquals(area.point.y, point.y)
    }
}

