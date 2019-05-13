node('master'){
	stage('checkout'){
	 checkout scm
	}
	stage('configure'){
		sh 'export AWS_ACCESS_KEY_ID=AKIAQ5P4KD6CXV32YPPL';
		sh 'export AWS_SECRET_ACCESS_KEY=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';
		sh 'export AWS_REGION=us-east-1';
		sh 'export AWS_REPOSITORY_URI=063343042437.dkr.ecr.us-east-1.amazonaws.com/cma-demo-ecr';
		sh 'export AWS_IMAGE_NAME=$AWS_REPOSITORY_URI:$BUILD_NUMBER';
		sh 'export AWS_EKS_CLUSTER_NAME=cma-cluster';
		sh 'export KUBERNETES_DEPLOYMENT_NAME=spring-boot-deployment';

		sh 'sudo aws configure set aws_access_key_id AKIAQ5P4KD6CXV32YPPL';
		sh 'sudo aws configure set aws_secret_access_key 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';
		sh 'sudo aws configure set default.region us-east-1'
	}
	stage('build'){
		sh 'mvn package';
		sh 'sudo docker build -f src/main/docker/Dockerfile -t $AWS_IMAGE_NAME .'
	}
	stage("Publish to AWS ECR") {
		sh 'sudo aws configure set aws_access_key_id AKIAQ5P4KD6CXV32YPPL'
		sh 'sudo aws configure set aws_secret_access_key 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'
		sh 'sudo aws configure set default.region us-east-1'
		sh 'eval sudo "$(aws ecr get-login --no-include-email --region us-east-1)"'
		sh 'sudo docker push 063343042437.dkr.ecr.us-east-1.amazonaws.com/cma-demo-ecr:$BUILD_NUMBER'
	}
	stage("Deploy into EKS") {
		# make rolling update into kubernetes
		sh 'kubectl --record deployment.v1.apps/$KUBERNETES_DEPLOYMENT_NAME set image deployment.v1.apps/$KUBERNETES_DEPLOYMENT_NAME spring-boot=$AWS_IMAGE_NAME';
		sh 'kubectl rollout status deployment.v1.apps/$KUBERNETES_DEPLOYMENT_NAME'
	}
}
