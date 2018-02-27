package Alex_Ladynin.View.Situations;

import javafx.scene.chart.XYChart;

public class ConstAAngleHorizont {

    private double velocity, velocityX, velocityY, sinA, cosA, hmax, lmax;
    private final double startVelocity;
    private double acceleration;
    private double coordinateX, coordinateY;
    private final double startCoordinateX;
    private final double startCoordinateY;
    private final double startAngleRad;
    private double time;
    private XYChart.Series<Number, Number> seriesVxT;
    private XYChart.Series<Number, Number> seriesGT;
    private XYChart.Series<Number, Number> seriesVyT;
    private XYChart.Series<Number, Number> seriesVT;
    private XYChart.Series<Number, Number> seriesHX;


    public ConstAAngleHorizont(double velocity, double angle, double X, double Y, Integer title) {
        this.velocity = velocity;
        startVelocity = velocity;
        startAngleRad = Math.toRadians(angle);
        this.startCoordinateX = X;
        this.startCoordinateY = Y;
        sinA = Math.sin(startAngleRad);
        cosA = Math.cos(startAngleRad);
        velocityX = Math.cos(startAngleRad) * velocity;
        velocityY = Math.sin(startAngleRad) * velocity;
        acceleration = -9.81;
        coordinateX = X;
        coordinateY = Y;
        hmax = Y;
        lmax = 0.;
        time = 0;
        seriesVxT = new XYChart.Series();
        seriesVxT.setName(title.toString());
        seriesGT = new XYChart.Series();
        seriesGT.setName(title.toString());
        seriesVyT = new XYChart.Series();
        seriesVyT.setName(title.toString());
        seriesVT = new XYChart.Series();
        seriesVT.setName(title.toString());
        seriesHX = new XYChart.Series();
        seriesHX.setName(title.toString());
    }

    public double getVelocity() {
        return velocity;
    }

    public double getVelocityX() {
        return velocityX;
    }

    public double getVelocityY() {
        return velocityY;
    }

    public double getStartVelocity() {
        return startVelocity;
    }

    public double getAcceleration() {
        return acceleration;
    }

    public double getCoordinateX() {
        return coordinateX;
    }

    public double getCoordinateY() {
        return coordinateY;
    }

    public double getStartCoordinateX() {
        return startCoordinateX;
    }

    public double getStartCoordinateY() {
        return startCoordinateY;
    }

    public double getTime() {
        return time;
    }

    public double getSinA() {
        return sinA;
    }

    public double getCosA() {
        return cosA;
    }

    public double getHmax() {
        return hmax;
    }

    public double getLmax() {
        return lmax;
    }

    public void setVelocity(double velocity) {
        this.velocity = velocity;
    }

    public void setVelocityY(double velocityY) {
        this.velocityY = velocityY;
    }

    public void setCoordinateX(double coordinateX) {
        this.coordinateX = coordinateX;
    }

    public void setCoordinateY(double coordinateY) {
        this.coordinateY = coordinateY;
    }

    public void setTime(double time) {
        this.time = time;
    }

    public XYChart.Series<Number, Number> getSeriesVxT() {
        return seriesVxT;
    }

    public XYChart.Series<Number, Number> getSeriesGT() {
        return seriesGT;
    }

    public XYChart.Series<Number, Number> getSeriesVyT() {
        return seriesVyT;
    }

    public XYChart.Series<Number, Number> getSeriesVT() {
        return seriesVT;
    }

    public XYChart.Series<Number, Number> getSeriesHX() {
        return seriesHX;
    }

    public void addToSeriesVxT(double x, double y) {
        seriesVxT.getData().add(new XYChart.Data(x, y));
    }

    public void addToSeriesGT(double x, double y) {
        seriesGT.getData().add(new XYChart.Data(x, y));
    }

    public void addToSeriesVyT(double x, double y) {
        seriesVyT.getData().add(new XYChart.Data(x, y));
    }

    public void addToSeriesVT(double x, double y) {
        seriesVT.getData().add(new XYChart.Data(x, y));
    }

    public void addToSeriesHX(double x, double y) {
        seriesHX.getData().add(new XYChart.Data(x, y));
    }

    public void setHmax(double hmax) {
        this.hmax = hmax;
    }

    public void setLmax(double lmax) {
        this.lmax = lmax;
    }
}