   pipeline 
   {
   environment {
		registry = "28551605/timesheet"
		registryCredential = 'dockerHub'
		dockerImage = '' }
		
         agent any
stages {
      stage("Cloning Project") {
      steps {
      git branch: 'Master', url: 'https://github.com/Halilaw/Timesheet_DevOps.git'
            }}
              stage("Build") {
       steps {
       
       bat "mvn clean install"
             }}

      stage("Unit tests") {
       steps {
       
       bat "mvn test"
             }}
    
	  stage("test statique") {
       steps{
		bat "mvn clean verify sonar:sonar -Dsonar.projectKey=timesheet -Dsonar.host.url=http://localhost:9000 -Dsonar.login=443bd1e56d5ed8471b38c0a9a5040043fc608c92"		
			}}
	  stage ("clean et packaging") {    
       steps {
         bat "mvn clean package "
			}}

      stage("DEPLOY with Nexus") {
      steps {
       bat "mvn deploy"
			}}
		
	  stage('Docker : Build image') {
			steps {
			script {
				dockerImage = docker.build registry}}}
	stage('Docker : Push image') {
			steps { 
			script  {
				docker.withRegistry( '', registryCredential ) {
				dockerImage.push()}
					}
				 }
			}
		}
		post {
        success {
            emailext body: 'build success', subject: 'Jenkins', to: 'wael.halila@gmail.com'
        }
        
        failure {
            emailext body: 'build failure', subject: 'Jenkins', to:'wael.halila@gmail.com'
        }
    }
	}
	