FROM eclipse-temurin:21.0.10_7-jre
RUN addgroup --system javauser
RUN useradd -ms /bin/bash -g javauser javauser
USER javauser:javauser
VOLUME /tmp
COPY build/libs/burgerlijke-stand-0.0.1-SNAPSHOT.jar app.jar
ENTRYPOINT ["java","-jar","/app.jar"]
