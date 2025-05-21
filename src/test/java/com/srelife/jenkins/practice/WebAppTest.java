package com.srelife.jenkins.practice; // El paquete debe coincidir con la ruta de carpetas

import org.junit.Test;
import static org.junit.Assert.assertEquals;

public class WebAppTest {
    @Test
    public void testGetGreeting() {
        // Llama al método de la clase de la aplicación (WebApp) para probarlo
        assertEquals("Hello Devops People this's jenkis!", WebApp.getGreeting());
    }
}
