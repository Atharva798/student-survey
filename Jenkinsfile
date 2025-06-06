pipeline {
    agent any

    tools {
        maven 'Maven3.9.9'
    }

    environment {
        DOCKERHUB_CREDENTIALS = credentials('38707636-0ffd-422c-a20b-2d6bb729ce88')
    }

    stages {
        stage('Initialize') {
            steps {
                script {
                    env.BUILD_TIMESTAMP = new Date().format("yyyyMMddHHmmss", TimeZone.getTimeZone('UTC'))
                    echo "Build timestamp: ${env.BUILD_TIMESTAMP}"
                }
            }
        }

        stage('Clone Repository') {
            steps {
                checkout scm
            }
        }

        stage('Build with Maven') {
            steps {
                sh 'mvn clean install'
            }
        }

        stage('Building API Image') {
            steps {
                script {
                    withCredentials([usernamePassword(credentialsId: '38707636-0ffd-422c-a20b-2d6bb729ce88',
                                                  usernameVariable: 'DOCKER_USER',
                                                  passwordVariable: 'DOCKER_PASS')]) {
                        sh """
                            echo \"\$DOCKER_PASS\" | docker login -u \"\$DOCKER_USER\" --password-stdin
                        """
                    }
                    def imageName = "atharvatheurkar/student-survey-app:${env.BUILD_TIMESTAMP}"
                    sh "docker build -t ${imageName} ."
                    env.IMAGE_NAME = imageName
                }
            }
        }

        stage('Pushing Image to DockerHub') {
            steps {
                script {
                    sh "docker push ${env.IMAGE_NAME}"
                }
            }
        }

        stage('Deploying to Rancher') {
            steps {
                script {
                    withCredentials([file(credentialsId: 'kubeconfig', variable: 'KUBECONFIG')]){
                        sh "kubectl set image deployment/deployment container-0=${env.IMAGE_NAME}"
                    }
                }
            }
        }
    }
}
