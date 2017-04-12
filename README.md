# Aplicação de exemplo

# Tecnologias utilizadas no projeto:

Spring 4.2.5.RELEASE
Spring Security 4.0.4.RELEASE
Hibernate Core 4.3.11.Final
validation-api 1.1.0.Final
hibernate-validator 5.1.3.Final
MySQL Server 5.6
Maven 3
JDK 1.8
Tomcat 8.0.21
Eclipse NEON 3
logback 1.1.7

----------------------------------------------------------------------------------------------
# Script para gerar o banco em MySQL:

--- All User's gets stored in APP_USER table

create table APP_USER (
   id BIGINT NOT NULL AUTO_INCREMENT,
   sso_id VARCHAR(30) NOT NULL,
   password VARCHAR(100) NOT NULL,
   first_name VARCHAR(30) NOT NULL,
   last_name  VARCHAR(30) NOT NULL,
   email VARCHAR(30) NOT NULL,
   PRIMARY KEY (id),
   UNIQUE (sso_id)
);
   
--- USER_PROFILE table contains all possible roles

create table USER_PROFILE(
   id BIGINT NOT NULL AUTO_INCREMENT,
   type VARCHAR(30) NOT NULL,
   PRIMARY KEY (id),
   UNIQUE (type)
);
   
--- JOIN TABLE for MANY-TO-MANY relationship

CREATE TABLE APP_USER_USER_PROFILE (
    user_id BIGINT NOT NULL,
    user_profile_id BIGINT NOT NULL,
    PRIMARY KEY (user_id, user_profile_id),
    CONSTRAINT FK_APP_USER FOREIGN KEY (user_id) REFERENCES APP_USER (id),
    CONSTRAINT FK_USER_PROFILE FOREIGN KEY (user_profile_id) REFERENCES USER_PROFILE (id)
);
  
--- Populate USER_PROFILE Table 

INSERT INTO USER_PROFILE(type)
VALUES ('USER');
  
INSERT INTO USER_PROFILE(type)
VALUES ('ADMIN');
  
INSERT INTO USER_PROFILE(type)
VALUES ('DBA');
  
  
--- Populate one Admin User which will further create other users for the application using GUI 

INSERT INTO APP_USER(sso_id, password, first_name, last_name, email)
VALUES ('diego','$2a$10$4eqIF5s/ewJwHK1p8lqlFOEm2QIA0S8g6./Lok.pQxqcxaBZYChRm', 'Diego','Uchoa','diego.uchoa@tecnisys.com.br');
  
  
--- Populate JOIN Table

INSERT INTO APP_USER_USER_PROFILE (user_id, user_profile_id)
  SELECT user.id, profile.id FROM APP_USER user, USER_PROFILE profile
  where user.sso_id='diego' and profile.type='ADMIN';
 
--- Create persistent_logins Table used to store rememberme related stuff

CREATE TABLE PERSISTENT_LOGINS (
    username VARCHAR(64) NOT NULL,
    series VARCHAR(64) NOT NULL,
    token VARCHAR(64) NOT NULL,
    last_used TIMESTAMP NOT NULL,
    PRIMARY KEY (series)
);

----------------------------------------------------------------------------------------------

Para executar a aplicação, execute o comando:
```
mvn clean install
```

Dentro da pasta do projeto e realize o deploy do .war gerado no tomcat.

----------------------------------------------------------------------------------------------

Lembre-se de modificar a senha e os dados do banco no arquivo:
```
/src/main/resources/application.properties
```

O usuário admin inserido no banco com a senha é:
```
Usuário: diego
Senha: abc125
```
