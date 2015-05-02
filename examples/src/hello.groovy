#!/usr/bin/env groovy

new HelloWorld().setName("WORLD").sayHello()
operators();

def loops() {
    (1..10).each { i ->
        printf "%d ", i
    }
}

def strings() {
    println ""
    def a = 10
    println '$a'
    println "${-> a}"
}

void collections() {
    def l = [1, 2, 3]
    int[] a = [4, 5, 6]
    Integer[] a2 = [4, 5, 6]
    def m = [1: 2, 3: 4]
    println '' + l + ' ' + l.getClass()
    println '' + a + ' ' + a.getClass()
    println '' + m + ' ' + m.getClass()
    println "$a2 ${a2.getClass()}"
}

void operators() {
    println 2 ?: 1
    println null ?: 1
    println null?.x
    Closure c = this.&addOne
    (1..5).each { i ->
        println c(i)
    }
}

def addOne(n) {
    n + 1
}

class HelloWorld {
    private String name = "World"
    def sayHello() {
        printf "Hello World!\n"
        println "Hello $name!"
    }

    def setName(String name) {
        this.name = name
        this
    }
}