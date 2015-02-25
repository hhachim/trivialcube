# Introduction

TrivialCube can compute full or partial cube with sum or count aggregate function.

# required

* Java

* Maven 

(To install maven in debian/ubuntu: `sudo apt-get install maven2`)

# Install

* git clone https://github.com/hhachim/trivialcube.git 

* cd trivialCube

* mvn clean package

# Run

* usage: 

```sh
java -jar target/trivialCube-1.0-SNAPSHOT-jar-with-dependencies.jar
```  
 
    -c,--column <arg>               (Optional) The column position (first
                                    column position is 0) containing the
                                    values to compute for the sum aggregate
                                    function
    -d,--output-dimensions <arg>    (Optional) The dimensions to compute
                                    (defaut : all dimensions), ex
                                    -d='1,0-1-3,3-2' to compute only theses
                                    three dimensions
    -f,--aggregate-function <arg>   The aggregate function to
                                    compute(possible values : sum or count)
    -i,--input-file <arg>           The input file containing the data to
                                    compute
    -o,--ouput-directory <arg>      The ouput directory to store the cube
 
* All cuboids (dimensions) with sum aggregate :

```sh
java -jar target/trivialCube-1.0-SNAPSHOT-jar-with-dependencies.jar -i="./samples/table.txt" -f=sum -o="/tmp" -c=3    
```

* All cuboids (dimensions) with count aggregate :

```sh
java -jar target/trivialCube-1.0-SNAPSHOT-jar-with-dependencies.jar -i=./samples/table.txt -f=count -o=/tmp
```
   
* One specific cuboid

```sh
java -jar target/trivialCube-1.0-SNAPSHOT-jar-with-dependencies.jar -i=./samples/table.txt -f=sum -o=/tmp -c=3 -d="1"
```

* Many specific cuboids (example cuboid 0-1, and cuboid 1-3)

```sh
java -jar target/trivialCube-1.0-SNAPSHOT-jar-with-dependencies.jar -i=./samples/table.txt -f=sum -o=/tmp -c=3 -d="0-1,1-3"
```
License
----

MIT