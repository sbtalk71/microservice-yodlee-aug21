#Goal:
1. Create Eureka Server and One microservice
2. Dockerize the microservices and eureka server
3. Register the service with eureka using containers
4. scale the microservice dynamically

# Eureka Server 
create the service with configuration as given:
server.port= 8761
eureka.client.registerWithEureka= false
eureka.client.fetchRegistry= false
eureka.server.waitTimeInMsWhenSyncEmpty= 0

#The Microservice (let's call it user-service)
the configuration file for the service is:
server.port= 8060
spring.application.name= user-service
eureka.client.serviceUrl.defaultZone= ${EUREKA_SERVER:http://localhost:8761/eureka}

management.endpoints.web.exposure.include=*

#Containerization
#The Dockerfile for eureka-server build (/demo/eureka/):

FROM adoptopenjdk/openjdk8:x86_64-alpine-jre8u-nightly
VOLUME /tmp
ADD eureka-server.jar eureka-server.jar
CMD java -jar eureka-server.jar

docker build command:
sudo docker build -f Dockerfile -t eureka-server .

#Dockerfile for user-service (/demo/user/)

FROM adoptopenjdk/openjdk8:x86_64-alpine-jre8u-nightly
VOLUME /tmp
ENV JAVA_OPTS=""
ADD user-service.jar user-service.jar
CMD java -jar $JAVA_OPTS user-service.jar

docker build command:
sudo docker build -f Dockerfile -t user-service .

#docker-compose.yml for the setup

version: '3'
services:
  eureka-server:
    image: eureka-server
    container_name: eureka-server
    expose:
    - "8761"
    ports: 
    - 8761:8761	
  user-service:
    image: user-service
    #container_name: user-service
    ports:
    - "8060-8070:8060"
    environment:
      - JAVA_OPTS=
            -DEUREKA_SERVER=http://eureka-server:8761/eureka  #(name of eureka host==eureka container name)
      
    links: 
        - eureka-server
		
## Docker compose
sudo docker-compose up
sudo docker-compose down
sudo docker container logs container-id/name

sudo docker-compose scale <service_name>=number_to_scale
e.g. sudo docker-compose scale user-service=4
