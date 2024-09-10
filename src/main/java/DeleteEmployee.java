import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;

public class DeleteEmployee {
    public void showDeleteEmployee(BorderPane mainLayout){
        GridPane grid = new GridPane();
        grid.setPadding(new Insets(10, 10, 10, 10));
        grid.setVgap(8);
        grid.setHgap(10);
        grid.setAlignment(Pos.CENTER);

        Label mitarbeiterIdLabel = new Label("MitarbeiterID:");
        GridPane.setConstraints(mitarbeiterIdLabel, 0, 1);
        TextField mitarbeiterIdInput = new TextField();
        GridPane.setConstraints(mitarbeiterIdInput, 1, 1);
        Button submitButton = new Button("Mitarbeiter löschen");
        GridPane.setConstraints(submitButton, 1, 2);

        grid.getChildren().addAll(mitarbeiterIdLabel, mitarbeiterIdInput, submitButton);

        mainLayout.setCenter(grid);

        submitButton.setOnAction(e -> {
            deleteMitarbeiter(mitarbeiterIdInput.getText());
            mitarbeiterIdInput.clear();
        });
    }

    private void deleteMitarbeiter(String mitarbeiterID) {
        EmployeeForm.refreshMitarbeiterList();
        Mitarbeiter toDeleteEmployee = null;
        for (Mitarbeiter mitarbeiter : EmployeeForm.mitarbeiterList) {
            if (mitarbeiter.getId().equals(mitarbeiterID)) {
                toDeleteEmployee = mitarbeiter;
                break;
            }
        }
        if (toDeleteEmployee != null) {
            EmployeeForm.mitarbeiterList.remove(toDeleteEmployee);
            EmployeeForm.refreshMitarbeiterJSON();
        }
    }
}
