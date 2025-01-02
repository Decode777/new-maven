pipeline {
    agent any

    tools {
        maven 'Maven 3.8.5' // Adjust to match your Jenkins configuration
    }

    environment {
        SONAR_TOKEN = credentials('sonar-token') // Replace with your Jenkins credential ID
    }

    stages {
        stage('Checkout') {
            steps {
                git 'https://github.com/your-repository-link.git' // Replace with your repo link
            }
        }
        stage('Build') {
            steps {
                sh 'mvn clean install'
            }
        }
        stage('Test') {
            steps {
                sh 'mvn test'
            }
        }
        stage('SonarQube Analysis') {
            steps {
                sh '''
                mvn sonar:sonar \
                -Dsonar.projectKey=AutomationProject \
                -Dsonar.host.url=http://localhost:9000 \
                -Dsonar.login=${SONAR_TOKEN}
                '''
            }
        }
    }

    post {
        always {
            junit '**/target/surefire-reports/*.xml'
            archiveArtifacts artifacts: '**/target/site/jacoco/*.html', fingerprint: true
        }
    }
}
