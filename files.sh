#!/usr/bin/env bash

#files in s3
aws s3 ls tigbear/math/

#aws db console
https://us-west-2.console.aws.amazon.com/rds/home?region=us-west-2#dbinstances:

#java stub
mvn archetype:generate -DgroupId=com.geneology -DartifactId=math-tree -DarchetypeArtifactId=maven-archetype-quickstart -DinteractiveMode=false

#java jar
/Users/tanya/Code/math-tree/math-tree/target
java -cp math-tree-1.0-SNAPSHOT.jar com.geneology.App

#one file in, one file out
java -Din=/Users/tanya/CrawlResults/31332.html -Dout=/Users/tanya/ProcessedResults/31332.json -Dlog=/Users/tanya/Logs/ -cp target/math-tree-1.0-SNAPSHOT.jar com.geneology.App