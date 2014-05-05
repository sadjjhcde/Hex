package ru.game.Hex.Core.Figure;

import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Path;

public class Triangle implements Figure {

    private static float triangleFirstMagicNumber = (float) (Math.sqrt(3)/3);
    private static float triangleSecondMagicNumber = (float) (Math.sqrt(3)/6);

    private static final Paint paint = new Paint();
    private float size;

    private Path path;

    @Override
    public void draw(float startX, float startY, float radius, Canvas canvas, int color) {

        size = (radius*5)/7;

        path = new Path();
        path.setFillType(Path.FillType.EVEN_ODD);
        path.moveTo(
                startX,
                startY - radius - size * triangleFirstMagicNumber);
        path.lineTo(
                startX - size/2,
                startY - radius + triangleSecondMagicNumber*size);
        path.lineTo(
                startX + size/2,
                startY - radius + triangleSecondMagicNumber*size);
        path.lineTo(
                startX,
                startY - radius - size * triangleFirstMagicNumber);
        path.close();

        if (color == Color.TRANSPARENT){
            drawStroke(startX, startY, radius, canvas);
        }else{
            drawFill(startX, startY, radius, canvas, color);
        }
    }

    public void drawFill(float startX, float startY, float radius, Canvas canvas, int color) {
        paint.setColor(color);
        paint.setStyle(Paint.Style.FILL);
        //paint.setStrokeWidth(3);
        canvas.drawPath(getPath(startX, startY, radius), paint);
    }


    public void drawStroke(float startX, float startY, float radius, Canvas canvas) {

        Path path = getPath(startX, startY, radius);

        paint.setColor(Color.WHITE);
        paint.setStyle(Paint.Style.FILL);
        canvas.drawPath(path, paint);

        paint.setColor(Color.BLACK);
        paint.setStyle(Paint.Style.STROKE);
        paint.setStrokeWidth(3);
        canvas.drawPath(path, paint);

    }

    private Path getPath(float startX, float startY, float radius){
        Path path = new Path();
        path.setFillType(Path.FillType.EVEN_ODD);
        path.moveTo(
                startX,
                startY - radius - size * triangleFirstMagicNumber);
        path.lineTo(
                startX - size/2,
                startY - radius + triangleSecondMagicNumber*size);
        path.lineTo(
                startX + size/2,
                startY - radius + triangleSecondMagicNumber*size);
        path.lineTo(
                startX,
                startY - radius - size * triangleFirstMagicNumber);
        path.close();

        return path;
    }

}
