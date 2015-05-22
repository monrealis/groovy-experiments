//@Grab(group = 'org.spockframework', module = 'spock-core', version = '1.0-groovy-2.4')
import spock.lang.Specification

class XmlVisualizerSpecification extends Specification {
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
        new Visualizer("<root />")
        when:
        null
        then:
        true
    }
}

class Visualizer {
    private Node node

    Visualizer(String text) {
        node = new XmlParser().parseText(text)
    }
}