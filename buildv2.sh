#!/bin/bash
set -e -x

sed -i -e "s/3EB0FF/3EBF5C/" src/main/resources/templates/fragments/layout.html
sed -i -e "s/<version>1.0/<version>2.0/" pom.xml

./mvnw package -DskipTests

sed -i -e "s/3EBF5C/3EB0FF/" src/main/resources/templates/fragments/layout.html
sed -i -e "s/<version>2.0/<version>1.0/" pom.xml

