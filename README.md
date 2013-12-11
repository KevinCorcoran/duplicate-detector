duplicate-detector
==================
This thing looks at the .jars on the classpath and identifies classes and clojure sources that might be duplicated between .jars.

## Examples
### Just the uberjar:
```
% java -jar target/duplicate-detector-0.1.0-SNAPSHOT-standalone.jar
Processing .jar file:  target/duplicate-detector-0.1.0-SNAPSHOT-standalone.jar
Resulting resource map is:
{"puppetlabs/duplicate_detector/core.clj"
 "target/duplicate-detector-0.1.0-SNAPSHOT-standalone.jar",
 "puppetlabs/duplicate_detector/core__init.class"
 "target/duplicate-detector-0.1.0-SNAPSHOT-standalone.jar",
 "puppetlabs/duplicate_detector/core.class"
 "target/duplicate-detector-0.1.0-SNAPSHOT-standalone.jar",
 "puppetlabs/duplicate_detector/core$should_process_QMARK_.class"
 "target/duplicate-detector-0.1.0-SNAPSHOT-standalone.jar",
 "puppetlabs/duplicate_detector/core$process_jar_BANG_.class"
 "target/duplicate-detector-0.1.0-SNAPSHOT-standalone.jar",
 "puppetlabs/duplicate_detector/core$loading__4910__auto__.class"
 "target/duplicate-detector-0.1.0-SNAPSHOT-standalone.jar",
 "puppetlabs/duplicate_detector/core$fn__794.class"
 "target/duplicate-detector-0.1.0-SNAPSHOT-standalone.jar",
 "puppetlabs/duplicate_detector/core$_main.class"
 "target/duplicate-detector-0.1.0-SNAPSHOT-standalone.jar",
 "puppetlabs/duplicate_detector/core$_main$fn__803.class"
 "target/duplicate-detector-0.1.0-SNAPSHOT-standalone.jar"}
 ```
 
### Duplicate classes between plugin jars:
This example uses the `plugins-with-duplicates` directory which includes multiple versions of apache commons-io.

```
% java -jar target/duplicate-detector-0.1.0-SNAPSHOT-standalone.jar --plugins plugins-with-duplicates
Processing .jar file:  target/duplicate-detector-0.1.0-SNAPSHOT-standalone.jar
Processing .jar file:  #<File plugins-with-duplicates/commons-io-2.1.jar>
Processing .jar file:  #<File plugins-with-duplicates/commons-io-2.4.jar>
Exception in thread "main" java.lang.Exception: Class or namespace org/apache/commons/io/ByteOrderMark.class found in both plugins-with-duplicates/commons-io-2.4.jar and plugins-with-duplicates/commons-io-2.1.jar
	at puppetlabs.duplicate_detector.core$process_jar_BANG_.invoke(core.clj:39)
	at puppetlabs.duplicate_detector.core$_main.doInvoke(core.clj:63)
	at clojure.lang.RestFn.applyTo(RestFn.java:137)
	at puppetlabs.duplicate_detector.core.main(Unknown Source)
```