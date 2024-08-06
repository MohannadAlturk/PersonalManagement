import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class EmployeeOverview {
    public void showOverview(Stage primaryStage) {
        VBox mainVBox = new VBox();
        ScrollPane scrollPane = new ScrollPane();
        VBox vbox = new VBox(10);
        BackgroundUtil.setBackground(mainVBox);
        vbox.setAlignment(Pos.CENTER);
        scrollPane.setContent(vbox);

        scrollPane.setFitToWidth(true);
        scrollPane.setFitToHeight(true);

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

            vbox.getChildren().add(gridPane);
            vbox.getChildren().add(new Separator());
        }

        Button backButton = new Button("Zurück");
        backButton.setOnAction(e -> {
            Main mainApp = new Main();
            mainApp.start(primaryStage);
        });
        vbox.getChildren().add(backButton);

        mainVBox.getChildren().add(scrollPane);
        Scene scene = new Scene(mainVBox, 400, 300);
        try {
            scene.getStylesheets().add(getClass().getResource("/styles.css").toExternalForm());
        } catch (NullPointerException e) {
            System.out.println("CSS-Datei konnte nicht geladen werden: " + e.getMessage());
        }
        primaryStage.setScene(scene);
        primaryStage.show();
    }
}
