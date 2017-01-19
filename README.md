# groovy-music
CRUD de músicas

Tecnologias utilizadas: 

### Apresentação
- AngularJS (1.4)
- Bootstrap (3.3.7)

### Backend
- JDK 8
- REST (JAX-RS 2.0.1/Resteasy)
- BD (Derby com JPA 2.1/Hibernate)
- Tomcat 9
- Google Guice

### Ambiente
- Maven
- Eclipse Neon
- Testes unitários (JUnit e Undertow)
- Git (https://github.com/wpiasecki/groovy-music/)

### Melhorias
- Testes de interface com Selenium e do próprio Angular
- Generalizar persistence.xml para evitar repetição do XML
- O código das operações CRUD das classes Resource e Service ficou com bastante repetição, verificar se há possibilidade de generalização
- Controller específica para a modal de edição