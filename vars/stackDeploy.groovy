def call(Map stageParams = [awsRegion: "us-east-1"]){
  withAWS(region: stageParams.awsRegion, credentials:"cloud_user") {
        awsIdentity()
        cfnCreateChangeSet(
          stack: stageParams.stackName, 
          changeSet:'my-change-set', 
          tags:['Environment='+stageParams.env],
		  params:['VpcId='+stageParams.VpcId, 'PublicSubnet1='+stageParams.PublicSubnet1, 'PublicSubnet2='+stageParams.PublicSubnet2],
          url:'https://'+ stageParams.bucketName + '.s3.amazonaws.com/'+ stageParams.stackFileName
        )
		cfnExecuteChangeSet(
          stack: stageParams.stackName, 
          changeSet:'my-change-set',
	  pollInterval: 1000,
	  timeoutInMinutes: 60
        )
		outputs = cfnDescribe(stack:stageParams.stackName)
		outputs.each { println it }

  }
}
