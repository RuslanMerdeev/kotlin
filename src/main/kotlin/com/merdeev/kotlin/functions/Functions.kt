package com.merdeev.kotlin.functions

fun doFunctions() {
    println()
    println("Functions:")
    println(function())
    println(function("arg"))
}

fun function() = "function"

fun function(arg: Any) = "function with arg = $arg"