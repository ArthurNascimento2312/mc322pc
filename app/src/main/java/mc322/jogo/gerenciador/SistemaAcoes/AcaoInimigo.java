package mc322.jogo.gerenciador.SistemaAcoes;

import mc322.jogo.entidades.Heroi;
import mc322.jogo.entidades.Inimigo;

/**
 * interface que tem como objetivo definir a regra de ação para o Inimigo.
 * Entre as ações temos:
 * 
 * ATACAR: tem uma habilidade que gera dano no oponente
 * ESCUDO: pode usar o seu escudo durante um turno
 * EFEITOS: tem efeitos que ele pode aplicar em um oponente
 */
public interface AcaoInimigo {
    public String executar(Inimigo dono, Heroi alvo);
    
}
