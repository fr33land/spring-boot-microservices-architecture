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

        stage('Docker publish') {
            steps {
                script {
                    modules.each { module ->
                        stage(module){
                            echo "Element: $module"
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