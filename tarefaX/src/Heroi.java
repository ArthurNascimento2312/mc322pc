

public class Heroi{
    private String nome;
    private int vida;
    private int escudo;
    private int energia;
    private CartaDano[] dano;

    public Heroi(String nome, int vida, int escudo, int energia){
        this.nome = nome;
        this.vida = vida;
        this.escudo = escudo;
        this.energia = energia;
        this.dano = new CartaDano[10];
    }
    
    public int acessoEscudo() {
        return this.escudo;
    }

    public  int encontraNome(String nome_carta) {
        int i = 0;

        while (i < this.dano.length) {
            if (this.dano[i].acessoCartaDano_nome().equals(nome_carta))
                return i;
            i++;
        }
        return -1; 
        }

    public CartaDano encontraCarta(String nomeCarta) {
        int i = encontraNome(nomeCarta);
        CartaDano carta = this.dano[i];
        return carta;
    }


    public void RecebeDano(Inimigo personagem){

        if (this.escudo >=  personagem.acessoDano()) {
            this.escudo -= personagem.acessoDano();
        } else {

        int dano_restante = personagem.acessoDano() - this.escudo;
        this.escudo = 0;
        this.vida -= dano_restante;}
    
    }
    

    public void ganhaEscudo(CartaEscudo carta_escudo){
        this.escudo = carta_escudo.acessoEscudoGanho();
    }


    public int Estar_vivo(){
        if (this.vida <=0){
            return 0; 
        } else {
            return 1;
        }
    }


    public void adiciona_card(CartaDano carta, int i) {
        this.dano[i] = carta;
}

    public int acessoEnergia() {
        return this.energia;
    }

    public void resetarEscudo(){
        this.escudo = 0; 
    }

    public String acessoNome()  {
        return this.nome;
    }

    public int acessoVida() {
        return this.vida;
    }

    public void imprimeCartasDano() {
        for (int i = 0; i < 2 /*this.dano.length*/; i++) {
            System.out.println(i + "-" + this.dano[i].acessoCartaDano_nome());
        }
    }

    public CartaDano[] acessoVetorCartaDano() {
        return this.dano;
    }
}





