//@Grab(group = 'org.spockframework', module = 'spock-core', version = '1.0-groovy-2.4')
import spock.lang.Specification

class XmlVisualizerSpecification extends Specification {
    private String xml = "<root />"

    def "HashMap accepts null key"() {
        setup:
        def map = new HashMap()

        when:
        map.put(null, "elem")

        then:
        notThrown(NullPointerException)
    }

    def "Should parse valid XML"() {
        given:
        new Visualizer(xml)
        when:
        null
        then:
        true
    }

    def "Should visualize root element"() {
        given:
        def v = new Visualizer(xml)
        when:
        null
        then:
        v.visualize() == 'root'
    }
}

class Visualizer {
    private Node node

    Visualizer(String text) {
        node = new XmlParser().parseText(text)
    }

    def visualize() {
        return node.name()
    }
}