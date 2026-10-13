# Aventura Spring

Migração do jogo de aventura da Unidade 1 (console) para Spring Boot no modelo MVC.

- `model/` — entidades do jogo (Personagem, Inimigo, Item, Missao, Partida...)
- `service/` — regras de negócio (combate, missões, herói, orquestração da partida)
- `controller/` — rotas REST

## Rodar

```bash
./mvnw spring-boot:run
```

Sobe em `http://localhost:8080`.

## Rotas

| Método | Rota | Descrição |
|---|---|---|
| POST | `/jogo` | Cria partida. Body: `{"nomeHeroi": "Kael"}` |
| GET | `/jogo` | Lista partidas |
| GET | `/jogo/{id}` | Status da partida |
| GET | `/jogo/{id}/inventario` | Inventário do herói |
| GET | `/jogo/{id}/missoes` | Missões |
| POST | `/jogo/{id}/atacar` | Herói ataca; inimigo revida |
| POST | `/jogo/{id}/usar-item/{posicao}` | Usa item (gasta o turno) |
| POST | `/jogo/{id}/equipar/{posicao}` | Equipa uma arma do inventário |
