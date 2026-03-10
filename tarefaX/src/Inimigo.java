public class Inimigo {
    String nome;
    int vida;
    int escudo;

    public Inimigo(String nome, int vida, int escudo) {
        this.nome = nome;
        this.vida = vida;
        this.escudo = escudo;
    }

    public void ReceberDano(int dano) {
        if (this.vida > dano) {
            this.vida = this.vida - dano;
        }
        this.vida = 0;
    }
    
    public boolean estaVivo() {
        if (this.vida > 0)
            return true;
        return false;
    }

    public void atacar(Heroi personaegem) {
        personaegem.RecebeDano()
        
    }
}