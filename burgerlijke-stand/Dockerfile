FROM eclipse-temurin:25.0.5_8-jdk

USER root
# This should be temporary untill a new version with the correct cert exist
# run keytool -list -cacerts -storepass changeit | grep -iE "emsign|aaa"
# This should not contain emsign root ca g3
RUN keytool -delete -cacerts -storepass changeit \
    -alias "cn_emsign_ecc_root_ca__g3,o_emudhra_technologies_limited,ou_emsign_pki,c_in [jdk]"
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
