import javafx.scene.image.Image;
import javafx.scene.layout.*;

public class BackgroundUtil {
    public static void setBackground(Pane pane) {
        Image backgroundImage = new Image("file:src/main/resources/backgroundImage.png");
        BackgroundSize backgroundSize = new BackgroundSize(BackgroundSize.AUTO, BackgroundSize.AUTO, false, false, true, true);
        BackgroundImage background = new BackgroundImage(backgroundImage,
                BackgroundRepeat.NO_REPEAT, BackgroundRepeat.NO_REPEAT,
                BackgroundPosition.CENTER, backgroundSize);
        pane.setBackground(new Background(background));
    }
}
