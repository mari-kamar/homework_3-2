import org.openqa.selenium.*;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.Test;
import java.util.List;
import java.util.concurrent.TimeUnit;


public class Example extends BaseTest {

    @Test
    public void exampleTest() {
        Actions actions = new Actions(driver);
        driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);

    // Ввести запрос поиска по двум производителям
        WebElement searchInput = driver.findElement(By.xpath("//*[@id=\"header-search\"]"));
        searchInput.sendKeys("Xiaomi Huawei", Keys.ENTER);
        logger.info("Поиск по телефонам Xiaomi Huawei");

    // Отсортировать по цене
        WebElement sortPrice = driver.findElement(By.xpath(("//div[contains(@data-bem, 'dprice')]")));
        actions.moveToElement(sortPrice).build().perform();
        actions.click(sortPrice).build().perform();
        logger.info("Сортировака по цене");

        List<WebElement> listXiaomi = driver.findElements(By.xpath("//a[contains(@title, 'Xiaomi')]/..//div[contains(@data-bem, 'compare')]"));
        List<WebElement> listHuawei =  driver.findElements(By.xpath("//a[contains(@title, 'Huawei')]/../..//div[contains(@data-bem, 'compare')]"));

    // Добавить в сравнение первый телефон Xiaomi
        WebElement addXiaomi = driver.findElements(By.xpath("//a[contains(@title, 'Xiaomi')]/..//div[contains(@data-bem, 'compare')]")).get(0);
        actions.moveToElement(addXiaomi).build().perform();
        actions.click(addXiaomi).build().perform();
        logger.info("Телефон Xiaomi добавлен к сравнению");

    // Проверить, что отобразилась плашка "Товар добавлен к сравнению"
        new WebDriverWait(driver,70).until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//div[@class=\"popup-informer__content\"]")));
        WebElement notificationXiaomi = driver.findElement(By.xpath("//div[@class=\"popup-informer__content\"]"));
        Assert.assertTrue(driver.findElement(By.xpath("//div[@class=\"popup-informer__content\"]")).isDisplayed());
        Assert.assertNotNull(notificationXiaomi);
        logger.info("Товар Xiaomi добавлен к сравнению");

    // Добавить в сравнение первый телефон Huawei
        WebElement addHuawei = driver.findElements(By.xpath("//a[contains(@title, 'Huawei')]/../..//div[contains(@data-bem, 'compare')]")).get(0);
        actions.moveToElement(addHuawei).build().perform();
        actions.click(addHuawei).build().perform();
        logger.info("Телефон Huawei добавлен к сравнению");

    // Проверить, что отобразилась плашка "Товар добавлен к сравнению"
        new WebDriverWait(driver,70).until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//div[@class=\"popup-informer__content\"]")));
        WebElement notificationHuawei = driver.findElement(By.xpath("//div[@class=\"popup-informer__content\"]"));
        Assert.assertTrue(driver.findElement(By.xpath("//div[@class=\"popup-informer__content\"]")).isDisplayed());
        Assert.assertNotNull(notificationHuawei);
        logger.info("Товар Huawei добавлен к сравнению");

    // Перейти в раздел Сравнение
        WebElement compareLink = driver.findElement(By.xpath("//a[contains(@href, 'compare')]"));
        actions.moveToElement(compareLink).build().perform();
        actions.click(compareLink).build().perform();
        logger.info("Произошел переход на страницу Сравнения");

    // Проверить, что в списке 2 позиции, нажать на опцию "Все характеристики"
        List<WebElement> compareList = driver.findElements(By.xpath("//div[contains(@data-bem, 'n-compare-cell')]"));
        Assert.assertEquals(compareList.size(),2);
        logger.info("В списке 2 телефона");

        WebElement allChar = driver.findElement(By.xpath("//*[contains(text(),'все характеристики')]"));
        actions.moveToElement(allChar).build().perform();
        actions.click(allChar).build().perform();
        logger.info("Нажата ссылка Все характеристики");

    // Проверить, что в списке характристик есть позиция "Операционная система"
        WebElement operationSystem = driver.findElement(By.xpath("//*[contains(text(),'Операционная система')]"));
        Assert.assertNotNull(operationSystem);
        logger.info("Присутствует опция Операционной системы");

    // Нажать на опцию "различающиеся характеристики"
        WebElement differentChar = driver.findElement(By.xpath("//*[contains(text(),'различающиеся характеристики')]"));
        actions.moveToElement(differentChar).build().perform();
        actions.click(differentChar).build().perform();
        logger.info("Нажата ссылка Различающиеся характеристики");

    // Проверить, что позиция "Операционная система" не отображается в списке характеристик
        Assert.assertFalse(driver.findElement(By.xpath("//*[contains(text(),'Операционная система')]")).isDisplayed());
        logger.info("Отустствует опция Операционной системы");
    }
}
