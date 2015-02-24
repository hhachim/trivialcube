# required

* Java

* Maven or Download the .jar file

trivialCube-1.0-SNAPSHOT-jar-with-dependencies.jar

(To install maven in debian/ubuntu: sudo apt-get install maven2)

# Install

* git https://github.com/hhachim/trivialcube.git 

* cd trivialCube

* mvn package

# Run
* Download this input file table.txt or create your own input file

* with sum aggregate :

java -jar -Dfunc=sum -Dcolumn=3 -Dfile=/tmp/table.txt target/trivialCube-1.0-SNAPSHOT-jar-with-dependencies.jar

The option -Dfunc indicates the aggregating function to process

The option -Dcolumn indicates column to compute sum aggregations

The opiton -Dfile indicates the inpute file containing the data to compute
