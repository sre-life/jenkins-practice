// Jenkinsfile (Declarativa)
pipeline {
    agent any
    tools {
        maven 'M3_HOME' // Asegúrate de que 'M3_HOME' esté configurado en Jenkins Global Tool Configuration
    }

    stages {
        stage('Checkout') {
            steps {
                echo 'Clonando el repositorio...'
                git url: 'https://github.com/sre-life/jenkins-practice.git', branch: 'main'
            }
        }
        stage('Build') {
            steps {
                echo 'Compilando el proyecto Java con Maven...'
                sh 'mvn clean install' // Compila y empaqueta el JAR
            }
        }
        stage('Run Tests') {
            steps {
                echo 'Ejecutando pruebas unitarias con Maven...'
                sh 'mvn test' // Ejecuta las pruebas y genera reportes JUnit XML
            }
        }
        stage('Run Application') {
            steps {
                echo 'Ejecutando la aplicación Java...'
                // El nombre del JAR se basa en <artifactId>-<version>.jar de tu pom.xml
                // En este caso: jenkins-practice-app-1.0-SNAPSHOT.jar
                sh 'java -jar target/jenkins-practice-app-1.0-SNAPSHOT.jar'
            }
        }
    }
    post {
        always {
            echo 'Pipeline finalizado.'
        }
        success {
            echo '¡Pipeline ejecutado con éxito!'
        }
        failure {
            echo 'El pipeline falló. Revisa los logs.'
        }
        always { // Publica los resultados de las pruebas incluso si fallan
            junit '**/target/surefire-reports/*.xml'
        }
    }
}