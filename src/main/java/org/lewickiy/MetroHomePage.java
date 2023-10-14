package org.lewickiy;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class MetroHomePage {
    private final WebDriver driver;
    private final By selectCityButton = By.className("select_metro__button");
    private final By fieldFrom = By.xpath(".//input[@placeholder='Откуда']");
    private final By fieldTo = By.xpath(".//input[@placeholder='Куда']");
    private final By routeStationFromTo = By.className("route-details-block__terminal-station");

    public MetroHomePage(WebDriver driver) {
        this.driver = driver;
    }

    // метод ожидания загрузки страницы: проверили видимость станции «Театральная»
    public void waitForLoadHomePage() {
        // ждем 8 секунд, пока появится веб-элемент с нужным текстом
        new WebDriverWait(driver, 8)
                .until(ExpectedConditions.visibilityOfElementLocated(By.xpath(".//*[text() = 'Театральная']")));
    }

    public void chooseCity(String cityName) {
        driver.findElement(selectCityButton).click();
        driver.findElement(By.xpath(String.format("//*[text()='%s']", cityName))).click();
    }

    public void setStationFrom(String stationFrom) {
        driver.findElement(fieldFrom).sendKeys(stationFrom, Keys.DOWN, Keys.ENTER);
    }

    public void setStationTo(String stationTo) {
        driver.findElement(fieldTo).sendKeys(stationTo, Keys.DOWN, Keys.ENTER);
    }

    public void waitForLoadRoute() {
        new WebDriverWait(driver, 3)
                .until(ExpectedConditions.visibilityOfElementLocated(By.xpath(".//*[text() = 'Получить ссылку на маршрут']")));
    }

    public void buildRoute(String stationFrom, String stationTo) {
        setStationFrom(stationFrom);
        setStationTo(stationTo);
        waitForLoadRoute();
    }

    public String getRouteStationFrom() {
        return driver.findElements(routeStationFromTo).get(0).getText();
    }

    public String getRouteStationTo() {
        return driver.findElements(routeStationFromTo).get(1).getText();
    }

    public String getApproximateRouteTime(int routeNumber) {
        return driver.findElements(By.className("route-list-item__time")).get(routeNumber).getText();
    }

    public void waitForStationVisibility(String stationName) {
        new WebDriverWait(driver, 8)
                .until(ExpectedConditions.visibilityOfElementLocated(By.xpath(String.format("//*[text()='%s']", stationName))));
    }
}
