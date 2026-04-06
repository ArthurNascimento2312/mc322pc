package mc322.jogo.cartas;

import mc322.jogo.Cores;
import mc322.jogo.RequisitoJogo;
import mc322.jogo.entidades.Entidade;

public class CartaEscudo extends Carta {
    private int escudoGanho;

    public CartaEscudo(String nome, String descricao, int custo, int escudoGanho) {
        this.nome = nome;
        this.descricao = descricao;
        this.custo = custo;
        this.escudoGanho = escudoGanho;
        this.tipo = TiposCartas.ESCUDO;
    }

    @Override /*podemos modificar isso aqui para o heroi poder usar a sua carta de escudo em um aliado */
    public String usar(Entidade dono, Entidade alvo) {
        /*ainda não usamos o alvo, porque a nossa carta de escudo o alvo é o mesmo que o dono, mas podemos generalizar */
        dono.ganhaEscudo(this.getEscudoGanho());
        // podemos personalizar no caso do escudo for para outro heroi e não em si.
        return Cores.AZUL + "\n🛡️ Você ativou " + this.getNome() + " e ganhou escudo!" + Cores.RESET; 
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
        return RequisitoJogo.HEROI;
    }

}
