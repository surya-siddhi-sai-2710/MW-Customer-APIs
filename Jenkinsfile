pipeline{
    tools{
        maven "mvn"
    }
    agent any{
        
    }

    environment{

    }
    stages{
        stage('check source code'){
            steps{
                git branch: 'main',  url: 'https://github.com/surya-siddhi-sai-2710/MW-Customer-APIs.git'
            }
        }
    }

    stage('Build jar') {
      steps {
        script {
            echo "Building jar"
          
          sh 'mvn clean -install'

        }
      }
    }
}
