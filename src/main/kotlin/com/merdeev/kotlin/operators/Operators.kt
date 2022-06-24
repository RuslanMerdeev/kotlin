package com.merdeev.kotlin.operators

fun doOperators() {
    println()
    println("Operators:")
    var prop: Class = Class(5).also { println("create prop = Class(5)") }
    println("prop + 6 = ${prop + 6}")
    println("prop - 2 = ${prop - 2}")
    println("prop * 2 = ${prop * 2}")
    println("prop / 2 = ${prop / 2}")
    println("prop % 2 = ${prop % 2}")
    println("prop++ = ${prop++}")
    println("prop-- = ${prop--}")
    println("prop() = ${prop()}")

}

class Class(var value: Int) {

    override fun toString(): String {
        return "$value"
    }
}

operator fun Class.plus(arg: Int): Int = this.value + arg               // defines plus operator for Class

operator fun Class.minus(arg: Int): Int = this.value - arg              // defines minus operator for Class

operator fun Class.times(arg: Int): Int = this.value * arg              // defines multiply operator for Class

operator fun Class.div(arg: Int): Int = this.value / arg                // defines divide operator for Class

operator fun Class.rem(arg: Int): Int = this.value % arg                // defines remain operator for Class

operator fun Class.inc(): Class = this.also { it.value++ }              // defines increment operator for Class

operator fun Class.dec(): Class = this.also { it.value-- }              // defines decrement operator for Class

operator fun Class.invoke(): String = "invoked"                         // defines invoke operator for Class