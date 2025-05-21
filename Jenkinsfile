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

            sh 'javac web-app-java'
            sh 'java HelloWorld'
        }
    }

  }
}