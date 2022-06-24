package com.merdeev.kotlin.properties

import kotlin.properties.Delegates
import kotlin.reflect.KProperty

fun doProperties() {
    println()
    println("Properties:")
    val clazz = Class("arg1", "arg2").also { println("create clazz") }
    try {
        println("clazz = $clazz")
    } catch (e: Exception) {
        println("can't read clazz due to unset prop4")
    }
    clazz.prop4 = "argNew".also { println("set clazz.prop4 = $it") }
    try {
        println("clazz = $clazz")
    } catch (e: Exception) {
        println("can't read clazz due to unset prop5")
    }
    clazz.prop5 = "argNew".also { println("set clazz.prop5 = $it") }
    println("clazz = $clazz")
    clazz.arg1 = "argNew".also { println("set clazz.arg1 = $it") }
    println("clazz = $clazz")
    clazz.prop3 = "argNew".also { println("set clazz.prop3 = $it") }
    println("clazz = $clazz")
    clazz.prop5 = "argNew".also { println("set clazz.prop5 = $it") }
    println("clazz = $clazz")
    clazz.prop8 = "argNew".also { println("set clazz.prop8 = $it") }
    println("clazz = $clazz")
    clazz.prop9 = "argNew".also { println("set clazz.prop9 = $it") }
    println("clazz = $clazz")
    clazz.prop9 = "no".also { println("set clazz.prop9 = $it") }
    println("clazz = $clazz")
    clazz.prop10 = "argNew".also { println("set clazz.prop10 = $it") }
    println("clazz = $clazz")
    println(PROP12)
}

class Class(var arg1: Any, private val arg2: Any) {

    private val prop1: Any = "prop1"        // initializes value here

    private val prop2: Any                  // initializes value in init block

    var prop3: Any = "prop3"                // has overrode getter and setter
        get() {
            println("prop3 getter called")
            return "$field $prop1"
        }
        set(value) {
            field = "$value $prop2"
        }

    lateinit var prop4: Any                 // must be initialized before read
    var prop5: Any by Delegates.notNull()   // the same

    var prop6: Any by Delegate()            // delegates getter and setter performance

    private val prop7: Any by lazy {        // initializes value lazily and once
        println("prop6 getter called")
        return@lazy "prop6"
    }

    var prop8: Any by Delegates.observable("prop8") { _, old, new ->        // observes value changing
        println("prop8 changed: $old -> $new")
    }

    var prop9: Any by Delegates.vetoable("prop9") { _, old, new ->          // filters value changing
        run {
            if (new != "no") {
                println("prop9 changed: $old -> $new")
                true
            } else {
                println("prop9 has not been changed")
                false
            }
        }
    }

    @Deprecated("Use 'prop9' instead", ReplaceWith("prop9"))
    var prop10: Any by this::prop9                                                      // replaces to prop9

    companion object{

        const val PROP11 = "PROP11"                                                     // const value
    }

    init {
        prop2 = "prop2"
    }

    override fun toString(): String {
        return "Class(arg1=$arg1, arg2=$arg2, prop1=$prop1, prop2=$prop2, prop3=$prop3, prop4=$prop4, prop5=$prop5, prop6=$prop6, prop7=$prop7, prop8=$prop8, prop9=$prop9, prop10=$prop9, PROP11=$PROP11)"
    }
}

const val PROP12 = "PROP12"

class Delegate {

    private var prop6: Any = ""

    operator fun setValue(thisRef: Class, property: KProperty<*>, value: Any) {
        prop6 = "${property.getter.call(thisRef)} $value"
    }

    operator fun getValue(thisRef: Class, property: KProperty<*>): Any {
        return prop6
    }
}
