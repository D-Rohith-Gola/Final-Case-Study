pipeline {
    agent any

    environment {
        IMAGE_NAME = "settlement-service"
        CONTAINER_NAME = "settlement-service-container"
    }

    stages {

        stage('Checkout') {
            steps {
                checkout scm
            }
        }

        stage('Build Jar') {
            steps {
                bat 'mvn clean package -DskipTests'
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
                bat 'docker run -d -p 8083:8083 --name %CONTAINER_NAME% %IMAGE_NAME%'
            }
        }
    }
}
