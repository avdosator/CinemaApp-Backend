pipeline {
    agent any

    environment {
        BACKEND_IMAGE = 'ahmedhamdo/cinemaapp-backend:latest'
        SERVER_PORT = '8082'
        DB_URL = 'postgresql://localhost:5433/cinema_app_db?user=cinema_app_user&password=password'
        JWT_EXPIRATION_TIME = '3600000'
        JWT_SECRET_KEY = '54a4f4f539b1a65c66bc5d2ed01996931a736e5e4cfaa40d0ef53eca7eff722a37d285c76879a051595ad2a75bcc1c5df55bee6b1eae571ac611ffd488942031'
        SPRING_MAIL_PASSWORD = 'jleveyashklmywxb'
        SPRING_MAIL_USERNAME = 'cinema.app.info@gmail.com'
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
                    -e POSTGRES_PASSWORD=password \
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
                    -e DATABASE_URL=${DB_URL} \
                    -e JWT_EXPIRATION_TIME=${JWT_EXPIRATION_TIME} \
                    -e JWT_SECRET_KEY=${JWT_SECRET_KEY} \
                    -e SPRING_MAIL_PASSWORD=${SPRING_MAIL_PASSWORD} \
                    -e SPRING_MAIL_USERNAME=${SPRING_MAIL_USERNAME} \
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
