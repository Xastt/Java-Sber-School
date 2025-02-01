package ru.xast.sbertasks.task17;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.stereotype.Component;
import ru.xast.sbertasks.task17.config.SpringConfig;
import ru.xast.sbertasks.task17.models.Ingredient;
import ru.xast.sbertasks.task17.models.Recipe;
import ru.xast.sbertasks.task17.services.IngredientsService;
import ru.xast.sbertasks.task17.services.RecipeService;

import java.util.Scanner;

@Component
public class RunApp {

    private final RecipeService recipeService;
    private final IngredientsService ingredientsService;

    @Autowired
    public RunApp(RecipeService recipeService, IngredientsService ingredientsService) {
        this.recipeService = recipeService;
        this.ingredientsService = ingredientsService;
    }

    public void run() {
        Scanner scanner = new Scanner(System.in);
        while (true) {
            System.out.println("Выберите действие:");
            System.out.println("1. Добавить рецепт");
            System.out.println("2. Найти рецепт");
            System.out.println("3. Удалить рецепт");
            System.out.println("4. Выход");
            String choice = scanner.nextLine();

            switch (choice) {
                case "1":
                    addRecipeIngredient(scanner);
                    break;
                case "2":
                    findRecipe(scanner);
                    break;
                case "3":
                    deleteRecipe(scanner);
                    break;
                case "4":
                    System.out.println("Выход...");
                    return;
                default:
                    System.out.println("Некорректный выбор, попробуйте снова.");
            }
        }
    }

    private void addRecipeIngredient(Scanner scanner) {
        System.out.print("Введите имя рецепта: ");
        String recipeName = scanner.nextLine();
        Recipe recipe = new Recipe(recipeName);
        recipeService.save(recipe);
        System.out.println("Рецепт добавлен: " + recipe.getName());
        System.out.println("Теперь перейдем к добавлению ингредиентов.");
        System.out.println("Введите кол-во ингредиентов: ");
        int numbers = scanner.nextInt();
        scanner.nextLine();
        for (int i = 0; i < numbers; i++) {
            System.out.print("Введите имя ингредиента: ");
            String ingredientName = scanner.nextLine();
            System.out.print("Введите количество: ");
            Integer quantity = scanner.nextInt();
            scanner.nextLine();
            Ingredient ingredient = new Ingredient(ingredientName, quantity);
            ingredientsService.save(ingredient);
        }
        System.out.println("Отлично, рецепт добавлен!");
    }

    private void findRecipe(Scanner scanner) {
        System.out.print("Введите название рецепта для поиска: ");
        String name = scanner.nextLine();
        recipeService.printRecipesByName(name);
    }

    private void deleteRecipe(Scanner scanner) {
        System.out.println("Для удаления рецепта нужно получить его ID.\n" +
                "Введите название рецепта, который хотите удалить. ");
        String name = scanner.nextLine();
        recipeService.printRecipesByName(name);
        System.out.println("Теперь скопируйте ID нужного рецепта и вставьте сюда: ");
        String id = scanner.nextLine();
        recipeService.delete(recipeService.findOne(Integer.parseInt(id)));
        ingredientsService.delete(ingredientsService.findOne(Integer.parseInt(id)));
        System.out.println("Рецепт удален.");
    }

    public static void main(String[] args) {
        ApplicationContext context = new AnnotationConfigApplicationContext(SpringConfig.class);
        RunApp app = context.getBean(RunApp.class);
        app.run();
    }
}
