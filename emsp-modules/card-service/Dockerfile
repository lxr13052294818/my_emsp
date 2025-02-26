FROM registry.cn-beijing.aliyuncs.com/platform-registry/java:8

COPY target/card-service-1.0.1-SNAPSHOT.jar /app/card-service.jar

EXPOSE 8082

ENTRYPOINT ["java", "-jar", "/app/card-service.jar"]
