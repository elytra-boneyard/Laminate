#!/bin/bash -ex
pwd=`pwd`
cd common
$pwd/gradlew setupDecompWorkspace eclipse
cd ../version-specific/1.7.10
$pwd/gradlew setupDecompWorkspace eclipse
cd ../1.8
$pwd/gradlew setupDecompWorkspace eclipse
cd ../..
$pwd/gradlew setupDecompWorkspace eclipse
