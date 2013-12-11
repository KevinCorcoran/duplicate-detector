duplicate-detector
==================
This thing looks at the .jars on the classpath and identifies classes and clojure sources that might be duplicated between .jars.

## Example
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
 "puppetlabs/duplicate_detector/core$fn__695.class"
 "target/duplicate-detector-0.1.0-SNAPSHOT-standalone.jar",
 "puppetlabs/duplicate_detector/core$_main.class"
 "target/duplicate-detector-0.1.0-SNAPSHOT-standalone.jar"}
```