pipeline {
	agent {label 'master'} //where we are executing this script
		
		stages {
			stage('Cleanws') {
				steps {
					 cleanWs()
					}
			}
			
			stage('check for the update') {
				steps {
					
					sshagent(['docker_host']) {
						 
						 sh 'ssh -o StrictHostKeyChecking=no ${hostname} sudo yum update -y'
						 
					}		  
				}
			}
			
			stage('Install The Docker packages') {
				steps {
					
					sshagent(['docker_host']) {
						 
						 sh 'ssh -o StrictHostKeyChecking=no ${hostname} sudo yum install docker -y'
						 sh 'ssh -o StrictHostKeyChecking=no ${hostname} sudo systemctl start docker'
						 
					}		  
				}
			}
			
			stage('Docker Pull basic images') {
				steps {
					
					sshagent(['docker_host']) {
						 
						 sh 'ssh -o StrictHostKeyChecking=no ${hostname} sudo docker pull centos'
						 sh 'ssh -o StrictHostKeyChecking=no ${hostname} sudo docker pull ubuntu'
						 sh 'ssh -o StrictHostKeyChecking=no ${hostname} sudo docker pull nginx'
						 
					}		  
				}
			}
			
			
			stage('Docker images') {
				steps {
					
					sshagent(['docker_host']) {
						 
						 sh 'ssh -o StrictHostKeyChecking=no ${hostname} sudo docker images'
						
						 
					}		  
				}
			}
		}
}		
