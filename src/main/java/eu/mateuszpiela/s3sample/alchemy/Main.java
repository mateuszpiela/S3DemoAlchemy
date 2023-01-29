package eu.mateuszpiela.s3sample.alchemy;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class Main {

	public static void main(String[] args) throws IOException {
		System.out.println("S3 Alchemy Demo ---------------------------");		
		String bucket = Properties.getInstance().getConfig("s3.bucket");
		
	    SimpleDateFormat formatter = new SimpleDateFormat("dd-MM-yyyy_HH-mm-ss");  
	    Date dateObj = new Date();
		
		String date = formatter.format(dateObj);
		
		AlchemyGenerator alchemyGenerator = new AlchemyGenerator();
		System.out.println("Generating alchemy recipe.....");
		
		String recipe = alchemyGenerator.genrateRecipe();
		String key = "recipes/recipe-" + date + ".txt";
		
		try {
			System.out.println("Trying to push generated file to S3.......");
			S3.getInstance().pushToS3(bucket, key, recipe);
		}
		catch (Exception e) {
			System.out.println("S3 SDK Crashed=======");
			System.out.println("Message:" + e.getMessage());
			System.out.println();
			e.printStackTrace();
			System.exit(1);
		}
		System.out.println("Check if your bucket: " + bucket + " contains key named:" + key);
	}

}
