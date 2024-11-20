package com.uis.ComedoresUIS;

import com.uis.ComedoresUIS.models.Role;
import com.uis.ComedoresUIS.models.menus.FoodCategory;
import com.uis.ComedoresUIS.models.menus.Ingredient;
import com.uis.ComedoresUIS.models.menus.Meal;
import com.uis.ComedoresUIS.models.menus.MealIngredient;
import com.uis.ComedoresUIS.models.menus.enums.FoodCategoryEnum;
import com.uis.ComedoresUIS.models.students.AccessToService;
import com.uis.ComedoresUIS.models.students.Student;
import com.uis.ComedoresUIS.repositories.menus.FoodCategoryRepository;
import com.uis.ComedoresUIS.repositories.menus.IngredientRepository;
import com.uis.ComedoresUIS.repositories.students.AccessToServiceRepository;
import com.uis.ComedoresUIS.repositories.students.StudentRepository;
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
						   StudentRepository studentRepository,
						   FoodCategoryRepository foodCategoryRepository,
						   AccessToServiceRepository accessRepository) {
		return args -> {

			FoodCategory grants = FoodCategory.builder()
					.name(FoodCategoryEnum.GRANOS_Y_DERIVADOS)
					.build();

			FoodCategory proteins = FoodCategory.builder()
					.name(FoodCategoryEnum.PROTEINA_ANIMAL)
					.build();

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

			//Access to service
			AccessToService access = AccessToService.builder()
					.breakfast(false)
					.lunch(false)
					.dinner(true)
					.build();

			accessRepository.save(access);

			//Create student
			Student student = Student.builder()
					.firstname("Mauricio")
					.lastname("Marín")
					.codeStudent("2215634")
					.password("$2a$12$mJauJZIuYiS0oBqtpZcWnuRKDBps2Ikv6bf3m64WDAwtPIUHB6RZS") //123
					.personalEmail("mauricio@gmail.com")
					.institutionalEmail("uis@gmail.com")
					.role(Role.USER)
					.activate(true)
					.access(access)
					.build();

			studentRepository.save(student);
		};

	}

}
