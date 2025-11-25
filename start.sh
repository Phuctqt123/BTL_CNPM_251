#!/bin/bash

# Cài Java 17
apt-get update -y
apt-get install -y openjdk-17-jdk

# Chạy JAR
java -jar Back_end/BTL_CNPM/target/demo-0.0.1-SNAPSHOT.jar
