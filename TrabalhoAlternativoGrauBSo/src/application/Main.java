package application;

import entities.Cientistas;

public class Main {
    public static void main(String[] args) throws InterruptedException {

        Cientistas cientistas = new Cientistas();


        Thread cientistasThread = new Thread(cientistas);

        cientistasThread.start();

        try {
            cientistasThread.join();
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }
}