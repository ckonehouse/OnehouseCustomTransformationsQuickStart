plugins {
    id("java")
}

group = "org.onehouse"
version = "1.0-SNAPSHOT"

repositories {
    mavenCentral()
    mavenLocal()
    maven {
        url = uri("https://packages.confluent.io/maven/")
    }
}

dependencies {
    compileOnly("org.apache.spark:spark-sql_2.12:3.2.0")
    compileOnly("org.apache.spark:spark-avro_2.12:3.2.0")
    compileOnly("org.apache.hudi:hudi-utilities_2.12:0.14.0")
    compileOnly("org.apache.parquet:parquet-avro:1.12.3")
    testImplementation("org.apache.spark:spark-sql_2.12:3.2.0")
    testImplementation("org.apache.spark:spark-avro_2.12:3.2.0")
    testImplementation("org.apache.hudi:hudi-utilities_2.12:0.14.0")
    testImplementation("org.apache.parquet:parquet-avro:1.12.3")
    testImplementation(platform("org.junit:junit-bom:5.9.1"))
    testImplementation("org.junit.jupiter:junit-jupiter")
}

tasks.test {
    useJUnitPlatform()
}