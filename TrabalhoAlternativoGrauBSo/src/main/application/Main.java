package main.application;

import main.entities.Cientistas;

public class Main {
    public static void main(String[] args) throws InterruptedException {

        // Execução do main
        // Criação de uma instância de Cientistas
        Cientistas cientistas = new Cientistas();
        // Criação de uma nova thread para executar o método run da classe Cientistas
        Thread cientistasThread = new Thread(cientistas);
        // Início da execução da thread
        cientistasThread.start();

        try {
            // Aguarda a conclusão da thread cientistasThread
            cientistasThread.join();
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }
}
