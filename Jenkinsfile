node {
  checkout scm
  env.PATH = "${tool 'M3'}/bin:${env.PATH}"
  def REGISTRY_NAME = 'harbor.hiteamtech.com'
  def PROJECT_NAME = 'uws-service'
  def PROJECT_VERSION = 'v0.5.6'
  
  stage('Package') {
      sh 'mvn clean package -DskipTests docker:build'
  }
  stage('Build Image') {
	dir('target/docker') {
      docker.build("${REGISTRY_NAME}/library/${PROJECT_NAME}:${PROJECT_VERSION}").push()
	}
  }
//  stage('Deploy') {
//    sh 'sshpass -e scp -r -P 6748 $PROJECT_NAME.yaml 00joinu3@192.168.10.12:/home/00joinu3/$PROJECT_NAME/'
//    sh 'sshpass -e ssh -p 6748 00joinu3@192.168.10.12 "kubectl create -f /home/00joinu3/$PROJECT_NAME/$PROJECT_NAME.yaml --record=true"'
//    sh 'sshpass -e ssh -p 6748 00joinu3@192.168.10.12 "rm -rf /home/00joinu3/$PROJECT_NAME"'
//  }

//  stage('Update') {
//    sh 'sshpass -e ssh -p 6748 00joinu3@192.168.10.12 "kubectl set image deployment/vuetemplate  $PROJECT_NAME=$REGISTRY_NAME/library/$PROJECT_NAME:$PROJECT_VERSION"'
//    sh 'sshpass -e ssh -p 6748 00joinu3@192.168.10.12 "rm -rf /home/00joinu3/$PROJECT_NAME"'
//  }

}


