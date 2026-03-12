
public class Heroi {
    private String nome;
    private int vida;
    private int escudo;
    private int energia;
    private CartaDano[] dano;
    private int tamanhoVetor;
    private int capacidade;

    public Heroi(String nome, int vida, int escudo, int energia, int capacidade) {
        this.nome = nome;
        this.vida = vida;
        this.escudo = escudo;
        this.energia = energia;
        this.dano = new CartaDano[capacidade];
        this.capacidade = capacidade;
        this.tamanhoVetor = 0;
    }


    public int encontraNome(String nomeCarta) {
        int i = 0;

        while (i < this.dano.length) {
            if (this.dano[i].acessoCartaDanoNome().equals(nomeCarta))
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

    public void recebeDano(Inimigo personagem) {

        if (this.escudo >= personagem.acessoDano()) {
            this.escudo -= personagem.acessoDano();
        } else {

            int danoRestante = personagem.acessoDano() - this.escudo;
            this.escudo = 0;
            this.vida -= danoRestante;
        }

    }

    
    public void ganhaEscudo(CartaEscudo cartaEscudo) {
        this.escudo = cartaEscudo.acessoEscudoGanho();
    }


    public boolean estaVivo() {
        if (this.vida <= 0) {
            return false;
        } else {
            return true;
        }
    }

    public void adiciona_card(CartaDano carta) {
        if (this.tamanhoVetor < this.capacidade) {
            this.dano[this.tamanhoVetor] = carta;
            this.tamanhoVetor = this.tamanhoVetor + 1;
            System.out.println(carta.acessoCartaDanoNome() + " foi inserido ao seu deck");
            }
        else {
            System.out.println("O seu deck está cheio ! Impossível adicionar mais cartas");
        } 

        
}

    public int acessoEscudo() {
        return this.escudo;
    }


    public int acessoEnergia() {
        return this.energia;
    }

    public void resetarEscudo() {
        this.escudo = 0;
    }

    public String acessoNome() {
        return this.nome;
    }

    public int acessoVida() {
        return this.vida;
    }

    public void imprimeCartasDano() {
        for (int i = 0; i < 2 /* this.dano.length */; i++) {
            System.out.println(i + "-" + this.dano[i].acessoCartaDanoNome());
        }
    }

    public CartaDano[] acessoVetorCartaDano() {
        return this.dano;
    }
}
