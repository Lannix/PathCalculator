package Alex_Ladynin.View;

import Alex_Ladynin.MainApp;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;

public class RootLayoutController {

    private MainApp main;

    public void setMainApp(MainApp main) {
        this.main = main;
    }

    @FXML
    private void SetFirstScene() {
        main.showFirstScene();
    }

    @FXML
    private void SetSecondScene() {
        main.showSecondScene();
    }

    @FXML
    private void handleHelp() {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("Справка");
        alert.setHeaderText("Справка");
        alert.setContentText("Приложение создано для простого и быстрого построения графиков, вам нужно лишь ввести начальные данные, все остальное программа сделает за вас. ");
        alert.showAndWait();
    }

    @FXML
    private void handleAbout() {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("О проекте");
        alert.setHeaderText("О проекте");
        alert.setContentText(" Автор: Ладынин Александр, г. Красноярск, шк. гимназия №13.\n Вк - https://vk.com/a.ladynin \n GitHub - https://github.com/Lannix/PathCalculator \n 2018г." );
        alert.showAndWait();
    }

    @FXML
    private void handleExit() {
        System.exit(0);
    }
}
