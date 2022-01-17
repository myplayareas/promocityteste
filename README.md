# Promocity
Serviço ubíquo de promoções geolocalizadas. 

A aplicação móvel fica disponível em https://github.com/juarezmeneses/PromoCityApp

A aplicação web fica disponível em https://github.com/armandossrecife/syspromocity

![License](https://img.shields.io/badge/License-BSD%202--Clause-orange.svg) 

Features
---

* Serviços para Usuários
* Serviços para Lojistas
* Serviços para Promoções
* Servicos para Cupons
* Identificar proximidade de localização do usuário para uma loja
* Envio de mensagem para usuário
* Envio de cupom para usuário

[![](https://codescene.io/projects/4278/status.svg) Get more details at **codescene.io**.](https://codescene.io/projects/4278/jobs/latest-successful/results)

Sobre as operações para execução da aplicação
---

1. Faça o clone do repositório.

2. Crie o banco promocity.
```
mysql> create database promocity
```

3. Rode o script restaura-banco.sql para criar as tabelas com os dados de exemplo.
```
mysql> source scripts/sql/restaura-banco.sql
```

4. Limpe o projeto via comando clean do maven.
```
$mvn clean
```

5. Compile o projeto via modo teste do maven. 
```
$mvn test
```

6. Execute a classe principal (BackendApplication) do projeto via maven. 
```
$mvn spring-boot:run
```

7. Listagens com dados de teste:

http://localhost:8082/promocity 

mostra os seguintes recursos disponíveis

"http://localhost:8082/promocity/users";
"http://localhost:8082/promocity/users/{idUser}";
"http://localhost:8082/promocity/users/{idUser}/coupons";
"http://localhost:8082/promocity/users/{idUser}/monitoring/location/{latitude}/{longitude}";
"http://localhost:8082/promocity/users/{email}/{senha}";
"http://localhost:8082/promocity/users/{idUser}/add/friend/{idFriend}";
"http://localhost:8082/promocity/users/{idUser}/list/friends";
"http://localhost:8082/promocity/users/{idUser}/delete/friend/{idFriend}";
"http://localhost:8082/promocity/users/{idUser}/activate/coupon/{idCoupon}/store/{idStore}/friends/{idFriend1}/{idFriend2}";
"http://localhost:8082/promocity/users/{idUser}/list/tracks";	

"http://localhost:8082/promocity/stores";
"http://localhost:8082/promocity/stores/{idStore}/promotions";
"http://localhost:8082/promocity/stores/{idStore}/promotions/{idPromotion}";
"http://localhost:8082/promocity/stores/{idStore}/reportpromotions/{idPromotion}";
"http://localhost:8082/promocity/stores/{idStore}/promotions/{idPromotion}/coupons";
"http://localhost:8082/promocity/stores/{idStore}/promotions/{idPromotion}/coupons/{idCoupon}";
"http://localhost:8082/promocity/stores/{idStore}/promotions/{idPromotion}/reportcoupons/{idCoupon}";
"http://localhost:8082/promocity/stores//{idStore}/promotions/{idPromotion}/coupon/consume/{idCoupon}/users/{idUser}

Referências
---

[1] Maven. Management of Builds and Dependencies. Available at https://maven.apache.org

[2] Spring Boot 1. It is a Java Framework (based on the Spring Platform) for web applications that use inversion control container for the Java platform. Available at https://docs.spring.io/spring-boot/docs/current/reference/htmlsingle/#boot-features-security

[3] Jersey. It is an open source RESTful Web Services framework to facilitate development of RESTful Web services and their clients in Java, a standard and portable JAX-RS API. Availale at https://jersey.github.io 

[4] Spring Data JPA. Abstartion of data access. Available at https://docs.spring.io/spring-data/jpa/docs/current/reference/html

[5] Mysql 5. Database Management System. Available at https://dev.mysql.com/downloads/mysql

Dúvidas, questionamentos ou sugestões enviar email para armando@ufpi.edu.br
