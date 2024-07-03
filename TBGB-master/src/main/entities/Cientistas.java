package main.entities;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.Scanner;

public class Cientistas implements Runnable {

    private Scanner tc = new Scanner(System.in);

    private String name;
    private int value;
    private int processo = 1;

    public List<Cientistas> list = new ArrayList<>();
    private String[] pessoas = {"Pedro", "Carlos", "Rodrigo", "Rian", "Ianca", "Gustavo", "Alexandra", "Tay"};


    public Cientistas(String name, int value, int processo) {
        this.name = name;
        this.value = value;
        this.processo = processo;
    }

    public Cientistas() {
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getValue() {
        return value;
    }

    public void setValue(int value) {
        this.value = value;
    }

    public int getProcesso() {
        return processo;
    }

    public void setProcesso(int processo) {
        this.processo = processo;
    }

    public void operar(int quantum) {
        value -= quantum;
    }

    public void criarProcesso(int qntMaxima) {
        for (int i = 0; i < qntMaxima; i++) {
            Random randi = new Random();
            int indiceAleatorio = randi.nextInt(pessoas.length);
            String name = pessoas[indiceAleatorio];
            int valorGerado = randi.nextInt(5000) + 1000;
            int value = valorGerado;
            Cientistas cientistas = new Cientistas(name, value, processo);
            list.add(cientistas);
            processo++;
        }
        for (int h = 0; h < list.size(); h++) {
            System.out.println("Cientista criado: " + list.get(h).getName() + ". Processo nº " + list.get(h).getProcesso() + ". Tempo de vida: " + list.get(h).getValue());
        }
    }

    @Override
    public void run() {

        System.out.println("Digite o tempo de vida máximo de cada processo: ");
        int quantum = tc.nextInt();

        System.out.println("Digite a quantidade de novos processos criados: ");
        int qntMaxima = tc.nextInt();

        criarProcesso(qntMaxima);

        while (!list.isEmpty()) {
            for (int i = 0; i < list.size(); i++) {
                System.out.println("___________________________________________________________________________________________________");
                System.out.println("Cientista: " + list.get(i).getName() + " de processo nº " + list.get(i).getProcesso() + ". Tempo de vida restante: " + list.get(i).getValue());
                try {
                    Thread.sleep(quantum);
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }

                if (list.get(i).getValue() > quantum) {
                    list.get(i).operar(quantum);
                } else {
                    System.out.println("Cientista: " + list.get(i).getName() + " foi removido!");
                    list.remove(i);
                    i--;
                    System.out.println("_______________________________");
                    System.out.println("Restam os seguintes processos:");
                    for (int x = 0; x < list.size(); x++) {
                        if (list.size() != 0) {
                            System.out.println(list.get(x).getProcesso() + "º Processo."  + " Cientista: " + list.get(x).getName() + ". Tempo restante: " + list.get(x).getValue());
                        }
                    }
                    System.out.println("Gostaria de adicionar novos processos? (y/n)");
                    String decision = tc.next();
                    if (decision.equalsIgnoreCase("y")) {
                        System.out.println("Digite a quantidade de novos processos criados:");
                        int sup = tc.nextInt();
                       do {
                    	   System.out.println("Digite a quantidade de novos processos criados:");
                           sup = tc.nextInt();
                        }while (sup <= 0);
                       criarProcesso(sup);
                    }
                    }

                }

            }
        }
    }
}
