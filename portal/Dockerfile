FROM anapsix/alpine-java:8_jdk

MAINTAINER Simon Meng <mengzl@teligen.com>

ENTRYPOINT ["java", "-Djava.security.egd=file:/dev/./urandom", "-jar", "/usr/share/myservice/myservice.jar"]

ARG JAR_FILE
ADD target/${JAR_FILE} /usr/share/myservice/myservice.jar

EXPOSE 8080


