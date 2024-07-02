package application;
import entities.Monitor;
import entities.Processos;

import java.util.Scanner;
public class Main {

    public static void main(String[] args) {

//Declaração de variáveis

     int capacity = 3;
     Scanner tc = new Scanner (System.in);
     int qntMaxima;
     System.out.println("Digite o tempo de vida máximo de cada processo:");
     int quantum = tc.nextInt();
     do {
            System.out.println("Digite a quantidade de novos processos criados:");
            qntMaxima = tc.nextInt();
        } while(qntMaxima > 8);

//Execução do código
        Monitor monitor = new Monitor(quantum, qntMaxima, capacity);

        Processos processos = new Processos(monitor);

        Thread processosThread = new Thread(processos);

        processosThread.start();

        try {
            processosThread.join();
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }
}
