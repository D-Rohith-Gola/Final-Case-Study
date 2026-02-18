pipeline {
    agent any

    tools {
        maven 'Maven-3'
    }

    environment {
        GIT_REPO        = 'https://github.com/D-Rohith-Gola/Final-Case-Study.git'
        GIT_BRANCH      = 'fraud-service-go'
        IMAGE_NAME      = 'fraud-service-go'
        CONTAINER_NAME  = 'fraud-service-go'
        DOCKER_NETWORK  = 'admin'
        IMAGE_TAG       = 'latest'
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

       stage('Build Application (Skip Tests)') {
    steps {
            bat 'mvn clean package -DskipTests'
        }
    }


        stage('Build Docker Image') {
    steps {
            bat """
            docker build -t ${IMAGE_NAME}:${IMAGE_TAG} .
            docker tag ${IMAGE_NAME}:${IMAGE_TAG} ${IMAGE_NAME}:latest
            """
        }
    }


        stage('Ensure Docker Network Exists') {
            steps {
                bat """
                docker network inspect ${DOCKER_NETWORK} >nul 2>&1 || docker network create ${DOCKER_NETWORK}
                """
            }
        }

        stage('Stop & Remove Existing Container If Running') {
            steps {
                bat """
                docker rm -f ${CONTAINER_NAME} >nul 2>&1 || exit 0
                """
            }
        }

        stage('Run New Container') {
            steps {
                bat """
                docker run -d ^
                    --name ${CONTAINER_NAME} ^
                    --network ${DOCKER_NETWORK} ^
                    -p 8762:8762^
                    ${IMAGE_NAME}:latest
                """
            }
        }
    }

    post {
        success {
            echo "Deployment Successful 🚀"
        }
        failure {
            echo "Deployment Failed ❌"
        }
    }
}