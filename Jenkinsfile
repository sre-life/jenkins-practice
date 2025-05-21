// Jenkinsfile (Scripted)
// Define un nodo donde se ejecutarán las tareas del pipeline.
node {
    // Etapa para obtener el código fuente del repositorio.
    stage('Checkout') {
        // Clona el repositorio Git.
        // Reemplaza 'tu_usuario' y 'tu_repositorio' con los datos de tu GitHub.
        // Si es privado, añade 'credentialsId: "tu-id-de-credencial-en-jenkins"'
        git url: 'https://github.com/sre-life/jenkins-practice.git', branch: 'main'
    }
    // Etapa para compilar el proyecto.
    stage('Build') {
        echo 'Compilando el proyecto Java con Maven...' // Mensaje de compilación
        // Si es un proyecto Java, usarías:
         sh 'mvn clear install'
        // Si es un proyecto Python simple, no hay paso de "compilación" explícito.
    }
     // Etapa para ejecutar las pruebas unitarias con Maven.
    // Este es el enfoque para el Proyecto 2: Automatización de Pruebas Unitarias.
    stage('Run Test') {
        echo 'Ejecutando pruebas unitarias con Maven...'
        // Ejecuta el comando Maven para ejecutar las pruebas.
        // Maven Surefire Plugin generará reportes JUnit XML en target/surefire-reports/.
        sh 'mvn test'
    }
     // Etapa para ejecutar la aplicación Java (asumiendo que se genera un JAR ejecutable).
    stage('Run Application') {
         echo 'Ejecutando la aplicación Java...'
        // Ejecuta el comando Maven para ejecutar las pruebas.
        // Maven Surefire Plugin generará reportes JUnit XML en target/surefire-reports/.
        sh 'java webapp'
    }

    // En la sintaxis Scripted, las acciones post-build (como notificaciones y publicación de resultados)
    // a menudo se manejan con bloques 'try-catch-finally' o pasos específicos.
    // Para publicar resultados de pruebas, usarías 'junit' después de la etapa de pruebas.
    // Por ejemplo, dentro de un bloque 'post' o 'finally' si usas try-catch:
     try {
    //     // ... todas las etapas ...
     } finally {
    //     // Publica los resultados de las pruebas unitarias.
    //     // Requiere el plugin 'JUnit Plugin' instalado en Jenkins.
    //     // Busca los archivos XML generados por Maven Surefire Plugin.
         junit '**/target/surefire-reports/*.xml'
         echo 'Pipeline finalizado.'
     }
}
