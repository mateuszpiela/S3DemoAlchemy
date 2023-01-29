package eu.mateuszpiela.s3sample.alchemy;

import java.util.Random;

public class AlchemyGenerator {
	private String ingredients[] = {
			"Allspice",
			"Arenaria",
			"Bear fat",
			"Blowball",
			"Empty bottle",
			"Mandrake root",
			"Phosphorus",
			"Wolfsbane",
			"Verbena",
			"Sulfur",
			"Saltpeter",
			"Moleyarrow"
	};
	
	
	public String genrateRecipe() {
		Random rand = new Random();
		String output = "Recipe: \n";
		
		Integer numberOfIngredients = rand.nextInt(8);
		
		if(numberOfIngredients < 3) {
			numberOfIngredients = 3;
		}
		
		for(Integer x = 1; x < numberOfIngredients; x++) {
			output += x.toString() + ". ";
			output += ingredients[rand.nextInt(ingredients.length)];
			output += "\n";
		}
		
		output += "\n\n\nIt's generated only for fun and to test the S3 SDK :)";
		return output;
	}
}
