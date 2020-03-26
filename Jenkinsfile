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
                            def imageName = "$repository:$module-${env.GIT_COMMIT}" 
                            echo "Building docker for service $module with image $imageName"
                            sh "docker build -t $imageName $module/Dockerfile ."
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