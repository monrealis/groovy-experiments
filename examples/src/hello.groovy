new HelloWorld().setName("WORLD").sayHello()

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