package PerformaceSistCiberfisicos;
import java.util.Random;
import java.util.ArrayList;
import java.util.List;

class Usuario extends Thread {
    private String nome_usuario;
    private Biblioteca biblioteca;
    private Random random;

    public Usuario(Biblioteca biblioteca, String nome_usuario) {
        this.biblioteca = biblioteca;
        this.nome_usuario = nome_usuario;
        this.random = new Random();
    }

    public void run() {
        try {
            while (true) {
                int aleatorio = random.nextInt(10);
                String livro_escolhido = "Livro:" + aleatorio;
                biblioteca.verificarLivro(livro_escolhido, nome_usuario);

                Thread.sleep(1000 + random.nextInt(1000)); 
            }
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}

class Biblioteca {
    private List<String> livros_disponiveis;

    public Biblioteca(List<String> nomesDosLivros) {
        livros_disponiveis = new ArrayList<>(nomesDosLivros);
    }

    public void verificarLivro(String livro_escolhido, String nome_usuario) {
        synchronized (this) {
            while (!livros_disponiveis.contains(livro_escolhido)) {
                try {
                    System.out.println(nome_usuario + " > Esperando " + livro_escolhido + " ficar disponível");
                    wait();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
            livros_disponiveis.remove(livro_escolhido);
            System.out.println(nome_usuario + " > Emprestou " + livro_escolhido);
        }

        try {
            Thread.sleep(1000 + new Random().nextInt(1000)); 
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        synchronized (this) {
            livros_disponiveis.add(livro_escolhido);
            System.out.println(nome_usuario + " > Devolveu " + livro_escolhido);
            notifyAll();
        }
    }
}

public class Tde {
    public static void main(String[] args) {
        List<String> nomesDosLivros = new ArrayList<>();
        for (int i = 0; i < 10; i++) {
            nomesDosLivros.add("Livro:" + i);
        }

        Biblioteca biblioteca = new Biblioteca(nomesDosLivros);

        Usuario usuario1 = new Usuario(biblioteca, "Usuário 1");
        Usuario usuario2 = new Usuario(biblioteca, "Usuário 2");
        Usuario usuario3 = new Usuario(biblioteca, "Usuário 3");

        usuario1.start();
        usuario2.start();
        usuario3.start();
    }
}
