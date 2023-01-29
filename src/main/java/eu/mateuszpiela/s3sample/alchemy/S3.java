package eu.mateuszpiela.s3sample.alchemy;

import java.io.IOException;

import com.amazonaws.auth.AWSCredentials;
import com.amazonaws.auth.AWSStaticCredentialsProvider;
import com.amazonaws.auth.BasicAWSCredentials;
import com.amazonaws.client.builder.AwsClientBuilder.EndpointConfiguration;
import com.amazonaws.services.s3.AmazonS3;
import com.amazonaws.services.s3.AmazonS3ClientBuilder;
import com.amazonaws.services.s3.model.AmazonS3Exception;

public class S3 {
	private static S3 instance;
	private AmazonS3 s3client;
	
    public static S3 getInstance() {
        if (instance == null) {
            instance = new S3();
        }
        return instance;
    }
	
    public void connectToS3() throws IOException {
    	String accessKey = Properties.getInstance().getConfig("s3.auth.accessKey");
    	String secretKey = Properties.getInstance().getConfig("s3.auth.secretKey");
    	String endpoint = Properties.getInstance().getConfig("s3.endpoint");
    	String region = Properties.getInstance().getConfig("s3.region");
    	
    	
    	AWSCredentials credentials = new BasicAWSCredentials(accessKey, secretKey);
    	if(endpoint.length() > 0) {
	    	s3client = AmazonS3ClientBuilder
	    			.standard()
	    			.withCredentials(new AWSStaticCredentialsProvider(credentials))
	    			.withEndpointConfiguration(new EndpointConfiguration(endpoint, region))
	    			.build();
    	} else {
	    	s3client = AmazonS3ClientBuilder
	    			.standard()
	    			.withCredentials(new AWSStaticCredentialsProvider(credentials))
	    			.withRegion(region)
	    			.build();
		}
    	
    }
    
	public void pushToS3(String bucket,String fileName,String content) throws IOException,AmazonS3Exception {
		if(s3client == null) {
			connectToS3();
		}
		
		s3client.putObject(bucket, fileName, content);
	}
}
