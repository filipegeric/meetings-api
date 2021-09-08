FROM openjdk:8-jdk
EXPOSE 3000:3000
RUN mkdir /app
COPY ./ktor-application/build/install/ktor-application/ /app/
WORKDIR /app/bin
CMD ["./ktor-application"]