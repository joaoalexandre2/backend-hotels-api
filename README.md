# 🏨 Backend Hotels API

API REST desenvolvida com **Spring Boot** para gerenciamento de hotéis, quartos e imagens.

Este projeto simula um backend de plataforma de reservas de hotéis, inspirado em sistemas como Booking ou Airbnb.

---

# 🚀 Tecnologias utilizadas

- Java 17
- Spring Boot
- Spring Data JPA
- Hibernate
- Maven
- PostgreSQL (H2 nos testes)
- Spring Security (autenticação por sessão com cookie)
- REST API
- DTO Pattern
- Pagination
- Exception Handling

---

# 📂 Arquitetura do Projeto

O projeto segue uma arquitetura em camadas para manter o código organizado e escalável.

---

# 🔐 Autenticação

Login por **sessão HTTP com cookie** (Spring Security, senhas com BCrypt).

| Método | Rota | Acesso | Descrição |
|---|---|---|---|
| `POST` | `/auth/login` | público | Recebe `{ "username", "password" }` e abre a sessão |
| `GET` | `/auth/me` | público | Retorna o usuário logado, ou `401` |
| `POST` | `/auth/logout` | público | Encerra a sessão |
| `GET` | `/api/v1/**` | **público** | Consulta de hotéis e quartos |
| `POST` `PUT` `PATCH` `DELETE` | `/api/v1/**` | **exige login** | Criar, editar e excluir |

- Requisições que alteram dados precisam do cabeçalho `X-Requested-With` (defesa contra CSRF).
- O frontend deve enviar cookies (`withCredentials: true` no Axios).

# ⚙️ Configuração (variáveis de ambiente)

Nenhum segredo fica no código. Defina no ambiente (ou no painel do Render):

| Variável | Obrigatória | Descrição |
|---|---|---|
| `DB_URL`, `DB_USER`, `DB_PASSWORD` | em produção | Conexão PostgreSQL (o padrão aponta para um banco local) |
| `ADMIN_USERNAME`, `ADMIN_PASSWORD` | sim | Cria o administrador na primeira execução |
| `CLOUDINARY_CLOUD_NAME`, `CLOUDINARY_API_KEY`, `CLOUDINARY_API_SECRET` | sim | Upload de imagens |
| `CORS_ALLOWED_ORIGINS` | em produção | Origens do frontend, separadas por vírgula (padrão `http://localhost:5173`) |
| `SESSION_COOKIE_SECURE` | em produção | `true` com HTTPS |
| `SESSION_COOKIE_SAMESITE` | em produção | `none` se o frontend estiver em outro domínio |

# 🧪 Testes

```bash
./mvnw test
```

Os testes de integração usam H2 em memória e cobrem login, logout, rotas públicas e rotas protegidas.
