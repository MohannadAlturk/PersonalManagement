import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.Region;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

import java.io.IOException;

public class Main extends Application {

    protected Stage primaryStage;
    private BorderPane mainLayout;

    public static void main(String[] args) throws IOException {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) {
        this.primaryStage = primaryStage;
        mainLayout = new BorderPane();

        VBox startLayout = new VBox(10);
        startLayout.setPadding(new Insets(20));
        startLayout.setAlignment(Pos.CENTER_LEFT);
        startLayout.setStyle("-fx-background-color: #2a2a2e;");

        Button addEmployeeButton = new Button("Neue Mitarbeiter hinzufügen");
        Button updateEmployeeButton = new Button("Mitarbeiter ändern");
        Button deleteEmployeeButton = new Button("Mitarbeiter löschen");
        Button employeeOverviewButton = new Button("Mitarbeiter anzeigen");
        Button closeButton = new Button("Programm beenden");

        Region spacer = new Region();
        VBox.setVgrow(spacer, javafx.scene.layout.Priority.ALWAYS);

        startLayout.getChildren().addAll(addEmployeeButton, employeeOverviewButton,
                updateEmployeeButton, deleteEmployeeButton, spacer, closeButton);

        mainLayout.setLeft(startLayout);

        Scene startScene = new Scene(mainLayout, 800, 600);
        try {
            startScene.getStylesheets().add(getClass().getResource("/styles.css").toExternalForm());
        } catch (NullPointerException e) {
            System.out.println("CSS-Datei konnte nicht geladen werden: " + e.getMessage());
        }
        BackgroundUtil.setBackground(mainLayout);
        WindowUtil.setWindowSize(primaryStage);
        primaryStage.setTitle("Personal Management Tool");
        primaryStage.setScene(startScene);
        primaryStage.show();

        EmployeeForm employeeForm = new EmployeeForm();
        DeleteEmployee deleteEmployee = new DeleteEmployee();
        EmployeeOverview employeeOverview = new EmployeeOverview();
        UpdateEmployee updateEmployee = new UpdateEmployee();

        addEmployeeButton.setOnAction(e -> showEmployeeForm(employeeForm));
        deleteEmployeeButton.setOnAction(e -> showDeleteEmployeeForm(deleteEmployee));
        employeeOverviewButton.setOnAction(e -> showEmployeeOverview(employeeOverview));
        updateEmployeeButton.setOnAction(e -> showUpdateEmployeeForm(updateEmployee));
        closeButton.setOnAction(e -> primaryStage.close());
    }
    private void showEmployeeForm(EmployeeForm form) {
        form.showEmployeeForm(mainLayout);
    }
    private void showDeleteEmployeeForm(DeleteEmployee deleteEmployee) {
        deleteEmployee.showDeleteEmployee(mainLayout);
    }
    private void showEmployeeOverview(EmployeeOverview overview) {
        overview.showOverview(mainLayout);
    }
    private void showUpdateEmployeeForm(UpdateEmployee updateEmployee) {
        updateEmployee.showUpdateEmployee(mainLayout);
    }
}
