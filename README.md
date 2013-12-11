duplicate-detector
==================
This thing looks at the .jars on the classpath and identifies classes and clojure sources that might be duplicated between .jars.

## Examples
### Just the uberjar:
```
% java -jar target/duplicate-detector-0.1.0-SNAPSHOT-standalone.jar
Processing .jar file:  target/duplicate-detector-0.1.0-SNAPSHOT-standalone.jar
"Elapsed time: 107.593 msecs"
Processed 11 resources from 1 .jars
 ```
 
### Duplicate classes between plugin jars:
This example uses the `plugins-with-duplicates` directory which includes multiple versions of apache commons-io.

```
% java -jar target/duplicate-detector-0.1.0-SNAPSHOT-standalone.jar --plugins plugins-with-duplicates
Processing .jar file:  target/duplicate-detector-0.1.0-SNAPSHOT-standalone.jar
Processing .jar file:  plugins-with-duplicates/commons-io-2.1.jar
Processing .jar file:  plugins-with-duplicates/commons-io-2.4.jar
ERROR: Class or namespace org/apache/commons/io/ByteOrderMark.class found in both plugins-with-duplicates/commons-io-2.4.jar and plugins-with-duplicates/commons-io-2.1.jar
```