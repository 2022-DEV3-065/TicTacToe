# TicTacToe

### To run on Docker (recommended):
1. Run Docker on your machine.
2. Run ```docker pull 2022dev3065/tictactoe```
3. Run ```docker run -p 8080:8080 2022dev3065/tictactoe```
4. The application should be running at : http://localhost:8080

### If you want to compile and run locally:
1. Clone and ```cd``` into the repository.
2. Set Java version to 17:
   ```export JAVA_HOME= **/path/to/java17**``` 
3. Run ```mvn clean install``` in mainå directory, to compile and run tests for both backend and frontend.
4. Run ```java -jar target/springboot-tictactoe.jar``` to run the application.
5. The application should be running at : http://localhost:8080

## For Developers:

### Backend:
1. This is a standard Spring Boot application.
2. The main class is ```com.tictactoe.TicTacToeApplication```
3. The backend is a REST API, and the main controller is ```com.tictactoe.controller.LogicController```
4. To run the backend tests, run ```mvn test``` in the main directory.

### Frontend:
1. The frontend is a React application created from the ```create-react-app``` template.
2. The react application code resides in ```src/main/frontend```
3. The main component is ```src/main/js/components/TicTacToe.js```
4. To run the frontend tests, run ```npm test``` in the ```src/main/frontend``` directory.
