import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

import java.io.IOException;

public class Main extends Application {

    protected Stage primaryStage;

    public static void main(String[] args) throws IOException {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) {
        this.primaryStage = primaryStage;
        VBox startLayout = new VBox(10);
        startLayout.setPadding(new Insets(10));
        startLayout.setAlignment(Pos.CENTER);
        startLayout.setPadding(new Insets(20));

        Button addEmployeeButton = new Button("Neue Mitarbeiter hinzufügen");
        Button deleteEmployeeButton = new Button("Mitarbeiter entfernen");
        Button employeeOverviewButton = new Button("Mitarbeiter anzeigen");
        Button closeButton = new Button("Fenster schließen");

        startLayout.getChildren().addAll(addEmployeeButton, deleteEmployeeButton, employeeOverviewButton,closeButton);

        Scene startScene = new Scene(startLayout, 300, 200);
        BackgroundUtil.setBackground(startLayout);
        WindowUtil.setWindowSize(primaryStage);
        primaryStage.setTitle("Personal Management Tool");
        primaryStage.setScene(startScene);
        primaryStage.show();
        EmployeeForm employeeForm = new EmployeeForm();
        DeleteEmployee deleteEmployee = new DeleteEmployee();
        EmployeeOverview employeeOverview = new EmployeeOverview();

        addEmployeeButton.setOnAction(e -> employeeForm.showEmployeeForm(primaryStage));
        deleteEmployeeButton.setOnAction(e -> deleteEmployee.showDeleteEmployee(primaryStage));
        employeeOverviewButton.setOnAction(e -> employeeOverview.showOverview(primaryStage));
        closeButton.setOnAction(e -> primaryStage.close());
    }
}
