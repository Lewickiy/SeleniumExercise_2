import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.lewickiy.MetroHomePage;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

import static org.junit.Assert.assertEquals;

public class SeleniumMetroTest {
    private WebDriver driver;
    private MetroHomePage metroPage;
    private static final String CITY_SAINTP = "Санкт-Петербург";
    private static final String STATION_SPORTIVNAYA = "Спортивная";
    private static final String STATION_LUBYANKA = "Лубянка";
    private static final String STATION_KRASNOGVARD = "Красногвардейская";

    @Before
    public void setUp() {
        driver = new ChromeDriver();
        driver.get("https://qa-metro.stand-2.praktikum-services.ru/");
        metroPage = new MetroHomePage(driver);
        metroPage.waitForLoadHomePage();

    }

    @Test
    public void checkChooseCityDropdown() { //Проверка выбора города
        metroPage.chooseCity(CITY_SAINTP);
        metroPage.waitForStationVisibility(STATION_SPORTIVNAYA);
    }

    @Test
    public void checkRouteApproxTimeIsDisplayed() { // тест отображения времени маршрута
        metroPage.buildRoute(STATION_LUBYANKA, STATION_KRASNOGVARD);
        assertEquals("≈ 36 мин.", metroPage.getApproximateRouteTime(0));
    }

    @Test
    public void checkRouteStationFromIsCorrect() { // проверь отображение станции «Откуда» в карточке маршрута
        metroPage.buildRoute(STATION_LUBYANKA, STATION_KRASNOGVARD);
        assertEquals(STATION_LUBYANKA, metroPage.getRouteStationFrom());
    }

    @Test
    public void checkRouteStationToIsCorrect() { // проверь отображение станции «Куда» в карточке маршрута
        metroPage.buildRoute(STATION_LUBYANKA, STATION_KRASNOGVARD);
        assertEquals(STATION_KRASNOGVARD, metroPage.getRouteStationTo());
    }

    @After
    public void tearDown() {
        driver.quit();
    }
}
