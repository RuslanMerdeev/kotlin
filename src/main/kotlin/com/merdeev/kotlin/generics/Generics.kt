package com.merdeev.kotlin.generics

fun doGenerics() {
    println()
    println("Generics:")

    val genericClass = GenericClass<Class>().also { println("create genericClass = GenericClass<Class>()") }
    println("genericClass.set(Class())").also { genericClass.set(Class()) }
    println("genericClass.set(SubClass())").also { genericClass.set(SubClass()) }
    println("genericClass.get()").also { println("${genericClass.get()}") }
    println("genericClass.get().plus(4)").also { println("${genericClass.get()!!.plus(4)}") }

//    val anotherGenericClass: GenericClass<Class> = GenericClass<SubClass>()           // can't do so

    val inClass = InClass<Class>().also { println("create inClass = InClass<Class>()") }
    println("inClass.set(SubClass())").also { inClass.set(SubClass()) }
//    println("inClass.get()").also { println("${inClass.get()}") }

//    val anotherInClass: InClass<Class> = InClass<SubClass>()                          // can't do so

    val outClass = OutClass<Class>().init(Class::class.java).also { println("create outClass = OutClass<Class>()") }
//    println("outClass.set(Class())").also { outClass.set(Class()) }
    println("outClass.get()").also { println("${outClass.get()}") }
    println("outClass.get().plus(4)").also { println("${outClass.get()!!.plus(4)}") }

    val anotherOutClass: OutClass<Class> = OutClass<SubClass>().init(SubClass::class.java).also { println("create anotherOutClass = OutClass<SubClass>()") }
    anotherOutClass.init(AnotherSubClass::class.java).also { println("init anotherOutClass with AnotherSubClass") }
    println("anotherOutClass.get()").also { println("${anotherOutClass.get()}") }

    val subclassedOutClass: OutClass<SubClass> = OutClass<SubClass>().init(AnotherSubClass::class.java).also { println("create subclassedOutClass = OutClass<SubClass>()") }
    println("subclassedOutClass.get()").also { println("${subclassedOutClass.get()}") }
//    println("subclassedOutClass.get().minus(4)").also { println("${subclassedOutClass.get()!!.minus(4)}") }       // throws ClassCastException

    val outBoundedClass = OutBoundedClass<Class>().init(Class::class.java).also { println("create outBoundedClass = OutBoundedClass<Class>()") }
    println("outBoundedClass.get()").also { println("${outBoundedClass.get()}") }
    println("outBoundedClass.get().plus(4)").also { println("${outBoundedClass.get()!!.plus(4)}") }

    val anotherBoundedOutClass: OutBoundedClass<Class> = OutBoundedClass<SubClass>().init(SubClass::class.java).also { println("create anotherBoundedOutClass = OutClass<SubClass>()") }
    anotherBoundedOutClass.init(AnotherSubClass::class.java).also { println("init anotherOutClass with AnotherSubClass") }
    println("anotherBoundedOutClass.get()").also { println("${anotherBoundedOutClass.get()}") }

    val subclassedBoundedOutClass: OutBoundedClass<SubClass> = OutBoundedClass<SubClass>().init(AnotherSubClass::class.java).also { println("create subclassedBoundedOutClass = OutClass<SubClass>()") }
    println("subclassedBoundedOutClass.get()").also { println("${subclassedBoundedOutClass.get()}") }
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
        return list.getOrNull(list.size-1)
    }

//    fun set(arg: T) {}            // can't do so cause of 'out T'
}

class OutBoundedClass<out T : Class> {

    private var list: MutableList<T> = mutableListOf()

    fun init(clazz: java.lang.Class<*>): OutBoundedClass<T> {
        val newOne = clazz.getDeclaredConstructor().newInstance() as T
        list.add(newOne)
        return this
    }

    fun get(): T? {
        return list.getOrNull(list.size-1)
    }

//    fun set(arg: T) {}            // can't do so cause of 'out T'
}