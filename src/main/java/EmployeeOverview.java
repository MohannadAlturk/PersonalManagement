import javafx.geometry.Pos;
import javafx.scene.control.*;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.VBox;

public class EmployeeOverview {
    public void showOverview(BorderPane mainLayout) {
        VBox mainVBox = new VBox();
        ScrollPane scrollPane = new ScrollPane();
        VBox vbox = new VBox(10);

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

        mainVBox.getChildren().add(scrollPane);

        mainLayout.setCenter(mainVBox);
    }
}
