FROM frolvlad/alpine-oraclejdk8
VOLUME /tmp
ARG JAR_FILE
ADD target/${JAR_FILE} app.jar
RUN sh -c 'touch /app.jar'
ENV JAVA_OPTS=""
ENTRYPOINT [ "sh", "-c", "java $JAVA_OPTS -Djava.security.egd=file:/dev/./urandom -jar /app.jar" ]
EXPOSE 8080

# FROM frolvlad/alpine-oraclejdk8

# MAINTAINER Simon Meng <dinfo.cn/jiabao@gmail.com>

# WORKDIR /

# ARG JAR_FILE
# ADD target/${JAR_FILE} /work/app.jar
# ADD run.sh /run.sh
# RUN chmod +x /run.sh

# ENV JAVA_OPTS=""

# Instead run app directly, Run app by a wrapped script will bring some benefits
# 1. able to accept extenal enviroment variables
# 2. won't extends the app stop time

# ENTRYPOINT ["/run.sh"]

# EXPOSE 8080


