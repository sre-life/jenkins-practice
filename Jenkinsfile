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
        echo 'Compilando el proyecto...' // Mensaje de compilación
        // Si es un proyecto Java, usarías:
         sh 'javac webapp.java'
        // Si es un proyecto Python simple, no hay paso de "compilación" explícito.
    }
    // Etapa para ejecutar la aplicación.
    stage('Run Application') {
        sh 'java webapp' // Ejecuta el script Python 'hello.py'
        // Si es un proyecto Java, usarías:
        // sh 'java HelloWorld'
    }

     stage('post') {
        echo2 'failed' // Ejecuta el script Python 'hello.py'
        // Si es un proyecto Java, usarías:
        // sh 'java HelloWorld'
    }

    // En la sintaxis Scripted, las acciones post-build (como notificaciones)
    // a menudo se manejan con bloques 'try-catch-finally' o pasos específicos.
    // Por ejemplo:
     try {
    //     // Todas las etapas del pipeline
     } catch (Exception e) {
         echo "El pipeline falló: ${e.getMessage()}"
     } finally {
         echo 'Pipeline finalizado.'
    }
}
