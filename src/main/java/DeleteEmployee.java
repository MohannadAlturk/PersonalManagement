import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;

public class DeleteEmployee {
    public void showDeleteEmployee(Stage primaryStage){
        GridPane grid = new GridPane();
        grid.setPadding(new Insets(10, 10, 10, 10));
        grid.setVgap(8);
        grid.setHgap(10);
        grid.setAlignment(Pos.CENTER);

        Label mitarbeiterIdLabel = new Label("MitarbeiterID:");
        GridPane.setConstraints(mitarbeiterIdLabel, 0, 1);
        TextField mitarbeiterIdInput = new TextField();
        GridPane.setConstraints(mitarbeiterIdInput, 1, 1);
        Button submitButton = new Button("Mitarbeiter Löschen");
        GridPane.setConstraints(submitButton, 1, 2);
        Button backButton = new Button("Zurück");
        GridPane.setConstraints(backButton, 1, 3);

        grid.getChildren().addAll(mitarbeiterIdLabel , mitarbeiterIdInput, submitButton, backButton);
        BackgroundUtil.setBackground(grid);
        Scene formScene = new Scene(grid);
        try {
            formScene.getStylesheets().add(getClass().getResource("/styles.css").toExternalForm());
        } catch (NullPointerException e) {
            System.out.println("CSS-Datei konnte nicht geladen werden: " + e.getMessage());
        }

        backButton.setOnAction(e -> {
            Main mainApp = new Main();
            mainApp.start(primaryStage);
        });

        submitButton.setOnAction(e -> {
            deleteMitarbeiter(mitarbeiterIdInput.getText());
            mitarbeiterIdInput.clear();
        });

        primaryStage.setScene(formScene);
        primaryStage.show();
    }

    private void deleteMitarbeiter(String mitarbeiterID){
        EmployeeForm.refreshMitarbeiterList();
        Mitarbeiter toDeleteEmployee = null;
        for (Mitarbeiter mitarbeiter : EmployeeForm.mitarbeiterList){
            if (mitarbeiter.getId().equals(mitarbeiterID)){
                toDeleteEmployee = mitarbeiter;
                mitarbeiter = null;
            }
        }
        EmployeeForm.mitarbeiterList.remove(toDeleteEmployee);
        EmployeeForm.refreshMitarbeiterJSON();
    }
}
