# 🌦️ Weather App

Aplicação Java que utiliza a API pública **Open-Meteo** para buscar e exibir dados climáticos de uma ou múltiplas cidades informadas pelo usuário.

---

## 🚀 Tecnologias utilizadas

* Java 21
* Maven
* Gson (manipulação de JSON)
* API Open-Meteo
* JUnit 5
* Mockito

---

## 📌 Funcionalidades

* 🔎 Buscar clima pelo nome da cidade
* 🌍 Suporte a múltiplas cidades (entrada em lote)
* 🌡️ Exibir temperatura atual
* ⚠️ Tratamento de erro para cidade inválida
* ⚡ Cache em memória com TTL (evita chamadas repetidas à API)
* 🧱 Arquitetura em camadas

---

## ⚡ Cache (Otimização)

A aplicação utiliza cache em memória para melhorar a performance.

* Armazena resultados por cidade
* Evita chamadas repetidas à API
* Possui tempo de expiração (TTL)

### Exemplo:

```
Entrada:
belo horizonte, belo horizonte

Saída:
🌎 Cidade: belo horizonte
🌡️ Temperatura: 24.5°C

⚡ Usando cache para: belo horizonte

🌎 Cidade: belo horizonte
🌡️ Temperatura: 24.5°C
```

---

## 🧠 Arquitetura do Projeto

```
Main → Controller → Service → ApiClient
                          ↓
                        API
```

### Separação de responsabilidades:

* **Main** → entrada da aplicação
* **Controller** → fluxo da aplicação
* **Service** → regras de negócio
* **ApiClient** → comunicação com API externa
* **DTO** → transporte de dados
* **UI** → exibição no console
* **Cache** → otimização de performance

---

## 📁 Estrutura do projeto

```
src/main/java/com/weatherapp/
├── Main.java
├── controller/
├── service/
├── client/
├── dto/
├── cache/        ← NOVO (cache com TTL)
├── util/
└── ui/
```

---

## ▶️ Como executar o projeto

### Pré-requisitos

* Java 21 instalado
* Maven instalado

### Passos

```
git clone https://github.com/IagoWiliian/weather-app.git
cd weather-app

mvn clean install
mvn exec:java -Dexec.mainClass="com.weatherapp.Main"
```

---

## 💻 Exemplo de uso

```
Digite as cidades separadas por vírgula: Belo Horizonte, São Paulo

=========================
   WEATHER APP ☀️
=========================
🌎 Cidade: Belo Horizonte
🌡️ Temperatura: 24.5°C
=========================
```

---

## 🧪 Testes

Para executar os testes:

```
mvn test
```

---

## 🔥 Melhorias futuras

* 🌬️ Adicionar velocidade do vento
* 🌥️ Exibir descrição do clima
* ⚡ Paralelismo com CompletableFuture
* 🌐 Transformar em API REST com Spring Boot
* 💾 Cache distribuído (Redis)
* 🎨 Criar interface web (React)
* ☁️ Deploy na nuvem

---

## 👨‍💻 Autor

Desenvolvido por **Iago Willian** 🚀
