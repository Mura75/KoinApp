package com.test.koinapp

import org.junit.Test

import org.junit.Assert.*

/**
 * Example local unit test, which will execute on the development machine (host).
 *
 * See [testing documentation](http://d.android.com/tools/testing).
 */
class ExampleUnitTest {
    @Test
    fun addition_isCorrect() {

        val classB = ClassTestB()
        val classA = ClassA(hello = classB)
        classA.showHello()

        val instance = ClassInstance.getInstance()
    }
}

class ClassA(val hello: Hello) {

    fun showHello() {
        println(hello.createHello())
    }

}

class ClassB() : Hello {

    override fun createHello(): String {
        return "Hello"
    }
}

class ClassTestB(): Hello {

    override fun createHello(): String {
        return "Test"
    }

}

class ClassInstance() {

    companion object {
        fun getInstance() = ClassInstance()
    }

    fun showHello() {
        print("hello world")
    }
}

interface Hello {
    fun createHello(): String
}