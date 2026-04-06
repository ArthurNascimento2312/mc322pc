package mc322.jogo.cartas;

import mc322.jogo.RequisitoJogo;
import mc322.jogo.entidades.Entidade;

public abstract class Carta {
    protected String nome;
    protected String descricao;
    protected int custo;
    protected TiposCartas tipo; //coloquei isso aqui para quebrar o encapsulamento

    public abstract String getNome();

    public abstract int getCusto();

    public abstract String usar(Entidade dono, Entidade alvo);

    public abstract String getDescricao();

    public abstract TiposCartas getTipoCarta();

    public abstract RequisitoJogo cartaRequisito();

}
