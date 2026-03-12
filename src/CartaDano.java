
public class CartaDano {

    private String nome;
    private int custo;
    private int danoCarta;

    public CartaDano(String nome, int custo, int dano) {
        this.nome = nome;
        this.custo = custo;
        this.danoCarta = dano;
    }

    public int validaDano() {
        return this.danoCarta;
    }

    public int causaDano(int vida_inimigo) {
        vida_inimigo -= this.danoCarta;
        return vida_inimigo;
    }
    
    public String acessoCartaDanoNome() {
        return this.nome;
    }

    public int acessoCartaDanoDano() {
        return this.danoCarta;
    }

    public int acessoCartaDanoCusto() {
        return this.custo;
    }
}
