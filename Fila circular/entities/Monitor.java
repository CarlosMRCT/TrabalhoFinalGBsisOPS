package entities;

import java.util.ArrayList;
import java.util.Random;

public class Monitor {

    private int quantum;
    private int qntMaxima;
    private int capacity;
    private int[] valores;
    private int novaQnt;
    private int x;


    public Monitor(int quantum, int qntMaxima, int capacity) {
        this.quantum = quantum;
        this.qntMaxima = qntMaxima;
        this.capacity = capacity;
        this.valores = new int[100];
    }

    public int[] getValores() {
        return valores;
    }

    public int getNovaQnt() {
        return novaQnt;
    }

    public void setNovaQnt(int novaQnt) {
        this.novaQnt = novaQnt;
    }

    public int getQuantum() {
        return quantum;
    }

    public int getQntMaxima() {
        return qntMaxima;
    }

    public void setQntMaxima(int qntMaxima) {
        this.qntMaxima = qntMaxima;
    }

    public void atualizarQnt() {
        qntMaxima += capacity;
    }


    public void criarProcessos(ArrayList<String> cientistas) {
        Random gerador = new Random();
        for (int i = 0; i < qntMaxima; i++) {
            int valorGerado = gerador.nextInt(5000) + 1000;
            valores[i] = valorGerado;
            x++;
            System.out.println("Cientista: " + cientistas.get(i) + " recebe o processo nº " + x + " de tempo: " + valores[i]);
        }
    }

    public void criarNovosProcessos(ArrayList<String> cientistas, int novaQnt) {
        Random gerador = new Random();
        for (int i = 0; i < novaQnt; i++) {
            int valorGerado = gerador.nextInt(5000) + 1000;
            valores[i] = valorGerado;
            x++;
            System.out.println("Cientista: " + cientistas.get(i) + " recebe o processo nº " + x + " de tempo: " + valores[i]);
        }
    }
}
