strings();

def hello() {
    printf "Hello World!\n"
}

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