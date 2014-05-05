package ru.game.Hex;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.util.AttributeSet;
import android.view.GestureDetector;
import android.view.MotionEvent;
import android.view.View;
import ru.game.Hex.Core.*;
import ru.game.Hex.Util.Randomizer;

public class GameField extends View {

    private float bitmapXPos;
    private float bitmapYPos;
    private float hexRadius;
    private Hex[][] hexes;
    private HexNet hexNet;
    private Paint hexPathsPaint;
    private Paint hexBorderPaint;

    private MainActivity.HexButtonGetter hexButtonGetter;

    public GameField(Context context, AttributeSet attributes) {
        super(context, attributes);
        this.setBackgroundColor(Color.WHITE);
        detector = new GestureDetector(context, new GameFieldGestureListener());

        bitmapXPos = 0;
        bitmapYPos = 0;
        hexRadius = 70;
        //fieldBitmap = Bitmap.createBitmap(2750, 2400, Bitmap.Config.ARGB_4444);
        fieldBitmap = Bitmap.createBitmap(1500, 1500, Bitmap.Config.ARGB_4444);

        Paint paint = new Paint();
        paint.setColor(Color.GREEN);
        Canvas canvas = new Canvas(fieldBitmap);
        canvas.drawLine(0, 0, fieldBitmap.getWidth() - 1, 0, paint);
        canvas.drawLine(fieldBitmap.getWidth() - 1, 0, fieldBitmap.getWidth() - 1, fieldBitmap.getHeight() - 1, paint);
        canvas.drawLine(fieldBitmap.getWidth() - 1, fieldBitmap.getHeight() - 1, 0, fieldBitmap.getHeight() - 1, paint);
        canvas.drawLine(0, fieldBitmap.getHeight(), 0, 0, paint);

        hexPathsPaint = new Paint();
        hexPathsPaint.setColor(Color.BLACK);

        hexBorderPaint = new Paint();
        hexBorderPaint.setColor(Color.LTGRAY);
        hexBorderPaint.setStyle(Paint.Style.STROKE);
        hexBorderPaint.setStrokeWidth((float) 1.3);

        hexNet = new HexNet(fieldBitmap);
        hexes = hexNet.createHexNet(10, 10, hexRadius, hexBorderPaint);

    }

    private Bitmap fieldBitmap;
    private final GestureDetector detector;

    private boolean firstDraw = true;
//
//    protected void init() {
//        setWillNotDraw(false);
//    }

    @Override
    protected void onDraw(Canvas canvas) {

        setWillNotDraw(false);
        if (firstDraw) {
            Hex centralHex = hexNet.getCentralHex();
            System.out.println(centralHex.getCentralPoint().getX() + " - " + centralHex.getCentralPoint().getY());
            bitmapXPos -= centralHex.getCentralPoint().getX() - canvas.getWidth() / 2;
            bitmapYPos -= centralHex.getCentralPoint().getY() - canvas.getHeight() / 2;
            centralHex.setFigure(Randomizer.getRandomFigure(), Color.TRANSPARENT);
            centralHex.setPaths(Path.getFullPathsSet(hexPathsPaint), 4);
            centralHex.drawHex();
            hexNet.drawSurroundingHexes(centralHex);
            firstDraw = false;
        }

        canvas.drawBitmap(fieldBitmap, bitmapXPos, bitmapYPos, null);
        //postInvalidate();
        //System.out.println("drawOn");
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        detector.onTouchEvent(event);
        return true;
    }

    public void setHexButtonGetter(MainActivity.HexButtonGetter hexButtonGetter) {
        this.hexButtonGetter = hexButtonGetter;
    }

    private class GameFieldGestureListener extends GestureDetector.SimpleOnGestureListener {

        @Override
        public boolean onScroll(MotionEvent e1, MotionEvent e2, float distanceX, float distanceY) {

            bitmapXPos -= distanceX;
            bitmapYPos -= distanceY;
            invalidate();
            return true;
        }

        @Override
        public boolean onSingleTapConfirmed(MotionEvent event) {

            int offset = 200;

            float X = (event.getX() - bitmapXPos);
            float Y = (event.getY() - bitmapYPos);
            int yHex = (int) ((Y - offset) / (1.5 * hexRadius));
            int xHex;
            if (yHex % 2 == 0) {
                xHex = (int) ((X - offset + hexRadius * Hex.magicNumber) / (2 * hexRadius * Hex.magicNumber));
            } else {
                xHex = (int) ((X - offset + 2 * hexRadius * Hex.magicNumber) / (2 * hexRadius * Hex.magicNumber));
            }

            if (hexButtonGetter != null && hexButtonGetter.getSelectedHexButton() != null) {

                Hex selectedHex = hexes[xHex][yHex];

                if (selectedHex.isDrawed() && !selectedHex.isFilled()) {
                    if (hexNet.validateHex(selectedHex, hexButtonGetter.getSelectedHexButton().getHex().getPaths())) {
                        selectedHex.redrawHex(hexButtonGetter.getSelectedHexButton().getHex(), 4);
                        hexButtonGetter.getSelectedHexButton().unselect();
                        hexButtonGetter.clearSelected();
                        hexNet.drawSurroundingHexes(selectedHex);
                    } else {
                        /*System.out.println("1");
                        selectedHex.setFigure(new Cross(), Color.RED);
                        selectedHex.drawHex();
                        System.out.println("1.5");
                        try {
                            Thread.sleep(3000);
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                        System.out.println("1.7");
                        selectedHex.setFigure(null, Color.WHITE);
                        selectedHex.drawHex();  */
                        System.out.println("NOPE");
                    }
                }
                System.out.println("3");

                postInvalidate();
            }

            return true;
        }

    }
}

