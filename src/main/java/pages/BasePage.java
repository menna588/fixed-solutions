package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.testng.Assert;

import com.shaft.driver.SHAFT;

import io.github.shafthq.shaft.enums.ClipboardAction;
import io.qameta.allure.Step;


public class BasePage {
	private final String url = System.getProperty("baseURL");

    public static By PumpkinPie(){return By.xpath("//a[text()='Pumpkin Pie']");}
    public static By Spaghetti(){return By.xpath("//a[contains(text(),'Spaghetti')]");}
    public static By OnionPie(){return By.xpath("//a[text()='Onion Pie']");}
    public static By AddRecipeButton(){return By.xpath("//button[text()='Add Recipe']");} //button[@id='show']
    //Add Recipe Modal elements
    public static By RecipeName_field(){return By.xpath("//input[@id='title']");}
    public static By Ingredients_field(){return By.xpath("//textarea[@id='ingredients']");}
    public static By AddButton(){return By.xpath("//button[@id='addButton']");}
    public static By CloseButton(){return By.xpath("//button[contains(text(),'Close')]");}
    public static By addedRecipe(String recipeTitle ){return By.xpath("//a[contains(text(),'"+recipeTitle+"')]");}
    public static By EditButton(){return By.xpath("//button[@id='btn-edit3']");}
    public static By Ingredients_view(String EditIngredients){return By.xpath("//li[contains(text(),'"+EditIngredients+"')]");}
    public static By DeleteButton(){return By.xpath("(//button[@class='btn btn-danger'])[5]");}
    //Login Page elements
    public static By LoginHeader(){return By.xpath("//a[@data-test-id='login-button']");}
    public static By Emailfield(){return By.id("login-email-field");}
    public static By passwordfield(){return By.id("login-password");}
    public static By Loginbtn(){return By.id("log-in-button");}   
    //View screen elements
    public static By ViewButton(){return By.xpath("//button[@data-test-id='view-switcher-button']");}    
    public static By FullView(){return By.xpath("(//a[@id='full-link'] )[1]");}
    public static By RightView(){return By.xpath("//button[@data-layout-type='right']");}
    public static By iframe(){return By.xpath("//iframe[@src='https://cdpn.io/SedatUygur/fullpage/jWgJdv?anon=true&view=fullpage']");}


    private SHAFT.GUI.WebDriver driver;

    public BasePage(SHAFT.GUI.WebDriver driver) {
        this.driver = driver;
    }
    
    @Step("Navigate to the target URL.")
    public BasePage NavigateMethod(){
    	driver.browser().navigateToURL(url);
    	return this;
    }
    
    @Step("Switch to the iframe")
    public BasePage switchtoiFrame(){  	
       driver.element().waitToBeReady(iframe());
    	driver.browser().element().switchToIframe(iframe());
    	return this;
    }
    
//    @Step
//    public BasePage ValidLogin(){
//    	driver.element().click(LoginHeader()).waitToBeReady(Emailfield());
//    	driver.element().type(Emailfield(), "mennaashraf588@gmail.com")
//    	.click(passwordfield()).type(passwordfield(), "Qwerty_0123456").click(Loginbtn());
//    	return this;
//    }
    
    @Step("Click on Pumpkin Pie")
    public BasePage clickOnPumpkinPie(){
        driver.element().waitToBeReady(PumpkinPie()).click(PumpkinPie());
        return this;
    }
   
    @Step("Select item from the table")
    public BasePage Selectitem(String recipeTitle){
        driver.element().click(addedRecipe(recipeTitle));
        return this;
    }
    
    @Step("Click on Spaghetti")
    public BasePage clickOnSpaghetti(){
        driver.element().waitToBeReady(Spaghetti()).click(Spaghetti());
        return this;
    }
    
    @Step("Click on Onion Pie")
    public BasePage clickOnOnionPie(){
        driver.element().waitToBeReady(OnionPie()).click(OnionPie());
        return this;
    }
    
    @Step("Click on add Recipe button")
    public BasePage clickOnAddRecipe(){
        driver.element().scrollToElement(AddRecipeButton()).waitToBeReady(AddRecipeButton(), true).click(AddRecipeButton());
        return this;
    }
    
    @Step("Assert on Pumpkin Pie is Displayed.")
    public BasePage AssertonPumpkinPie(){
        driver.assertThat().element(PumpkinPie()).exists().withCustomReportMessage("The PumpkinPie is Displayed").perform();
        return this;
    }
    
    @Step("Assert on Spaghetti is Displayed.")
    public BasePage AssertonSpaghetti(){
        driver.assertThat().element(Spaghetti()).isVisible().withCustomReportMessage("The Spaghetti is Displayed").perform();
        return this;
    }
   
    @Step("Assert on Onion Pie is Displayed.")
    public BasePage AssertonOnionPie(){ 	
       driver.assertThat().element(OnionPie()).isVisible().withCustomReportMessage("The Onion Pie is Displayed").perform();
        return this;
    }
    
    @Step("Change the view to right")
    public BasePage ChangeView(){
    driver.element().click(ViewButton()).waitToBeReady(RightView()).click(RightView()).click(AddRecipeButton());
    return this;
    }
    
    @Step("Add Receipe")
    public BasePage addReceipe(String recipeTitle , String Ingredients){
    driver.element().click(RecipeName_field()).type(RecipeName_field(), recipeTitle)
    .click(Ingredients_field()).type(Ingredients_field(), Ingredients)
    .click(AddButton());   
    return this;
    }
    
    @Step("Edit Receipe")
    public BasePage EditReceipe(String recipeEditTitle , String EditIngredients){
    	driver.element().click(RecipeName_field())
    	                 .clipboardActions(RecipeName_field(), ClipboardAction.SELECT_ALL)
    	                .clipboardActions(RecipeName_field(), ClipboardAction.CUT)
                        .type(RecipeName_field(), recipeEditTitle)
                        .click(Ingredients_field())
                        .clipboardActions(Ingredients_field(), ClipboardAction.SELECT_ALL)
    	                .clipboardActions(Ingredients_field(), ClipboardAction.CUT)
    	                .type(Ingredients_field(), EditIngredients)
    .click(AddButton());   
    return this;
    }
    
    @Step("Assert on added Recipe is Displayed.")
    public BasePage Asserton_AddedRecipe(String recipeTitle){ 	
       driver.assertThat().element(addedRecipe(recipeTitle)).isVisible().withCustomReportMessage("The added Recipe is Displayed").perform();
        return this;
    }
    
    @Step("Assert on Edit Recipe is Displayed.")
    public BasePage Asserton_EditRecipe(String recipeEditTitle){ 	
       driver.assertThat().element(addedRecipe(recipeEditTitle)).isVisible().withCustomReportMessage("The added Recipe is Edited successfully").perform();
        return this;
    }
    
    @Step("Assert on Delete Recipe is NOT Displayed.")
    public BasePage Asserton_DeletedRecipe(String recipeEditTitle){ 	
       driver.assertThat().element(addedRecipe(recipeEditTitle)).doesNotExist().withCustomReportMessage("The added Recipe is deleted successfully").perform();
        return this;
    }
    
    @Step("Assert on Edit Recipe Ingredients is Displayed.")
    public BasePage Asserton_EditRecipeIngredients(String EditIngredients){ 
    	
    	driver.element().waitToBeReady(Ingredients_view(EditIngredients));
    	driver.assertThat().element(Ingredients_view(EditIngredients)).text().equalsIgnoringCaseSensitivity(EditIngredients).perform();
        return this;
    }
    
    @Step("Click on Edit button")
    public BasePage ClickOnEditButton(){ 	
     driver.element().click(EditButton());
     return this;
    }
    
    @Step("Click on delete button")
    public BasePage ClickOnDeleteButton(){ 	
     driver.element().click(DeleteButton());
     return this;
    }
}
