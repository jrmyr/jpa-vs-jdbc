FROM docker-registry.int.tagus.me/ubuntu-based-jre-11:1.0

COPY build/libs/jpa-vs-jdbc.jar .

EXPOSE 8080
CMD java $JAVA_OPTS -jar /tsf-app/jpa-vs-jdbc.jar
