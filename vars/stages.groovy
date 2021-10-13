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
                   uploadTemplateS3(s3Bucket: "filesbucket-anne")
                }
            } 
            stage('Create Bucket') {                  
                steps {
                    createBucket()
                }
            }
            stage('Upload Files to S3') {                  
                steps {
                    uploadAllFileS3(s3Bucket:"filesbucket-anne")
                }
            }
            /*stage('Upload Sample File to S3') {                  
                steps {
                    uploadCertainFileS3(s3Bucket:"filesbucket-anne", sampleFile: "sampleFile.txt")
                }
            }*/
            stage('Delete a file from S3 bucket') {                  
                steps {
                    deleteFileS3(s3Bucket: "filesbucket-anne", pathName: "Hey.txt")
                }
            }
            stage('Deploy EC2') {                  
                steps {
                    deployToEC2(stackName: "EC2Jenkins-Anne", s3Bucket: "filesbucket-anne")
                }
            }
        }
    }
}
