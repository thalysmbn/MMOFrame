# MMOFrame

&nbsp;MMOFrame é um projeto em Kotlin utilizando Netty, com o foco de se tornar uma framework para um servidor MMO, facilitando na criação e gerenciamento do server-side de um jogo MMO, trazendo fácil a leitura e implementação de gerenciamento de salas, lobbys, sessões e usuários

# Netty

&nbsp;Utilizar o Netty em um servidor MMO (Massively Multiplayer Online) oferece inúmeras vantagens significativas. Netty é um framework de rede altamente escalável e eficiente que foi projetado com desempenho, uma escolha sólida para servidores MMO devido à sua eficiência, flexibilidade e escalabilidade, podendo lidar com o tráfego intenso e a comunicação de rede complexa que são essenciais para uma experiência de jogo online suave e envolvente.

# Iniciação

**Start**
```java
NettyBootstrap bootstrap = new NettyBootstrap(new NetworkFactory(), 10000, Duration.ofSeconds(10));
bootstrap.build();
```

**Factory**
```java
public class NetworkFactory implements SessionFactory {
    @Override
    public Session create(ChannelAdapter channel) {
        return new NetworkSession(channel);
    }
}
```

**Sessão**
```java
public class NetworkSession implements Session {
    private ChannelAdapter channel;

    public NetworkSession(ChannelAdapter channel) {
        this.channel = channel;
    }

    @Override
    public void close() {

    }

    @Override
    public void receive(Object msg) {

    }

    @Override
    public void inactive() {

    }

    @Override
    public ChannelAdapter channel() {
        return channel;
    }
}
```
