pipeline {
    agent {
        label 'master'
    }

    environment {
        modules = getModules()
        registry = "fr33land/spring-boot-microservices-architecture"
        registryCredential = 'dockerHub'
    }
    
    stages {
        stage('Maven build') { 
            steps {
                sh 'mvn clean package -Dmaven.test.skip=true' 
            }
        }

        stage('Docker publish') {
            steps {
                script {
                    def dockerBuilds = [:]
                    modules.each { module ->
                        dockerBuilds[module] = {
                            dir(module) {
                                def imageName = "$registry:$module-${env.GIT_COMMIT}" 
                                echo "Building docker image for service $module with image $imageName"
                                def msImg = docker.build("$imageName")
                                docker.withRegistry('', registryCredential ) {
                                    msImg.push("$module-latest")
                                }
                                sh "docker rmi $imageName"
                            }
                        }
                    }
                    parallel dockerBuilds
                }
            }
        }
    }
}

def getModules() {
    return ['ms-config-server', 'ms-eureka-server', 'ms-oauth-server', 'ms-users-service', 'ms-webapp-service']
}