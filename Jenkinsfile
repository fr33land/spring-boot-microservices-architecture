pipeline {
    agent any

    environment {
        modules = getModules()
    }
    
    stages {
        stage('Build') { 
            steps {
                sh 'mvn clean package -Dmaven.test.skip=true -Dactive.profile=stg' 
            }
        }

        stage('Docker publishing') {
            steps {
                script {
                    for(int i=0; i < modules.size(); i++) {
                        stage(modules[i]){
                            echo "Element: $i"
                        }
                    }
                }
            }
        }
    }
}

def getModules() {
    return ['ms-config-server', 'ms-eureka-server', 'ms-oauth-server', 'ms-users-service', 'ms-webapp-service']
}