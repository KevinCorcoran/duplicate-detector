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
## Benchmarks
### Plugins dir includes commons-io and clj-http
```
% java -jar target/duplicate-detector-0.1.0-SNAPSHOT-standalone.jar --plugins plugins-timing
Processing .jar file:  target/duplicate-detector-0.1.0-SNAPSHOT-standalone.jar
Processing .jar file:  plugins-timing/clj-http-0.5.3.jar
Processing .jar file:  plugins-timing/commons-io-2.4.jar
"Elapsed time: 117.502 msecs"
Processed 128 resources from 3 .jars
```
### Plugins dir includes a handful of .jars
```
% java -jar target/duplicate-detector-0.1.0-SNAPSHOT-standalone.jar --plugins plugins-timing-heavy
Processing .jar file:  target/duplicate-detector-0.1.0-SNAPSHOT-standalone.jar
Processing .jar file:  plugins-timing-heavy/args4j-2.0.16.jar
Processing .jar file:  plugins-timing-heavy/clj-aws-s3-0.3.6.jar
Processing .jar file:  plugins-timing-heavy/clj-http-0.5.3.jar
Processing .jar file:  plugins-timing-heavy/clojureql-1.0.3.jar
Processing .jar file:  plugins-timing-heavy/commons-codec-1.6.jar
Processing .jar file:  plugins-timing-heavy/commons-fileupload-1.3.jar
Processing .jar file:  plugins-timing-heavy/commons-io-2.4.jar
Processing .jar file:  plugins-timing-heavy/commons-net-2.2.jar
Processing .jar file:  plugins-timing-heavy/joda-time-2.2.jar
Processing .jar file:  plugins-timing-heavy/postgresql-9.1-901-1.jdbc4.jar
"Elapsed time: 165.157 msecs"
Processed 889 resources from 11 .jars
```
## Conclusion
This approach seems to work fine.  It also seems to be plenty fast - all tests so far have taken 100-200ms.  Most importantly, the slowdown incurred by examining more .jars seems very minimal; processing just the uberjar (11 classes) took ~100ms, while processing the uberjar plus 10 "plugin" .jars (889 classes/namespaces) took only ~160ms.  Those numbers seem to validate this approach.