package com.merdeev.kotlin

import com.merdeev.kotlin.classes.doClasses
import com.merdeev.kotlin.functions.doFunctions
import com.merdeev.kotlin.properties.doProperties

fun main(args: Array<String>) {
    println("Program arguments: ${args.joinToString()}")

    doClasses()
    doFunctions()
    doProperties()
}

