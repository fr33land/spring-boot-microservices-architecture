pipeline {
    agent any

    environment {
        modules = ['ms-config-server', 'ms-eureka-server', 'ms-oauth-server', 'ms-users-service', 'ms-webapp-service']
    }
    
    stages {
        stage('Build') { 
            steps {
                sh 'mvn clean package -Dmaven.test.skip=true -Dactive.profile=stg' 
            }
        }

        stage('Docker publishing') {
            steps {
                for(int i=0; i < list.size(); i++) {
                    stage(list[i]){
                        echo "Element: $i"
                    }
                }
            }
        }
    }
}