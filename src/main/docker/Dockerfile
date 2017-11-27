FROM harbor.hiteamtech.com/library/java:v1
USER root
ADD uws-service-0.5.1.jar /
EXPOSE 80
ENTRYPOINT ["java", "-jar", "/uws-service-0.5.1.jar"]