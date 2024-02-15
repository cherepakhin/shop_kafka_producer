
pipeline {

    agent any
    options {
        durabilityHint 'MAX_SURVIVABILITY'
    }
    stages {
        stage('Checkout') {
            steps {
                sh 'rm -rf shop_kafka_producer; git clone https://github.com/cherepakhin/shop_kafka_producer'
            }
        }

        stage('Unit tests') {
            steps {
                sh 'pwd;cd shop_kafka_producer;./gradlew clean test'
            }
        }

        stage('Build bootJar') {
            steps {
                sh 'pwd;cd shop_kafka_producer;./gradlew bootJar'
            }
        }

        stage('Publish to Nexus') {
            environment {
                NEXUS_CRED = credentials('nexus_admin')
            }
            steps {
                sh 'cd shop_kafka_producer;./gradlew publish'
            }
        }
    }
}