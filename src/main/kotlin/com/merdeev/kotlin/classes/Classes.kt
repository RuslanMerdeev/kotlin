package com.merdeev.kotlin.classes

fun doClasses() {
    println()
    println("Classes:")
    println(Class())
    println(Class1("arg"))
}

class Class

class Class1(private val arg: Any) {

    override fun toString(): String {
        return "Class1(arg=$arg)"
    }
}