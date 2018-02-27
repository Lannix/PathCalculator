package Alex_Ladynin;

import Alex_Ladynin.View.ConstAAngleHorizontController;
import Alex_Ladynin.View.ConstALineController;
import Alex_Ladynin.View.RootLayoutController;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;

import java.io.IOException;

public class MainApp extends Application {

    private Stage primaryStage;
    private BorderPane rootLayout;

    @Override
    public void start(Stage primaryStage) throws Exception {
        this.primaryStage = primaryStage;
        primaryStage.setMaximized(true);
        this.primaryStage.setTitle("Равноускоренное движение");
        initRootLayout();
        showFirstScene();
    }

    public void initRootLayout() {
        try {
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(MainApp.class
                    .getResource("View/RootLayout.fxml"));
            rootLayout = loader.load();
            Scene scene = new Scene(rootLayout);
            primaryStage.setScene(scene);

            RootLayoutController controller = loader.getController();
            controller.setMainApp(this);

            primaryStage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void showFirstScene() {
        try {
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(MainApp.class.getResource("View/ConstALineLayout.fxml"));
            AnchorPane chart = (AnchorPane) loader.load();
            rootLayout.setCenter(chart);

            ConstALineController controller = loader.getController();
            controller.setDialogStage(primaryStage);
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    public void showSecondScene() {
        try {
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(MainApp.class.getResource("View/ConstAAngleHorizontLayout.fxml"));
            AnchorPane chart = (AnchorPane) loader.load();
            rootLayout.setCenter(chart);

            ConstAAngleHorizontController controller = loader.getController();
            controller.setDialogStage(primaryStage);
        } catch (IOException e) {
            e.printStackTrace();
        }

    }


    public static void main(String[] args) {
        launch(args);
    }
}
