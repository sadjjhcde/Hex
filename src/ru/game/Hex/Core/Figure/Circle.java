package ru.game.Hex.Core.Figure;

import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;

public class Circle implements Figure {

    private static Paint paint = new Paint();

    @Override
    public void draw(float startX, float startY, float radius, Canvas canvas, int color) {
        float size = (float) (radius * (2.3/7));

        if (color == Color.TRANSPARENT){
            paint.setColor(Color.WHITE);
            paint.setStyle(Paint.Style.FILL);

            canvas.drawCircle(startX, startY - radius, size, paint);

            paint.setColor(Color.BLACK);
            paint.setStyle(Paint.Style.STROKE);
            paint.setStrokeWidth(3);
        }else{
            paint.setColor(color);
            paint.setStyle(Paint.Style.FILL);
        }
        canvas.drawCircle(startX, startY - radius, size, paint);
    }
}
