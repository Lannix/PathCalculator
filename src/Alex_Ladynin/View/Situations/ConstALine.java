package Alex_Ladynin.View.Situations;

import javafx.scene.chart.XYChart;

public class ConstALine {

    private double velocity;
    private final double startVelocity;
    private final double acceleration;
    private double coordinateX;
    private final double startCoordinateX;
    private double path;
    private double time;
    private XYChart.Series<Number, Number> seriesVT;
    private XYChart.Series<Number, Number> seriesAT;
    private XYChart.Series<Number, Number> seriesST;
    private XYChart.Series<Number, Number> seriesXT;

    public ConstALine(double velocity, double acceleration, double X, Integer title) {
        this.velocity = velocity;
        startVelocity = velocity;
        this.acceleration = acceleration;
        coordinateX = X;
        startCoordinateX = X;
        path = 0.;
        time = 0;
        seriesVT = new XYChart.Series();
        seriesVT.setName(title.toString());
        seriesAT = new XYChart.Series();
        seriesAT.setName(title.toString());
        seriesST = new XYChart.Series();
        seriesST.setName(title.toString());
        seriesXT = new XYChart.Series();
        seriesXT.setName(title.toString());
    }

    public double getVelocity() {
        return velocity;
    }

    public double getAcceleration() {
        return acceleration;
    }

    public double getCoordinateX() {
        return coordinateX;
    }

    public double getPath() {
        return path;
    }

    public double getTime() {
        return time;
    }

    public double getStartVelocity() {
        return startVelocity;
    }

    public double getStartCoordinateX() {
        return startCoordinateX;
    }

    public XYChart.Series<Number, Number> getSeriesVT() {
        return seriesVT;
    }

    public XYChart.Series<Number, Number> getSeriesAT() {
        return seriesAT;
    }

    public XYChart.Series<Number, Number> getSeriesST() {
        return seriesST;
    }

    public XYChart.Series<Number, Number> getSeriesXT() {
        return seriesXT;
    }

    public void setVelocity(double velocity) {
        this.velocity = velocity;
    }

    public void setCoordinateX(double coordinateX) {
        this.coordinateX = coordinateX;
    }

    public void setPath(double path) {
        this.path = path;
    }

    public void setTime(double time) {
        this.time = time;
    }

    public void addToSeriesVT(double x, double y) {
        seriesVT.getData().add(new XYChart.Data(x, y));
    }

    public void addToSeriesAT(double x, double y) {
        seriesAT.getData().add(new XYChart.Data(x, y));
    }

    public void addToSeriesST(double x, double y) {
        seriesST.getData().add(new XYChart.Data(x, y));
    }

    public void addToSeriesXT(double x, double y) {
        seriesXT.getData().add(new XYChart.Data(x, y));
    }
}
