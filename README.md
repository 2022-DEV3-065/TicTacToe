# TicTacToe

To run on Docker (recommended):
1. Run Docker on your machine.
2. Run 'docker pull 2022dev3065/tictactoe'
3. Run 'docker run -p 8080:8080 2022dev3065/tictactoe'
4. Go to http://localhost:8080/index.html for the application


To compile and run locally:
1. Set Java version to 17: "export JAVA_HOME= /path/to/java17" 
2. Run 'mvn clean install' in main directory, to compile and run tests.
3. Run 'java -jar target/springboot-tictactoe.jar' to run the application.
4. Go to http://localhost:8080/index.html for the application