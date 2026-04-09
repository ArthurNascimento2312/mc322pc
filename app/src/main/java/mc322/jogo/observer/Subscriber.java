package mc322.jogo.observer;

import mc322.jogo.entidades.Entidade;

/**
 * Interface que será implementada pelas classes que são observadoras de
 * determinados eventos do Jogo.
 * A classe responsável por implementar essa interface é a {@link Efeito}
 */
public interface Subscriber {
    /**
     * Méotodo responsável por determinar a açãodde um efeito ao ser notificado por
     * um Estado do jogo. Cada
     * efeito implementa da maneira mais adequada a sua ação.
     * 
     * @param state
     */
    public void serNotificado(Estados state);

    /**
     * Método para acessar a Entidade que está sob ação de um determinado Efeito,
     * para sabermos
     * quem receberá o efeito que foi notificado.
     * 
     * @return Uma entidade que pode ser tanto um Inimigo quanto um Heroi.
     */
    public Entidade getDono();
}
