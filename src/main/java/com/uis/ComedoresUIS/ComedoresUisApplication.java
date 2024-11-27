package com.uis.ComedoresUIS;

import com.uis.ComedoresUIS.persistence.models.Role;
import com.uis.ComedoresUIS.persistence.models.admins.Administrator;
import com.uis.ComedoresUIS.persistence.models.menus.FoodCategory;
import com.uis.ComedoresUIS.persistence.models.menus.Ingredient;
import com.uis.ComedoresUIS.persistence.models.menus.TypeMeal;
import com.uis.ComedoresUIS.persistence.models.menus.enums.FoodCategoryEnum;
import com.uis.ComedoresUIS.persistence.models.menus.enums.TypeMealEnum;
import com.uis.ComedoresUIS.persistence.models.students.AccessToService;
import com.uis.ComedoresUIS.persistence.models.students.Student;
import com.uis.ComedoresUIS.persistence.repositories.admins.AdministratorRepository;
import com.uis.ComedoresUIS.persistence.repositories.menus.FoodCategoryRepository;
import com.uis.ComedoresUIS.persistence.repositories.menus.IngredientRepository;
import com.uis.ComedoresUIS.persistence.repositories.menus.TypeMealRepository;
import com.uis.ComedoresUIS.persistence.repositories.students.AccessToServiceRepository;
import com.uis.ComedoresUIS.persistence.repositories.students.StudentRepository;
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
						   AccessToServiceRepository accessRepository,
						   AdministratorRepository adminRepository,
						   TypeMealRepository typeMealRepository) {
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

			//Create Admin
			Administrator admin = Administrator.builder()
					.firstname("Jorge")
					.lastname("Nitales")
					.codeAdmin("2215631")
					.password("$2a$12$mJauJZIuYiS0oBqtpZcWnuRKDBps2Ikv6bf3m64WDAwtPIUHB6RZS") //123
					.superUser(false)
					.role(Role.ADMIN)
					.build();

			adminRepository.save(admin);

			//Create Type Meal
			TypeMeal breakfast = TypeMeal.builder()
					.name(TypeMealEnum.DESAYUNO)
					.build();

			TypeMeal lunch = TypeMeal.builder()
					.name(TypeMealEnum.ALMUERZO)
					.build();

			TypeMeal dinner = TypeMeal.builder()
					.name(TypeMealEnum.CENA)
					.build();

			typeMealRepository.saveAll(List.of(breakfast, lunch, dinner));
		};

	}

}
