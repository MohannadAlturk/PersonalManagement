import javafx.geometry.HPos;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.*;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.ColumnConstraints;
import javafx.scene.layout.GridPane;

public class UpdateEmployee {

    private TextField IdInput, firstnameInput, lastnameInput, dobInput, emailInput, telefonnummerInput, positionInput;
    private CheckBox marriedInput;

    public void showUpdateEmployee(BorderPane mainLayout) {
        GridPane grid = createGridPane();

        Label headerLabel = new Label("Mitarbeiter ändern");
        headerLabel.setStyle("-fx-font-size: 60px; -fx-font-weight: bold;");
        GridPane.setConstraints(headerLabel, 0, 0, 2, 1);

        IdInput = createTextField(1);
        Label IdLabel = createLabel("Mitarbeiter ID eingeben:", 1);

        firstnameInput = createTextField(2);
        Label firstnameLabel = createLabel("Vorname:", 2);

        lastnameInput = createTextField(3);
        Label lastnameLabel = createLabel("Nachname:", 3);

        dobInput = createTextField(4);
        Label dobLabel = createLabel("Geburtsdatum:", 4);

        marriedInput = new CheckBox();
        GridPane.setConstraints(marriedInput, 1, 5);
        Label marriedLabel = createLabel("Verheiratet:", 5);

        emailInput = createTextField(6);
        Label emailLabel = createLabel("Email:", 6);

        telefonnummerInput = createTextField(7);
        Label telefonnummerLabel = createLabel("Telefonnummer:",  7);

        positionInput = createTextField(8);
        Label positionLabel = createLabel("Position:", 8);
        Button searchButton = createButton("Suchen", 9);
        Button submitButton = createButton("Daten speichern", 10);

        setButtonActions(searchButton, submitButton);
        mainLayout.setCenter(grid);

        grid.getChildren().addAll(headerLabel, IdLabel, IdInput, firstnameLabel, firstnameInput,
                lastnameLabel, lastnameInput, dobLabel, dobInput, marriedLabel, marriedInput,
                emailLabel, emailInput, telefonnummerLabel, telefonnummerInput,
                positionLabel, positionInput, searchButton, submitButton);
    }

    private GridPane createGridPane() {
        GridPane grid = new GridPane();
        grid.setPadding(new Insets(10, 10, 10, 10));
        grid.setVgap(8);
        grid.setHgap(10);
        grid.setAlignment(Pos.CENTER);
        grid.setMaxWidth(600);
        grid.setMaxHeight(450);

        ColumnConstraints col1 = new ColumnConstraints();
        col1.setHalignment(HPos.RIGHT);
        ColumnConstraints col2 = new ColumnConstraints();
        col2.setHalignment(HPos.LEFT);
        grid.getColumnConstraints().addAll(col1, col2);

        return grid;
    }

    private Label createLabel(String text, int rowIndex) {
        Label label = new Label(text);
        GridPane.setConstraints(label, 0, rowIndex);
        return label;
    }

    private TextField createTextField(int rowIndex) {
        TextField textField = new TextField();
        GridPane.setConstraints(textField, 1, rowIndex);
        return textField;
    }

    private Button createButton(String text, int rowIndex) {
        Button button = new Button(text);
        GridPane.setConstraints(button, 1, rowIndex);
        return button;
    }

    private void setButtonActions(Button searchButton, Button submitButton) {
        submitButton.setOnAction(e -> saveEmployeeData());
        searchButton.setOnAction(e -> searchEmployeeData());
    }

    private void saveEmployeeData() {
        String employeeID = IdInput.getText();
        if (employeeID.isEmpty() || firstnameInput.getText().isEmpty() || lastnameInput.getText().isEmpty() ||
                dobInput.getText().isEmpty() || emailInput.getText().isEmpty() || telefonnummerInput.getText().isEmpty() ||
                positionInput.getText().isEmpty()) {
            Alert alert = new Alert(Alert.AlertType.ERROR, "Alle Felder müssen ausgefüllt sein!", ButtonType.OK);
            alert.showAndWait();
            return;
        }
        EmployeeForm.refreshMitarbeiterList();
        EmployeeForm.mitarbeiterList.stream()
                .filter(mitarbeiter -> employeeID.equals(mitarbeiter.getId()))
                .findFirst()
                .ifPresent(mitarbeiter -> {
                    mitarbeiter.setVorname(firstnameInput.getText());
                    mitarbeiter.setNachname(lastnameInput.getText());
                    mitarbeiter.setGeburtsdatum(dobInput.getText());
                    mitarbeiter.setVerheiratet(marriedInput.isSelected());
                    mitarbeiter.setEmail(emailInput.getText());
                    mitarbeiter.setTelefonnummer(telefonnummerInput.getText());
                    mitarbeiter.setPosition(positionInput.getText());
                    EmployeeForm.refreshMitarbeiterJSON();
                    IdInput.clear();
                    firstnameInput.clear();
                    lastnameInput.clear();
                    dobInput.clear();
                    marriedInput.setSelected(false);
                    emailInput.clear();
                    telefonnummerInput.clear();
                    positionInput.clear();
                });
    }

    private void searchEmployeeData() {
        String employeeID = IdInput.getText();
        EmployeeForm.refreshMitarbeiterList();
        EmployeeForm.mitarbeiterList.stream()
                .filter(mitarbeiter -> employeeID.equals(mitarbeiter.getId()))
                .findFirst()
                .ifPresentOrElse(mitarbeiter -> {
                    firstnameInput.setText(mitarbeiter.getVorname());
                    lastnameInput.setText(mitarbeiter.getNachname());
                    dobInput.setText(mitarbeiter.getGeburtsdatum());
                    marriedInput.setSelected(mitarbeiter.getVerheiratet());
                    emailInput.setText(mitarbeiter.getEmail());
                    telefonnummerInput.setText(mitarbeiter.getTelefonnummer());
                    positionInput.setText(mitarbeiter.getPosition());
                }, () -> {
                    Alert alert = new Alert(Alert.AlertType.ERROR, "Mitarbeiter wurde nicht gefunden", ButtonType.OK);
                    alert.showAndWait();
                });
    }
}
