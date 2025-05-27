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

         stage('Build Docker Image') {
             steps {
             echo 'Construyendo la imagen Docker...'
             // *** CORRECCIÓN CRÍTICA AQUÍ: El nombre de la imagen debe ser 'francistv/webapp:1.0' ***
             sh 'docker build -t francistv/webapp:1.0 .'
             echo 'Imagen Docker construida: francistv/webapp:1.0'
             // Las líneas de push comentadas aquí son redundantes, ya que tienes una etapa 'Push Docker Image to Docker Hub' separada
         }
       }

        stage('Stop and Remove Old Container') {
        steps {
            script {
                echo 'Deteniendo y eliminando el contenedor antiguo si existe...'
                // Verificar si el contenedor 'myapp' está corriendo o existe (detenido)
                def containerExists = sh(script: "docker ps -a --format '{{.Names}}' | grep -q myapp", returnStatus: true)

                if (containerExists == 0) { // grep -q devuelve 0 si encuentra el nombre
                    echo 'Contenedor antiguo "myapp" encontrado. Deteniendo y eliminando...'
                    sh 'docker stop myapp || true' // '|| true' para que no falle si ya está detenido
                    sh 'docker rm myapp || true'   // '|| true' para que no falle si ya no existe después del stop
                } else {
                    echo 'Contenedor antiguo "myapp" no encontrado. Continuando...'
                }
            }
        }
    }

        stage('Docker run') {
            steps {
              
                echo 'Corriendo applicacion Docker..'
                // Asume que Docker está instalado y configurado en el agente de Jenkins.
                // Usa el nombre del artefacto y la versión de tu pom.xml para el tag.
                // El '.' al final indica que el Dockerfile está en el directorio actual.
                sh 'docker run --name myapp -d sre-life/target/webapp-1.0'
                echo 'Imagen Docker running: sre-life/target/webapp-1.0'
                // Opcional: Si quieres subirla a Docker Hub (necesitarías docker login en Jenkins):
                // sh 'docker push sre-life/jenkins-practice-app:1.0-SNAPSHOT'
                
            }
        }

         stage('Push Docker Image to Docker Hub') {
            steps {
                script {
                    // *** ESTE ES EL CAMBIO CLAVE QUE DEBE ESTAR AQUI ***
                    // Utiliza withDockerRegistry para la autenticación
                    withDockerRegistry(credentialsId: 'DOCKER_HUB_CREDS', url: 'https://index.docker.io/v1/') {
                        echo 'Autenticación con Docker Hub realizada automáticamente.'

                        echo 'Subiendo la imagen a Docker Hub...'
                        // Asegúrate de que este nombre y tag coincidan con el de la etapa 'Build Docker Image'
                        sh "docker push francistv/webapp:1.0:latest"

                        echo 'Desconexión de Docker Hub realizada automáticamente.'
                    }
                }
            }
        }
        stage('Run Application') {
            steps {
                echo 'Ejecutando la aplicación Java...'
                // El nombre del JAR se basa en <artifactId>-<version>.jar de tu pom.xml
                // En este caso: jenkins-practice-app-1.0-SNAPSHOT.jar
                sh 'java -jar target/webapp-1.0.jar'
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
