package ru.game.Hex.Core.Figure;

import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;

public class Cross implements Figure {
    @Override
    public void draw(float startX, float startY, float radius, Canvas canvas, int color) {
        canvas.save();
        canvas.rotate(45, startX, startY - radius);
        Paint paint = new Paint();
        paint.setColor(color);
        drawCross(paint, canvas, startX ,startY, radius);
        canvas.restore();

//        try {
//            Thread.sleep(100000);
//        } catch (InterruptedException e) {
//            e.printStackTrace();
//        }
//
//        canvas.save();
//        canvas.rotate(45, startX, startY - radius);
//        paint.setColor(Color.WHITE);
//        drawCross(paint, canvas, startX ,startY, radius);
//        canvas.restore();
    }

    private void drawCross(Paint paint, Canvas canvas, float startX, float startY, float radius){
        int width = 10;
        canvas.drawRect(
                (float) (startX - radius / 1.5),
                startY - radius + width / 2,
                (float) (startX + radius / 1.5),
                startY - radius - width / 2,
                paint);
        canvas.drawRect(
                startX  - width / 2,
                (float) (startY - radius + radius / 1.5),
                startX + width/2,
                (float)(startY - radius - radius / 1.5),
                paint
        );
    }

}
