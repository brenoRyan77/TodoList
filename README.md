# TodoList com Spring Boot

Este é um projeto de exemplo que demonstra a criação de tarefas e o uso do `@Scheduled` para atualizar o status da Tarefa.

## Como Usar a Anotação @Scheduled no Spring Framework

A anotação `@Scheduled` é uma característica poderosa do Spring Framework que permite agendar a execução de métodos em intervalos regulares. Essa funcionalidade é útil para tarefas como processamento em segundo plano, geração de relatórios programados, atualizações periódicas de dados e muito mais.

Neste guia, vamos explicar como utilizar a anotação `@Scheduled` em seu aplicativo Spring.

### 1. Configuração do Projeto

Antes de usar a anotação `@Scheduled`, é importante garantir que seu projeto Spring esteja configurado corretamente. Siga os passos a seguir:

1. **Adicione as Dependências**: Certifique-se de que seu arquivo de configuração (pom.xml ou build.gradle) inclua as dependências necessárias, como `spring-boot-starter` e `spring-boot-starter-web`. Certifique-se de que o Spring Boot esteja configurado em seu projeto.

2. **Habilitar Agendamento**: Em sua classe principal (a que possui o método `main`), adicione a anotação `@EnableScheduling` para habilitar o agendamento de tarefas. Por exemplo:

```java
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;

@SpringBootApplication
@EnableScheduling
public class MinhaAplicacao {
    public static void main(String[] args) {
        SpringApplication.run(MinhaAplicacao.class, args);
    }
}
```

### 2. Uso da Anotação @Scheduled

A anotação `@Scheduled` pode ser usada para marcar qualquer método que você deseja agendar para execução em intervalos regulares. Existem três atributos principais que você pode utilizar:

 - fixedRate: Especifica o intervalo entre a conclusão da execução anterior e o início da próxima.
 - fixedDelay: Especifica o intervalo entre o fim da última execução e o início da próxima.
 - cron: Permite agendar tarefas de acordo com uma expressão cron.

Aqui está um exemplo de um método anotado com `@Scheduled`:

```java
import org.springframework.scheduling.annotation.Scheduled;

@Scheduled(fixedRate = 60000) // Executado a cada minuto
public void meuMetodoAgendado() {
    // Lógica da tarefa agendada
}
```

### 3. Personalização Avançada

Além dos atributos fixedRate e fixedDelay, a anotação `@Scheduled` permite personalizações avançadas por meio do atributo cron. O atributo cron oferece a flexibilidade de definir agendamentos altamente personalizados, de acordo com a expressão cron.

Por exemplo, para agendar uma tarefa para ser executada todos os dias às 15:30, você pode fazer o seguinte:


```java
@Scheduled(cron = "0 30 15 * * ?") // Executado todos os dias às 15:30
public void tarefaDiaria() {
    // Lógica da tarefa diária
}
```


# Instruções de Compilação e Execução
  - Clone este repositório em sua máquina local.
  - Configure as dependências no seu ambiente de desenvolvimento.
  - Compile o código-fonte.
  - Execute o aplicativo.
