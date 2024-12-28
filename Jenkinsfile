pipeline{
    agent any
    tools{
        maven "maven"
    }
    stages{
        stage("SCM checkout"){
            steps{
                      checkout scmGit(branches: [[name: '*/main']], extensions: [], userRemoteConfigs: [[url: 'https://github.com/yash938/Spring-Boot-JWT-Authentication.git']])
            }
        }

        stage("Build Process"){
            steps{
                script{
                    bat 'mvn clean install'
                }
            }
        }

        stage("Deploy to Container"){
            steps{
                deploy adapters: [tomcat9(credentialsId: 'tomcat-pwd2', path: '', url: 'http://localhost:9090/')], contextPath: 'jenkinsCICD', war: '**/*.war'
            }
        }
    }
    post{
        always{
            emailext attachLog: true, body: '''<html>
<body>
       <p> Build status : ${BUILD_STATUS} </p>
       <p> Build number : ${BUILD_NUMBER} </p>
        <p> Check the <a href="${BUILD_URL}"> console output </a></p>
</body>
</html>''', mimeType: 'text/html', replyTo: 'sharma.yash0116@gmail.com', subject: 'Pipeline Status : ${BUILD_NUMBER}', to: 'sharma.yash0116@gmail.com'
        }
    }
}