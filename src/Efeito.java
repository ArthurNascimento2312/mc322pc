public abstract class Efeito {
    protected String nome;
    protected Entidade dono;
    protected int acumulos;
    protected int acumulosInicial;
    protected String descricao;

    /* método para informar sobre a carta de efeito e a quantidade de acumulos inicial*/
    public void getString() {
        System.out.println("Carta de efeito - " + this.nome + "com - " + this.acumulos + "de acúmulo");
    }

    public void getDescricao() {
        System.out.println("Descrição carta de efeito: " + this.nome + " descrição: " + this.descricao + "\nacúmulos: " + this.acumulos );
    }

    public int getAcumulos() {
        return this.acumulos;
    }

    public void setAcumulos(int acumulos) {
        this.acumulos = acumulos;
    }

    public int getAcumulosInicial() {
        return this.acumulosInicial;
    }

    public Entidade getDono() {
        return this.dono;
    }

    public void setDono(Entidade dono) {
        this.dono = dono;
    }
}   

