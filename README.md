# RPG do Shrek 

**Matéria:** MC322  
**Membros:** Arthur Nascimento de Souza (269451) - Felipe Garcia Prado (281173)



## Visão Geral
Num lugar tão, tão distante, a paz de um pântano bucólico foi interrompida por perigos diversos. Este projeto é uma simulação de combate onde a lógica de programação encontra o universo de Shrek. O objetivo é simples: sobreviver ao ataque de um Dragão montando estratégias a partir de um baralho de habilidades e gerenciando seus recursos com sabedoria.


**Funcionalidades:**
* **Sistema de Turnos:** O jogador e o inimigo alternam ações até que a vida de um chegue a zero.
* **Sistema de Fases:** O turno do jogador é dividido entre "Fase de Compra" e "Fase de Ação", trazendo mecânicas de deckbuilding e card games.
* **Gerenciamento de Energia:** O herói possui uma quantidade limitada de energia por turno para usar cartas.
* **Baralho e Mão Dinâmicos:** As cartas são compradas de um baralho, utilizadas a partir da mão do jogador e embaralhadas novamente ao fim do turno.
* **Deck de Cartas:** Cartas de dano com diferentes custos e potências.
* **Mecânica de Escudo:** Proteção que absorve o dano recebido e reseta ao final de cada rodada.
* **Interface via Console:** Interação interativa utilizando a classe Scanner.

---


## Estrutura do Projeto

O projeto é dividido nas seguintes classes principais:

| Classe |Descrição| 
| :--- | :--- | 
| **App** | Ponto de entrada (Main) que gerencia o fluxo da partida e a interface com o usuário. |
| **Herói** | Representa o jogador, gerencia o inventário de cartas, vida, energia e escudo. | 
| **Inimigo** | Entidade autônoma que ataca o herói após o encerramento do turno do jogador. | 
| **CartaDano** | Define o nome, custo de energia e o valor de dano causado ao oponente.|
| **Baralho** | Estrutura responsável por armazenar todas as cartas disponíveis, gerenciar a pilha de compras e embaralhar o deck. |
| **CartaEscudo** | Define a quantidade de proteção fornecida ao herói. |
| **Carta** | Classe/Interface base que define o comportamento padrão de uma carta jogável (Polimorfismo). | 
| **Entidade** | Classe base que define o comportamento padrão de um personagem. |



## Como Jogar 

Ao iniciar o jogo, os embates acontecem em ciclos de turnos. O turno do jogador é dividido em duas etapas principais:

* **1. Fase de compra**

* No início do seu turno, você se depara com a pilha de compras.
* Você pode escolher e comprar até 4 cartas das opções disponíveis no topo do baralho.
* Assim que terminar sua seleção (ou atingir o limite), escolha a opção para avançar para a batalha.


* **2. Fase de ação**
Com as cartas na mão e sua Energia restaurada, você deve decidir sua estratégia:

* **Ver mão:** Lista todas as cartas que você comprou nesta rodada, mostrando seus custos de energia e efeitos.
* **Usar cartas:** Permite selecionar uma carta da mão pelo seu número.  Gasta energia. Se for uma *carta de dano*, o inimigo perderá vida, se for uma *carta de escudo*, o herói ganha proteção extra para o turno atual.
* **Encerrar turno** Finaliza suas ações. As cartas restantes na sua mão são descartadas, o baralho é reembaralhado e o Inimigo realiza o seu ataque.

* ***Dica de Ouro***: O escudo não acumula entre os turnos!



## Como Executar

Para rodar o jogo, certifique-se de ter o Java instalado e um terminal com suporte a cores ANSI.


```java

javac App.java
java App
```


