def call(Map stageParams = [awsRegion: "us-east-1"]){
  withAWS(region: stageParams.awsRegion, credentials:"${AWS_CRED}") {
        awsIdentity()
        cfnCreateChangeSet(
          stack:'EC2-Anne', 
          changeSet:'my-change-set', 
          url:'https://filesbucket-anne.s3.amazonaws.com/deploy.yml'
        )
        cfnExecuteChangeSet(
          stack:'EC2-Anne', 
          changeSet:'my-change-set'
        )
  }
}
