FROM registry.cn-beijing.aliyuncs.com/platform-registry/java:8

COPY target/gateway-service-1.0.1-SNAPSHOT.jar /app/gateway-service.jar

EXPOSE 8888

ENTRYPOINT ["java", "-jar", "/app/gateway-service.jar"]
