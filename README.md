# Loopi API
 
> Back-end do Loopi, um app de mobilidade urbana que aprende a rotina do usuário e notifica o horário exato de saída com base no trajeto e condições em tempo real.
 
Desenvolvido com **Java + Spring Boot**, a API é modular, RESTful e integra o **Mapbox API** para cálculo dinâmico de rotas e tempo de deslocamento.
 
---
 
## Sobre o projeto
 
O Loopi resolve um problema cotidiano: saber exatamente quando sair de casa para chegar no horário. A API é responsável por:
 
- Gerenciar usuários, rotas, alertas e transportes
- Integrar com a API do Mapbox para calcular duração e tradução dinâmica de perfis de transporte
- Notificar o horário ideal de saída com base no trajeto cadastrado
- Garantir integridade dos dados com soft delete, validações e tratamento global de erros
---
 
## Tecnologias
 
| Camada | Tecnologia |
|---|---|
| Linguagem | Java 17 |
| Framework | Spring Boot |
| Banco de dados | H2 (em memória) |
| Documentação | Swagger / OpenAPI |
| Testes | JUnit 5 + Mockito |
| Mapeamento | MapStruct / Lombok |
| Integração externa | Mapbox API |
| Controle de versão | Git / GitHub |
 
---

## Funcionalidades
 
- [x] CRUD completo de Usuários, Rotas, Alertas e Transportes
- [x] Integração com Mapbox para cálculo de duração de rota e tradução de perfis de transporte
- [x] Soft delete global (usuários, rotas, alertas, transportes)
- [x] DTOs para entrada e saída de dados em todas as entidades
- [x] Bean Validation nas requisições
- [x] Tratamento global de erros com respostas padronizadas
- [x] Testes unitários do `UsuarioService` com Mockito
- [x] Documentação da API via Swagger UI
- [ ] Autenticação JWT (em desenvolvimento)
- [ ] Integração com o app mobile (em desenvolvimento)
---
 
## Como rodar localmente
 
### Pré-requisitos
 
- Java 17+
- Maven
- Conta no [Mapbox](https://www.mapbox.com/) para obter um token de API
### Passos
 
```bash
# Clone o repositório
git clone https://github.com/biafraga/loopi-api.git
cd loopi-api
```
 
Configure o token do Mapbox. Crie um arquivo `application-local.properties` na pasta `src/main/resources/` com o seguinte conteúdo:
 
```properties
mapbox.token=SEU_TOKEN_AQUI
```
 
> O token não é commitado no repositório por segurança (já está no `.gitignore`).
 
```bash
# Execute a aplicação
./mvnw spring-boot:run
```
 
A API estará disponível em `http://localhost:8080`
 
A documentação Swagger estará disponível em `http://localhost:8080/swagger-ui.html`
 
O banco H2 pode ser acessado em `http://localhost:8080/h2-console`
 
---

## Endpoints principais
 
| Método | Rota | Descrição |
|---|---|---|
| POST | `/usuarios` | Cadastrar usuário |
| GET | `/usuarios/{id}` | Buscar usuário por ID |
| PUT | `/usuarios/{id}` | Atualizar usuário |
| DELETE | `/usuarios/{id}` | Soft delete de usuário |
| POST | `/rotas` | Cadastrar rota |
| GET | `/rotas/{id}` | Buscar rota por ID |
| POST | `/alertas` | Criar alerta de notificação |
| GET | `/transportes` | Listar transportes disponíveis |
 
> Documentação completa e interativa disponível no Swagger UI ao rodar a aplicação.
 
---
 
## Testes
 
```bash
./mvnw test
```
 
Os testes unitários cobrem o `UsuarioService` com cenários de criação, busca, listagem, atualização e deleção, usando Mockito para mock do repositório.
 
---
 
## Projeto relacionado
 
O front-end mobile do Loopi está sendo desenvolvido em React Native: [loopi-app](https://github.com/biafraga/loopi-app)
 
---
 
## Autora
 
**Beatriz Fraga** — Desenvolvedora Full Stack em formação
 
[![LinkedIn](https://img.shields.io/badge/LinkedIn-beatrizfraga-blue?style=flat&logo=linkedin)](https://linkedin.com/in/beatrizfraga)
[![GitHub](https://img.shields.io/badge/GitHub-biafraga-black?style=flat&logo=github)](https://github.com/biafraga)
