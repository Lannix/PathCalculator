package Alex_Ladynin.View;

import Alex_Ladynin.View.Situations.ConstAAngleHorizont;
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

public class ConstAAngleHorizontController {

    @FXML
    private TextField velocity;

    @FXML
    private TextField angle;

    @FXML
    private TextField X;

    @FXML
    private TextField Y;

    @FXML
    private LineChart<Number, Number> chartVxt;

    @FXML
    private LineChart<Number, Number> chartGt;

    @FXML
    private LineChart<Number, Number> chartVyt;

    @FXML
    private LineChart<Number, Number> chartVt;

    @FXML
    private LineChart<Number, Number> chartHx;

    @FXML
    private Label info;

    @FXML
    private Label moreInfo;

    private Stage stage;
    private ObservableList<ConstAAngleHorizont> situations;
    private ConstAAngleHorizont currentSituation;
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
        moreInfo.setText("");

        for (int i = 0; i < situations.size(); i++) {
            currentSituation = situations.get(i);

            currentSituation.addToSeriesVxT(currentSituation.getTime(), currentSituation.getVelocityX());
            currentSituation.addToSeriesGT(currentSituation.getTime(), currentSituation.getAcceleration());
            currentSituation.addToSeriesVyT(currentSituation.getTime(), currentSituation.getVelocityY());
            currentSituation.addToSeriesVT(currentSituation.getTime(), currentSituation.getVelocity());
            currentSituation.addToSeriesHX(currentSituation.getCoordinateX(), currentSituation.getCoordinateY());

            currentSituation.setTime(currentSituation.getTime() + (double) dTime / 1000.);
            currentSituation.setVelocity(Math.sqrt((currentSituation.getVelocityY() + currentSituation.getAcceleration() * dTime / 1000.) * (currentSituation.getVelocityY() + currentSituation.getAcceleration() * dTime / 1000.) + currentSituation.getVelocityX() * currentSituation.getVelocityX()));
            currentSituation.setVelocityY(currentSituation.getVelocityY() + currentSituation.getAcceleration() * dTime / 1000.);
            currentSituation.setCoordinateX(currentSituation.getStartCoordinateX() + currentSituation.getStartVelocity() * currentSituation.getTime() * currentSituation.getCosA());
            currentSituation.setCoordinateY(currentSituation.getStartCoordinateY() + currentSituation.getStartVelocity() * currentSituation.getTime() * currentSituation.getSinA() + 0.5 * currentSituation.getAcceleration() * currentSituation.getTime() * currentSituation.getTime());

            if ((currentSituation.getCoordinateY() - currentSituation.getStartCoordinateY()) > currentSituation.getHmax())
                currentSituation.setHmax(Math.round(currentSituation.getCoordinateY() * 10.) / 10.);

            if (Math.abs(currentSituation.getCoordinateX() - currentSituation.getStartCoordinateX()) > currentSituation.getLmax())
                currentSituation.setLmax(Math.round(Math.abs(currentSituation.getCoordinateX() - currentSituation.getStartCoordinateX()) * 10.) / 10.);

            moreInfo.setText(moreInfo.getText().toString() + (Integer.valueOf(situations.size())).toString() + ") Hmax = " + Double.valueOf(currentSituation.getHmax()).toString() + ", Lmax = " + Double.valueOf(currentSituation.getLmax()).toString() + ", t = " + Double.valueOf(Math.round(currentSituation.getTime() * 10.) / 10.).toString() + ";   ");
        }
    }

    @FXML
    private void addChart() {
        if (isInputValid()) {
            if (Double.parseDouble(velocity.getText().toString()) <= 20) {
                dTime = 50;
            }

            situations.add(new ConstAAngleHorizont(Double.parseDouble(velocity.getText().toString()), Double.parseDouble(angle.getText().toString()), Double.parseDouble(X.getText().toString()), Double.parseDouble(Y.getText().toString()), situations.size() + 1));
            info.setText(info.getText().toString() + (Integer.valueOf(situations.size())).toString() + ") vн = " + velocity.getText().toString() + ", ∠ = " + angle.getText().toString() + ", Xн = " + X.getText().toString() + ", Yн = " + Y.getText().toString() + ";   ");

            velocity.setText("");
            angle.setText("");
            X.setText("");
            Y.setText("");

            currentSituation = situations.get(situations.size() - 1);
            chartVxt.getData().addAll(currentSituation.getSeriesVxT());
            chartGt.getData().addAll(currentSituation.getSeriesGT());
            chartVyt.getData().addAll(currentSituation.getSeriesVyT());
            chartVt.getData().addAll(currentSituation.getSeriesVT());
            chartHx.getData().addAll(currentSituation.getSeriesHX());
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
        angle.setText("");
        X.setText("");
        Y.setText("");
        info.setText("");
        moreInfo.setText("");
        situations.clear();
        chartVxt.getData().clear();
        chartGt.getData().clear();
        chartVyt.getData().clear();
        chartVt.getData().clear();
        chartHx.getData().clear();
        dTime = 200;
    }

    private boolean isInputValid() {
        String errorMessage = "";

        if (velocity.getText() == null || velocity.getText().toString().length() == 0) {
            errorMessage += "-Скорость\n";
        }
        if (angle.getText() == null || angle.getText().toString().length() == 0) {
            errorMessage += "-Ускорение\n";
        }
        if (X.getText() == null || Y.getText().length() == 0) {
            errorMessage += "-Координата X\n";
        }
        if (Y.getText() == null || Y.getText().length() == 0) {
            errorMessage += "-Координата Y\n";
        }

        try {
            double a = Double.parseDouble(velocity.getText().toString()) + 1.;
            a = Double.parseDouble(angle.getText().toString()) + 1.;
            a = Double.parseDouble(X.getText()) + 1.;
            a = Double.parseDouble(Y.getText()) + 1.;
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