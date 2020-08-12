# Backend-R.O.B.O
R.O.B.O - Robô Operacional Binariamente Orientado (R.O.B.O) desenvolvido como Prova de Conceito (PoC)

![Robo](https://raw.github.com/crtiago/Frontend-R.O.B.O/master/src/assets/img/robot.png)

# Restrições para o funcionamento do R.O.B.O.
- O estado inicial dos movimentos é **Em Repouso**.
- Só poderá movimentar o **Pulso** caso o **Cotovelo** esteja **Fortemente Contraído**.
- Só poderá **Rotacionar** a **Cabeça** caso sua **Inclinação** da **Cabeça** não esteja em
estado **Para Baixo**.
- Ao realizar a progressão de estados, é necessário que sempre siga a ordem
crescente ou decrescente, por exemplo, a partir do estado 4, pode-se ir para
os estados 3 ou 5, nunca pulando um estado.
- Atenção aos limites! Se tentar enviar um estado inválido você irá corromper o
sistema do R.O.B.O.

# Passos para executar o Frontend:
- Passo 1: realizar o download do Backend.
- Passo 2: abrir o projeto em uma IDE compatível com Java, preferencialmente na IDE [Spring Tool Suite](https://spring.io/tools).
- Passo 3: localizar a classe [Main](https://github.com/crtiago/Backend-R.O.B.O/blob/master/src/main/java/br/com/crtiago/apirest/ApirestApplication.java) e
executar como **Spring Boot App**.
- Passo 4: executar Frontend disponível em [Frontend Robo](https://github.com/crtiago/Frontend-R.O.B.O), seguindo os passos propostos no mesmo.

