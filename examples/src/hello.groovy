new HelloWorld().sayHello()

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

class HelloWorld {
    def sayHello() {
        printf "Hello World!\n"
    }
}