
transaction-api
--------------------------

To build the project :

Java 1.8
Maven-3.8.x

please ensure the maven and java home paths are set before executing the below command

>  mvn clean install

To execute the application follow the steps
--------------------------------------------

Requirement :
    Java 1.8 version is required to run the application

Linux platform:
-----------------

1 . Locate to the project folder in th command prompt
2. execute the command [ ./unix-run.sh  or sh unix-run.sh ]

Ex. root@1234 usr/local/transaction-api$ sh unix-run.sh

Windows platform:
-----------------

1 . Locate to the project folder in th command prompt
2. execute the command in the command prompt [ win-run.bat]

C:\User\xxxx\transaction-api> win-run.bat

Swagger-ui for API specifications:
-----------------------------------

you can access this open-api 3 (swagger-ui) to invoke the services and the APIs

1 . Swagger-ui url : http://localhost:8080/swagger-ui.html
2 . At first step the initial data setup need to be done , since there is no RDBMS/RDS used in the application.
3 . To load the data invoke the API url and feed the request body as much we want in the format below
    a. POST : http://localhost:8080/api/initialize
    b. Sample request body: {"players":[{"id":"123","account":{"balance":10.0,"accountId":"12345"},"name":"Sam"},{"id":"456","account":{"balance":20.0,"accountId":"6789"},"name":"Raj"}] }
4 . Credit transaction api, based on the initialization records for each playerId you can pass the request parameter [ex. playerId : 123]
    a. POST : http://localhost:8080/api/credit/123
    b. Sample request body: {"transaction":{"id":"123456678","type":"C","amount":11.0,"account":{"accountId":"12345"}}}
5 . Debit transaction api,
    a. POST : http://localhost:8080/api/debit/123
    b. Sample request body: {"transaction":{"id":"123456491","type":"D","amount":1.0,"account":{"accountId":"12345"}}}
6 . Balance check api
    a. GET : http://localhost:8080/api/balance/123
    b. Sample request body: you can leave it empty as we have passed the playerid in the url it recognize the account and fetch the data
7 . Transaction history
    a. GET : http://localhost:8080/api/history/123
    b. No request body, as we pass the request parameter


Note : The JUnit test case is implemented for one of the major  class only.
