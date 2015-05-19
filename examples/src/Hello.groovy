#!/usr/bin/env groovy
import com.google.common.collect.HashMultiset
import groovy.text.Template
import groovy.text.markup.MarkupTemplateEngine
import groovy.text.markup.TemplateConfiguration
@Grapes([@Grab("com.google.guava:guava:18.0")])

import groovy.transform.builder.Builder
import groovy.transform.builder.SimpleStrategy
import org.codehaus.groovy.runtime.InvokerHelper

InvokerHelper.runScript(MyScript)
println getString()

private String getString() {
    'a'.concat 'b' concat 'c' toString() concat 'd'
}

private void eval() {
    println new GroovyShell().evaluate('3 * 5')
    println Eval.me('4 * 6')
}

private void callClassLoader() {
    def gcl = new GroovyClassLoader()
    def clazz = gcl.parseClass('class Test { void run() { println "Here" } }')
    def o = clazz.newInstance()
    o.run()
}

private void printTemplate() {
    TemplateConfiguration c = new TemplateConfiguration()
    MarkupTemplateEngine e = new MarkupTemplateEngine(c)
    Template t = e.createTemplate("p{a(href:'#', 'test')}")
    def map = new HashMap<>();
    def w = t.make(map)
    def s = new StringWriter()
    w.writeTo(s)
    println s
}

private void createMultiset() {
    println HashMultiset.create().class
}

private void executeProcess() {
    def p = "ls -l".execute()
    println p.text
}

private void closures() {
    def addOneToSumOfTwo = { i, j -> i + j + 1 }
    def addTwo = addOneToSumOfTwo.curry(1)
    println addTwo(5)
}

def loops() {
    (1..10).each { i ->
        printf "%d ", i
    }
}

def strings() {
    println ""
    def (a, b) = [10, 20]
    println '$a'
    println "${-> b}"
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
    println null?.eval
    Closure c = this.&addOne
    (1..5).each { i ->
        println c(i)
    }
}

def addOne(n) {
    n + 1
}

def regexps() {
    a = ~/eval/
    b = 'eval' =~ a
    c = 'eval' =~ 'eval'
    d = 'eval' ==~ '.'
    println "$a, $b, $c, $d"
}

def otherOperators() {
    a = [3, 4]
    println([1, 2, *a] as String)
}

interface Sayer {
    def sayHello()
}

trait SayerTrait {
    def sayHello() {
        println "Hello from trait"
    }
}

class DefaultSayer implements Sayer, SayerTrait {

}

@Builder(builderStrategy = SimpleStrategy)
class HelloWorld {
    String name = "World"

    def say() {
        printf "Hello World!\n"
        println "Hello $name!"
    }
}