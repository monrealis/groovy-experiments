//@Grab(group = 'org.spockframework', module = 'spock-core', version = '1.0-groovy-2.4')

import groovy.xml.QName
import spock.lang.Specification

class XmlVisualizerSpecification extends Specification {
    private String xml
    private String text
    private int indentation

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

    def "Should visualize root element with attributes"() {
        given:
        xml = "<root a='x' b=''/>"
        when:
        visualize()
        then:
        text == 'root @a @b'
    }

    def "Should visualize root element with prefixed attribute"() {
        given:
        xml = "<root t:a='' xmlns:t='urn:test:test' />"
        when:
        visualize()
        then:
        text == 'root @a'
    }

    def "Should visualize two elements"() {
        given:
        xml = "<root><one /></root>"
        when:
        visualize()
        then:
        text == 'root\none'
    }

    def "Should use indentation"() {
        given:
        xml = "<root><one><two /></one></root>"
        indentation = 2
        when:
        visualize()
        then:
        text == 'root\n  one\n    two'
    }

    private void visualize() {
        Visualizer v = new Visualizer(xml)
        v.indentation = indentation
        text = v.visualize()
    }
}

class Visualizer {
    private Node node
    private def items
    def indentation

    Visualizer(String text) {
        node = new XmlParser().parseText(text)
    }

    def visualize() {
        items = []
        collect(node)
        items.join('\n')
    }

    private void collect(Node n, int level = 0) {
        def arr = [localName(n)];
        n.attributes().each { arr << "@${localName it.key}" }
        items << formatLine(level, arr)
        n.children().each { collect(it, level + 1) }
    }

    private String formatLine(int level, items) {
        ' '.multiply(indentation * level) + items.join(" ")
    }

    private Object localName(Node n) {
        localName n.name()
    }

    private String localName(QName qname) {
        qname.localPart
    }

    private String localName(String name) {
        name
    }
}