package entities;

import static org.junit.Assert.*;
import org.junit.Before;
import org.junit.Test;

import main.entities.Cientistas;

import java.io.ByteArrayInputStream;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

public class CientistasTest {

    private Cientistas cientistas;
    private List<Cientistas> mockList;

    @Before
    //mock
    public void setUp() {
        cientistas = new Cientistas();
        mockList = new ArrayList<>();
    }

    @Test
    public void testConstructorAndGettersSetters() {
        //verificando se os valores tão indo certo
        Cientistas c = new Cientistas("Carlos", 3000, 1);

        assertEquals("Carlos", c.getName());
        assertEquals(3000, c.getValue());
        assertEquals(1, c.getProcesso());

        //ve se o set tá passando os valores certo
        c.setName("Pedro");
        c.setValue(2000);
        c.setProcesso(2);

        assertEquals("Pedro", c.getName());
        assertEquals(2000, c.getValue());
        assertEquals(2, c.getProcesso());
    }

    @Test
    public void testOperar() {
        //ve se o valor que ta indo pra cientista ta certo
        Cientistas c = new Cientistas("Carlos", 3000, 1);
        c.operar(1000);
        assertEquals(2000, c.getValue());
    }

    @Test
    public void testCriarProcesso() {
        // ciração de processo
        cientistas.criarProcesso(2);

        // mock
        mockList.addAll(cientistas.list);
        cientistas.list = mockList;

        //ve se os 2 processos foram criados
        assertEquals(2, mockList.size());
        //verifica se os valores estão de acordo
        for (Cientistas c : mockList) {
            assertNotNull(c.getName());
            assertTrue(c.getValue() >= 1000 && c.getValue() <= 6000);
            assertTrue(c.getProcesso() > 0);
        }
    }

    @Test
    public void testRun() {
        String input = "1000\n2\n";
        InputStream in = new ByteArrayInputStream(input.getBytes());
        System.setIn(in);

        // mock
        cientistas.list = mockList;

        Thread thread = new Thread(cientistas);
        thread.start();

        try {
            thread.join(3000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

// ver se remoção ta funcionado
        assertTrue(mockList.isEmpty());
    }
}
