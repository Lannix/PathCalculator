package Alex_Ladynin.View;

import Alex_Ladynin.View.Situations.ConstALine;
import javafx.animation.Animation;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.chart.LineChart;
import javafx.scene.control.Alert;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import javafx.util.Duration;

public class ConstALineController {

    @FXML
    private TextField velocity;

    @FXML
    private TextField acceleration;

    @FXML
    private TextField X;

    @FXML
    private LineChart<Number, Number> chartVt;

    @FXML
    private LineChart<Number, Number> chartAt;

    @FXML
    private LineChart<Number, Number> chartSt;

    @FXML
    private LineChart<Number, Number> chartXt;

    @FXML
    private Label info;

    private Stage stage;
    private ObservableList<ConstALine> situations;
    private ConstALine currentSituation;
    private boolean started;
    private long dTime;
    private Timeline timeline;

    @FXML
    private void initialize() {
        situations = FXCollections.observableArrayList();
        dTime = 200;
        started = false;
        timeline = new Timeline(new KeyFrame(Duration.millis(dTime), ae -> MyTimer()));
        timeline.setCycleCount(Animation.INDEFINITE);
    }

    public void setDialogStage(Stage stage) {
        this.stage = stage;
        stage.setOnCloseRequest(e -> timeline.stop());
    }

    public void MyTimer() {
        for (int i = 0; i < situations.size(); i++) {
            currentSituation = situations.get(i);
            currentSituation.addToSeriesVT(currentSituation.getTime(), currentSituation.getVelocity());
            currentSituation.addToSeriesAT(currentSituation.getTime(), currentSituation.getAcceleration());
            currentSituation.addToSeriesST(currentSituation.getTime(), currentSituation.getPath());
            currentSituation.addToSeriesXT(currentSituation.getTime(), currentSituation.getCoordinateX());

            currentSituation.setTime(currentSituation.getTime() + (double) dTime / 1000.);
            currentSituation.setVelocity(currentSituation.getVelocity() + currentSituation.getAcceleration() * dTime / 1000.);
            currentSituation.setPath(Math.abs(currentSituation.getStartVelocity() * currentSituation.getTime() + currentSituation.getAcceleration() * currentSituation.getTime() * currentSituation.getTime() / 2.));
            currentSituation.setCoordinateX(currentSituation.getStartCoordinateX() + currentSituation.getStartVelocity() * currentSituation.getTime() + currentSituation.getAcceleration() * currentSituation.getTime() * currentSituation.getTime() / 2.);
        }
    }

    @FXML
    private void addChart() {
        if (isInputValid()) {
            if (Double.parseDouble(velocity.getText().toString()) <= 20) {
                dTime = 50;
            }

            situations.add(new ConstALine(Double.parseDouble(velocity.getText().toString()), Double.parseDouble(acceleration.getText().toString()), Double.parseDouble(X.getText().toString()), situations.size() + 1));
            info.setText(info.getText().toString() + (Integer.valueOf(situations.size())).toString() + ") vн = " + velocity.getText().toString() + ", aн = " + acceleration.getText().toString() + " Xн = " + X.getText().toString() + ";   ");
            velocity.setText("");
            acceleration.setText("");
            X.setText("");

            currentSituation = situations.get(situations.size() - 1);
            chartVt.getData().addAll(currentSituation.getSeriesVT());
            chartAt.getData().addAll(currentSituation.getSeriesAT());
            chartSt.getData().addAll(currentSituation.getSeriesST());
            chartXt.getData().addAll(currentSituation.getSeriesXT());
        }
    }

    @FXML
    private void StartStopCharts() {
        if (started) {
            timeline.pause();
            started = false;
        } else {
            timeline.play();
            started = true;
        }
    }

    @FXML
    private void resetCharts() {
        velocity.setText("");
        acceleration.setText("");
        info.setText("");
        X.setText("");
        situations.clear();
        chartVt.getData().clear();
        chartAt.getData().clear();
        chartSt.getData().clear();
        chartXt.getData().clear();
    }

    private boolean isInputValid() {
        String errorMessage = "";

        if (velocity.getText() == null || velocity.getText().toString().length() == 0) {
            errorMessage += "-Скорость\n";
        }
        if (acceleration.getText() == null || acceleration.getText().toString().length() == 0) {
            errorMessage += "-Ускорение\n";
        }
        if (X.getText() == null || X.getText().toString().length() == 0) {
            errorMessage += "-Координату X\n";
        }

        try {
            double a = Double.parseDouble(velocity.getText().toString()) + 1.;
            a = Double.parseDouble(acceleration.getText().toString()) + 1.;
            a = Double.parseDouble(X.getText().toString()) + 1.;
        } catch (Exception e) {
            errorMessage += "-В полях ввода присутствуют посторонние знаки\n";
        }

        if (errorMessage.length() == 0) {
            return true;
        } else {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.initOwner(stage);
            alert.setTitle("Ошибка");
            alert.setHeaderText("Пожалуйста, введите в пустые поля данные");
            alert.setContentText(errorMessage);
            alert.showAndWait();
            return false;
        }
    }
}
