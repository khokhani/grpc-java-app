import com.google.protobuf.gradle.*
import org.gradle.kotlin.dsl.provider.gradleKotlinDslOf


plugins {
    java
    jacoco
    idea
    id("com.google.protobuf") version "0.8.10"
    id ("com.dorongold.task-tree") version "1.5"
    id ("dev.jacomet.logging-capabilities") version "0.7.0"
}

group = "com.kbn.grpc"
version = "1.0.0"

java {
    sourceCompatibility = org.gradle.api.JavaVersion.VERSION_1_8
    targetCompatibility = org.gradle.api.JavaVersion.VERSION_1_8
}

dependencies {

    //gRPC framework dependencies
    implementation("io.grpc", "grpc-netty-shaded","1.26.0")
    implementation("io.grpc", "grpc-protobuf", "1.26.0")
    implementation("io.grpc", "grpc-stub", "1.26.0")

    testCompile("junit", "junit", "4.12")
}

protobuf {
    protoc {
        artifact = "com.google.protobuf:protoc:3.11.0"
    }

    plugins {
        id("grpc") {
            artifact = "io.grpc:protoc-gen-grpc-java:1.26.0"
        }
    }

    generateProtoTasks {
        ofSourceSet("main").forEach {
            it.plugins {
                // Apply the "grpc" plugin whose spec is defined above, without options.
                id("grpc")
            }
        }
    }
}

repositories {
    mavenCentral()
}

