package mc322.jogo.cartas;

import java.util.ArrayList;

import mc322.jogo.Cores;
import mc322.jogo.RequisitoJogo;
import mc322.jogo.entidades.Entidade;
import mc322.jogo.entidades.Inimigo;

public class CartaEscudo extends Carta {
    private int escudoGanho;

    public CartaEscudo(String nome, String descricao, int custo, int escudoGanho) {
        this.nome = nome;
        this.descricao = descricao;
        this.custo = custo;
        this.escudoGanho = escudoGanho;
        this.tipo = TiposCartas.ESCUDO;
    }

    @Override 
    public String usar(Entidade dono, Entidade alvo, ArrayList<Inimigo> inimigos) {
        alvo.ganhaEscudo(this.getEscudoGanho());
        // podemos personalizar no caso do escudo for para outro heroi e não em si.
        return Cores.AZUL + "\n🛡️ Você ativou " + this.getNome() + " e ativou o escudo em " + alvo.getNome()+ Cores.RESET; 
    }

    @Override
    public String getNome() {
        return this.nome;
    }

    public int getEscudoGanho() {
        return this.escudoGanho;
    }

    @Override
    public int getCusto() {
        return this.custo;
    }

    @Override
    public String getDescricao() {
        return this.descricao;
    }

    @Override
    public TiposCartas getTipoCarta() {
        return this.tipo;
    }

    @Override
    public RequisitoJogo cartaRequisito() {
        return RequisitoJogo.TODOS_HEROIS;
    }

}
