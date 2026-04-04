package mc322.jogo.gerenciador;

import mc322.jogo.Dados;
import mc322.jogo.cartas.CartaDano;
import mc322.jogo.cartas.CartaEscudo;
import mc322.jogo.entidades.Inimigo;



/*  Esquemaático do mapa

                                1-inicio
                                1-batalha 
                                10-rumpel
                                10-flores

                  21-burro                                    22-gato
           31-golpe        32-batalha              33-golpe          34-buraco
    41-batalha    42-bar     43-batalha      44-batalha  45-golpe      46-bar

                                5-farquaad
                                5-masmorra

                           51-batalha     52-batalha
                       61 -dragao/fiona     62-golpe
                                  7-encantado

                            81-batalha    82-golpe

                                    9-fada

                                    90-golpe

                                    100-rumpel

*/




public class Campanha {

    
    private static Oponente empacotarOponente(Inimigo ini) {
        Oponente op = new Oponente();
        op.adicionarInimigoTodos(ini);
        op.gerarInimigos(1); 
        return op;
    }

   

    public static NoMapa criarMapa(GameManager gm) {
        
        
        // 1 - 10  Inicio a flores
        NoMapa n1_inicio = new NoMapa(new EventoMapa(
            "1-Inicio", "Narrador: O caos começa no pântano...", TipoEvento.ESCOLHA_HISTORIA, null
        ));


        NoMapa n1_batalha = new NoMapa(new EventoMapa(
            "1-Batalha", "Capangas invadem sua casa!", TipoEvento.BATALHA, empacotarOponente(Dados.criarAldeao(gm))
        ));


        NoMapa n10_rumpel = new NoMapa(new EventoMapa(
            "10-Rumpel", "Rumpelstiltskin: Assine aqui, ogro!", TipoEvento.BOSS, empacotarOponente(Dados.criarRumpelFraco(gm))
        ));

        NoMapa n10_flores = new NoMapa(new EventoMapa(
            "10-Flores", "Dois caminhos se abrem. Qual você segue?", TipoEvento.ESCOLHA_HISTORIA, null
        ));

        n1_inicio.adicionarCaminho(n1_batalha);
        n1_batalha.adicionarCaminho(n10_rumpel);
        n10_rumpel.adicionarCaminho(n10_flores);


        // --- CAMINHO ESQUERDO (BURRO) ---
        NoMapa n21_burro = new NoMapa(new EventoMapa(
            "21-Batalha Burro", 
            "Burro: Me salva desses caras!", 
            TipoEvento.BATALHA, 
            empacotarOponente(Dados.criarAldeao(gm)) 
        ));
        
        NoMapa n31_golpe = new NoMapa(new EventoMapa("31-Golpe Único", "Você treina um golpe novo.", TipoEvento.RECOMPENSA_CARTA, null));
        n31_golpe.getEvento().setCartaRecompensa(new CartaDano("Voadora Deslizante", "[Dano: 25]", 2, 25, 0));
        NoMapa n32_batalha = new NoMapa(new EventoMapa("32-Batalha", "Mais inimigos na floresta.", TipoEvento.BATALHA, empacotarOponente(Dados.criarLobo(gm))));
        
        NoMapa n41_batalha = new NoMapa(new EventoMapa("41-Batalha", "Luta difícil.", TipoEvento.BATALHA, empacotarOponente(Dados.criarLobo(gm))));
        NoMapa n42_bar = new NoMapa(new EventoMapa("42-Bar", "O bar maçã envenenada. Descanse um pouco.", TipoEvento.DESCANSO_BAR, null));
        NoMapa n43_batalha = new NoMapa(new EventoMapa("43-Batalha", "Luta na ponte.", TipoEvento.BATALHA, empacotarOponente(Dados.criarLobo(gm))));

        n10_flores.adicionarCaminho(n21_burro);

        n21_burro.adicionarCaminho(n31_golpe);
        n21_burro.adicionarCaminho(n32_batalha);

        n31_golpe.adicionarCaminho(n41_batalha);
        n31_golpe.adicionarCaminho(n42_bar);

        n32_batalha.adicionarCaminho(n43_batalha);


        // --- CAMINHO DIREITO (GATO) ---
        NoMapa n22_gato = new NoMapa(new EventoMapa(
            "22-Batalha Gato", "Gato de Botas: Prepare-se para morrer!", TipoEvento.BATALHA, empacotarOponente(Dados.criarAldeao(gm))
        ));

        NoMapa n33_golpe = new NoMapa(new EventoMapa("33-Golpe Único", "Você acha uma habilidade na árvore.", TipoEvento.RECOMPENSA_CARTA, null));
        n33_golpe.getEvento().setCartaRecompensa(new CartaEscudo("Esquiva Furtiva", "[Escudo: 15]", 1, 15, 1));
        
        NoMapa n34_buraco = new NoMapa(new EventoMapa("34-Buraco", "Cuidado onde pisa!", TipoEvento.ARMADILHA, null));
        
        NoMapa n44_batalha = new NoMapa(new EventoMapa("44-Batalha", "Emboscada felina.", TipoEvento.BATALHA, empacotarOponente(Dados.criarLobo(gm))));
        NoMapa n45_golpe = new NoMapa(new EventoMapa("45-Golpe", "Outro pergaminho mágico.", TipoEvento.RECOMPENSA_CARTA, null));
        n45_golpe.getEvento().setCartaRecompensa(new CartaDano("Corte Duplo", "[Dano: 25]", 2, 25, 0));
        
        NoMapa n46_bar = new NoMapa(new EventoMapa("46-20 de Escudo", "Acha uma panela de ferro. Ganha defesa passiva.", TipoEvento.RECOMPENSA_CARTA, null));

         n10_flores.adicionarCaminho(n22_gato);

        n22_gato.adicionarCaminho(n33_golpe);
        n22_gato.adicionarCaminho(n34_buraco);

        n33_golpe.adicionarCaminho(n44_batalha);
        n33_golpe.adicionarCaminho(n45_golpe);

        n34_buraco.adicionarCaminho(n46_bar);


   
        // 5 FARQUAAD

        NoMapa n5_farquaad  = new NoMapa(new EventoMapa(
            "5-Farquaad", 
            "Farquaad: Morra, aberração!", 
            TipoEvento.BOSS, 
            empacotarOponente(Dados.criarFarquaad(gm)) ));

        NoMapa n5_masmorra = new NoMapa(new EventoMapa("masmorra", "Descendo para o vulcão...", TipoEvento.ESCOLHA_HISTORIA, null));

        
        n41_batalha.adicionarCaminho(n5_farquaad);
        n42_bar.adicionarCaminho(n5_farquaad);
        n43_batalha.adicionarCaminho(n5_farquaad);
        n44_batalha.adicionarCaminho(n5_farquaad);
        n45_golpe.adicionarCaminho(n5_farquaad);
        n46_bar.adicionarCaminho(n5_farquaad);

        n5_farquaad.adicionarCaminho(n5_masmorra);


        // Até encantado

        NoMapa n51_batalha = new NoMapa(new EventoMapa("51-batalha", "Morcegos de Fogo!", TipoEvento.BATALHA, empacotarOponente(Dados.criarLobo(gm))));
        NoMapa n52_batalha = new NoMapa(new EventoMapa("52-batalha", "Esqueletos vivos!", TipoEvento.BATALHA, empacotarOponente(Dados.criarLobo(gm))));
        
        NoMapa n61_dragao_fiona = new NoMapa(new EventoMapa("61-dragao/fiona", "O Dragão protege a torre.", TipoEvento.BOSS, empacotarOponente(Dados.criarDragao(gm))));
        
        NoMapa n62_golpe = new NoMapa(new EventoMapa("62-golpe", "Acha uma espada quebrada.", TipoEvento.RECOMPENSA_CARTA, null));
        n62_golpe.getEvento().setCartaRecompensa(new CartaDano("Lâmina da Masmorra", "[Dano: 35]", 2, 35, 0));

        NoMapa n7_encantado = new NoMapa(new EventoMapa("7-encantado", "Príncipe: Ela é minha!", TipoEvento.BOSS, empacotarOponente(Dados.criarEncantado(gm))));

        n5_masmorra.adicionarCaminho(n51_batalha);
        n5_masmorra.adicionarCaminho(n52_batalha);

        n51_batalha.adicionarCaminho(n61_dragao_fiona);
        n52_batalha.adicionarCaminho(n62_golpe);

        n61_dragao_fiona.adicionarCaminho(n7_encantado);
        n62_golpe.adicionarCaminho(n7_encantado);


        //fada e rumpel
        NoMapa n81_batalha = new NoMapa(new EventoMapa("81-batalha", "Elfos furiosos!", TipoEvento.BATALHA, empacotarOponente(Dados.criarLobo(gm))));
        
        NoMapa n82_golpe = new NoMapa(new EventoMapa("82-golpe", "Rouba uma poção da fábrica.", TipoEvento.RECOMPENSA_CARTA, null));
        n82_golpe.getEvento().setCartaRecompensa(new CartaEscudo("Poção de Defesa", "[Escudo: 40]", 2, 40, 1));

        NoMapa n9_fada = new NoMapa(new EventoMapa("9-fada", "Fada Madrinha ataca!", TipoEvento.BOSS, empacotarOponente(Dados.criarFada(gm))));
        
        NoMapa n90_golpe = new NoMapa(new EventoMapa("90-golpe", "O feitiço final.", TipoEvento.RECOMPENSA_CARTA, null));
        n90_golpe.getEvento().setCartaRecompensa(new CartaDano("Varinha Quebrada", "[Dano: 50]", 3, 50, 0));

        NoMapa n100_rumpel = new NoMapa(new EventoMapa("100-rumpel", "Rumpelstiltskin: A magia é minha!", TipoEvento.BOSS, empacotarOponente(Dados.criarRumpel(gm))));

        n7_encantado.adicionarCaminho(n81_batalha);
        n7_encantado.adicionarCaminho(n82_golpe);

        n81_batalha.adicionarCaminho(n9_fada);
        n82_golpe.adicionarCaminho(n9_fada);

        n9_fada.adicionarCaminho(n90_golpe);
        n90_golpe.adicionarCaminho(n100_rumpel);

        return n1_inicio; 
    }
}