package PerformaceSistCiberfisicos;
// Enzzo Machado Silvino
// Bernardo Walker

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.util.ArrayList;

class ContaCaractereThread extends Thread {
    private int inicio;
    private int fim;
    private ArrayList<String> lista;
    private int[] vetorContador;

    public ContaCaractereThread(ArrayList<String> lista, int inicio, int fim) {
        this.lista = lista;
        this.inicio = inicio;
        this.fim = fim;
        this.vetorContador = new int[256];
    }

    public void run() {
        for (int i = inicio; i < fim; i++) {
            String arquivo = lista.get(i);
            try {
                byte[] bytes = Files.readAllBytes(new File(arquivo).toPath());
                for (byte b : bytes) {
                    vetorContador[b & 0xFF]++;
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    public int[] getVetorContador() {
        return vetorContador;
    }
}

public class Implementacao1 {
    public static void main(String[] args) throws InterruptedException {
        long startTime = System.currentTimeMillis();

        String caminhoPasta = "C:\\Users\\enzzo\\OneDrive\\Área de Trabalho\\AA\\todosArquivos\\";
        File f = new File(caminhoPasta);
        String[] arquivos = f.list();
        ArrayList<String> arquivosCaminho = new ArrayList<>();

        if (arquivos != null) {
            for (String s : arquivos) {
                arquivosCaminho.add(caminhoPasta + s);
            }
        }

        int numeroThreads = 5;
        ContaCaractereThread[] threads = new ContaCaractereThread[numeroThreads];
        int tamanhoPorThread = arquivosCaminho.size() / numeroThreads;
        for (int i = 0; i < numeroThreads; i++) {
            int inicio = i * tamanhoPorThread;
            int fim = (i == numeroThreads - 1) ? arquivosCaminho.size() : (i + 1) * tamanhoPorThread;
            threads[i] = new ContaCaractereThread(arquivosCaminho, inicio, fim);
            threads[i].start();
        }

        for (ContaCaractereThread thread : threads) {
            try {
                thread.join();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }

        int[] qtCaractere = new int[256];
        for (ContaCaractereThread thread : threads) {
            int[] vetorContador = thread.getVetorContador();
            for (int i = 0; i < vetorContador.length; i++) {
                qtCaractere[i] += vetorContador[i];
            }
        }

        for (int i = 0; i < qtCaractere.length; i++) {
            char c = (char) i;
            System.out.println("Quantidade de caractere '" + c + "' (" + i + "): " + qtCaractere[i]);
        }

        long endTime = System.currentTimeMillis();
        long totalTime = endTime - startTime;
        System.out.println("Tempo de execução: " + totalTime + " ms");
    }
}
