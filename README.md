# 📊 API to convert currency
- This microservice allows users to register with information such as name, email, preferred currency (USD, EUR, etc.) and balance in reais (BRL). After registration, the system converts the balance into the currency chosen by the user using an external exchange API. An email is then sent to the user containing the converted balance in the specified currencies.

## To start
- 1º Install Java
- 2º Install PostgreSQL
- 3º Install Postman or other.
- 4º Install IDE Spring Tools Suite
- 5º Import project and run.

## 📈 Roadmap
- 1º Flow organization and class diagram.
- 2º Creating the project using Spring Initializr and adding the necessary dependencies.
- 3º Creation of the user microservice.
- 4º Creation of the email microservice.
- 5º RabbitMQ configuration.
- 6º Google SMTP configuration.
- 7º Consumption of the external API for currency conversion.

## 🚀 Features
- Create and save the user, and then send the email.
- Deposit to the user's balance.
- Get all users.
  
## 📋 Class Diagram
![ConvertCoinAPI drawio](https://github.com/user-attachments/assets/57ca33a7-b2c7-4aad-a4f1-00d2a2923484)

## 🚦 Flowchart
- This is the main flow of microservices where user creation occurs, communication between services using the broker (RabbitMQ), consumption of the external API, sending the email and all data is saved in PostgreSQL.
![Flowchart Create User](https://github.com/user-attachments/assets/4b88f3ee-89c2-4bc3-a89a-14aa794f8fe6)

## 🔗 Routes
- POST /users -> Create user
  
![Create User](https://github.com/user-attachments/assets/95650414-5ac1-436d-8b58-a71260cbdd52)
- GET /users -> Get all users
- GET /email/{email} -> Get user by e-mail
- POST /deposit/{email}/{balance} -> Deposit money to a client

## ✒️ Autor

* [**Romulo Matheus**](https://github.com/Romulomdr) - *At the moment developer Java back-end* [<img src="https://img.shields.io/badge/LinkedIn-0077B5?style=for-the-badge&logo=linkedin&logoColor=white" />](https://www.linkedin.com/in/romulo-dantasmdr/)
