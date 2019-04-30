# Kruger-CallTaxi-Services
consolidates Microservices to handle taxi services

1. service specification is uploaded to the repository, please read through it, it is our blue print.

2. conf/routes helps identify when a certain service request is made, where the service processing starts at.
   for example,
   GET /consumers/:consumer_id/reservations controllers.ReservationsController.fetchReservations(consumer_id : String)
   specifies that all the reservations a customer made can be accessed using a GET request made at /consumers/:consumer_id/reservations
   is processed by fetchReservations method provided by ReservationsController.

3. app folder contains source code and test folder contains code which helps unit test source code at app.

4. this is a play java project, please download SBT and execute "sbt clean compile eclipse run" at the project folder where build.sbt
   is present along with app and test folders. this will build the application, creates eclipse related files required for import into
   eclipse and then deploys it locally on 9000 port.
   
5. execute "sbt jacoco" at project folder to get a test case execution report.
