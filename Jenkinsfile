// Jenkinsfile (Declarativa)
pipeline {
    agent any
    tools {
        // Asegúrate de que 'M3_HOME' coincida con el 'Name' que le diste en Global Tool Configuration
        maven 'maven3'
    }
    stages {
        stage('Checkout') {
            steps {
                git url: 'https://github.com/sre-life/jenkins-practice.git', branch: 'main'
            }
        }
        stage('Build') {
            steps {
                echo 'Compilando el proyecto Java con Maven...'
                // Ahora 'mvn' será encontrado porque Jenkins lo configuró en el PATH
                sh 'mvn clean install'
            }
        }
        stage('Run Tests') {
            steps {
                echo 'Ejecutando pruebas unitarias con Maven...'
                sh 'mvn test'
            }
        }
        stage('Run Application') {
            steps {
                echo 'Ejecutando la aplicación Java...'
                sh 'java webpp' // Reemplaza con el nombre real de tu JAR
            }
        }
    }
    post {
        always {
            junit '**/target/surefire-reports/*.xml'
            echo 'Pipeline finalizado.'
        }
    }
}