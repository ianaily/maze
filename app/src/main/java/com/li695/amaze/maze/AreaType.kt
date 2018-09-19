package com.li695.amaze.maze

enum class AreaType {
    Way {
        override val isPassable: Boolean = true
        override fun toString() = "."
    },
    Thread {
        override val isPassable: Boolean = true
        override fun toString() = " "
    },
    Enter {
        override val isPassable: Boolean = true
        override fun toString() = "i"
    },
    Exit {
        override val isPassable: Boolean = true
        override fun toString() = "o"
    },
    Pass {
        override val isPassable: Boolean = true
        override fun toString() = ","
    },
    Wall {
        override val isPassable: Boolean = false
        override fun toString() = "X"
    };

    abstract val isPassable: Boolean
}