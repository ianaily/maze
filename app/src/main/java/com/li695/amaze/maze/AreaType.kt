package com.li695.amaze.maze

import com.li695.amaze.exceptions.InvalidMapError

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
        override fun toString() = "/"
    },
    Wall {
        override val isPassable: Boolean = false
        override fun toString() = "X"
    };

    abstract val isPassable: Boolean

    companion object {
        fun from(char: String) = when (char) {
            Way.toString() -> Way
            Thread.toString() -> Thread
            Enter.toString() -> Enter
            Exit.toString() -> Exit
            Pass.toString() -> Pass
            Wall.toString() -> Wall
            else -> throw InvalidMapError()
        }
    }
}