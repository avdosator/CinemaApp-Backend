pipeline {
    agent any

    environment {
        BACKEND_IMAGE = 'ahmedhamdo/cinemaapp-backend:latest'
        SERVER_PORT = '8083'

        // Varijable povučene iz Jenkins Credentials
        DB_URL = credentials('DB_URL')
        JWT_EXPIRATION_TIME = credentials('JWT_EXPIRATION_TIME')
        JWT_SECRET_KEY = credentials('JWT_SECRET_KEY')
        SPRING_MAIL_PASSWORD = credentials('SPRING_MAIL_PASSWORD')
        SPRING_MAIL_USERNAME = credentials('SPRING_MAIL_USERNAME')
    }

    stages {
        stage('Deploy PostgreSQL') {
            steps {
                echo "Deploying PostgreSQL database"
                sh """
                docker rm -f cinemaapp-postgres || true
                docker run -d --name cinemaapp-postgres \
                    -e POSTGRES_DB=cinema_app_db \
                    -e POSTGRES_USER=cinema_app_user \
                    -e POSTGRES_PASSWORD='password' \
                    -p 5433:5432 \
                    postgres:latest
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
                docker rm -f cinemaapp-backend || true
                docker run -d --name cinemaapp-backend \
                    -p ${SERVER_PORT}:8080 \
                    -e DATABASE_URL='${DB_URL}' \
                    -e JWT_EXPIRATION_TIME='${JWT_EXPIRATION_TIME}' \
                    -e JWT_SECRET_KEY='${JWT_SECRET_KEY}' \
                    -e SPRING_MAIL_PASSWORD='${SPRING_MAIL_PASSWORD}' \
                    -e SPRING_MAIL_USERNAME='${SPRING_MAIL_USERNAME}' \
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

