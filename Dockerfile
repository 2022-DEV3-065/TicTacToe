FROM openjdk:17.0.2
EXPOSE 8080
ADD target/springboot-tictactoe.jar springboot-tictactoe.jar
ENTRYPOINT ["java","-jar","/springboot-tictactoe.jar"]
