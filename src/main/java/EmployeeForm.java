import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import javafx.geometry.HPos;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.ColumnConstraints;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class EmployeeForm {

    protected static List<Mitarbeiter> mitarbeiterList = new ArrayList<>();
    protected void showEmployeeForm(Stage primaryStage) {
        GridPane grid = new GridPane();
        grid.setPadding(new Insets(10, 10, 10, 10));
        grid.setVgap(8);
        grid.setHgap(10);
        grid.setAlignment(Pos.CENTER);

        ColumnConstraints col1 = new ColumnConstraints();
        col1.setHalignment(HPos.RIGHT);

        ColumnConstraints col2 = new ColumnConstraints();
        col2.setHalignment(HPos.LEFT);

        grid.getColumnConstraints().addAll(col1, col2);

        Label headerLabel = new Label("Neuer Mitarbeiterformular");
        headerLabel.setStyle("-fx-font-size: 60px; -fx-font-weight: bold;");
        GridPane.setConstraints(headerLabel, 0, 0, 2, 1);

        Label firstnameLabel = new Label("Vorname:");
        GridPane.setConstraints(firstnameLabel, 0, 1);
        TextField firstnameInput = new TextField();
        GridPane.setConstraints(firstnameInput, 1, 1);

        Label lastnameLabel = new Label("Nachname:");
        GridPane.setConstraints(lastnameLabel, 0, 2);
        TextField lastnameInput = new TextField();
        GridPane.setConstraints(lastnameInput, 1, 2);

        Label dobLabel = new Label("Geburtsdatum:");
        GridPane.setConstraints(dobLabel, 0, 3);
        TextField dobInput = new TextField();
        GridPane.setConstraints(dobInput, 1, 3);

        Label marriedLabel = new Label("Verheiratet:");
        GridPane.setConstraints(marriedLabel, 0, 4);
        CheckBox marriedInput = new CheckBox();
        GridPane.setConstraints(marriedInput, 1, 4);

        Label emailLabel = new Label("Email:");
        GridPane.setConstraints(emailLabel, 0, 5);
        TextField emailInput = new TextField();
        GridPane.setConstraints(emailInput, 1, 5);

        Label telefonnummerLabel = new Label("Telefonnummer:");
        GridPane.setConstraints(telefonnummerLabel, 0, 6);
        TextField telefonnummerInput = new TextField();
        GridPane.setConstraints(telefonnummerInput, 1, 6);

        Label positionLabel = new Label("Position:");
        GridPane.setConstraints(positionLabel, 0, 7);
        TextField positionInput = new TextField();
        GridPane.setConstraints(positionInput, 1, 7);

        Button submitButton = new Button("Daten speichern");
        GridPane.setConstraints(submitButton, 1, 8);
        Button backButton = new Button("Zurück");
        GridPane.setConstraints(backButton, 1, 9);

        grid.getChildren().addAll(headerLabel,firstnameLabel, firstnameInput,
                lastnameLabel, lastnameInput, dobLabel, dobInput,
                marriedLabel, marriedInput,emailLabel, emailInput, telefonnummerLabel,
                telefonnummerInput, positionLabel, positionInput, submitButton, backButton);

        backButton.setOnAction(e -> {
            Main mainApp = new Main();
            mainApp.start(primaryStage);
        });

        submitButton.setOnAction(e -> {
            String firstname = firstnameInput.getText();
            String lastname = lastnameInput.getText();
            String dob = dobInput.getText();
            boolean married = marriedInput.isSelected();
            String email = emailInput.getText();
            String telefonnummer = telefonnummerInput.getText();
            String position = positionInput.getText();

            refreshMitarbeiterList();

            Mitarbeiter mitarbeiter = new Mitarbeiter(firstname, lastname, dob,
                    married, email, telefonnummer, position);

            mitarbeiterList.add(mitarbeiter);

            refreshMitarbeiterJSON();

            firstnameInput.clear();
            lastnameInput.clear();
            dobInput.clear();
            marriedInput.setSelected(false);
            emailInput.clear();
            telefonnummerInput.clear();
            positionInput.clear();
        });

        BackgroundUtil.setBackground(grid);
        Scene formScene = new Scene(grid);
        try {
            formScene.getStylesheets().add(getClass().getResource("/styles.css").toExternalForm());
        } catch (NullPointerException e) {
            System.out.println("CSS-Datei konnte nicht geladen werden: " + e.getMessage());
        }
        primaryStage.setScene(formScene);
    }

    public static void refreshMitarbeiterList(){
        ObjectMapper objectMapper = new ObjectMapper();
        File file = new File("src/main/resources/mitarbeiter.json");
        if (file.exists()) {
            try {
                mitarbeiterList = objectMapper.readValue(file, new TypeReference<List<Mitarbeiter>>(){});
            } catch (IOException ex) {
                System.out.println("Fehler beim Lesen der bestehenden JSON-Datei: " + ex.getMessage());
            }
        }
    }

    public static void refreshMitarbeiterJSON(){
        ObjectMapper objectMapper = new ObjectMapper();
        File file = new File("src/main/resources/mitarbeiter.json");
        try {
            objectMapper.writeValue(file, mitarbeiterList);
        } catch (IOException ex) {
            throw new RuntimeException("Fehler beim Speichern der JSON-Datei: " + ex.getMessage());
        }
    }
}
