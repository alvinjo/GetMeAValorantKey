package valorant;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import sites.Twitch;

import java.util.Timer;
import java.util.TimerTask;
import java.util.concurrent.TimeUnit;

import static util.Constants.PATH_TO_WEB_DRIVER;
import static util.Constants.URL;

public class SeleniumScrape {

    private final WebDriver driver;
    private Twitch twitch = new Twitch();

    public static void main(String[] args) {
        SeleniumScrape sc = new SeleniumScrape();
        sc.login();
        sc.startValorantKeyDropLoop();
    }

    public SeleniumScrape(){
        System.setProperty("webdriver.gecko.driver", PATH_TO_WEB_DRIVER);
        driver = new FirefoxDriver();
        driver.manage().timeouts().pageLoadTimeout(40, TimeUnit.SECONDS);
        driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
    }

    public void startValorantKeyDropLoop(){
        Timer timer = new Timer();
        TimerTask task = new TimerTask() {
            @Override
            public void run() {
                loadStream();
            }
        };

        timer.scheduleAtFixedRate(task,0, 60*60*1000);
    }

    public void login(){
        driver.get(URL);
        twitch = new Twitch();
        twitch.login(driver);
        try{
            Thread.sleep(30*1000);
        }catch (InterruptedException e) {
            e.printStackTrace();
            driver.close();
            System.exit(0);
        }
    }

    public void loadStream(){
        driver.get(twitch.getStreamUrl(driver));
    }

}
