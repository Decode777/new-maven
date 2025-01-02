pipeline {
    agent any

    tools {
        maven 'sonarmaven' // Adjust to match your Jenkins configuration
    }

    environment {
        SONAR_TOKEN = credentials('sonar-token') // Replace with your Jenkins credential ID
    }

    stages {
        stage('Checkout') {
            steps {
                git 'https://github.com/Decode777/new-maven.git' // Replace with your repo link
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
                -Dsonar.projectKey=new-maven \
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
