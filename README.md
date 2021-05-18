#Carrinho-checkout

## Gerenciamento do projeto
- Maven

## framework
- Java spring boot
  
## Banco de dados
- Spring JPA
- H2

## Segurança
- Spring security
- JWT

# Execução do projeto

Requisitos: Java 8;

- Git clone no projeto;
- Baixar as depedências do maven;
- Ajustar o perfil no arquivo de properties para spring.profiles.active = test;
- Excutar o projeto e ele iniciará com o banco de dados H2 alocado em memória temporária;
- Caso necessitar presistir e dados, instalar o postgres, configurar as credenciais no arquivo application-dev.properties e mudar o spring.profiles.active = dev;