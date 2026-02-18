pipeline {
    agent any

    environment {
        GIT_REPO       = 'https://github.com/D-Rohith-Gola/Final-Case-Study.git'
        GIT_BRANCH     = 'fraud-service-go'
        IMAGE_NAME     = 'fraud-service-go'
        CONTAINER_NAME = 'fraud-service-go'
        DOCKER_NETWORK = 'admin'
        IMAGE_TAG      = 'latest'
    }

    stages {

        stage('Clean Workspace') {
            steps {
                cleanWs()
            }
        }

        stage('Clone Repository') {
            steps {
                git branch: "${GIT_BRANCH}", url: "${GIT_REPO}"
            }
        }

        stage('Build Go Application') {
            steps {
                bat 'go mod tidy'
                bat 'go build -o app.exe'
            }
        }

        stage('Build Docker Image') {
            steps {
                bat "docker build -t ${IMAGE_NAME}:${IMAGE_TAG} ."
            }
        }

        stage('Ensure Docker Network Exists') {
            steps {
                bat "docker network inspect ${DOCKER_NETWORK} >nul 2>&1 || docker network create ${DOCKER_NETWORK}"
            }
        }

        stage('Stop Old Container') {
            steps {
                bat "docker rm -f ${CONTAINER_NAME} >nul 2>&1 || exit 0"
            }
        }

        stage('Run New Container') {
            steps {
                bat """
                docker run -d ^
                    --name ${CONTAINER_NAME} ^
                    --network ${DOCKER_NETWORK} ^
                    -p 8085:8085 ^
                    ${IMAGE_NAME}:${IMAGE_TAG}
                """
            }
        }
    }

    post {
        success {
            echo "Fraud Service Deployed 🚀"
        }
        failure {
            echo "Deployment Failed ❌"
        }
    }
}
