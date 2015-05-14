import org.junit.Test

import static groovy.test.GroovyAssert.shouldFail
import static org.junit.Assert.assertEquals

class ExampleTest {
    @Test
    public void fail() {
        shouldFail({})
    }

    @Test
    public void pass() {
        assertEquals(1, 1)
    }
}