public class CartaDano{

    private String nome;
    private int custo;
    private int dano;   

    public CartaDano(String nome, int custo, int dano){
        this.nome = nome;
        this.custo = custo;
        this.dano = dano;
    }

    public int valida_dano() {
        return this.dano;
    }

    public int Causa_dano(int vida_inimigo){
        vida_inimigo -= this.dano;
        return vida_inimigo;
    }


}