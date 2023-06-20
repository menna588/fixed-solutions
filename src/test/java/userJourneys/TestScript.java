package userJourneys;

import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import com.shaft.driver.SHAFT;

import Helpers.Generators;
import pages.BasePage;

public class TestScript {

	SHAFT.GUI.WebDriver driver;
	public String Random ;
	public String RecipeTitle ;
	public String RecipeEditTitle ;
	public String Ingredients;
	public String EditIngredients;

	@Test(description = "TC001: Read added Receipe data in the table" , priority=1)
	public void ReadReceipeTest() {
		new BasePage(driver).AssertonPumpkinPie().AssertonSpaghetti()
		.AssertonOnionPie();
	}
	
	@Test(description = "TC002: Add Receipe data in the table" , priority=2)
	public void AddReceipeTest() {
		new BasePage(driver).clickOnAddRecipe().addReceipe(RecipeTitle,Ingredients)
		.Asserton_AddedRecipe(RecipeTitle);
	}
	
	@Test(description = "TC003: Edit Receipe data in the table" , priority=3)
	public void EditReceipeTest() {
		RecipeEditTitle = "Edit Recipe Automation "+ Random;
		EditIngredients = "Edit Recipe Ingredients "+ Random;
		new BasePage(driver).Selectitem(RecipeTitle).ClickOnEditButton().EditReceipe(RecipeEditTitle, EditIngredients)
		.Asserton_EditRecipe(RecipeEditTitle)
		.Selectitem(RecipeEditTitle)
		.Asserton_EditRecipeIngredients(EditIngredients);
	}
	
	@Test(description = "TC004: Delete Receipe data in the table" , priority=4)
	public void DeleteReceipeTest() {
		new BasePage(driver)
		.ClickOnDeleteButton().Asserton_DeletedRecipe(RecipeEditTitle);
	}


	@BeforeClass
	public void beforeClass() {
		driver = new SHAFT.GUI.WebDriver();
		new BasePage(driver).NavigateMethod().switchtoiFrame();
		//Generate Random 5 characters 
	    Random =  Generators.generateRandomWord(5);
		RecipeTitle = "Recipe Automation "+ Random;
		Ingredients = "Ingredients Automation "+ Random;

	}

//	@AfterMethod
//	public void afterMethod() {
//		driver.browser().refreshCurrentPage();
//	}

	@AfterClass
	public void afterClass() {
		driver.quit();
	}
}
