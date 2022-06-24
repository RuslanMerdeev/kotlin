package com.merdeev.kotlin.lambdas

import java.lang.RuntimeException

fun doLambdas() {
    println()
    println("Lambdas:")
    prop1.toImplement("prop1")
    prop2.toImplement("prop2")
    prop3("prop3")
    forLambda(prop3)
    prop4("prop4")
    forLambda(prop4)
    prop5("prop5")
    forLambda(prop5)
    prop6("prop6")
    forLambda(prop6)
    try {
        prop7("prop7")
    } catch (e: Exception) {
        println(e.message)
    }
}

val prop1 = object : Interface {                            // implements interface
    override fun toImplement(arg: Any) {
        println("$arg")
    }
}

val prop2 = FunInterface { println("$it") }                 // implements functional interface (or SAM)

val prop3: (Any) -> Unit = ::toImplement                    // converts function to lambda

val prop4 = { arg: Any -> println("$arg") }                 // implements lambda with type (Any) -> Unit
val prop5: (Any) -> Unit = { arg -> println("$arg") }       // the same
val prop6: (Any) -> Unit = { println("$it") }               // the same

val prop7: (Any) -> Nothing = { throw RuntimeException("$it") }     // implements lambda with type (Any) -> Nothing



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