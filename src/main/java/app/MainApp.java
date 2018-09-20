package app;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * @author rumman
 * @since 6/2/18.
 */
public class MainApp {

    private static final Logger logger = LoggerFactory.getLogger(MainApp.class);

    public static void main(String[] args) {

        logger.info("[MainApp::main] started");

        MainApp mainApp = new MainApp();
        mainApp.appRunner();

        logger.info("[MainApp::main] finished");
    }

    public void appRunner() {
        Driver driver = new Driver();
        driver.orderColumnTest();
    }
}