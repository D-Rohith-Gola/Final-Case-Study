pipeline {
    agent any

    environment {
        IMAGE_NAME = "fraud-service"
        CONTAINER_NAME = "fraud-service-container"
    }

    stages {

        stage('Checkout') {
            steps {
                checkout scm
            }
        }

        stage('Build Docker Image') {
            steps {
                bat 'docker build -t %IMAGE_NAME% .'
            }
        }

        stage('Stop Old Container') {
            steps {
                bat 'docker rm -f %CONTAINER_NAME% || exit 0'
            }
        }

        stage('Run New Container') {
            steps {
                bat 'docker run -d --name %CONTAINER_NAME% -e KAFKA_BROKER=localhost:9092 %IMAGE_NAME%'
            }
        }
    }
}
