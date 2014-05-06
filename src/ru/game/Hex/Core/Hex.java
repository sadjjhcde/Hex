package ru.game.Hex.Core;

import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import ru.game.Hex.Core.Figure.Figure;

import java.util.ArrayList;
import java.util.List;

public class Hex {

    private static Paint defaultBackgroundPaint;

    static {
        defaultBackgroundPaint = new Paint();
        defaultBackgroundPaint.setColor(Color.WHITE);
        defaultBackgroundPaint.setStyle(Paint.Style.FILL);
    }

    public static float magicNumber = (float) Math.sqrt(0.75);

    private int massXPos;
    private int massYPos;
    private float bitmapXPos;
    private float bitmapYPos;
    private float radius;
    private Canvas canvas;
    private List<Path> paths;
    private List<Point> pathsPoints;

    private Figure figure;
    private int figureColor;
    private Paint borderPaint;
    private int pathStrokeWidth;

    private boolean isDrawed = false;
    private boolean isFilled = false;

    public Hex(float bitmapXPos, float bitmapYPos, float radius, Canvas canvas, Paint borderPaint, int color, int strokeWidth){
        this.bitmapXPos = bitmapXPos;
        this.bitmapYPos = bitmapYPos;
        this.canvas = canvas;
        this.radius = radius;
        this.borderPaint = borderPaint;
        pathsPoints = getPathsPoints();
    }

    public void setFigure(Figure figure, int figureColor){
        this.figure = figure;
        this.figureColor = figureColor;
    }

    public Figure getFigure() {
        return figure;
    }

    public int getFigureColor() {
        return figureColor;
    }

    public void setPaths(List<Path> paths, int pathStrokeWidth){
        this.paths = paths;
        this.pathStrokeWidth = pathStrokeWidth;
    }

    public List<Path> getPaths(){
        return paths;
    }

    public Point getCentralPoint(){
        return new Point(bitmapXPos, bitmapYPos-radius);
    }

    private void drawPaths() {
        for (int i=0; i<paths.size(); i++){
            paths.get(i).getPaint().setStrokeWidth(pathStrokeWidth);
            canvas.drawLine(
                    bitmapXPos,
                    bitmapYPos-radius,
                    pathsPoints.get(i).getX(),
                    pathsPoints.get(i).getY(),
                    paths.get(i).getPaint());
        }
    }

    private List<Point> getPathsPoints(){
        List<Point> points = new ArrayList<Point>();
        points.add(new Point(
                bitmapXPos + radius*magicNumber/2,
                bitmapYPos - radius/4));
        points.add(new Point(
                bitmapXPos + radius*magicNumber,
                bitmapYPos - radius));
        points.add(new Point(
                bitmapXPos + radius*magicNumber/2,
                (float) (bitmapYPos - 1.75*radius)));
        points.add(new Point(
                bitmapXPos - (radius*magicNumber)/2,
                (float) (bitmapYPos - 1.75*radius)));
        points.add(new Point(
                bitmapXPos - radius*magicNumber,
                bitmapYPos - radius));
        points.add(new Point(
                bitmapXPos - (radius*magicNumber)/2,
                bitmapYPos - radius/4));
        return points;
    }

    public void drawHex() {

        android.graphics.Path path = getHexPath();

        canvas.drawPath(path, defaultBackgroundPaint);
        canvas.drawPath(path, borderPaint);

        isDrawed = true;

        if (paths != null){
            drawPaths();
        }
        if (figure != null){
            figure.draw(bitmapXPos, bitmapYPos, radius, canvas, figureColor);
            isFilled = true;
        }
    }

    public void drawBackground(int color){
        android.graphics.Path path = getHexPath();
        Paint backgroundPaint = new Paint();
        backgroundPaint.setColor(color);
        backgroundPaint.setStyle(Paint.Style.FILL);
        canvas.drawPath(path, backgroundPaint);
        canvas.drawPath(path, borderPaint);
    }

    private android.graphics.Path getHexPath(){
        android.graphics.Path path = new android.graphics.Path();
        path.setFillType(android.graphics.Path.FillType.EVEN_ODD);

        path.moveTo(
                bitmapXPos - radius * magicNumber,
                bitmapYPos - radius / 2);
        path.lineTo(
                bitmapXPos,
                bitmapYPos);
        path.lineTo(
                bitmapXPos + radius * magicNumber,
                bitmapYPos - radius / 2);
        path.lineTo(
                bitmapXPos + radius * magicNumber,
                (float) (bitmapYPos - radius * 1.5));
        path.lineTo(
                bitmapXPos,
                bitmapYPos - radius * 2);
        path.lineTo(
                bitmapXPos - radius * magicNumber,
                (float) (bitmapYPos - radius * 1.5));
        path.lineTo(
                bitmapXPos - radius * magicNumber,
                bitmapYPos - radius / 2);
        path.lineTo(
                bitmapXPos - radius * magicNumber,
                bitmapYPos - radius / 2);
        path.close();

        return path;
    }

    public void redrawHex(Hex hex, int pathStrokeWidth){
        figure = hex.getFigure();
        figureColor = hex.getFigureColor();
        setPaths(hex.getPaths(), pathStrokeWidth);
        drawHex();
    }

    public int getMassXPos() {
        return massXPos;
    }

    public void setMassXPos(int massXPos) {
        this.massXPos = massXPos;
    }

    public int getMassYPos() {
        return massYPos;
    }

    public void setMassYPos(int massYPos) {
        this.massYPos = massYPos;
    }

    public boolean isFilled() {
        return isFilled;
    }

    public boolean isDrawed() {
        return isDrawed;
    }
}
