package org.example


import spock.lang.Specification

class CalculateSpec extends Specification {

    def "calculate sum"() {
        when:
        def total = 1 + 2

        then:
        3 == total
    }
}
