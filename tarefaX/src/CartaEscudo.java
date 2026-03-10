public class CartaEscudo {

    private String nome;
    private int custo;
    private int escudo_ganho;

    public void usaEscudo(Heroi personagem) {
        personagem.GanhaEscudo(this);
    }


    public String acessoNome() {
        return this.nome;
    }

    public int acessoEscudoGanho() {
        return this.escudo_ganho;
    }

    public int acessoCusto() {
        return  this.custo;
    }
}

