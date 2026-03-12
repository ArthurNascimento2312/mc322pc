public class CartaEscudo {

    private String nome;
    private int custo;
    private int escudoGanho;

    public void usaEscudo(Heroi personagem) {
        personagem.ganhaEscudo(this);
    }

    public CartaEscudo(String nome, int custo, int escudoGanho) {
        this.nome = nome;
        this.custo = custo;
        this.escudoGanho = escudoGanho;
    }


    public String acessoNome() {
        return this.nome;
    }

    public int acessoEscudoGanho() {
        return this.escudoGanho;
    }

    public int acessoCusto() {
        return  this.custo;
    }

    
}

