package main.entities;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.Scanner;

public class Cientistas implements Runnable {
    // Scanner para entrada do usuário
    private Scanner tc = new Scanner(System.in);
    // Variáveis 
    private String name;
    private int value;
    private int processo = 1;

    // Lista que armazena todos os cientistas e seus detalhes
    public List<Cientistas> list = new ArrayList<>();
    private String[] pessoas = {"Pedro", "Carlos", "Rodrigo", "Rian", "Ianca", "Gustavo", "Alexandra", "Tay"};

    // Construtor que inicializa um cientista com nome, valor e processo
    public Cientistas(String name, int value, int processo) {
        this.name = name;
        this.value = value;
        this.processo = processo;
    }
    // Construtor vazio por padrão e boas práticas de POO
    public Cientistas() {
    }
    // Getters e setters para nome, valor e processo
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
    // Método para operar no valor do cientista com base em um quantum
    public void operar(int quantum) {
        value -= quantum;
    }
    // Método para criar um número máximo de processos com valores aleatórios
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
        // Mostra informações dos cientistas criados
        for (int h = 0; h < list.size(); h++) {
            System.out.println("Cientista criado: " + list.get(h).getName() + ". Processo nº " + list.get(h).getProcesso() + ". Tempo de vida: " + list.get(h).getValue());
        }
    }

    //Execução do código
    @Override
    public void run() {
        // Solicita ao usuário o tempo máximo de vida de cada processo e quantidade de novos processos a serem criados
        System.out.println("Digite o tempo de vida máximo de cada processo: ");
        int quantum = tc.nextInt();
        
        System.out.println("Digite a quantidade de novos processos criados: ");
        int qntMaxima = tc.nextInt();
        // Cria os processos iniciais
        criarProcesso(qntMaxima);
         // Enquanto houver cientistas na lista
        while (!list.isEmpty()) {
            for (int i = 0; i < list.size(); i++) {
                System.out.println("___________________________________________________________________________________________________"); //Imprime a situação dos cientistas
                System.out.println("Cientista: " + list.get(i).getName() + " de processo nº " + list.get(i).getProcesso() + ". Tempo de vida restante: " + list.get(i).getValue()); 
                try {
                    Thread.sleep(quantum); // Aguarda o tempo do quantum para maior imersão e visibilidade do código
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }

                if (list.get(i).getValue() > quantum) {
                    list.get(i).operar(quantum); // Reduz o tempo de vida do cientista
                } else {
                    // Remove o cientista da lista se seu tempo de vida acabar
                    System.out.println("Cientista: " + list.get(i).getName() + " foi removido!");
                    list.remove(i);
                    i--; // Volta um índice para reestruturação do código
                    System.out.println("_______________________________");
                    System.out.println("Restam os seguintes processos:");
                    for (int x = 0; x < list.size(); x++) {
                        if (list.size() != 0) {
                            System.out.println(list.get(x).getProcesso() + "º Processo."  + " Cientista: " + list.get(x).getName() + ". Tempo restante: " + list.get(x).getValue());
                        }
                    }
                    // Pergunta se deseja adicionar novos processos
                    System.out.println("Gostaria de adicionar novos processos? (y/n)");
                    String decision = tc.next();
                    if (decision.equalsIgnoreCase("y")) {
                        System.out.println("Digite a quantidade de novos processos criados:");
                        int sup = tc.nextInt();
                       criarProcesso(sup); // Cria novos processos conforme escolha do usuário
                    }
                    }

                }

            }
        }
    }

