pipeline {
	agent {label 'master'}
	
	stages {
		stage('CLEAN WORKSPACE IN JENKINS SERVER'){
			steps {
				cleanWs()
			} // Steps Completed	
		}  // Stage Completed
	

		stage('CLONE THE SOURCE CODE FROM GIT-HUB'){
			steps {
				echo 'In SCM Stage'
				
				git credentialsId: 'c8300171-d7b3-452e-a384-893f10299ad5', url: 'https://github.com/hariharanjenkin/docker.git',branch: 'main'


			} // Steps Completed
		}  // Stage Completed	


		stage('Restarting Docker Service Status'){
			steps {
				
				sh'''
					systemctl status docker
					systemctl restart docker
				'''
			} // Steps Completed
		}  // Stage Completed

		stage('Verifiying the Dockerfile'){
			steps {
				
				sh'''
					echo 'Docker file verification'
					
					pwd
					ls

					cat Dockerfile
				'''
			} // Steps Completed
		}  // Stage Completed
	}
}
