# Food Sharing Web Application Backend

This repository contains the backend of the concept project for a food-sharing web application. 

## Purpose
The purpose of this project is to revise and reinforce concepts related to Java backend development. 

## Status
Currently, this backend code is for testing purposes.

## Application Execution Flow

1. **Register a New User:**
   - Use the POST API endpoint (for local development):
     ```
     http://localhost:8080/api/v1/register
     ```
   - Example request body:
     ```json
     {
         "username": "admin",
         "password": "1234"
     }
     ```

2. **Get JWT Token:**
   - Use the POST API endpoint to retrieve a token:
     ```
     http://localhost:8080/api/v1/authenticate
     ```
     - Example request body:
     ```json
     {
         "username": "admin",
         "password": "1234"
     }
     ```

3. **Send Requests Using the Token:**
   - **Get a all Orders:**
     ```
     http://localhost:8080/api/v1/order
     ```
   -  **Get All active Orders:**
     ```
     http://localhost:8080/api/v1/order/active
     ```
   -  **Get All completed Orders:**
     ```
     http://localhost:8080/api/v1/order/past
     ```
   - **Add a Order (POST):**
     - Example POST API endpoint:
       ```
       http://Localhost:8080/api/v1/order
       ```
     - Example request body:
       ```json
       {
           "Name": "Coke",
           "Price": 80,
           "description": "testing description",
           "date": "2024-03-24",
           "userId": 6,
           "isactive": true
       }
       ```
       - Note you can get userId with the token while authenticating.
   
   - **Update a Order (PUT):**
     - Example PUT API endpoint:
       ```
       http://localhost:8080/api/v1/order/{{orderId}}
       ```
     - Example request body:
       ```json
       {
           "Name": "Coke",
           "Price": 80,
           "description": "testing description",
           "date": "2024-03-24",
           "userId": 6,
           "isactive": true
       }
       ```
   - **Delete a Order:**
     - Example DELETE API endpoint:
       ```
       http://localhost:8080/api/v1/order/{{orderId}}
       ```
   - **Get User Statistics:**
     - Example API endpoint:
       ```Example DELETE API endpoin
       http://localhost:8080/api/v1/order/stats?userId=6
       ```
   - **Complete User Order:**
     - Example API endpoint:
       ```Example DELETE API endpoin
       http://localhost:8080/api/v1/order/complete/{{orderId}}
       ```
