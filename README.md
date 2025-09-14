# RH Management

Sistema de gestão de recursos humanos desenvolvido com Spring Boot.

## Tecnologias Utilizadas

- **Java 21** - Linguagem de programação
- **Spring Boot 3.2.5** - Framework principal
- **Spring Data JPA** - Persistência de dados
- **H2 Database** - Banco de dados em memória
- **Lombok** - Redução de boilerplate code
- **JaCoCo** - Cobertura de código
- **Gradle 8.4** - Gerenciador de dependências
- **JUnit 5** - Testes unitários

## Estrutura do Projeto

```
src/
├── main/
│   ├── java/com/rhmanagement/
│   │   ├── RhManagementApplication.java
│   │   ├── controller/
│   │   ├── service/
│   │   ├── repository/
│   │   ├── model/
│   │   ├── dto/
│   │   └── config/
│   └── resources/
│       └── application.properties
└── test/
    └── java/com/rhmanagement/
        ├── RhManagementApplicationTests.java
        ├── controller/
        ├── service/
        └── repository/
```

## Como Executar

### Pré-requisitos
- Java 21
- Gradle 8.4+

### Executando a aplicação
```bash
./gradlew bootRun
```

### Executando os testes
```bash
./gradlew test
```

### Gerando relatório de cobertura
```bash
./gradlew jacocoTestReport
```

### Build completo
```bash
./gradlew clean build
```

## Endpoints da API

- `GET /api/employees` - Lista todos os funcionários
- `GET /api/employees/{id}` - Busca funcionário por ID
- `POST /api/employees` - Cria novo funcionário
- `DELETE /api/employees/{id}` - Remove funcionário

## Banco de Dados H2

A aplicação utiliza o H2 Database em memória. Para acessar o console do H2:

- URL: http://localhost:8080/h2-console
- JDBC URL: jdbc:h2:mem:testdb
- Username: sa
- Password: password

## Relatórios

Após executar os testes, os relatórios são gerados em:
- Testes: `build/reports/tests/test/index.html`
- Cobertura: `build/reports/jacoco/test/html/index.html`