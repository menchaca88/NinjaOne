FROM gradle:7.5.1-jdk17 as builder

RUN mkdir rmm

WORKDIR /rmm

COPY . .

RUN gradle build -x test --no-daemon

FROM openjdk:19-jdk-alpine3.16

RUN mkdir rmm

WORKDIR /rmm

COPY --from=builder /rmm/api/build/libs/api-1.0.jar .

EXPOSE 8080

CMD [ "java", "-jar", "api-1.0.jar" ]