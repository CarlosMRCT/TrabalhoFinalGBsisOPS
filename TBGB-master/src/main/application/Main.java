package main.application;

import main.entities.Cientistas;

public class Main {
    public static void main(String[] args) throws InterruptedException {

        //Execução do main. 
        //Criação de threads para executar o método Run na classe cientistas.
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
