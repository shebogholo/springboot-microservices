FROM openjdk:18-jdk-alpine

# create non-root user
RUN addgroup dockergroup && adduser -S dockeruser -G dockergroup

# runninng as non-root user
USER dockeruser

WORKDIR /app

COPY ../.mvn .mvn
COPY ../mvnw pom.xml ./
RUN ./mvnw dependency:go-offline

COPY ../src ./src

CMD ["./mvnw", "spring-boot:run"]