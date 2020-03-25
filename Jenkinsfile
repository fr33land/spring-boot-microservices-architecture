pipeline {
    agent any

    environment {
        modules = getModules()
        repository = "fr33land/spring-boot-microservices-architecture"
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
                            def imageName = "$repository:$module_${env.BUILD_NUMBER}" 
                            echo "Building docker for service $module with image $imageName"
                            docker.build("$imageName", "-f ./$module/Dockerfile .")
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