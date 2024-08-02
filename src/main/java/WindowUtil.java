import javafx.geometry.Rectangle2D;
import javafx.stage.Screen;
import javafx.stage.Stage;

public class WindowUtil {
    public static void setWindowSize(Stage primaryStage){
        Rectangle2D screenBounds = Screen.getPrimary().getVisualBounds();
        primaryStage.setWidth(screenBounds.getWidth() * 0.9);
        primaryStage.setHeight(screenBounds.getHeight() * 0.9);
        primaryStage.setMaxWidth(screenBounds.getWidth());
        primaryStage.setMaxHeight(screenBounds.getHeight());
        primaryStage.centerOnScreen();
    }
}
