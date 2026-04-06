package mc322.jogo.cartas;

import mc322.jogo.Cores;
import mc322.jogo.RequisitoJogo;
import mc322.jogo.entidades.Entidade;

public class CartaDano extends Carta {
    private int danoCarta;
   

    public CartaDano(String nome, String descricao, int custo, int dano) {
        this.nome = nome;
        this.descricao = descricao;
        this.custo = custo;
        this.danoCarta = dano;
        this.tipo = TiposCartas.DANO;
    }

    @Override
    public String usar(Entidade dono, Entidade alvo) {
        dono.ataque(alvo, this.acessoCartaDanoDano());
        return Cores.VERMELHO + "\n⚔️ Você usou " + this.getNome() + " no " + alvo.getNome() + " e causou dano! " + Cores.RESET;
    }

    @Override
    public String getNome() {
        return this.nome;
    }

    public int acessoCartaDanoDano() {
        return this.danoCarta;  
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
        return RequisitoJogo.INIMIGO;
    }

}
