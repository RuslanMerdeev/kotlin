package com.merdeev.kotlin.generics

fun doGenerics() {
    println()
    println("Generics:")

    val genericClass = GenericClass<Class>().also { println("create genericClass = GenericClass<Class>()") }
    println("genericClass.set(Class())").also { genericClass.set(Class()) }
    println("genericClass.set(SubClass())").also { genericClass.set(SubClass()) }
    println("genericClass.get()").also { println("${genericClass.get()}") }
    println("genericClass.get() + 4").also { println("${genericClass.get()!! + 4}") }

//    val castedGenericClass: GenericClass<Class> = GenericClass<SubClass>()                // can't do so

    val anotherGenericClass = GenericClass<AnotherClass>().also { println("create anotherGenericClass = GenericClass<AnotherClass>()") }
    println("anotherGenericClass.set(AnotherClass())").also { anotherGenericClass.set(AnotherClass()) }
    println("anotherGenericClass.get()").also { println("${anotherGenericClass.get()}") }

    val inClass = InClass<Class>().also { println("create inClass = InClass<Class>()") }
    println("inClass.set(SubClass())").also { inClass.set(SubClass()) }
//    println("inClass.get()").also { println("${inClass.get()}") }                         // can't do so

//    val castedInClass: InClass<Class> = InClass<SubClass>()                               // can't do so

    val outClass = OutClass<Class>().init(Class::class.java).also { println("create outClass = OutClass<Class>()") }
//    println("outClass.set(Class())").also { outClass.set(Class()) }                       // can't do so
    println("outClass.get()").also { println("${outClass.get()}") }
    println("outClass.get() + 4").also { println("${outClass.get()!! + 4}") }

    val castedOutClass: OutClass<Class> = OutClass<SubClass>().init(SubClass::class.java).also { println("create castedOutClass = OutClass<SubClass>()") }
    castedOutClass.init(AnotherSubClass::class.java).also { println("init castedOutClass with AnotherSubClass") }
    println("castedOutClass.get()").also { println("${castedOutClass.get()}") }

    val subclassedOutClass: OutClass<SubClass> = OutClass<SubClass>().init(AnotherSubClass::class.java).also { println("create subclassedOutClass = OutClass<SubClass>()") }
    println("subclassedOutClass.get()").also { println("${subclassedOutClass.get()}") }
//    println("subclassedOutClass.get() - 4").also { println("${subclassedOutClass.get()!! - 4}") }         // throws ClassCastException

    val anotherOutClass= OutClass<Class>().init(AnotherClass::class.java).also { println("create anotherOutClass = OutClass<AnotherClass>()") }
    println("anotherOutClass.get()").also { println("${anotherOutClass.get()}") }
//    println("anotherOutClass.get() + 4").also { println("${anotherOutClass.get()!! + 4}") }               // throws ClassCastException

    val boundedClass = BoundedClass<Class>().also { println("create boundedClass = OutBoundedClass<Class>()") }
    println("boundedClass.set(Class())").also { boundedClass.set(Class()) }
    println("boundedClass.set(SubClass())").also { boundedClass.set(SubClass()) }
    println("boundedClass.get()").also { println("${boundedClass.get()}") }
    println("boundedClass.get() + 4").also { println("${boundedClass.get()!! + 4}") }

//    val castedBoundedClass: BoundedClass<Class> = BoundedClass<SubClass>()                // can't do so

//    val anotherBoundedClass: BoundedClass<AnotherClass> = BoundedClass<AnotherClass>()    // can't do so
}

open class Class(var value: Int = 10) {

    open operator fun plus(arg: Int): Int = value + arg

    override fun toString(): String {
        return "$value"
    }
}

class SubClass(private var addValue: Int = 11) : Class() {

    override fun plus(arg: Int): Int = value + addValue + arg

    operator fun minus(arg: Int): Int = value + addValue - arg

    override fun toString(): String {
        return "$value $addValue"
    }
}

class AnotherSubClass(private var addValue: Int = 3) : Class() {

    override fun plus(arg: Int): Int = value + addValue + arg

    override fun toString(): String {
        return "$value $addValue"
    }
}

class AnotherClass {

    override fun toString(): String {
        return "AnotherClass()"
    }
}

class GenericClass<T> {

    private var list: MutableList<T> = mutableListOf()

    fun get(): T? {
        return list.getOrNull(list.size-1)
    }

    fun set(arg: T) {
        list.add(arg)
    }
}

class InClass<in T> {

    private var list: MutableList<T> = mutableListOf()

//    fun get(): T? = null          // can't do so cause of 'in T'

    fun set(arg: T) {
        list.add(arg)
    }
}

class OutClass<out T> {

    private var list: MutableList<T> = mutableListOf()

    fun init(clazz: java.lang.Class<*>): OutClass<T> {
        val newOne = clazz.getDeclaredConstructor().newInstance() as T
        list.add(newOne)
        return this
    }

    fun get(): T? {
        return list.getOrNull(list.size - 1)
    }

//    fun set(arg: T) {}            // can't do so cause of 'out T'
}

class BoundedClass<T : Class> {

    private var list: MutableList<T> = mutableListOf()

    fun get(): T? {
        return list.getOrNull(list.size-1)
    }

    fun set(arg: T) {
        list.add(arg)
    }
}