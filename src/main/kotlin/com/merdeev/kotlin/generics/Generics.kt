package com.merdeev.kotlin.generics

fun doGenerics() {
    println()
    println("Generics:")

    val genericClass = GenericClass<Class>().also { println("create genericClass = GenericClass<Class>()") }
    println("genericClass.set(SubClass())").also { genericClass.set(SubClass()) }
    println("genericClass.get() = ${genericClass.get()}")
    println("genericClass.get() + 4 = ${genericClass.get()!! + 4}")

//    val castedGenericClass: GenericClass<Class> = GenericClass<SubClass>()                // can't do so

    val anotherGenericClass = GenericClass<AnotherClass>().also { println("create anotherGenericClass = GenericClass<AnotherClass>()") }
    println("anotherGenericClass.set(AnotherClass())").also { anotherGenericClass.set(AnotherClass()) }
    println("anotherGenericClass.get() = ${anotherGenericClass.get()}")

    val inClass = InClass<Class>().also { println("create inClass = InClass<Class>()") }
    println("inClass.set(SubClass())").also { inClass.set(SubClass()) }
//    println("inClass.get() = ${inClass.get()}")                                           // can't do so

//    val castedInClass: InClass<Class> = InClass<SubClass>()                               // can't do so

    val outClass = OutClass<Class>().init(Class::class.java).also { println("create outClass = OutClass<Class>()") }
//    println("outClass.set(Class())").also { outClass.set(Class()) }                       // can't do so
    println("outClass.get() = ${outClass.get()}")
    println("outClass.get() + 4 = ${outClass.get()!! + 4}")

    val castedOutClass: OutClass<Class> = OutClass<SubClass>().init(SubClass::class.java).also { println("create castedOutClass = OutClass<SubClass>()") }
    castedOutClass.init(AnotherSubClass::class.java).also { println("init castedOutClass with AnotherSubClass") }
    println("castedOutClass.get() = ${castedOutClass.get()}")

    val subclassedOutClass: OutClass<SubClass> = OutClass<SubClass>().init(AnotherSubClass::class.java).also { println("create subclassedOutClass = OutClass<SubClass>()") }
    println("subclassedOutClass.get() = ${subclassedOutClass.get()}")
//    println("subclassedOutClass.get() - 4 = ${subclassedOutClass.get()!! - 4}")           // throws ClassCastException

    val anotherOutClass= OutClass<Class>().init(AnotherClass::class.java).also { println("create anotherOutClass = OutClass<AnotherClass>()") }
    println("anotherOutClass.get() = ${anotherOutClass.get()}")
//    println("anotherOutClass.get() + 4 = ${anotherOutClass.get()!! + 4}")                 // throws ClassCastException

    val boundedClass = BoundedClass<Class>().also { println("create boundedClass = OutBoundedClass<Class>()") }
    println("boundedClass.set(SubClass())").also { boundedClass.set(SubClass()) }
    println("boundedClass.get() = ${boundedClass.get()}")
    println("boundedClass.get() + 4 = ${boundedClass.get()!! + 4}")

//    val castedBoundedClass: BoundedClass<Class> = BoundedClass<SubClass>()                // can't do so

//    val anotherBoundedClass: BoundedClass<AnotherClass> = BoundedClass<AnotherClass>()    // can't do so

    val starProjectedGenericClass: GenericClass<*> = GenericClass<Class>().also { println("create starProjectedGenericClass = GenericClass<Class>()") }
//    println("starProjectedGenericClass.set(Class())").also { starProjectedGenericClass.set(Class()) }     // can't do so cause of 'in Nothing'
    println("starProjectedGenericClass.get() = ${starProjectedGenericClass.get()}")
//    println("starProjectedGenericClass.get() + 4 = ${starProjectedGenericClass.get()!! + 4}")             // can't do so cause of 'out Any?'

    val inProjectedGenericClass: GenericClass<in Class> = GenericClass<Class>().also { println("create inProjectedGenericClass = GenericClass<Class>()") }
    println("inProjectedGenericClass.set(SubClass())").also { inProjectedGenericClass.set(SubClass()) }
    println("inProjectedGenericClass.get() = ${inProjectedGenericClass.get()}")
//    println("inProjectedGenericClass.get() + 4 = ${inProjectedGenericClass.get()!! + 4}")                 // can't do so cause of 'out Any?'

    val outProjectedGenericClass: GenericClass<out Class> = GenericClass<Class>().init(Class::class.java).also { println("create outProjectedGenericClass = GenericClass<Class>()") }
//    println("outProjectedGenericClass.set(Class())").also { outProjectedGenericClass.set(Class()) }       // can't do so cause of 'in Nothing'
    println("outProjectedGenericClass.get() = ${outProjectedGenericClass.get()}")
    println("outProjectedGenericClass.get() + 4 = ${outProjectedGenericClass.get()!! + 4}")

    val starProjectedInClass: InClass<*> = InClass<Class>().also { println("create starProjectedInClass = InClass<Class>()") }
//    println("starProjectedInClass.set(SubClass())").also { starProjectedInClass.set(Class()) }            // can't do so cause of 'in Nothing'

    val starProjectedOutClass: OutClass<*> = OutClass<Class>().init(Class::class.java).also { println("create starProjectedOutClass = OutClass<Class>()") }
    println("starProjectedOutClass.get() = ${starProjectedOutClass.get()}")
//    println("starProjectedOutClass.get() + 4 = ${starProjectedOutClass.get()!! + 4}")                     // can't do so cause of 'out Any?'

    val starProjectedBoundedClass: BoundedClass<*> = BoundedClass<Class>().init(Class::class.java).also { println("create starProjectedBoundedClass = OutBoundedClass<Class>()") }
//    println("starProjectedBoundedClass.set(Class())").also { starProjectedBoundedClass.set(Class()) }     // can't do so cause of 'in Nothing'
    println("starProjectedBoundedClass.get() = ${starProjectedBoundedClass.get()}")
    println("starProjectedBoundedClass.get() + 4 = ${starProjectedBoundedClass.get()!! + 4}")

    val inProjectedBoundedClass: BoundedClass<in Class> = BoundedClass<Class>().also { println("create inProjectedBoundedClass = OutBoundedClass<Class>()") }
    println("inProjectedBoundedClass.set(SubClass())").also { inProjectedBoundedClass.set(SubClass()) }
    println("inProjectedBoundedClass.get() = ${inProjectedBoundedClass.get()}")
//    println("inProjectedBoundedClass.get() + 4 = ${inProjectedBoundedClass.get()!! + 4}")                 // can't do so cause of 'out Any?'

    val outProjectedBoundedClass: BoundedClass<out Class> = BoundedClass<Class>().init(Class::class.java).also { println("create outProjectedBoundedClass = OutBoundedClass<Class>()") }
//    println("outProjectedBoundedClass.set(SubClass())").also { outProjectedBoundedClass.set(Class()) }    // can't do so cause of 'in Nothing'
    println("outProjectedBoundedClass.get() = ${outProjectedBoundedClass.get()}")
    println("outProjectedBoundedClass.get() + 4 = ${outProjectedBoundedClass.get()!! + 4}")

    println("genericFunction(\"hi\") = ${genericFunction("hi")}")
//    println("boundedFunction(\"hi\") = ${boundedFunction("hi")}")             // can't do so
    println("boundedFunction(SubClass()) = ${boundedFunction(SubClass())}")
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

    fun init(clazz: java.lang.Class<*>): GenericClass<T> {
        val newOne = clazz.getDeclaredConstructor().newInstance() as T
        list.add(newOne)
        return this
    }

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

    fun init(clazz: java.lang.Class<*>): BoundedClass<T> {
        val newOne = clazz.getDeclaredConstructor().newInstance() as T
        list.add(newOne)
        return this
    }

    fun get(): T? {
        return list.getOrNull(list.size-1)
    }

    fun set(arg: T) {
        list.add(arg)
    }
}

fun <T> genericFunction(arg: T): T {
    return arg
}

fun <T : Class> boundedFunction(arg: T): T {
    return arg
}