//@Grab(group = 'org.spockframework', module = 'spock-core', version = '1.0-groovy-2.4')
import spock.lang.Specification

class XmlVisualizerSpecification extends Specification {
    private String xml
    private String text

    def "Should visualize root element"() {
        given:
        xml = "<root />"
        when:
        visualize()
        then:
        text == 'root'
    }

    def "Should visualize root element with namespace"() {
        given:
        xml = "<root xmlns='urn:test:test' />"
        when:
        visualize()
        then:
        text == 'root'
    }

    def "Should visualize root element with namespace and prefix"() {
        given:
        xml = "<t:root xmlns:t='urn:test:test' />"
        when:
        visualize()
        then:
        text == 'root'
    }

    private void visualize() {
        Visualizer v = new Visualizer(xml)
        text = v.visualize()
    }
}

class Visualizer {
    private Node node

    Visualizer(String text) {
        node = new XmlParser().parseText(text)
    }

    def visualize() {
        return localName(node)
    }

    private Object localName(Node n) {
        String s = n.name()
        s.replaceFirst('\\{.*\\}', "")
    }
}