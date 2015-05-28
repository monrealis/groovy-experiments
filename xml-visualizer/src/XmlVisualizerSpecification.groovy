//@Grab(group = 'org.spockframework', module = 'spock-core', version = '1.0-groovy-2.4')

import groovy.xml.QName
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
        def arr = [localName(node)];
        node.attributes().each { arr << "@${localName it.key}" }
        arr.join(" ")
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