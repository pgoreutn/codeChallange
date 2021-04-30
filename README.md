
Run the solution:
---------------------
docker-compose up -d 
./gradle clean build
./gradlew bootRun

By the way , inside the folder http you could have some curl/http examples.

ask Description:
---------------------
Create a simple application that allows to manage products.
It should consist of:

- REST API
- service layer
- DAO layer
- relational database

Product attributes:

Name
Description
Weight
Price
Country

API endpoints:
- create new product
- update existing product
- delete a product
- get list of product (pagination, sorting by name, search by name)

Technologies to use: spring boot, spring framework, spring data, MySQL db.

Code Repo: Please use GitHub repo so we can check the code when it is ready

Important:
-  It is important to show well-organised and clean code
-  No Front end code is required. Java code only
- Examples of Unit tests would be a plus (no need for full coverage)
- DB should contain only one table (Product). Think of required indexes
- The code should be well-readable
——————————
