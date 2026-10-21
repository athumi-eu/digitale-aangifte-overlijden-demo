FROM eclipse-temurin:25.0.4_7-jre
USER root
RUN apt-get update && \
# This should be temporary untill a new version with the correct cert exist \
# check  curl https://dv-g3b-emudhra.we-have-a-problem.com/ to ensure the problem is gone before deleting
    apt-get install -y --only-upgrade ca-certificates && \
    rm -rf /var/lib/apt/lists/*
RUN addgroup --system javauser
RUN useradd -ms /bin/bash -g javauser javauser
USER javauser:javauser
VOLUME /tmp
COPY build/libs/burgerlijke-stand-0.0.1-SNAPSHOT.jar app.jar
ENTRYPOINT ["java","-jar","/app.jar"]
