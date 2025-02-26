FROM registry.cn-beijing.aliyuncs.com/platform-registry/java:8

COPY target/account-service-1.0.1-SNAPSHOT.jar /app/account-service.jar

EXPOSE 8081

ENTRYPOINT ["java", "-jar", "/app/account-service.jar"]
