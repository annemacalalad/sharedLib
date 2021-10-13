def call(String s3Bucket = 'filesbucket-anne', Map stageParams = [awsRegion: "us-east-1", s3Bucket: "filesbucket-anne"]) {
    withAWS(region: stageParams.awsRegion, credentials:"${AWS_CRED}") {
        awsIdentity()
        s3Upload( 
            includePathPattern: "*.yml",
            bucket: "${s3Bucket}"
        )
    }
}
