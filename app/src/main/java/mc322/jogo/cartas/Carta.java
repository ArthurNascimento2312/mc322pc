package mc322.jogo.cartas;

import java.util.ArrayList;

import mc322.jogo.RequisitoJogo;
import mc322.jogo.entidades.Entidade;
import mc322.jogo.entidades.Inimigo;

/**
 * Classe Abstrata que serve como molde básico para todas as cartas do jogo
 */
public abstract class Carta {
    protected String nome;
    protected String descricao;
    protected int custo;
    /** Atributo para identificar nas classes concretas o tipo específico de cada Carta */
    protected TiposCartas tipo;

    public abstract String getNome();

    public abstract int getCusto();

    /**
     * Método abstrato para usar uma Carta a depender do seu tipo. Toda
     * Carta independentemente do seu tipo tem um dono responsável por executar
     * a jogada e um alvo ou conjunto de alvos, que inclusive pode ser o próprio 
     * agente, como o caso da carta de escudo.
     * 
     * @param dono Entidade que usa a Carta.
     * @param alvo Entidade que vai receber a ação da Carta
     * @param inimigos Conjunto de inimigos que podem receber a ação da Carta, caso ela  tenha uma ação em um vetor de Entidades
     * @return String com o resultado da operação, que usamos para fazer a impressão no terminal.
     */
    public abstract String usar(Entidade dono, Entidade alvo, ArrayList<Inimigo> inimigos);

    public abstract String getDescricao();

    /**
     * Método para retornar o atibuto TiposCartas que indica qual o tipo de carta que estamos lidando
     * Pode ver todos os tipos de Cartas em {@link TiposCartas}
     * 
     * @return devolve o Tipo de Carta, ou seja, qual classe concreta ela pertence.
     */
    public abstract TiposCartas getTipoCarta();

    /**
     * Requisitos necessários para usar cada tipo de Carta, já que precisamos captar os dados escollhidos
     * pelo usuário via terminal a depender do que cada Carta necessita. Como exemplo, temos
     * que Cartas de Dano precisam de um Inimigo, já Cartas de Escudo precisam de Heróis aliados.
     * 
     * @return uma constante que representa um requisito, pode ver todos os requisitos em {@link RequisitoJogo}
     */
    public abstract RequisitoJogo cartaRequisito();

}
