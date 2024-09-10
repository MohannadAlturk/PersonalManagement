import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import javafx.geometry.HPos;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.*;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.ColumnConstraints;
import javafx.scene.layout.GridPane;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class EmployeeForm {

    private final TextField firstnameInput = new TextField();
    private final TextField lastnameInput = new TextField();
    private final TextField dobInput = new TextField();
    protected CheckBox marriedInput = new CheckBox();
    private final TextField emailInput = new TextField();
    private final TextField telefonnummerInput = new TextField();
    private final TextField positionInput = new TextField();

    protected String errorStyle = "-fx-border-color: red; -fx-border-width: 1px;";
    protected static List<Mitarbeiter> mitarbeiterList = new ArrayList<>();
    protected void showEmployeeForm(BorderPane mainLayout) {
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
        GridPane.setConstraints(this.firstnameInput, 1, 1);

        Label lastnameLabel = new Label("Nachname:");
        GridPane.setConstraints(lastnameLabel, 0, 2);
        GridPane.setConstraints(this.lastnameInput, 1, 2);

        Label dobLabel = new Label("Geburtsdatum:");
        GridPane.setConstraints(dobLabel, 0, 3);
        GridPane.setConstraints(this.dobInput, 1, 3);

        Label marriedLabel = new Label("Verheiratet:");
        GridPane.setConstraints(marriedLabel, 0, 4);
        GridPane.setConstraints(marriedInput, 1, 4);

        Label emailLabel = new Label("Email:");
        GridPane.setConstraints(emailLabel, 0, 5);
        GridPane.setConstraints(this.emailInput, 1, 5);

        Label telefonnummerLabel = new Label("Telefonnummer:");
        GridPane.setConstraints(telefonnummerLabel, 0, 6);
        GridPane.setConstraints(this.telefonnummerInput, 1, 6);

        Label positionLabel = new Label("Position:");
        GridPane.setConstraints(positionLabel, 0, 7);
        GridPane.setConstraints(this.positionInput, 1, 7);

        Button submitButton = new Button("Daten speichern");
        GridPane.setConstraints(submitButton, 1, 8);

        grid.getChildren().addAll(headerLabel,firstnameLabel, this.firstnameInput,
                lastnameLabel, this.lastnameInput, dobLabel, this.dobInput,
                marriedLabel, this.marriedInput,emailLabel, this.emailInput, telefonnummerLabel,
                this.telefonnummerInput, positionLabel, this.positionInput, submitButton);


        submitButton.setOnAction(e -> {
            Map<String, Object> employeeInfo = new HashMap<>();
            employeeInfo.put("firstname", firstnameInput.getText());
            employeeInfo.put("lastname", lastnameInput.getText());
            employeeInfo.put("dob", dobInput.getText());
            employeeInfo.put("married", marriedInput.isSelected());
            employeeInfo.put("email", emailInput.getText());
            employeeInfo.put("telefonnummer", telefonnummerInput.getText());
            employeeInfo.put("position", positionInput.getText());

            boolean noInputError = noInputCheck(employeeInfo);
            if (!noInputError) {
                refreshMitarbeiterList();
                Mitarbeiter mitarbeiter = new Mitarbeiter(
                        (String) employeeInfo.get("firstname"),
                        (String) employeeInfo.get("lastname"),
                        (String) employeeInfo.get("dob"),
                        (Boolean) employeeInfo.get("married"),
                        (String) employeeInfo.get("email"),
                        (String) employeeInfo.get("telefonnummer"),
                        (String) employeeInfo.get("position")
                );
                mitarbeiterList.add(mitarbeiter);

                refreshMitarbeiterJSON();

                firstnameInput.clear();
                lastnameInput.clear();
                dobInput.clear();
                marriedInput.setSelected(false);
                emailInput.clear();
                telefonnummerInput.clear();
                positionInput.clear();
            }
        });

        mainLayout.setCenter(grid);
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

    private boolean noInputCheck(Map<String, Object> map){
        boolean noInput = false;

        Map<String, TextInputControl> inputFields = Map.of(
                "firstname", firstnameInput,
                "lastname", lastnameInput,
                "dob", dobInput,
                "email", emailInput,
                "telefonnummer", telefonnummerInput,
                "position", positionInput
        );

        inputFields.values().forEach(field -> field.setStyle(""));

        for (Map.Entry<String, Object> entry : map.entrySet()) {
            String key = entry.getKey();
            Object value = entry.getValue();
            if (value instanceof String && ((String) value).isEmpty()) {
                TextInputControl inputField = inputFields.get(key);
                if (inputField != null) {
                    inputField.setStyle(errorStyle);
                    noInput = true;
                }
            }
        }
        return noInput;
    }
}
