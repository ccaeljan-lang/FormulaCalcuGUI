package ph.edu.dlsu.lbycpob.formulamenu;

import javafx.application.Application;
import javafx.application.Platform;
import javafx.geometry.Pos;
import javafx.geometry.Rectangle2D;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.VBox;
import javafx.stage.Screen;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

import java.util.ArrayList;

import static javafx.stage.Modality.WINDOW_MODAL;


public class FormulaApp extends Application {
    private final boolean isLogicOnly = false; /* Setting the app to naked design w/o CSS */
    private double screenWidth;
    private double screenHeight;

    private TextField firstInput;
    private TextField secondInput;
    private TextField thirdInput;
    private TextField lblResultValue;

    private IFormula formula;
    private String[] buttonText;

    public static void main(String[] args) {
        launch(args);
    }

    private static Scene scene;

    @Override
    public void start(Stage primaryStage) {
        // Get Screen size
        Rectangle2D screen = Screen.getPrimary().getVisualBounds();
        screenWidth = screen.getWidth();
        screenHeight = screen.getHeight();

        // Create buttons
        Button btnOne = new Button("Formula 1: Bernoulli's Equation");
        Button btnTwo = new Button("Formula 2: Shannon Channel Capacity");
        Button btnClose = new Button("Exit");

        // Adding of CSS styles
        btnOne.getStyleClass().add("formula-button");
        btnTwo.getStyleClass().add("formula-button");
        btnClose.getStyleClass().add("exit-button");

        // Set button handlers
        btnOne.setOnAction(e -> handleFormula(primaryStage, 1));
        btnTwo.setOnAction(e -> handleFormula(primaryStage, 2));
        btnClose.setOnAction(e -> Platform.exit());

        // Label
        Label menu = new Label("MAIN MENU");

        // Create the scene
        VBox root = new VBox();
        root.setAlignment(Pos.CENTER);
        root.setSpacing(20);
        root.getChildren().addAll(menu, btnOne, btnTwo, btnClose);
        scene = new Scene(root, screenWidth, screenHeight);
        if (!isLogicOnly) {
            scene.getStylesheets().add(getClass().getResource("/mystyle.css").toExternalForm());
        }

        // Create the stage
        primaryStage.setScene(scene);
        primaryStage.setTitle("Formula App");
        primaryStage.setFullScreen(true);
        primaryStage.show();
    }


    private void handleFormula(Stage primaryStage, int formulaID) {
        Stage stage = new Stage();

        formula = (formulaID == 1) ? new Bernoulli() : new ShannonCapacity();
        buttonText = formula.getParameterList();

        // Label
        Label menu = new Label("VARIABLE MENU");

        VBox root = new VBox();
        root.setAlignment(Pos.CENTER);
        root.setSpacing(20);
        root.getChildren().addAll(menu);

        // Dynamically create buttons since Shannon formula has 6 variables
        // and bernoulli has 4.
        for (int i = 0; i < buttonText.length; i++) {
            final int index = i;
            Button button = new Button("Compute " + buttonText[i]);
            button.getStyleClass().add("variable-button");
            button.setOnAction(e -> handleVariable(stage, index));
            root.getChildren().add(button);
        }

        // Back to Main Menu button
        Button buttonClose = new Button("Back to Main Menu");
        buttonClose.getStyleClass().add("back-button");
        buttonClose.setOnAction(e -> stage.close());
        root.getChildren().add(buttonClose);

        Scene scene = new Scene(root, screenWidth, screenHeight);
        if (!isLogicOnly) {
            scene.getStylesheets().add(getClass().getResource("resources/mystyle.css").toExternalForm());
        }

        stage.initOwner(primaryStage);
        stage.initModality(WINDOW_MODAL);
        stage.setScene(scene);
        stage.setTitle("Formula1 Sub-Menu");
        stage.setFullScreen(true);
        stage.show();
    }

    private void handleVariable(Stage stage, int varIndex) {
        Stage lastStage = new Stage();
        lastStage.initOwner(stage);
        lastStage.initModality(WINDOW_MODAL);

        String selectedVariable = buttonText[varIndex];
        String[] inputParameters = formula.getInputParameters(selectedVariable);

        // Creates the scene
        GridPane gridPane = new GridPane();
        gridPane.setHgap(10);
        gridPane.setVgap(20);
        gridPane.setAlignment(Pos.CENTER);

        // Create labels using ArrayList as different number of variables for each formula
        ArrayList<TextField> inputFields = new ArrayList<>();
        for (int i = 0; i < inputParameters.length; i++) {
            Label label = new Label(inputParameters[i]);
            TextField input = new TextField();
            input.setPromptText("Enter Value");
            inputFields.add(input);
            gridPane.add(label, 0, i);
            gridPane.add(input, 1, i);
        }

        // Compute button
        Button buttonCompute = new Button("Compute");
        buttonCompute.getStyleClass().add("compute-button");

        // Result display field
        TextField resultField = new TextField();
        resultField.setEditable(false);
        resultField.getStyleClass().add("result-field");

        // Button action for computation
        buttonCompute.setOnAction(e -> {
            try {String[] args = new String[inputFields.size()];
                for (int i = 0; i < inputFields.size(); i++) {
                    args[i] = inputFields.get(i).getText();
                }
                double result = formula.compute(selectedVariable, args);
                resultField.setText(String.format("%.2f", result));
            } catch (NumberFormatException ex) {
                resultField.setText("Invalid input");
            }
        });

        // Button close action
        Button buttonClose = new Button("Back to Selection");
        buttonClose.getStyleClass().add("back-button");
        buttonClose.setOnAction(e -> lastStage.close());

        // Adding the buttons, labels, adn fields on the grid pane
        int resultRow = inputParameters.length;
        gridPane.add(buttonCompute, 1, resultRow);
        Label resultLabel = new Label("RESULT:");
        resultLabel.getStyleClass().add("result-label");
        gridPane.add(resultLabel, 0, resultRow + 1);
        gridPane.add(resultField, 1, resultRow + 1);
        gridPane.add(buttonClose, 1, resultRow + 2);

        // Vertical box grid pane
        VBox root = new VBox(gridPane);
        root.setAlignment(Pos.CENTER);
        root.setSpacing(20);

        // Adding of CSS Style
        Scene scene = new Scene(root, screenWidth / 2, screenHeight);
        scene.getStylesheets().add(getClass().getResource("/mystyle.css").toExternalForm());

        // Configuration stage
        lastStage.setScene(scene);
        lastStage.initStyle(StageStyle.UNDECORATED);
        lastStage.show();
    }
}
