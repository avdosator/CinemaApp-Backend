pipeline {
    agent any

    environment {
        BACKEND_IMAGE = 'ahmedhamdo/team3-backend:latest'
        SERVER_PORT = '8082'

        POSTGRES_IMAGE = 'postgres:latest'
        POSTGRES_CONTAINER = 'team3-postgres'
        POSTGRES_PORT = '5432'
        POSTGRES_USER = credentials('POSTGRES_USER')
        POSTGRES_PASSWORD = credentials('POSTGRES_PASSWORD')
        POSTGRES_DB = credentials('POSTGRES_DB')

        DB_URL = "postgresql://${POSTGRES_CONTAINER}:${POSTGRES_PORT}/${POSTGRES_DB}?user=${POSTGRES_USER}&password=${POSTGRES_PASSWORD}"
        DOCKER_NETWORK = 'team3-network'

        JWT_EXPIRATION_TIME = credentials('JWT_EXPIRATION_TIME')
        JWT_SECRET_KEY = credentials('JWT_SECRET_KEY')
        SPRING_MAIL_PASSWORD = credentials('SPRING_MAIL_PASSWORD')
        SPRING_MAIL_USERNAME = credentials('SPRING_MAIL_USERNAME')
    }

    stages {
        stage('Create Docker Network') {
            steps {
                echo "Creating Docker network if not exists"
                sh """
                docker network inspect ${DOCKER_NETWORK} >/dev/null 2>&1 || \
                docker network create ${DOCKER_NETWORK}
                """
            }
        }

        stage('Deploy PostgreSQL') {
            steps {
                echo "Deploying PostgreSQL database"
                sh """
                docker rm -f ${POSTGRES_CONTAINER} || true
                docker run -d --name ${POSTGRES_CONTAINER} \
                    --network ${DOCKER_NETWORK} \
                    -e POSTGRES_USER=${POSTGRES_USER} \
                    -e POSTGRES_PASSWORD=${POSTGRES_PASSWORD} \
                    -e POSTGRES_DB=${POSTGRES_DB} \
                    -v team3_postgres_data:/var/lib/postgresql/data \
                    -p 5433:5432 \
                    ${POSTGRES_IMAGE}
                """
            }
        }

        stage('Checkout') {
            steps {
                echo "Cloning backend repository"
                git branch: 'develop',
                    url: 'https://github.com/avdosator/CinemaApp-Backend.git',
                    credentialsId: 'gh-token2'
            }
        }

        stage('Build Docker Image') {
            steps {
                echo "Building backend Docker image"
                sh """
                docker build -t ${BACKEND_IMAGE} .
                """
            }
        }

        stage('Push Docker Image') {
            steps {
                echo "Pushing backend Docker image to Docker Hub"
                withDockerRegistry([credentialsId: 'dockerhub-credentials', url: '']) {
                    sh "docker push ${BACKEND_IMAGE}"
                }
            }
        }

        stage('Deploy Backend Container') {
            steps {
                echo "Deploying backend container on port ${SERVER_PORT}"
                sh """
                docker rm -f team3-backend || true
                docker run -d --name team3-backend \
                    --network ${DOCKER_NETWORK} \
                    -p ${SERVER_PORT}:8080 \
                    -e DATABASE_URL="${DB_URL}" \
                    -e JWT_EXPIRATION_TIME="${JWT_EXPIRATION_TIME}" \
                    -e JWT_SECRET_KEY="${JWT_SECRET_KEY}" \
                    -e SPRING_MAIL_PASSWORD="${SPRING_MAIL_PASSWORD}" \
                    -e SPRING_MAIL_USERNAME="${SPRING_MAIL_USERNAME}" \
                    ${BACKEND_IMAGE}
                """
            }
        }
    }

    post {
        success {
            echo "Backend and database deployment completed successfully!"
        }
        failure {
            echo "Backend build or deployment failed."
        }
    }
}
