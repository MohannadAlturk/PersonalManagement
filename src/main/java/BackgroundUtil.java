import javafx.scene.image.Image;
import javafx.scene.layout.*;

public class BackgroundUtil {
    public static void setBackground(Pane pane) {
        Image backgroundImage = new Image("file:src/main/java/hintergrund1.png");
        BackgroundImage background = new BackgroundImage(backgroundImage,
                BackgroundRepeat.NO_REPEAT, BackgroundRepeat.NO_REPEAT,
                BackgroundPosition.DEFAULT, BackgroundSize.DEFAULT);
        pane.setBackground(new Background(background));
    }
}
