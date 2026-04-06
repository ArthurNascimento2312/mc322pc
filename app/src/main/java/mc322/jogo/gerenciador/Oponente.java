package mc322.jogo.gerenciador;

import mc322.jogo.entidades.Inimigo;
import java.util.ArrayList;
import java.util.Collections;

/**
 * Classe responsável por gerenciar a equipe de Inimigos.
 * Funciona como o "Técnico" do time inimigo, armazenando o catálogo
 * completo de monstros e sorteando a equipe que entrará na batalha atual.
 */

public class Oponente {
    private ArrayList<Inimigo> todosInimigos = new ArrayList<>();
    private ArrayList<Inimigo> inimigosEscolhidos = new ArrayList<>();

    // Adiciona os vilões à lista de todos os vilões
    public void adicionarInimigoTodos(Inimigo enemy) {
        this.todosInimigos.add(enemy);
    }

    // Todos os inimigos escolhidos, é aleatório e baseado na dificuldade
    public void gerarInimigos(int dificuldade) {
        this.inimigosEscolhidos.clear();

        Collections.shuffle(this.todosInimigos);

        int quantidade = Math.min(dificuldade, todosInimigos.size());

        for (int i = 0; i < quantidade; i++) {
            this.inimigosEscolhidos.add(this.todosInimigos.get(i));
        }
    }

    /* método para imprimir os inimigos vivos que foram escolhidos naquele jogo */
    public void imprimeInimigosVivos() {
        for (int j = 0; j < this.inimigosEscolhidos.size(); j++) {
            if (this.inimigosEscolhidos.get(j).estaVivo()) {
                System.out.println(j + " - " + this.inimigosEscolhidos.get(j).getNome() + " (Vida: "
                        + this.inimigosEscolhidos.get(j).getVida() + ")");
            }
        }
    }

    /*método para validar escolha do usuário*/
    public boolean validaEscolhaInimigo(int alvoEscolhido) {
        if (alvoEscolhido >= 0 && alvoEscolhido < this.inimigosEscolhidos.size() && this.inimigosEscolhidos.get(alvoEscolhido).estaVivo())
            return true;
        return false;
    }

    public ArrayList<Inimigo> getInimigosEscolhidos() {
        return this.inimigosEscolhidos;
    }

    // Olha se tem alguém vivo no time de inimigos
    public boolean temInimigosVivos() {
        for (Inimigo i : inimigosEscolhidos) {
            if (i.estaVivo()) {
                return true;
            }
        }
        return false;
    }

    public Inimigo getInimigo(int indice) {
        return this.getInimigosEscolhidos().get(indice);
    }

}
