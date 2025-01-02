pipeline {
    agent any

    tools {
        maven 'sonarmaven' // Adjust to match your Jenkins configuration
    }

    environment {
        SONAR_TOKEN = credentials('sonar-token') // Replace with your Jenkins credential ID
        JAVA_HOME = 'C:\\Program Files\\Java\\jdk-17'
        PATH = "${JAVA_HOME}\\bin;${env.PATH}"

    }

    stages {
        stage('Checkout Code') {
            steps {
                checkout scm
            }
        }

        stage('Build with Maven') {
            steps {
                bat 'mvn clean package'
            }
        }

        stage('Test') {
            steps {
                bat 'mvn test'
            }
        }

        stage('SonarQube Analysis') {
            steps {
                bat """
                mvn sonar:sonar ^
                  -Dsonar.projectKey=new-maven ^
                  -Dsonar.java.binaries=target/classes ^
                  -Dsonar.java.test.binaries=target/test-classes ^
                  -Dsonar.host.url=http://localhost:9000 ^
                  -Dsonar.login=%SONAR_TOKEN%
                """
            }
        }
        
    }

    post {
        success {
        echo 'Pipeline completed successfully!'
        }

        failure {
        echo 'Pipeline failed. Check logs for details.'
        }

        always {
            junit '**/target/surefire-reports/*.xml'
            archiveArtifacts artifacts: '**/target/site/jacoco/*.html', fingerprint: true
        }
    }
}
