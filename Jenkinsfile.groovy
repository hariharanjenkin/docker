pipeline {
	agent {label 'master'} //where we are executing this script
		
		stages {
			stage('Cleanws') {
				steps {
					 cleanWs()
					}
			}
		
			
			stage('Clone Your Source Code from GitHub') {
				steps {
					
					sshagent(['docker_login_repeat']) {
						 
						 sh 'ssh -o StrictHostKeyChecking=no ${hostname} sudo apt-get install git -y'
						 sh 'ssh -o StrictHostKeyChecking=no ${hostname} sudo git clone https://github.com/hariharanjenkin/docker.git'
						 
				
					}		  
				}
			}
			
			
			stage('Local Profile Creation') {
				steps {
					
					sshagent(['docker_login_repeat']) {
						 
						 
						 sh 'ssh -o StrictHostKeyChecking=no ${hostname} sudo su'
						 sh 'ssh -o StrictHostKeyChecking=no ${hostname} sudo aws eks update-kubeconfig --name hr-cluster --region=eu-central-1'
						 
					}		  
				}
			}
			
			
			stage('Trigger the Deployment POD') {
				steps {
					
					sshagent(['docker_login_repeat']) {
						 
						 
						 sh 'ssh -o StrictHostKeyChecking=no ${hostname} sudo kubectl apply -f /home/ubuntu/docker/K8S_Deployments/deployment.yml'
						 
					}		  
				}
			}
			
			
			
			stage('Build the Services') {
				steps {
					
					sshagent(['docker_login_repeat']) {
						 
						 
						 sh 'ssh -o StrictHostKeyChecking=no ${hostname} sudo kubectl apply -f /home/ubuntu/docker/K8S_Deployments/service.yml'
						 
					}		  
				}
			}
			
		}
}		
