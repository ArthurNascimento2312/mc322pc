package mc322.jogo.entidades;

import java.util.ArrayList;

import mc322.jogo.cartas.Carta;
import mc322.jogo.cartas.CartaDano;
import mc322.jogo.cartas.CartaEfeito;
import mc322.jogo.cartas.CartaEscudo;
import mc322.jogo.cartas.TiposCartas;
import mc322.jogo.efeitos.Efeito;
import mc322.jogo.efeitos.EfeitoFraqueza;
import mc322.jogo.efeitos.TiposEfeitos;
import mc322.jogo.gerenciador.GameManager;
import mc322.jogo.observer.Estados;
import java.util.Collections;

/**
 * Classe responsável por representar os adversários do jogo.
 * Herda de Entidade e possui uma mecânica de combate automática baseada
 * em um baralho pré-definido.
 */

public class Inimigo extends Entidade {

    private ArrayList<Carta> deckInimigo;
    private Carta ultimaCartaUsada;

    public Inimigo(String nome, int vida, int escudo, int vidaInicial, int velocidade, boolean turno, GameManager gm) {
        this.nome = nome;
        this.vida = vida;
        this.escudo = escudo;
        this.vidaInicial = vidaInicial;
        this.velocidade = velocidade;
        this.turno = turno;
        this.deckInimigo = new ArrayList<>(); // isso aqui deveria ser na verdade um sistema de ações !!
        this.gm = gm;
        this.listaEfeitos = new ArrayList<>();
    }

    public void adicionaCard(Carta carta) {
        this.deckInimigo.add(carta);
    }

    public void embaralhaBaralho() {
        Collections.shuffle(this.deckInimigo);
    }

    @Override
    public void recebeDano(int dano) {
        if (this.escudo >= dano) {
            this.escudo -= dano;
        } else {

            int danoRestante = dano - this.escudo;
            this.escudo = 0;
            this.vida -= danoRestante;
        }
    }

    @Override
    public void recebeDanoEfeito(int dano) {
        if (this.vida >= dano) {
            this.vida -= dano;
        } else {
            this.vida = 0;
        }
    }

    @Override
    public boolean estaVivo() {
        if (this.vida > 0)
            return true;
        return false;
    }

    // Pega a última carta, analisa e usa. Depois volta pro deck
    public void ataque(Entidade alvo, int valorDano) {
        if (this.deckInimigo.size() > 0) {
            this.ultimaCartaUsada = this.deckInimigo.remove(0);
            this.ultimaCartaUsada.setPersonagem(this);

            if (this.ultimaCartaUsada.getTipoCarta() == TiposCartas.DANO) {
                CartaDano cartaDano = (CartaDano) this.ultimaCartaUsada;

                /* tenho que publicar que o inimigo vai atacar */
                gm.notificar(this, Estados.ATAQUE);
                for (Efeito efeito : this.listaEfeitos) {

                    if (efeito.getTipo() == TiposEfeitos.FRAQUEZA) {
                        valorDano = cartaDano.acessoCartaDanoDano();
                        double fator = (100.0 - ((EfeitoFraqueza) efeito).getValorFraqueza()) / 100;
                        valorDano = (int) (valorDano * fator); // aqui fiz o truncamento para baixo.
                        System.out.println(valorDano);
                    }

                    if (efeito.getTipo() == TiposEfeitos.FORCA) {
                        // vamos ter a implementação do efeito força aqui somando no dano !!
                    }
                }

                alvo.recebeDano(valorDano);

            } else if (this.ultimaCartaUsada.getTipoCarta() == TiposCartas.ESCUDO) {
                CartaEscudo cartaescudo = (CartaEscudo) this.ultimaCartaUsada;
                this.ganhaEscudo(cartaescudo.getEscudoGanho());

            } else if (this.ultimaCartaUsada.getTipoCarta() == TiposCartas.EFEITO) {
                CartaEfeito cartaEfeito = (CartaEfeito) this.ultimaCartaUsada;
                alvo.aplicarEfeito(cartaEfeito.getEfeito());
            }

            this.deckInimigo.add(this.ultimaCartaUsada);
        }
    }

    // Pega o valor da última carta usada, se for do tipo dano.
    public int getDano() {
        if (this.ultimaCartaUsada != null && this.ultimaCartaUsada.getTipoCarta() == TiposCartas.DANO) {
            CartaDano cartadano = (CartaDano) this.ultimaCartaUsada;
            return cartadano.acessoCartaDanoDano();
        }

        return 0;
    }

    public String getNomeCarta() {
        return this.ultimaCartaUsada.getNome();
    }

    public TiposCartas getTipoCarta() {
        return this.ultimaCartaUsada.getTipoCarta();
    }

    @Override
    public void ganhaEscudo(int valorEscudo) {
        this.escudo += valorEscudo;
    }

    @Override
    public void zeraEscudo() {
        this.escudo = 0;
    }

    @Override
    public int getEscudo() {
        return this.escudo;
    }

    @Override
    public String getNome() {
        return this.nome;
    }

    @Override
    public int getVida() {
        return this.vida;
    }

    @Override
    public int getVidaInicial() {
        return this.vidaInicial;
    }

    // Subsitui o deck atual do inimigo por uma lista de cartas definidas, que estão
    // em Dados.
    public void transformaDeck(ArrayList<Carta> cartas) {
        this.deckInimigo = cartas;
    }

    @Override
    public int getVelocidade() {
        return this.velocidade;
    }

    @Override
    public boolean getTurno() {
        return this.turno;
    }

    // Olha se já atacou
    @Override
    public void verificaseAtacou(boolean status) {
        this.turno = status;
    }

    /* vale questionar se isso deve estar aqui ou não */
    private int buscaEfeito(TiposEfeitos tipoAlvo) {
        for (int i = 0; i < this.listaEfeitos.size(); i++) {
            Efeito efeito = listaEfeitos.get(i);

            if (efeito.getTipo() == tipoAlvo)
                return i;
        }
        return -1; // não existe ainda esse efeito agindo no Heroi
    }

    public void aplicarEfeito(Efeito efeito) {
        int valor = this.buscaEfeito(efeito.getTipo());

        /* significa que ainda não existe esse efeito nessa entidade */
        if (valor == -1) {
            Efeito novoEfeito = Efeito.criaEfeito(efeito);
            novoEfeito.setDono(this);
            this.listaEfeitos.add(novoEfeito);

            /* preciso inscrever cada efeito do modo correto */
            if (novoEfeito.getTipo() == TiposEfeitos.VENENO) {
                this.gm.inscrever(novoEfeito, Estados.INICIO_DE_TURNO);

            } else if (novoEfeito.getTipo() == TiposEfeitos.FRAQUEZA) {
                this.gm.inscrever(novoEfeito, Estados.ATAQUE);
                this.gm.inscrever(novoEfeito, Estados.FIM_DE_TURNO);

            } else if (novoEfeito.getTipo() == TiposEfeitos.FORCA) {
                // implementação do tipo força
            }

        } else {
            /* decido como cada tipo de efeito vai se comportar */
            if (efeito.getTipo() == TiposEfeitos.VENENO) {
                this.listaEfeitos.get(valor).aumentaAcumulos(efeito.getAcumulosInicial());

            } else if (efeito.getTipo() == TiposEfeitos.FRAQUEZA) {
                ((EfeitoFraqueza) this.listaEfeitos.get(valor))
                        .alteraFraqueza(((EfeitoFraqueza) efeito).getValorFraqueza(), efeito.getAcumulosInicial());

            } else if (efeito.getTipo() == TiposEfeitos.FORCA) {
                // implementação do tipo força
            }
        }
    }

    @Override
    public void terminaEfeito(TiposEfeitos tipo) {
        int indice = this.buscaEfeito(tipo);
        this.listaEfeitos.remove(indice);
    }

    public void setHasEfeitoFraqueza(boolean valor) {
        this.hasEfeitoFraqueza = valor;
    }

    public boolean getHasEfeitoFraqueza() {
        return this.hasEfeitoFraqueza;
    }
}
