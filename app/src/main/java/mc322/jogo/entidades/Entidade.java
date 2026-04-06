package mc322.jogo.entidades;

import mc322.jogo.gerenciador.GameManager;
import mc322.jogo.efeitos.Efeito;
import mc322.jogo.efeitos.TiposEfeitos;

import java.util.ArrayList;

/**
 * Classe abstrata com o contrato mínimo que uma Entidade (Herois e inimigos)
 * devem ter.
 */
public abstract class Entidade {
    protected String nome;
    protected int vida;
    protected int escudo;
    protected int vidaInicial;
    protected boolean turno;
    protected GameManager gm;
    protected int velocidade;
    /*atributos para gerenciar os efeitos que estão agindo na entidade */
    protected ArrayList<Efeito> listaEfeitos;


    /* método para gerar o dano partindo de uma carta de dano */
    public abstract void recebeDano(int dano);

    /* método para gerar o dano partindo de uma carta de Efeito */
    public abstract void recebeDanoEfeito(int dano);

    public abstract boolean estaVivo();

    public abstract int getEscudo();

    public abstract void ganhaEscudo(int valorEscudo);

    public abstract void zeraEscudo(); 

    public abstract String getNome();

    public abstract int getVida();

    public abstract int getVidaInicial();

    public abstract int getVelocidade();

    public abstract boolean getTurno();

    public abstract void verificaseAtacou(boolean status);

    public abstract void aplicarEfeito(Efeito efeito);

    /* retira o efeito da lista de efeitos daquela entidade */
    public abstract void terminaEfeito(TiposEfeitos tipo);

    public abstract void ataque(Entidade alvo, int valorDano); //preciso colocar isso em inimigo

    public abstract void imprimeEfeitos();

}
