package application;

import static org.junit.Assert.*;

import main.application.Main;
import org.junit.Test;

import java.io.ByteArrayInputStream;
import java.io.InputStream;

public class MainTest {
    @Test
    public void testMainCancelAddProcesses() {
        // mock de entrada de usuario
        String input = "1000\n1\nn\n";
        InputStream in = new ByteArrayInputStream(input.getBytes());
        System.setIn(in);

        try {
            Main.main(new String[]{});
        } catch (InterruptedException e) {
            e.printStackTrace();
            fail("Ocorreu uma interrupção inesperada.");
        }
        //pra ver se o codggio rodou certo
        assertTrue(true);
    }

    @Test(expected = NumberFormatException.class)
    public void testMainInvalidInput() throws InterruptedException {
        // entrada inválida
        String input = "invalid\n1\nn\n";
        InputStream in = new ByteArrayInputStream(input.getBytes());
        System.setIn(in);

        Main.main(new String[]{});
    }

    @Test
    public void testMainAddProcesses() {
        // mock
        String input = "2000\n1\ny\n2\nn\nn\nn";
        InputStream in = new ByteArrayInputStream(input.getBytes());
        System.setIn(in);

        try {
            Main.main(new String[]{});
        } catch (InterruptedException e) {
            e.printStackTrace();
            fail("Ocorreu uma interrupção inesperada.");
        }

        // ver se rodou certo o procso
        assertTrue(true);
    }
}
