pipeline {
    agent any
    stages {
        stage('Build') {
            steps {
                sh "chmod +x gradlew"
                echo 'Building has been started...'
                sh "./gradlew build"
                echo 'Building executed succesfully!'
            }
        }
        stage('Test') {
            steps {
                echo 'Testing has been started...'
                sh "./gradlew test"
                echo 'Testing executed successfully!'
            }
        }
        stage('Deploy') {
            steps {
                echo 'Deploy has been started...'
                sh "docker push kybernetique/web-project:latest"
                echo 'Deploy executed successfully!'
            }
        }
    }
}