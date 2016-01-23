#!/bin/bash -ex
pwd=`pwd`
cd common
$pwd/gradlew build
cd ../version-specific/1.7.10
$pwd/gradlew build
cd ../1.8
$pwd/gradlew build
cd ../..
$pwd/gradlew build
