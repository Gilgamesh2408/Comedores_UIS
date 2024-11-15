package com.uis.ComedoresUIS;

import com.uis.ComedoresUIS.models.menus.FoodCategory;
import com.uis.ComedoresUIS.models.menus.Ingredient;
import com.uis.ComedoresUIS.models.menus.Meal;
import com.uis.ComedoresUIS.models.menus.enums.FoodCategoryEnum;
import com.uis.ComedoresUIS.repositories.menus.FoodCategoryRepository;
import com.uis.ComedoresUIS.repositories.menus.IngredientRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import java.util.List;

@SpringBootApplication
public class ComedoresUisApplication {

	public static void main(String[] args) {
		SpringApplication.run(ComedoresUisApplication.class, args);
	}

	@Bean
	CommandLineRunner init(IngredientRepository ingredientRepository,
						   FoodCategoryRepository foodCategoryRepository) {
		return args -> {

			FoodCategory grants = new FoodCategory();
			grants.setName(FoodCategoryEnum.GRANOS_Y_DERIVADOS);

			FoodCategory proteins = new FoodCategory();
			proteins.setName(FoodCategoryEnum.PROTEINA_ANIMAL);

			foodCategoryRepository.saveAll(List.of(grants, proteins));

			Ingredient ingredient = new Ingredient();
			ingredient.setName("Arroz");
			ingredient.setCalories(1.3F);
			ingredient.setCarbohydrates(0.28F);
			ingredient.setProteins(0.027F);
			ingredient.setFats(0.003F);
			ingredient.setFoodCategory(grants);

			Ingredient ingredient1 = new Ingredient();
			ingredient1.setName("Pollo");
			ingredient1.setCalories(1.95F);
			ingredient1.setCarbohydrates(0F);
			ingredient1.setProteins(0.3F);
			ingredient1.setFats(0.0772F);
			ingredient1.setFoodCategory(proteins);

			ingredientRepository.saveAll(List.of(ingredient1, ingredient));
		};
	}

}
