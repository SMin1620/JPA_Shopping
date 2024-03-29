FROM openjdk:17-alpine

WORKDIR /usr/src/app

ARG JAR_PATH=./build/libs

COPY ./build/libs/jpashop-0.0.1-SNAPSHOT.jar /build/libs/jpashop-0.0.1-SNAPSHOT.jar

CMD ["java","-jar","/build/libs/jpashop-0.0.1-SNAPSHOT.jar"]