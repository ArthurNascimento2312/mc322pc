package mc322.jogo.cartas;

import mc322.jogo.Cores;
import mc322.jogo.RequisitoJogo;
import mc322.jogo.efeitos.Efeito;
import mc322.jogo.efeitos.TiposEfeitos;
import mc322.jogo.entidades.Entidade;

public class CartaEfeito extends Carta {
    protected Efeito efeito;

    public CartaEfeito(String nome, String descricao, int custo, Efeito efeito) {
        this.nome = nome;
        this.descricao = descricao;
        this.custo = custo;
        this.tipo = TiposCartas.EFEITO;
        this.efeito = efeito;
    }

    public Efeito getEfeito() {
        return this.efeito;
    }

    @Override
    public String getNome() {
        return this.nome;
    }

    @Override
    public int getCusto() {
        return this.custo;
    }

    @Override
    public String usar(Entidade dono, Entidade alvo) {
        alvo.aplicarEfeito(this.efeito);
        return Cores.AZUL + "\n Você ativou " + this.getNome() + " e aplicou o " + this.getNome() + " : " + this.getDescricao() + Cores.RESET;
    }

    @Override
    public String getDescricao() {
        return this.descricao;
    }

    @Override
    public TiposCartas getTipoCarta() {
        return this.tipo;
    }


    public void explicaEfeito(TiposEfeitos tipo) {
        if (tipo == TiposEfeitos.VENENO) {
            System.out.print(
                    "\nO efeito Veneno gera um dano de dimensão igual ao seu acúmulo e a cada inicio de turno o acúmulo cai em uma unidade.O efeito tem validade até acúmulo atingir zero\n");

        } else if (tipo == TiposEfeitos.FRAQUEZA) {
            System.out.print(
                    "\nO efeito Fraqueza reduz a força do oponente em 25%. Tem duração equivalente a quantidade de acúmulos que vão sendo subtraídos a cada final de turno\n");
        }
        System.out.println("APLICAR CARTAS DO MESMO TIPO DE EFEITO TEM COMO RESULTADO A SOMA DOS ACÚMULOS");

    }

    @Override
    public RequisitoJogo cartaRequisito() {
        return RequisitoJogo.INIMIGO;
    }
}
