# Promocity
Serviço de promoções geolocalizadas. 

Features
---

* Serviços para Usuários
* Serviços para Lojistas
* Serviços para Promoções
* Servicos para Cupons
* Identificar proximidade de localização do usuário para uma loja
* Envio de mensagem para usuário
* Envio de cupom para usuário

TODO
--- 

* Gerar imagem de QRCode para um cupom
* Serviços de CRUD de usuários
* Serviços de CRUD de lojistas
* Serviços de Controle de autenticação de usuários e lojistas
* Serviços de CRUD de promoções
* Serviços CRUD de cupons
* Camanda de segurança

Sobre as operações para execução da aplicação
---

1. Faça o clone do repositório.

2. Limpe o projeto via comando clean do maven.
```
$mvn clean
```
3. Compile o projeto via modo teste do maven. 
```
$mvn test
```
4. Execute a classe principal (BackendApplication) do projeto via maven. 
```
$mvn spring-boot:run
```
5. Listagens com dados de teste:

* localhost:8082/promocity/users
* localhost:8082/promocity/stores

Referências
---

[1] Maven. Management of Builds and Dependencies. Available at https://maven.apache.org

[2] Spring Boot 1. It is a Java Framework (based on the Spring Platform) for web applications that use inversion control container for the Java platform. Available at https://docs.spring.io/spring-boot/docs/current/reference/htmlsingle/#boot-features-security

[3] Jersey. It is an open source RESTful Web Services framework to facilitate development of RESTful Web services and their clients in Java, a standard and portable JAX-RS API. Availale at https://jersey.github.io 

Dúvidas, questionamentos ou sugestões enviar email para armando@ufpi.edu.br
