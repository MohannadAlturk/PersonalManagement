import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.geometry.Rectangle2D;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import javafx.stage.Screen;
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

        backButton.setOnAction(e -> {
            Main mainApp = new Main();
            mainApp.start(primaryStage);
        });

        submitButton.setOnAction(e -> {
            deleteMitarbeiter(mitarbeiterIdInput.getText());
        });

        Rectangle2D screenBounds = Screen.getPrimary().getVisualBounds();
        primaryStage.setWidth(screenBounds.getWidth() * 0.9);
        primaryStage.setHeight(screenBounds.getHeight() * 0.9);

        primaryStage.setMaxWidth(screenBounds.getWidth());
        primaryStage.setMaxHeight(screenBounds.getHeight());

        primaryStage.centerOnScreen();
        primaryStage.setScene(formScene);
    }

    private void deleteMitarbeiter(String mitarbeiterID){
        System.out.println("Test1");
        EmployeeForm.refreshMitarbeiterList();
        for (Mitarbeiter mitarbeiter : EmployeeForm.mitarbeiterList){
            System.out.println("Test2");
            if (mitarbeiter.getId().equals(mitarbeiterID)){
                System.out.println(mitarbeiterID);
                EmployeeForm.mitarbeiterList.remove(mitarbeiter);
                mitarbeiter = null;
            }
        }
        EmployeeForm.refreshMitarbeiterJSON();
    }
}
