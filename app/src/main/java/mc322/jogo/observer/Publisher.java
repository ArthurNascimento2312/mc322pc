package mc322.jogo.observer;

import mc322.jogo.entidades.Entidade;

/**
 * Interface responsável por publicar e gerenciar a lista de interessados em um
 * evento
 * dentro do jogo. Essa interface é implementada pela classe
 * {@link Gamemanager}, já
 * que essa classe é quem gerencia os estados do jogo.
 */
public interface Publisher {
    /**
     * Método para inscrever um Efeito em uma lista de eventos do joggo. Esses
     * eventos
     * estão listados em {@link Estados}.
     * 
     * @param observador Efeito do jogo que passa a precisar de eventos para poder
     *                   ser executado
     * @param state      Estado do jogo o qual o Efeito está interessado em saber
     *                   quando vai acontecer
     */
    public void inscrever(Subscriber observador, Estados state);

    /**
     * O método casado com Inscrever que serve para retirar o Efeito da lista de
     * observadores quando ele terminar
     * o seu prazo de ocorrência.
     * 
     * @param observador efeito do jogo que precisa ser retirado da lista de eventos
     * @param state      Estado do jogo o qual o Efeito está interessaddo em retirar
     *                   a sua inscrição
     */
    public void desinscrever(Subscriber observador, Estados state);

    /**
     * Método para notificar o efeito quando determinado evento do jogo acontecer.
     * Esse método funciona com um outro método serNotificado da interface
     * {@link Subscriber}
     * para que a ação de um efeito ocorra durante o evento certo.
     * 
     * @param observador Efeito do jogo que precisa ser notificado
     * @param state      Estado do jogo que aconteceu naquele instante e foi o
     *                   motivo para disparar essa notificação para os efeitos
     *                   interessados.
     */
    public void notificar(Entidade observador, Estados state);
}
