package it.unibo.tirocinio.martelli.springbot;
import it.unibo.tirocinio.martelli.controller.impl.ControllerImpl;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import it.unibo.tirocinio.martelli.controller.api.Controller;

import java.util.List;

@RestController
public class Hello {
    private final Controller controller = new ControllerImpl();
    @GetMapping("/hello")
    public String HelloWorld(){
        return "Hello World";
    }

    @GetMapping("/execute")
    public String execute() throws Exception {
        controller.execute();
        return "ok";
    }

    @GetMapping("/show")
    public List<String> show() throws Exception {
        return controller.showDatabase();
    }
}
/*
[INFO] +- junit:junit:jar:4.13.1:test
[INFO] |  \- org.hamcrest:hamcrest-core:jar:1.3:test
[INFO] +- com.fasterxml.jackson.core:jackson-databind:jar:2.10.0:compile
[INFO] |  +- com.fasterxml.jackson.core:jackson-annotations:jar:2.10.0:compile
[INFO] |  \- com.fasterxml.jackson.core:jackson-core:jar:2.10.0:compile
[INFO] +- org.yaml:snakeyaml:jar:2.2:compile
[INFO] +- org.apache.httpcomponents:httpclient:jar:4.5.14:compile
[INFO] |  +- org.apache.httpcomponents:httpcore:jar:4.4.16:compile
[INFO] |  +- commons-logging:commons-logging:jar:1.2:compile
[INFO] |  \- commons-codec:commons-codec:jar:1.11:compile
[INFO] +- org.apache.spark:spark-core_2.12:jar:3.1.2:compile
[INFO] |  +- com.thoughtworks.paranamer:paranamer:jar:2.8:compile
[INFO] |  +- org.apache.avro:avro:jar:1.8.2:compile
[INFO] |  |  +- org.codehaus.jackson:jackson-core-asl:jar:1.9.13:compile
[INFO] |  |  +- org.codehaus.jackson:jackson-mapper-asl:jar:1.9.13:compile
[INFO] |  |  \- org.tukaani:xz:jar:1.5:compile
[INFO] |  +- org.apache.avro:avro-mapred:jar:hadoop2:1.8.2:compile
[INFO] |  |  \- org.apache.avro:avro-ipc:jar:1.8.2:compile
[INFO] |  +- com.twitter:chill_2.12:jar:0.9.5:compile
[INFO] |  |  \- com.esotericsoftware:kryo-shaded:jar:4.0.2:compile
[INFO] |  |     \- com.esotericsoftware:minlog:jar:1.3.0:compile
[INFO] |  +- com.twitter:chill-java:jar:0.9.5:compile
[INFO] |  +- org.apache.xbean:xbean-asm7-shaded:jar:4.15:compile
[INFO] |  +- org.apache.spark:spark-launcher_2.12:jar:3.1.2:compile
[INFO] |  +- org.apache.spark:spark-kvstore_2.12:jar:3.1.2:compile
[INFO] |  +- org.apache.spark:spark-network-common_2.12:jar:3.1.2:compile
[INFO] |  +- org.apache.spark:spark-network-shuffle_2.12:jar:3.1.2:compile
[INFO] |  +- org.apache.spark:spark-unsafe_2.12:jar:3.1.2:compile
[INFO] |  +- javax.activation:activation:jar:1.1.1:compile
[INFO] |  +- org.apache.curator:curator-recipes:jar:2.13.0:compile
[INFO] |  |  \- org.apache.curator:curator-framework:jar:2.13.0:compile
[INFO] |  +- org.apache.zookeeper:zookeeper:jar:3.4.14:compile
[INFO] |  |  \- org.apache.yetus:audience-annotations:jar:0.5.0:compile
[INFO] |  +- jakarta.servlet:jakarta.servlet-api:jar:4.0.3:compile
[INFO] |  +- org.apache.commons:commons-math3:jar:3.4.1:compile
[INFO] |  +- org.apache.commons:commons-text:jar:1.6:compile
[INFO] |  +- com.google.code.findbugs:jsr305:jar:3.0.0:compile
[INFO] |  +- org.slf4j:slf4j-api:jar:1.7.30:compile
[INFO] |  +- org.slf4j:jul-to-slf4j:jar:1.7.30:compile
[INFO] |  +- org.slf4j:jcl-over-slf4j:jar:1.7.30:compile
[INFO] |  +- log4j:log4j:jar:1.2.17:compile
[INFO] |  +- org.slf4j:slf4j-log4j12:jar:1.7.30:compile
[INFO] |  +- com.ning:compress-lzf:jar:1.0.3:compile
[INFO] |  +- org.xerial.snappy:snappy-java:jar:1.1.8.2:compile
[INFO] |  +- org.lz4:lz4-java:jar:1.7.1:compile
[INFO] |  +- com.github.luben:zstd-jni:jar:1.4.8-1:compile
[INFO] |  +- org.roaringbitmap:RoaringBitmap:jar:0.9.0:compile
[INFO] |  |  \- org.roaringbitmap:shims:jar:0.9.0:runtime
[INFO] |  +- commons-net:commons-net:jar:3.1:compile
[INFO] |  +- org.scala-lang.modules:scala-xml_2.12:jar:1.2.0:compile
[INFO] |  +- org.scala-lang:scala-library:jar:2.12.10:compile
[INFO] |  +- org.scala-lang:scala-reflect:jar:2.12.10:compile
[INFO] |  +- org.json4s:json4s-jackson_2.12:jar:3.7.0-M5:compile
[INFO] |  |  \- org.json4s:json4s-core_2.12:jar:3.7.0-M5:compile
[INFO] |  |     +- org.json4s:json4s-ast_2.12:jar:3.7.0-M5:compile
[INFO] |  |     \- org.json4s:json4s-scalap_2.12:jar:3.7.0-M5:compile
[INFO] |  +- org.glassfish.jersey.core:jersey-client:jar:2.30:compile
[INFO] |  |  +- jakarta.ws.rs:jakarta.ws.rs-api:jar:2.1.6:compile
[INFO] |  |  \- org.glassfish.hk2.external:jakarta.inject:jar:2.6.1:compile
[INFO] |  +- org.glassfish.jersey.core:jersey-common:jar:2.30:compile
[INFO] |  |  +- jakarta.annotation:jakarta.annotation-api:jar:1.3.5:compile
[INFO] |  |  \- org.glassfish.hk2:osgi-resource-locator:jar:1.0.3:compile
[INFO] |  +- org.glassfish.jersey.core:jersey-server:jar:2.30:compile
[INFO] |  |  +- org.glassfish.jersey.media:jersey-media-jaxb:jar:2.30:compile
[INFO] |  |  \- jakarta.validation:jakarta.validation-api:jar:2.0.2:compile
[INFO] |  +- org.glassfish.jersey.containers:jersey-container-servlet:jar:2.30:compile
[INFO] |  +- org.glassfish.jersey.containers:jersey-container-servlet-core:jar:2.30:compile
[INFO] |  +- org.glassfish.jersey.inject:jersey-hk2:jar:2.30:compile
[INFO] |  |  +- org.glassfish.hk2:hk2-locator:jar:2.6.1:compile
[INFO] |  |  |  +- org.glassfish.hk2.external:aopalliance-repackaged:jar:2.6.1:compile
[INFO] |  |  |  +- org.glassfish.hk2:hk2-api:jar:2.6.1:compile
[INFO] |  |  |  \- org.glassfish.hk2:hk2-utils:jar:2.6.1:compile
[INFO] |  |  \- org.javassist:javassist:jar:3.25.0-GA:compile
[INFO] |  +- io.netty:netty-all:jar:4.1.51.Final:compile
[INFO] |  +- com.clearspring.analytics:stream:jar:2.9.6:compile
[INFO] |  +- io.dropwizard.metrics:metrics-core:jar:4.1.1:compile
[INFO] |  +- io.dropwizard.metrics:metrics-jvm:jar:4.1.1:compile
[INFO] |  +- io.dropwizard.metrics:metrics-json:jar:4.1.1:compile
[INFO] |  +- io.dropwizard.metrics:metrics-graphite:jar:4.1.1:compile
[INFO] |  +- io.dropwizard.metrics:metrics-jmx:jar:4.1.1:compile
[INFO] |  +- com.fasterxml.jackson.module:jackson-module-scala_2.12:jar:2.10.0:compile
[INFO] |  |  \- com.fasterxml.jackson.module:jackson-module-paranamer:jar:2.10.0:compile
[INFO] |  +- org.apache.ivy:ivy:jar:2.4.0:compile
[INFO] |  +- oro:oro:jar:2.0.8:compile
[INFO] |  +- net.razorvine:pyrolite:jar:4.30:compile
[INFO] |  +- net.sf.py4j:py4j:jar:0.10.9:compile
[INFO] |  +- org.apache.spark:spark-tags_2.12:jar:3.1.2:compile
[INFO] |  +- org.apache.commons:commons-crypto:jar:1.1.0:compile
[INFO] |  \- org.spark-project.spark:unused:jar:1.0.0:compile
[INFO] +- org.apache.spark:spark-sql_2.12:jar:3.1.2:compile
[INFO] |  +- com.univocity:univocity-parsers:jar:2.9.1:compile
[INFO] |  +- org.apache.spark:spark-sketch_2.12:jar:3.1.2:compile
[INFO] |  +- org.apache.spark:spark-catalyst_2.12:jar:3.1.2:compile
[INFO] |  |  +- org.scala-lang.modules:scala-parser-combinators_2.12:jar:1.1.2:compile
[INFO] |  |  +- org.codehaus.janino:janino:jar:3.0.16:compile
[INFO] |  |  +- org.codehaus.janino:commons-compiler:jar:3.0.16:compile
[INFO] |  |  +- org.antlr:antlr4-runtime:jar:4.8-1:compile
[INFO] |  |  +- org.apache.arrow:arrow-vector:jar:2.0.0:compile
[INFO] |  |  |  +- org.apache.arrow:arrow-format:jar:2.0.0:compile
[INFO] |  |  |  +- org.apache.arrow:arrow-memory-core:jar:2.0.0:compile
[INFO] |  |  |  \- com.google.flatbuffers:flatbuffers-java:jar:1.9.0:compile
[INFO] |  |  \- org.apache.arrow:arrow-memory-netty:jar:2.0.0:compile
[INFO] |  +- org.apache.orc:orc-core:jar:1.5.12:compile
[INFO] |  |  +- org.apache.orc:orc-shims:jar:1.5.12:compile
[INFO] |  |  +- commons-lang:commons-lang:jar:2.6:compile
[INFO] |  |  +- io.airlift:aircompressor:jar:0.10:compile
[INFO] |  |  \- org.threeten:threeten-extra:jar:1.5.0:compile
[INFO] |  +- org.apache.orc:orc-mapreduce:jar:1.5.12:compile
[INFO] |  +- org.apache.hive:hive-storage-api:jar:2.7.2:compile
[INFO] |  +- org.apache.parquet:parquet-column:jar:1.10.1:compile
[INFO] |  |  +- org.apache.parquet:parquet-common:jar:1.10.1:compile
[INFO] |  |  \- org.apache.parquet:parquet-encoding:jar:1.10.1:compile
[INFO] |  \- org.apache.parquet:parquet-hadoop:jar:1.10.1:compile
[INFO] |     +- org.apache.parquet:parquet-format:jar:2.4.0:compile
[INFO] |     \- org.apache.parquet:parquet-jackson:jar:1.10.1:compile
[INFO] +- org.apache.hadoop:hadoop-hdfs:jar:3.3.4:compile
[INFO] |  +- org.apache.hadoop.thirdparty:hadoop-shaded-guava:jar:1.1.1:compile
[INFO] |  +- org.eclipse.jetty:jetty-server:jar:9.4.43.v20210629:compile
[INFO] |  |  +- org.eclipse.jetty:jetty-http:jar:9.4.43.v20210629:compile
[INFO] |  |  \- org.eclipse.jetty:jetty-io:jar:9.4.43.v20210629:compile
[INFO] |  +- org.eclipse.jetty:jetty-util:jar:9.4.43.v20210629:compile
[INFO] |  +- org.eclipse.jetty:jetty-util-ajax:jar:9.4.43.v20210629:compile
[INFO] |  +- com.sun.jersey:jersey-core:jar:1.19:compile
[INFO] |  |  \- javax.ws.rs:jsr311-api:jar:1.1.1:compile
[INFO] |  +- com.sun.jersey:jersey-server:jar:1.19:compile
[INFO] |  +- commons-cli:commons-cli:jar:1.2:compile
[INFO] |  +- commons-io:commons-io:jar:2.8.0:compile
[INFO] |  +- commons-daemon:commons-daemon:jar:1.0.13:compile
[INFO] |  +- ch.qos.reload4j:reload4j:jar:1.2.22:compile
[INFO] |  +- com.google.protobuf:protobuf-java:jar:2.5.0:compile
[INFO] |  +- javax.servlet:javax.servlet-api:jar:3.1.0:compile
[INFO] |  +- io.netty:netty:jar:3.10.6.Final:compile
[INFO] |  \- org.fusesource.leveldbjni:leveldbjni-all:jar:1.8:compile
[INFO] +- org.apache.hadoop:hadoop-common:jar:3.3.4:compile
[INFO] |  +- org.apache.hadoop.thirdparty:hadoop-shaded-protobuf_3_7:jar:1.1.1:compile
[INFO] |  +- org.apache.hadoop:hadoop-annotations:jar:3.3.4:compile
[INFO] |  +- commons-collections:commons-collections:jar:3.2.2:compile
[INFO] |  +- jakarta.activation:jakarta.activation-api:jar:1.2.1:compile
[INFO] |  +- org.eclipse.jetty:jetty-servlet:jar:9.4.43.v20210629:compile
[INFO] |  |  \- org.eclipse.jetty:jetty-security:jar:9.4.43.v20210629:compile
[INFO] |  +- org.eclipse.jetty:jetty-webapp:jar:9.4.43.v20210629:compile
[INFO] |  |  \- org.eclipse.jetty:jetty-xml:jar:9.4.43.v20210629:compile
[INFO] |  +- javax.servlet.jsp:jsp-api:jar:2.1:runtime
[INFO] |  +- com.sun.jersey:jersey-servlet:jar:1.19:compile
[INFO] |  +- com.sun.jersey:jersey-json:jar:1.19:compile
[INFO] |  |  +- org.codehaus.jettison:jettison:jar:1.1:compile
[INFO] |  |  +- com.sun.xml.bind:jaxb-impl:jar:2.2.3-1:compile
[INFO] |  |  +- org.codehaus.jackson:jackson-jaxrs:jar:1.9.2:compile
[INFO] |  |  \- org.codehaus.jackson:jackson-xc:jar:1.9.2:compile
[INFO] |  +- commons-beanutils:commons-beanutils:jar:1.9.4:compile
[INFO] |  +- org.apache.commons:commons-configuration2:jar:2.1.1:compile
[INFO] |  +- org.slf4j:slf4j-reload4j:jar:1.7.36:compile
[INFO] |  +- com.google.re2j:re2j:jar:1.1:compile
[INFO] |  +- com.google.code.gson:gson:jar:2.8.9:compile
[INFO] |  +- org.apache.hadoop:hadoop-auth:jar:3.3.4:compile
[INFO] |  |  +- com.nimbusds:nimbus-jose-jwt:jar:9.8.1:compile
[INFO] |  |  |  \- com.github.stephenc.jcip:jcip-annotations:jar:1.0-1:compile
[INFO] |  |  +- net.minidev:json-smart:jar:2.4.7:compile
[INFO] |  |  |  \- net.minidev:accessors-smart:jar:2.4.7:compile
[INFO] |  |  |     \- org.ow2.asm:asm:jar:9.1:compile
[INFO] |  |  \- org.apache.kerby:kerb-simplekdc:jar:1.0.1:compile
[INFO] |  |     +- org.apache.kerby:kerb-client:jar:1.0.1:compile
[INFO] |  |     |  +- org.apache.kerby:kerby-config:jar:1.0.1:compile
[INFO] |  |     |  +- org.apache.kerby:kerb-common:jar:1.0.1:compile
[INFO] |  |     |  |  \- org.apache.kerby:kerb-crypto:jar:1.0.1:compile
[INFO] |  |     |  +- org.apache.kerby:kerb-util:jar:1.0.1:compile
[INFO] |  |     |  \- org.apache.kerby:token-provider:jar:1.0.1:compile
[INFO] |  |     \- org.apache.kerby:kerb-admin:jar:1.0.1:compile
[INFO] |  |        +- org.apache.kerby:kerb-server:jar:1.0.1:compile
[INFO] |  |        |  \- org.apache.kerby:kerb-identity:jar:1.0.1:compile
[INFO] |  |        \- org.apache.kerby:kerby-xdr:jar:1.0.1:compile
[INFO] |  +- com.jcraft:jsch:jar:0.1.55:compile
[INFO] |  +- org.apache.curator:curator-client:jar:4.2.0:compile
[INFO] |  +- org.apache.commons:commons-compress:jar:1.21:compile
[INFO] |  +- org.apache.kerby:kerb-core:jar:1.0.1:compile
[INFO] |  |  \- org.apache.kerby:kerby-pkix:jar:1.0.1:compile
[INFO] |  |     +- org.apache.kerby:kerby-asn1:jar:1.0.1:compile
[INFO] |  |     \- org.apache.kerby:kerby-util:jar:1.0.1:compile
[INFO] |  +- org.codehaus.woodstox:stax2-api:jar:4.2.1:compile
[INFO] |  +- com.fasterxml.woodstox:woodstox-core:jar:5.3.0:compile
[INFO] |  \- dnsjava:dnsjava:jar:2.1.7:compile
[INFO] +- org.apache.hadoop:hadoop-client:jar:3.3.4:compile
[INFO] |  +- org.apache.hadoop:hadoop-hdfs-client:jar:3.3.4:compile
[INFO] |  +- org.apache.hadoop:hadoop-yarn-api:jar:3.3.4:compile
[INFO] |  |  \- javax.xml.bind:jaxb-api:jar:2.2.11:compile
[INFO] |  +- org.apache.hadoop:hadoop-yarn-client:jar:3.3.4:compile
[INFO] |  |  +- org.eclipse.jetty.websocket:websocket-client:jar:9.4.43.v20210629:compile
[INFO] |  |  |  +- org.eclipse.jetty:jetty-client:jar:9.4.43.v20210629:compile
[INFO] |  |  |  \- org.eclipse.jetty.websocket:websocket-common:jar:9.4.43.v20210629:compile
[INFO] |  |  |     \- org.eclipse.jetty.websocket:websocket-api:jar:9.4.43.v20210629:compile
[INFO] |  |  \- org.jline:jline:jar:3.9.0:compile
[INFO] |  +- org.apache.hadoop:hadoop-mapreduce-client-core:jar:3.3.4:compile
[INFO] |  |  \- org.apache.hadoop:hadoop-yarn-common:jar:3.3.4:compile
[INFO] |  |     +- com.sun.jersey:jersey-client:jar:1.19:compile
[INFO] |  |     +- com.fasterxml.jackson.module:jackson-module-jaxb-annotations:jar:2.12.7:compile
[INFO] |  |     \- com.fasterxml.jackson.jaxrs:jackson-jaxrs-json-provider:jar:2.12.7:compile
[INFO] |  |        \- com.fasterxml.jackson.jaxrs:jackson-jaxrs-base:jar:2.12.7:compile
[INFO] |  \- org.apache.hadoop:hadoop-mapreduce-client-jobclient:jar:3.3.4:compile
[INFO] |     \- org.apache.hadoop:hadoop-mapreduce-client-common:jar:3.3.4:compile
[INFO] +- org.apache.hadoop:hadoop-aws:jar:3.3.4:compile
[INFO] |  +- com.amazonaws:aws-java-sdk-bundle:jar:1.12.262:compile
[INFO] |  \- org.wildfly.openssl:wildfly-openssl:jar:1.0.7.Final:runtime
[INFO] +- com.google.guava:guava:jar:31.1-jre:compile
[INFO] |  +- com.google.guava:failureaccess:jar:1.0.1:compile
[INFO] |  +- com.google.guava:listenablefuture:jar:9999.0-empty-to-avoid-conflict-with-guava:compile
[INFO] |  +- org.checkerframework:checker-qual:jar:3.12.0:compile
[INFO] |  +- com.google.errorprone:error_prone_annotations:jar:2.11.0:compile
[INFO] |  \- com.google.j2objc:j2objc-annotations:jar:1.3:compile
[INFO] +- org.apache.commons:commons-lang3:jar:3.12.0:compile
[INFO] +- org.springframework.boot:spring-boot-starter-web:jar:2.5.5:compile
[INFO] |  +- org.springframework.boot:spring-boot-starter:jar:2.5.5:compile
[INFO] |  |  +- org.springframework.boot:spring-boot:jar:2.5.5:compile
[INFO] |  |  \- org.springframework.boot:spring-boot-autoconfigure:jar:2.5.5:compile
[INFO] |  +- org.springframework.boot:spring-boot-starter-json:jar:2.5.5:compile
[INFO] |  |  +- com.fasterxml.jackson.datatype:jackson-datatype-jdk8:jar:2.12.5:compile
[INFO] |  |  +- com.fasterxml.jackson.datatype:jackson-datatype-jsr310:jar:2.12.5:compile
[INFO] |  |  \- com.fasterxml.jackson.module:jackson-module-parameter-names:jar:2.12.5:compile
[INFO] |  +- org.springframework.boot:spring-boot-starter-tomcat:jar:2.5.5:compile
[INFO] |  |  +- org.apache.tomcat.embed:tomcat-embed-core:jar:9.0.53:compile
[INFO] |  |  +- org.apache.tomcat.embed:tomcat-embed-el:jar:9.0.53:compile
[INFO] |  |  \- org.apache.tomcat.embed:tomcat-embed-websocket:jar:9.0.53:compile
[INFO] |  +- org.springframework:spring-web:jar:5.3.10:compile
[INFO] |  |  \- org.springframework:spring-beans:jar:5.3.10:compile
[INFO] |  \- org.springframework:spring-webmvc:jar:5.3.10:compile
[INFO] |     +- org.springframework:spring-aop:jar:5.3.10:compile
[INFO] |     +- org.springframework:spring-context:jar:5.3.10:compile
[INFO] |     \- org.springframework:spring-expression:jar:5.3.10:compile
[INFO] \- org.springframework.boot:spring-boot-starter-test:jar:2.5.5:test
[INFO]    +- org.springframework.boot:spring-boot-test:jar:2.5.5:test
[INFO]    +- org.springframework.boot:spring-boot-test-autoconfigure:jar:2.5.5:test
[INFO]    |  +- net.bytebuddy:byte-buddy-agent:jar:1.10.20:test
[INFO]    |  \- org.objenesis:objenesis:jar:3.2:compile
[INFO]    +- org.mockito:mockito-junit-jupiter:jar:3.9.0:test
[INFO]    +- org.skyscreamer:jsonassert:jar:1.5.0:test
[INFO]    |  \- com.vaadin.external.google:android-json:jar:0.0.20131108.vaadin1:test
[INFO]    +- org.springframework:spring-core:jar:5.3.10:compile
[INFO]    |  \- org.springframework:spring-jcl:jar:5.3.10:compile
[INFO]    +- org.springframework:spring-test:jar:5.3.10:test
[INFO]    \- org.xmlunit:xmlunit-core:jar:2.8.2:test

 */
