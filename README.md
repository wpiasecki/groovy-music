# groovy-music
CRUD de músicas

Tecnologias utilizadas: 
Apresentação:
- AngularJS (1.4)
- Bootstrap (3.3.7)

Backend:
- REST (JAX-RS 2.0.1/Resteasy)
- BD (Derby com JPA 2.1/Hibernate)
- Testes unitários (JUnit e Undertow)
- Tomcat

Ambiente:
- Maven
- Eclipse Neon

Melhorias:
- Utilizar injeção de dependência para desacoplar obtenção de EntityManager entre ambiente de produção e testes
- Generalizar persistence.xml para evitar repetição do XML
