package mc322.jogo.efeitos;

import mc322.jogo.RequisitoJogo;
import mc322.jogo.gerenciador.GameManager;
import mc322.jogo.observer.Estados;

public class EfeitoForca extends Efeito {
    private int valorForca;

    public EfeitoForca(int acumulos, GameManager gm, int valorForca) {
        super(acumulos, gm);
        this.nome = "Efeito Força";
        this.valorForca = valorForca;
        this.tipo = TiposEfeitos.FORCA;
    }

    public EfeitoForca(EfeitoForca efeito) {
        this(efeito.getAcumulosInicial(), efeito.getGm(), efeito.getValorForca());
    }

    public void serNotificado(Estados state) {
        if (state == Estados.FIM_DE_TURNO) {
            this.diminuiAcumulos();
            /* aqui é a condição para o efeito terminar */
            if (this.getAcumulos() <= 0) {
                this.getDono().terminaEfeito(TiposEfeitos.FORCA);
                this.getGm().desinscrever(this, Estados.ATAQUE); //Posso tirar dessa lista de subscriber
                this.getGm().desinscrever(this, Estados.FIM_DE_TURNO);
            }
        }
    }

    @Override
    public void acaoEfeito() { // temos que ver como vamos usar isso aqui
        System.out.println(this.getDono().getNome() + " sofreu o efeito de força: " + this.nome
                + " que aumentou a força de suas cartas em 25% ");
    }

    public int getValorForca() {
        return this.valorForca;
    }

    public void setValorForca(int valorForca) {
        this.valorForca = valorForca;
    }

    public void alteraForca(int valorForca, int acumulos) {
        if (this.getValorForca() < valorForca)
            this.setValorForca(valorForca);
        this.aumentaAcumulos(acumulos);
    }

    @Override
    public RequisitoJogo requisitoEfeito() {
        return RequisitoJogo.HEROI;
    }
}
