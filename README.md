# groovy-music

Este projeto apresenta um CRUD (criação, busca, exclusão e atualização) de músicas. 

### Apresentação
- **AngularJS (1.5.8)**: framework JavaScript para simplificar as interações entre componentes de tela e lógica de negócio. AngularJS acrescenta diretivas e expressões para estender o HTML.
- **Bootstrap (3.3.7)**: framework para design responsivo.

### Backend
- **JDK 8**: Última versão da plataforma Java. Tem novos recursos como API de stream e lambdas.
- **JAX-RS 2.0.1/Resteasy**: Framework para suporte a REST.
- **Derby**: Banco de dados em Java que pode ser usado em memória ou arquivo. Foi utilizado tanto para desenvolvimento quanto testes.
- **JPA 2.1/Hibernate**: JPA é a biblioteca de persistência do Java
- **Tomcat 9**: Servidor Java/web simples, sem todas as bibliotecas do framework Java EE. Para incluir suporte à JPA e JAX-RS foi necessário a inclusão manual destas bibliotecas.
- **Guice**: Biblioteca de injeção de dependência. Utilizado principalmente para separar as fontes de dados do ambiente de testes e de desenvolvimento.

### Ambiente
- **Maven**: Ferramenta para gerenciar projetos. Possui diversos recursos como versionamento, implantação, testes, construção, entre outros. Neste projeto foi utilizado para gerenciar dependências.
- **Eclipse Neon**: IDE Java em sua última versão. Utilizou-se versão para Java EE.
- **Testes unitários (JUnit e Undertow)**: JUnit é uma ferramenta para automação de testes. Utilizou-se para testes unitários e de integração. Como servidor dos testes de integração utilizou-se Undertow.
- **Git**: Ferramenta de gerenciamento de versão descentralizada. Neste projeto utilizou-se um repositório no github (https://github.com/wpiasecki/groovy-music/)

### Melhorias

Algumas melhorias que poderiam ser realizadas:

- Testes de interface com Selenium e do próprio Angular
- Generalizar persistence.xml para evitar repetição do XML
- O código das operações CRUD das classes Resource e Service ficou com bastante repetição, verificar se há possibilidade de generalização