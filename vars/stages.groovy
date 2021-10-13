def call() {
    pipeline {
        agent any
        environment {
            AWS_CRED = 'cloud_user'
            AWS_REGION = 'us-east-1'
        }
        stages {
             stage('Upload Templates') {                  
                steps { 
                   uploadTemplateS3(s3Bucket: "filesdarren")
                }
            } 
            stage('Create Bucket') {                  
                steps {
                    createBucket()
                }
            }
            stage('Upload Files to S3') {                  
                steps {
                    uploadAllFileS3(s3Bucket:"sample-bucket-darren")
                }
            }
            /*stage('Upload Certain File to S3') {                  
                steps {
                    uploadCertainFileS3(s3Bucket:"sample-bucket-darren", certainFile: "CertainFileSample.txt")
                }
            }*/
            stage('Delete a file from S3 bucket') {                  
                steps {
                    deleteFileS3(s3Bucket: "sample-bucket-darren", pathName: "HelloWorld.txt")
                }
            }
            stage('Deploy EC2') {                  
                steps {
                    deployToEC2(stackName: "EC2Jenkins-Darren", s3Bucket: "filesdarren")
                }
            }
        }
    }
}
