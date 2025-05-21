// Jenkinsfile (Declarativa)
pipeline {
    agent any
    tools {
        maven 'maven3' // Asegúrate de que 'M3_HOME' esté configurado en Jenkins Global Tool Configuration
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
                sh 'java -jar target/WebApp 2.0-SNAPSHOT.jar'
            }
        }
    }
    post {
        // Combinamos los bloques 'always' para evitar duplicados.
        // Las acciones en 'always' se ejecutan siempre, sin importar el resultado del pipeline.
        always {
            echo 'Pipeline finalizado.'
            // Publica los resultados de las pruebas unitarias.
            // Requiere el plugin 'JUnit Plugin' instalado en Jenkins.
            // Busca los archivos XML generados por Maven Surefire Plugin en el espacio de trabajo.
            // El patrón '**/*.xml' busca cualquier archivo XML en cualquier subdirectorio.
            // Para Maven, los reportes están en 'target/surefire-reports/'.
            junit '**/target/surefire-reports/*.xml'
        }
        // Estas condiciones se ejecutan solo si el pipeline tiene el estado correspondiente.
        success {
            echo '¡Pipeline ejecutado con éxito!'
        }
        failure {
            echo 'El pipeline falló. Revisa los logs.'
        }
    }
}
