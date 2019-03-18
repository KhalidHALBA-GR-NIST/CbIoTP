#!/usr/bin/env bash
mvn package -DskipTests; java -jar target/app1-0.0.1-SNAPSHOT-fat.jar
