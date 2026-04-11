package mc322.jogo.gerenciador;

    import java.util.Scanner;

import mc322.jogo.Cores;
import mc322.jogo.RequisitoJogo;
import mc322.jogo.entidades.Heroi;
import mc322.jogo.observer.Estados;

/**
 * Classe responsável por gerenciar a interação do jogador durante o turno de um
 * Herói.
 * Divide-se em duas etapas principais: Fase de Compra (Loja) e Fase de Ação
 * (Combate).
 */
public class TurnoHeroi {
    private GameManager gm;

    /* criar constantes para melhorar a legibilidade */
    private static final int OPCAO_ENCERRA_TURNO = 3;
    private static final int OPCAO_VER_CARTAS = 1;
    private static final int OPCAO_USAR_CARTAS = 2;
    private static final int ENCERRAR_FASE_COMPRA = 2;
    private static final int COMPRA_CARTA = 1;

    public TurnoHeroi(GameManager gm) {
        this.gm = gm;
    }

    /**
     * Método responsável por gerenciar um Turno do Heroi com respeito a interação
     * com o terminal.
     * Responsável por gerenciar as entradas do usuário durante o turno do heroi, ou
     * seja, as cartas
     * escolhidas e as ações de ataacar, aplicar escudo ou aplicar efeito.
     * 
     * @param player   Heroi que vai atacar nesse turno
     * @param herois   Objeto Jogador que serve para gerenciar a lista com todos os
     *                 herois da batalha.
     * @param oponente lista com todos os inimigos escolhidos nessa batalha
     * @param tela     Classe com todos os prints de telas do jogo {@link Prints}
     * @param sc       Scanner do teclado.
     */
    public void jogar(Heroi player, Jogador herois, Oponente oponente, Prints tela, Scanner sc) {

        player.zeraEscudo();
        int opcaoCompra = 0;
        int cartasCompradas = 0;
        int limiteCompra = 3;
        player.getBaralhoPessoal().criarPilhaCompra(4);

        /* vamos notificar que iniciou o turno para a entidade */
        gm.notificar(player, Estados.INICIO_DE_TURNO);

        /*
         * é necessário resetar a energia do player para ele jogar no turno (posso
         * trocar por inicia turno no futuro)
         */
        player.resetaEnergia();

        // ----------------------------FASE DE COMPRA ----------------------------------

        while (opcaoCompra != ENCERRAR_FASE_COMPRA && oponente.temInimigosVivos() && cartasCompradas < limiteCompra
                && player.estaVivo()) {
            
            player.imprimeEfeitos();
            tela.status_batalha(player, herois.getHeroisEscolhidos(), oponente.getInimigosEscolhidos());
            tela.energia(player.getEnergiaAtual());
            tela.faseCompra(limiteCompra, cartasCompradas);

            opcaoCompra = sc.nextInt();

            if (opcaoCompra == COMPRA_CARTA) {
                player.getBaralhoPessoal().imprimePilhaCompra();
                int i = sc.nextInt();

                if (i < 0 || player.getBaralhoPessoal().tamanhoPilha() <= i) {
                    System.out.println(Cores.VERMELHO + "Opção inválida!" + Cores.RESET);

                } else {
                    player.getBaralhoPessoal().compraCarta(player, i);
                    Prints.limparTela();  
                    System.out.println("\n");
                    System.out.println(Cores.VERDE + "Você comprou uma carta!" + Cores.RESET);
                    cartasCompradas++;
                }

            } else if (opcaoCompra != ENCERRAR_FASE_COMPRA) {
                System.out.println(Cores.VERMELHO + "Opção inválida!" + Cores.RESET);
            }
        }
        Prints.limparTela();  
        player.getBaralhoPessoal().devolverCartasNaoCompradas();

        // ---------------------------FASE DE
        // AÇÃO-----------------------------------------

        int opcao = 0;
        while (opcao != OPCAO_ENCERRA_TURNO && oponente.temInimigosVivos() && player.estaVivo()) {
           
            tela.status_batalha(player, herois.getHeroisEscolhidos(), oponente.getInimigosEscolhidos());
            tela.energia(player.getEnergiaAtual());

            tela.faseBatalha();
            opcao = sc.nextInt();

            if (opcao == OPCAO_VER_CARTAS) {
                Prints.limparTela();  
                System.out.println("\nSuas Cartas na Mão:");
                player.imprimeMaoJogador();
            }

            else if (opcao == OPCAO_USAR_CARTAS) {
                
                System.out.println(Cores.CIANO + "\nSuas Cartas na Mão:" + Cores.RESET);
                player.imprimeMaoJogador(); // imprimi que a mão está vazia também

                if (player.temCartaDisponivel()) { // verifica se a mão não está vazia
                    System.out.print(Cores.NEGRITO + "Escolha o número da carta para usar: " + Cores.RESET);

                    /* a lógica de escolher um índice válido de carta */
                    int i = sc.nextInt();
                    while (!player.temOpcaoCartaMao(i)) {
                        System.out.println(Cores.VERMELHO + "Carta inválida!" + Cores.RESET);
                        System.out.print(Cores.NEGRITO + "Escolha o número da carta para usar: " + Cores.RESET);
                        i = sc.nextInt();
                    }

                    // A ideia desse switch é pedir a carta o que ela precisa para ser
                    // executada,assim não perco o encapsulamento
                    RequisitoJogo requisito = player.temRequisito(i); // pergunto o que preciso para a carta escolhida
                    String resposta;

                    /* para gerenciar as telas */
                    switch (requisito) {
                        // por enquanto aqui vão entrar carta de dano e carta de efeito direto no
                        // inimigo
                        case RequisitoJogo.INIMIGO:

                            /*
                             * vamos gerenciar a tela para ele escolher um inimigo necessário para a carta
                             * que ele escolheu
                             */
                            System.out.println(Cores.VERMELHO + "\nEscolha o seu alvo: " + Cores.RESET);
                            oponente.imprimeInimigosVivos();

                            System.out.print(Cores.NEGRITO + "Alvo: " + Cores.RESET);
                            int alvoEscolhido = sc.nextInt();

                            /* valida a entrada do usuário */
                            while (!oponente.validaEscolhaInimigo(alvoEscolhido)) {
                                System.out.println(Cores.VERMELHO + "Alvo inválido!" + Cores.RESET);
                                System.out.print(Cores.NEGRITO + "Alvo: " + Cores.RESET);
                                alvoEscolhido = sc.nextInt();
                            }
                            Prints.limparTela(); 
                            resposta = player.jogarCarta(i, oponente.getInimigo(alvoEscolhido));
                            System.out.println(resposta);
                            break;

                        /* cartas de escudo e cartas de regeneração */
                        case RequisitoJogo.HEROI:
                            resposta = player.jogarCarta(i, player);
                            System.out.println(resposta);
                            break;

                        /* cartas como efeito em área vão entrar aqui */
                        case RequisitoJogo.TODOS_INIMIGOS:
                            Prints.limparTela(); 
                            resposta = player.jogarCarta(i, oponente.getInimigosEscolhidos());
                            System.out.println(resposta);
                            break;

                        /* Aqui é quando o usuário pode escolher um novo companheiro Herói */
                        case RequisitoJogo.TODOS_HEROIS:
                            System.out.println(Cores.VERDE + "\nEscolha o seu Herói: " + Cores.RESET);
                            herois.imprimeHeroisVivos();

                            System.out.print(Cores.NEGRITO + "Herói: " + Cores.RESET);
                            int heroiEscolhido = sc.nextInt();

                            /* valida entrada do usuário */
                            while (!herois.validaEscolhaHeroi(heroiEscolhido)) {
                                System.out.println(Cores.VERMELHO + "Heroi inválido!" + Cores.RESET);
                                System.out.print(Cores.NEGRITO + "Heroi: " + Cores.RESET);
                                heroiEscolhido = sc.nextInt();
                            }

                            Prints.limparTela(); 

                            resposta = player.jogarCarta(i, herois.getHeroisEscolhidos().get(heroiEscolhido));
                            System.out.println(resposta);
                            break;
                        default:
                            break;
                    }
                } else {
                    System.out.println("Você não tem mais cartas disponíveis !!!");
                }
            }

        }
        Prints.limparTela();  
        player.limpaMao();
        gm.notificar(player, Estados.FIM_DE_TURNO);
    }
}
