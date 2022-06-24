package com.merdeev.kotlin.functions

fun doFunctions() {
    println()
    println("Functions:")
    println(function())
    println(function("arg"))
    println(456 infix 789)
    println(Class() infix 789)
}

fun function() = "function"                                                         // function

fun function(arg: Any) = "function with arg = $arg"                                 // function with argument

infix fun Any.infix(arg: Any): Any = "infix function for $this with arg = $arg"     // infix function

class Class {                                                                       // infix function for Class

    private val value = 456

    infix fun infix(arg: Any): Any = "infix function for $this with arg = $arg"

    override fun toString(): String {
        return value.toString()
    }
}