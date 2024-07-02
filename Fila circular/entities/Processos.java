package entities;

import java.util.ArrayList;
import java.util.Scanner;

public class Processos implements Runnable {

    private final Monitor monitor;
    ArrayList <String> cientistas = new ArrayList<String>();

    Scanner tc = new Scanner(System.in);

    public Processos(Monitor monitor) {
        this.monitor = monitor;
        // Adicionando elementos na lista no construtor
        cientistas.add("Pedro");
        cientistas.add("Carlos");
        cientistas.add("Rodrigo");
        cientistas.add("Rian");
        cientistas.add("Ianca");
        cientistas.add("Gustavo");
        cientistas.add("Alexandra");
        cientistas.add("Tay");
    }

    public ArrayList<String> getCientistas() {
        return cientistas;
    }

    public void setCientistas(ArrayList<String> cientistas) {
        this.cientistas = cientistas;
    }

    @Override
    public void run() {
        monitor.atualizarQnt();
        monitor.criarProcessos(cientistas);
        while (true) {
            for (int i = 0; i < monitor.getQntMaxima(); i++) {
                if (monitor.getValores()[i] == 0) continue;
                System.out.println("Cientista: " + cientistas.get(i) + " com tempo de vida de: " + monitor.getValores()[i] + " milis");
                try {
                    if (monitor.getValores()[i] > monitor.getQuantum()) {
                        Thread.sleep(monitor.getQuantum());
                        int backup = monitor.getValores()[i];
                        backup -= monitor.getQuantum();
                        monitor.getValores()[i] = backup;
                    } else if (monitor.getValores()[i] <= monitor.getQuantum()) {
                        Thread.sleep(monitor.getValores()[i]);
                        monitor.getValores()[i] = 0;
                        System.out.println("Processo do cientista: " + cientistas.get(i) + " removido!");
                    }
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }
                if (monitor.getValores()[i] == 0) {
                    System.out.println("Gostaria de adicionar um novo processo? S/N");
                    String decision = tc.next();
                    if (decision.equalsIgnoreCase("S")) {
                        do {
                            System.out.println("Digite a quantidade de novos processos criados:");
                            int sup = tc.nextInt();
                            monitor.setNovaQnt(sup);
                        } while (monitor.getNovaQnt() > 100);
                        monitor.setQntMaxima(monitor.getQntMaxima() + monitor.getNovaQnt());
                        monitor.criarNovosProcessos(cientistas, monitor.getNovaQnt());
                    }
                }
            }
        }
    }
}
