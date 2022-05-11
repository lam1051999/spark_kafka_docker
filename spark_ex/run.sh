#!/bin/bash

mvn clean

mvn package

mvn dependency:copy-dependencies
