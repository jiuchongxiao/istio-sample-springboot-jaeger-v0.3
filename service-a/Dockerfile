FROM anapsix/alpine-java:8u172b11_jdk

MAINTAINER Simon Meng <menghot@gmail.com>

WORKDIR /

ARG JAR_FILE
ADD target/${JAR_FILE} /work/app.jar
ADD run.sh /run.sh
RUN chmod +x /run.sh

ENV JAVA_OPTS=""

# Instead run app directly, Run app by a wrapped script will bring some benefits
# 1. able to accept extenal enviroment variables
# 2. won't extends the app stop time

ENTRYPOINT ["/run.sh"]

EXPOSE 8081


