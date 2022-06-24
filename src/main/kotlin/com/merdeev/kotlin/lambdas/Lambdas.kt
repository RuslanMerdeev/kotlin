package com.merdeev.kotlin.lambdas

import java.lang.RuntimeException

fun doLambdas() {
    println()
    println("Lambdas:")
    prop1.toImplement("prop1")
    prop2("prop2")
    forLambda(prop2)
    prop3("prop3")
    forLambda(prop3)
    prop4("prop4")
    forLambda(prop4)
    try {
        prop5("prop5")
    } catch (e: Exception) {
        println(e.message)
    }
    prop6.toImplement("prop6")
    prop7("prop7")
    forLambda(prop7)
    prop8("prop8")
    forLambda(prop8)
    prop9 { toImplement1("prop9") }
    prop9 { toImplement2("prop9") }
}

val prop1 = object : Interface {                            // implements interface
    override fun toImplement(arg: Any) {
        println("$arg")
    }
}

val prop2 = { arg: Any -> println("$arg") }                 // implements lambda with type (Any) -> Unit
val prop3: (Any) -> Unit = { arg -> println("$arg") }       // the same
val prop4: (Any) -> Unit = { println("$it") }               // the same

val prop5: (Any) -> Nothing = { throw RuntimeException("$it") }     // implements lambda with type (Any) -> Nothing


val prop6 = FunInterface { println("$it") }                 // implements functional interface (or SAM)

val prop7: (Any) -> Unit = ::toImplement                    // converts reference to function to lambda

val prop8: (Any) -> Unit = fun (arg: Any) {                 // converts anonymous function to lambda
    println("$arg")
}

val prop9: (Class.() -> Unit) -> Unit =                     // converts anonymous function with receiver to lambda
    fun(init: Class.() -> Unit) {
        Class().init()
    }

fun forLambda(lambda: (Any) -> Unit) {
    lambda("lambda")
}

interface Interface {

    fun toImplement(arg: Any)
}

fun interface FunInterface {

    fun toImplement(arg: Any)
}

fun toImplement(arg: Any) {
    println("$arg")
}

class Class {

    fun toImplement1(arg: Any) {
        println("$arg 1")
    }

    fun toImplement2(arg: Any) {
        println("$arg 2")
    }
}