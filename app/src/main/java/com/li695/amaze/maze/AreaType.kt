package com.li695.amaze.maze

enum class AreaType {
    Way {
        override val isPassable: Boolean = true
    },
    Thread {
        override val isPassable: Boolean = true
    },
    Enter {
        override val isPassable: Boolean = true
    },
    Exit {
        override val isPassable: Boolean = true
    },
    Pass {
        override val isPassable: Boolean = true
    },
    Wall {
        override val isPassable: Boolean = false
    };

    abstract val isPassable: Boolean
}