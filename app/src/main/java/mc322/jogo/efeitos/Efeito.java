package mc322.jogo.efeitos;

import mc322.jogo.observer.Subscriber;
import mc322.jogo.entidades.Entidade;
import mc322.jogo.gerenciador.GameManager;

public abstract class Efeito implements Subscriber {
    protected String nome;
    protected Entidade dono;
    protected int acumulos;
    protected int acumulosInicial;
    protected GameManager gm;
    protected TiposEfeitos tipo;

    public Efeito(int acumulos, GameManager gm) {
        this.acumulosInicial = acumulos;
        this.acumulos = acumulos;
        this.gm = gm;
    }

    /*
     * método para informar sobre a carta de efeito e a quantidade de acumulos
     * inicial
     */
    public String getString() {
        return "Efeito - " + this.nome + "com - " + this.acumulos + "de acúmulo";
    }

    public int getAcumulos() {
        return this.acumulos;
    }

    public void diminuiAcumulos() {
        this.acumulos = this.acumulos - 1;
    }

    public void aumentaAcumulos(int x) {
        this.acumulos = this.acumulos + x;
    }

    public int getAcumulosInicial() {
        return this.acumulosInicial;
    }

    @Override
    public Entidade getDono() {
        return this.dono;
    }

    public void setDono(Entidade dono) {
        this.dono = dono;
    }

    public GameManager getGm() {
        return this.gm;
    }

    public TiposEfeitos getTipo() {
        return this.tipo;
    }

    public abstract void acaoEfeito();

    /* Nosso padrão para criar qualquer tipo de efeito */
    public static Efeito criaEfeito(Efeito efeito) {
        if (efeito.getTipo() == TiposEfeitos.VENENO) {
            return new EfeitoVeneno((EfeitoVeneno) efeito);

        } else if (efeito.getTipo() == TiposEfeitos.FORCA) {
            return null; // próxima implementação

        } else if (efeito.getTipo() == TiposEfeitos.FRAQUEZA) { // garanto que ele é do tipo fraqueza
            return new EfeitoFraqueza((EfeitoFraqueza) efeito);
        }
        return null;
    }
}
