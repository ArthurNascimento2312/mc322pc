package mc322.jogo.cartas;

import java.util.ArrayList;

import mc322.jogo.RequisitoJogo;
import mc322.jogo.entidades.Entidade;
import mc322.jogo.entidades.Inimigo;

public abstract class Carta {
    protected String nome;
    protected String descricao;
    protected int custo;
    protected TiposCartas tipo; //coloquei isso aqui para quebrar o encapsulamento

    public abstract String getNome();

    public abstract int getCusto();

    public abstract String usar(Entidade dono, Entidade alvo, ArrayList<Inimigo> inimigos);

    public abstract String getDescricao();

    public abstract TiposCartas getTipoCarta();

    public abstract RequisitoJogo cartaRequisito();

}
