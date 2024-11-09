package xyz.ronella.template.app;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

public class ApplicationTest {

    @Test
    public void helloWorld() {
        var main = new Application();
        assertEquals("Hello World", main.hello("World"));
    }

}
