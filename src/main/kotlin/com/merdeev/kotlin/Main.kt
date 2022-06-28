package com.merdeev.kotlin

import com.merdeev.kotlin.classes.doClasses
import com.merdeev.kotlin.functions.doFunctions
import com.merdeev.kotlin.properties.doProperties
import com.merdeev.kotlin.lambdas.doLambdas
import com.merdeev.kotlin.operators.doOperators
import com.merdeev.kotlin.generics.doGenerics
import com.merdeev.kotlin.coroutines.doCoroutines

fun main(args: Array<String>) {
    println("Program arguments: ${args.joinToString()}")

//    doClasses()
//    doFunctions()
//    doProperties()
//    doLambdas()
//    doOperators()
//    doGenerics()
    doCoroutines()
}

