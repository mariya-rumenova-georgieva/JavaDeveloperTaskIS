# JavaDeveloperTaskIS
IS - Java candidate developer task 

Задачата е разработена като самостоятелно Spring Boot приложение с вграден Tomcat server. Приложението се деплойва и стартира директно след импортиране в Intellij като Maven project и стартиране на TaskApplication. Разработвано е върху Java 11. MVC архитектура (Spring MVC, Thymeleaf, JS/JQuery, Bootstrap, JPA с hibernate dialect, Lombok, Junit). В task/src/test/java/is/canididate/task/DAOs има малко тестове към основните операции с базата за правия случай (не е направено пълно покритие от тестове).

За базата данни е използван MySQL. Създаването и инициализирането на таблиците се намира в RecreateScript.sql
Базата се създава от скрипта, а не от приложението.

Конекцията към базата в приложението е зададено в task/src/main/resources/application.properties файла, като 
spring.datasource.url
spring.datasource.username
spring.datasource.password
текущо отговарят на локалната ми среда. 

С текущите настройки след билд и деплой приложението ще бъде достъпно на http://localhost:8081/people

NB: Вариант да се билдне и стартира приложението от команден ред е от папката, в която се намира pom.xml да се изпълни:
mvn install
и да се стартира създадения task-0.0.1-SNAPSHOT.pom с подходящите параметри за -Dserver.port, -Dspring.datasource.username, -Dspring.datasource.password, Dspring.datasource.driver-class-name, -jar <jar-path>/task-0.0.1-SNAPSHOT.pom

