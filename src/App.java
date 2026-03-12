import java.util.Scanner;

public class App {
  public static void main(String[] args) {
    Scanner sc = new Scanner(System.in);

    Heroi player1 = new Heroi("Shrek", 100, 20, 5, 10); 
    Inimigo enemy1 = new Inimigo("Dragão", 80, 10, 40);                    
    CartaDano card1 = new CartaDano("Bola de Fogo", 2, 15);
    CartaDano card2 = new CartaDano("Corte de Espada", 1, 10);
    CartaEscudo shield = new CartaEscudo("Proteção", 3, 20);

    System.out.println("Bem vindo ao jogo de RPG do Shrek !!");
    System.out.println("Use suas cartas para destruir o Dragão\n");

    player1.adiciona_card(card1); 
    player1.adiciona_card(card2); 
    System.out.println("\n-----------------------------------------------------------\n");


    int vida_inicial_p1 = player1.acessoVida(); 
    int vida_inicial_e1 = enemy1.acessoVida(); 

    while (player1.estaVivo() && enemy1.estaVivo()) {
  
      int energia = player1.acessoEnergia(); // energia inicial do personagem heroi.
      int opcao = 0;
      player1.resetarEscudo();

      while (opcao != 3 && enemy1.estaVivo()) { 

        System.out.println(player1.acessoNome() + " " + player1.acessoVida() + "/" + vida_inicial_p1 + " de vida  | "
            + player1.acessoEscudo() + " de escudo");
        System.out.println("vs");
        System.out.println(enemy1.acessoNome() + " " + enemy1.acessoVida() + "/" + vida_inicial_e1 + " de vida  | "
            + enemy1.acessoEscudo() + " de escudo ");

        System.out.println("Energia: " + energia + " de energia disponível");
        System.out.println("1 - Usar Carta de Dano");
        System.out.println("2 - Usar Carta de Escudo");
        System.out.println("3 - Encerrar Turno");
        System.out.println("Escolha:");

        opcao = sc.nextInt();

        if (opcao == 1) {
          System.out.println("Suas Cartas:");
          player1.imprimeCartasDano();

          System.out.println("Escolha a carta:");
          int i = sc.nextInt();
          CartaDano[] vetor = player1.acessoVetorCartaDano();
          CartaDano carta_escolhida = vetor[i];
          int custo = carta_escolhida.acessoCartaDanoCusto();

          if (energia >= custo) {
            enemy1.ReceberDano(player1, carta_escolhida.acessoCartaDanoNome());
            energia -= custo;
            System.out.println("\nVocê usou " + carta_escolhida.acessoCartaDanoNome() + "!");
          } else {
            System.out.println("\nVOCÊ  NÃO TEM MAIS ENERGIA!");
          }
        }

        else if (opcao == 2) {
          int custo = shield.acessoCusto();
          if (energia >= custo) {
            player1.ganhaEscudo(shield);
            energia -= custo;
            System.out.println("Você ganhou escudo!");
          } else {
            System.out.println("\nVOCÊ  NÃO TEM MAIS ENERGIA!");
          }

        }

        else {
          break; 
        }
        System.out.println("\n-----------------------------------------------------------\n"); 
      }

      /* ataque do inimigo */
      if (enemy1.estaVivo()) { // o inimigo só vai atacar se estiver vivo (caso a gente mate ele antes de passar de turno)
        System.out.println("\n-----------------------------------------------------------\n");
        System.out.println("\nTurno do Inimigo:");
        enemy1.atacar(player1);
        System.out.println(enemy1.acessoNome() + " executou o ataque");
        System.out.println("\n-----------------------------------------------------------\n"); 
      }

    }

    /* vamos verificar quem ganhou o jogo */
    if (player1.estaVivo()) {
      System.out.println("Parabéns, você ganhou !!" + "\uD83D\uDE0A"); // emoji feliz
    } else {
      System.out.println("Você foi derrotado !" + "\uD83D\uDE35"); // emoji de derrota
    }
  }

}


