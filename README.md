# Lavador de Pratos - Problema Produtor/Consumidor

## Descrição
Este projeto implementa o problema clássico de programação concorrente, o **Produtor/Consumidor**, no contexto de um sistema de lavagem de pratos. A solução utiliza **threads**, **wait/notify** para sincronização e uma estrutura de **fila FIFO circular** para o escorredor de pratos.

## Estrutura do Projeto
### 1. Classe **Prato**
- Representa um prato sujo com os seguintes atributos:
  - **Número de série** (sequencial).
  - **Nível de sujeira**, definido aleatoriamente conforme a distribuição:
    - 30% **BAIXO**
    - 60% **MÉDIO**
    - 10% **ENGORDURADO**

### 2. Classe **Escorredor**
- Implementa um **buffer limitado** como uma fila FIFO circular para armazenar pratos lavados.
- Regras:
  - **0 <= pratos <= MAX** (onde **MAX** é a capacidade máxima do escorredor).
  - Se o escorredor estiver **cheio** ou **vazio**, uma mensagem será exibida informando o estado e a quantidade de pratos.

### 3. Classe **PratosSujosFactory**
- Simula uma pilha infinita de pratos sujos, gerando um novo prato sempre que solicitado.

### 4. Classe **Lavador** *(Runnable)*
- Responsável por lavar pratos e colocá-los no escorredor.
- **Sincronização:** Utiliza `wait/notify` para interação com o escorredor.
- **Fluxo de trabalho:**
  1. Verifica se há espaço no escorredor.
  2. Se houver, pega um prato sujo e o lava (tempo baseado no nível de sujeira):
     - **BAIXO:** 3ms
     - **MÉDIO:** 5ms
     - **ENGORDURADO:** 10ms
  3. Caso o escorredor esteja cheio, aguarda até que haja espaço.

### 5. Classe **Enxugador** *(Runnable)*
- Responsável por retirar pratos lavados do escorredor e secá-los.
- **Sincronização:** Utiliza `wait/notify` para interação com o escorredor.
- **Fluxo de trabalho:**
  1. Verifica se há pratos lavados no escorredor.
  2. Se houver, retira um prato e o enxuga (tempo aleatório entre **3ms e 10ms**).
  3. Caso o escorredor esteja vazio, aguarda até que o lavador adicione pratos.

### 6. Classe **App**
- Responsável por iniciar e controlar a aplicação.
- Métodos principais:
  - `work()`: Inicia os processos de lavagem e secagem.
  - `stop()`: Encerra os processos.
  - `main()`: Instancia a aplicação, inicia os processos e os encerra após **2 minutos**.

## Informações
- O projeto utiliza **java.util.logging.Logger** para logs.
- A aplicação rodará por **2 minutos** antes de encerrar automaticamente.

---
Desenvolvido para a disciplina de **Programação Concorrente**.

