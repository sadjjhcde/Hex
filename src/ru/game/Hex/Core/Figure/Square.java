package ru.game.Hex.Core.Figure;

import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;

public class Square implements Figure {

    private static Paint paint = new Paint();

    @Override
    public void draw(float startX, float startY, float radius, Canvas canvas, int color) {

        float size = (radius*4)/7;

        if (color == Color.TRANSPARENT){
            paint.setColor(Color.WHITE);
            paint.setStyle(Paint.Style.FILL);
            canvas.drawRect(
                    startX - size/2,
                    startY - radius + size/2,
                    startX + size/2,
                    startY - radius - size/2,
                    paint);
            paint.setColor(Color.BLACK);
            paint.setStyle(Paint.Style.STROKE);
            paint.setStrokeWidth(3);
        }else{
            paint.setColor(color);
            paint.setStyle(Paint.Style.FILL);
        }

        canvas.drawRect(
                startX - size/2,
                startY - radius + size/2,
                startX + size/2,
                startY - radius - size/2,
                paint);

        //canvas.drawCircle(startX, startY - raduis, 5, paint);
    }
}
