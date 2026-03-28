# 🌦️ Weather App

Aplicação Java que consome a API pública **Open-Meteo** para buscar e exibir dados climáticos de uma cidade informada pelo usuário.

---

## 🚀 Tecnologias utilizadas

* Java 21
* Maven
* Gson (para manipulação de JSON)
* API Open-Meteo

---

## 📌 Funcionalidades

* Buscar clima pelo nome da cidade
* Exibir temperatura atual
* Tratamento de erro para cidade inválida
* Arquitetura em camadas:

  * Controller
  * Service
  * Client (API)
  * DTO
  * UI (console)

---

## 🧠 Arquitetura do Projeto

```
Main → Controller → Service → ApiClient
                          ↓
                        API
```

Separação de responsabilidades:

* **Main** → entrada da aplicação
* **Controller** → fluxo da aplicação
* **Service** → regras de negócio
* **ApiClient** → comunicação com API externa
* **DTO** → transporte de dados
* **UI** → exibição no console

---

## ▶️ Como executar o projeto

### Pré-requisitos

* Java 21 instalado
* Maven instalado

### Passos

```bash
git clone https://github.com/IagoWiliian/weather-app.git
cd weather-app

mvn clean install
mvn exec:java -Dexec.mainClass="com.weatherapp.Main"
```

---

## 💻 Exemplo de uso

```
Digite a cidade: Belo Horizonte

=========================
   WEATHER APP ☀️
=========================
Cidade: Belo Horizonte
Temperatura: 20.1°C
=========================
```

---

## 📁 Estrutura do projeto

```
src/main/java/com/weatherapp/
├── Main.java
├── controller/
├── service/
├── client/
├── dto/
├── model/
├── util/
└── ui/
```

---

## 🔥 Melhorias futuras

* Adicionar velocidade do vento 🌬️
* Exibir descrição do clima 🌥️
* Transformar em API com Spring Boot
* Criar interface web (React)
* Deploy na nuvem

---

## 👨‍💻 Autor

Desenvolvido por **Iago Willian** 🚀
