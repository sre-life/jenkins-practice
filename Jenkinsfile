pipeline {
  agent any
 
  stages {

    stage('checkout') {

      steps {

            git url: 'https://github.com/sre-life/jenkins-practice.git', branch: "main"
        }
    }

    stage('Build Java app') {

      steps {

            sh 'javac webapp.java'
            
        }
    }

    stage('Post') {

      steps {

            sh 'java webapp'
            
        }
    }

  }
}