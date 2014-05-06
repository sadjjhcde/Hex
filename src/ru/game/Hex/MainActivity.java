package ru.game.Hex;

import android.app.Activity;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;
import android.widget.TextView;
import ru.game.Hex.Core.Hex;
import ru.game.Hex.Core.HexButtonsContainer;
import ru.game.Hex.Core.Path;
import ru.game.Hex.Util.Randomizer;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends Activity {

    public interface HexButtonGetter {
        public HexButtonsContainer getSelectedHexButton();

        public void clearSelected();
    }

    private List<HexButtonsContainer> buttons;
    private Paint hexPathsPaint;
    private Paint hexBorderPaint;
    private HexButtonsContainer selectedHexButton;

    private GameField gameField;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);
        View mainLayot = findViewById(R.id.mainlayuot);
        mainLayot.setBackgroundColor(Color.LTGRAY);
        View linearLayout = findViewById(R.id.linearLayout);
        linearLayout.setBackgroundColor(Color.LTGRAY);
        gameField = (GameField) findViewById(R.id.game_field);
        gameField.setHexButtonGetter(new HexButtonGetter() {
            @Override
            public HexButtonsContainer getSelectedHexButton() {
                return selectedHexButton;
            }

            @Override
            public void clearSelected() {
                if (selectedHexButton != null) {
                    selectedHexButton.unselect();
                    randomizeButton(selectedHexButton);
                    selectedHexButton = null;
                }
            }


        });

        hexPathsPaint = new Paint();
        hexPathsPaint.setColor(Color.BLACK);
        hexPathsPaint.setStrokeWidth(2);

        hexBorderPaint = new Paint();
        hexBorderPaint.setColor(Color.BLACK);
        hexBorderPaint.setStyle(Paint.Style.STROKE);
        hexBorderPaint.setStrokeWidth(2);

        buttons = new ArrayList<HexButtonsContainer>();
        buttons.add(
                new HexButtonsContainer(
                        (ImageButton) findViewById(R.id.rotateButton1),
                        (ImageButton) findViewById(R.id.hexImage1))
        );
        buttons.add(
                new HexButtonsContainer(
                        (ImageButton) findViewById(R.id.rotateButton2),
                        (ImageButton) findViewById(R.id.hexImage2))
        );
        buttons.add(
                new HexButtonsContainer(
                        (ImageButton) findViewById(R.id.rotateButton3),
                        (ImageButton) findViewById(R.id.hexImage3))
        );
        buttons.add(
                new HexButtonsContainer(
                        (ImageButton) findViewById(R.id.rotateButton4),
                        (ImageButton) findViewById(R.id.hexImage4))
        );
        buttons.add(
                new HexButtonsContainer(
                        (ImageButton) findViewById(R.id.rotateButton5),
                        (ImageButton) findViewById(R.id.hexImage5))
        );

        buttons.add(
                new HexButtonsContainer(
                        (ImageButton) findViewById(R.id.rotateButton6),
                        (ImageButton) findViewById(R.id.hexImage6))
        );
        fillButtons();

    }

    private void fillButtons() {
        for (final HexButtonsContainer hexButton : buttons) {
            randomizeButton(hexButton);
        }
    }

    private void randomizeButton(final HexButtonsContainer hexButton) {
        final ImageButton rotateButton = hexButton.getRotateButton();
        final ImageButton hexImage = hexButton.getHexImage();

        rotateButton.setBackgroundColor(Color.GRAY);
        hexImage.setBackgroundColor(Color.LTGRAY);

        final Bitmap bitmap = Bitmap.createBitmap(80, 80, Bitmap.Config.ARGB_8888);
        Canvas canvas = new Canvas(bitmap);
        final Hex hex = new Hex(40, 75, 35, canvas, hexBorderPaint, Color.BLACK, 2);
        hex.setPaths(Randomizer.getRandomPaths(hexPathsPaint), 2);
        hex.setFigure(Randomizer.getRandomFigure(), Color.TRANSPARENT);
        hex.drawHex();
        hexImage.setImageBitmap(bitmap);
        hexButton.setHex(hex);

        rotateButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Path.rotate(hex.getPaths());
                hex.drawHex();
                hexImage.setImageBitmap(bitmap);
                System.out.println("clickOn");
            }
        });

        hexImage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (selectedHexButton != null) {
                    selectedHexButton.unselect();
                    selectedHexButton = null;
                }

                selectedHexButton = hexButton;
                selectedHexButton.select();
            }
        });
    }
}
