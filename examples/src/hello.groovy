#!/usr/bin/env groovy

new HelloWorld().setName("WORLD").sayHello()
maps();

def loops() {
    (1..10).each { i ->
        printf "%d ", i
    }
}

def strings() {
    println ""
    def a = 10
    println '$a'
    println "$a"
}

def maps() {
    def map = [:]
    map.'x' = 'X'
    println map.x
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