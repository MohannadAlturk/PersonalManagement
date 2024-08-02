import javafx.geometry.Pos;
import javafx.geometry.Rectangle2D;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.Separator;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.VBox;
import javafx.stage.Screen;
import javafx.stage.Stage;
import javafx.scene.control.TextField;


public class EmployeeOverview {
    public void showOverview(Stage primaryStage) {
        ScrollPane scrollPane = new ScrollPane();
        VBox vbox = new VBox(10);
        BackgroundUtil.setBackground(vbox);
        vbox.setAlignment(Pos.CENTER); // Zentriere die VBox-Inhalte
        scrollPane.setContent(vbox);
        scrollPane.setFitToWidth(true);

        EmployeeForm.refreshMitarbeiterList();

        for (Mitarbeiter mitarbeiter : EmployeeForm.mitarbeiterList) {
            GridPane gridPane = new GridPane();
            gridPane.setHgap(10);
            gridPane.setVgap(5);
            gridPane.setAlignment(Pos.CENTER);

            gridPane.add(new Label("MitarbeiterID:"), 0, 0);
            TextField textField = new TextField(mitarbeiter.getId());
            textField.setEditable(false);
            textField.setFocusTraversable(false);
            gridPane.add(textField, 1, 0);

            gridPane.add(new Label("Vorname:"), 0, 1);
            gridPane.add(new Label(mitarbeiter.getVorname()), 1, 1);

            gridPane.add(new Label("Nachname:"), 0, 2);
            gridPane.add(new Label(mitarbeiter.getNachname()), 1, 2);

            gridPane.add(new Label("Geburtsdatum:"), 0, 3);
            gridPane.add(new Label(mitarbeiter.getGeburtsdatum()), 1, 3);

            gridPane.add(new Label("Telefonnummer:"), 0, 4);
            gridPane.add(new Label(mitarbeiter.getTelefonnummer()), 1, 4);

            gridPane.add(new Label("Position:"), 0, 5);
            gridPane.add(new Label(mitarbeiter.getPosition()), 1, 5);

            gridPane.add(new Label("Email:"), 0, 6);
            gridPane.add(new Label(mitarbeiter.getEmail()), 1, 6);

            gridPane.add(new Label("Verheiratet:"), 0, 7);
            String verheiratetText = mitarbeiter.getVerheiratet() ? "Ja" : "Nein";
            gridPane.add(new Label(verheiratetText), 1, 7);

            vbox.getChildren().add(gridPane); // Füge das GridPane zur VBox hinzu
            vbox.getChildren().add(new Separator());
        }

        // Zurück-Button
        Button backButton = new Button("Zurück");
        backButton.setOnAction(e -> {
            Main mainApp = new Main();
            mainApp.start(primaryStage);
        });
        vbox.getChildren().add(backButton); // Füge den Button zur VBox hinzu

        Rectangle2D screenBounds = Screen.getPrimary().getVisualBounds();

        primaryStage.setWidth(screenBounds.getWidth() * 0.9);
        primaryStage.setHeight(screenBounds.getHeight() * 0.9);
        primaryStage.setMaxWidth(screenBounds.getWidth());
        primaryStage.setMaxHeight(screenBounds.getHeight());
        primaryStage.centerOnScreen();

        primaryStage.setScene(new Scene(scrollPane, 400, 300));
        primaryStage.show();
    }
}
