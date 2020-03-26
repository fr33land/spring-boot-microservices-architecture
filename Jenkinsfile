pipeline {
    agent any

    environment {
        modules = getModules()
        repository = "fr33land/spring-boot-microservices-architecture"
    }
    
    stages {
        stage('Maven build') { 
            steps {
                sh 'mvn clean package -Dmaven.test.skip=true -Dactive.profile=stg' 
            }
        }

        stage('Docker publish') {
            agent any
            steps {
                script {
                    modules.each { module ->
                        stage(module){
                            sh "pwd"
                            dir(module) {
                                sh "pwd"
                                def imageName = "$repository:$module-${env.GIT_COMMIT}" 
                                echo "Building docker for service $module with image $imageName"
                                docker.build("$imageName")
                            }
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