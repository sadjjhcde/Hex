package ru.game.Hex.Core;

import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import ru.game.Hex.Core.Figure.Square;
import ru.game.Hex.Util.Randomizer;

import java.util.ArrayList;
import java.util.List;

public class HexNet {

    private Bitmap bitmap;
    private int width;
    private int height;
    private Hex[][] hexes;

    public HexNet(Bitmap bitmap) {
        this.bitmap = bitmap;
    }

    public Hex[][] createHexNet(int width, int height, float hexRadius, Paint borderPaint) {

        this.height = height;
        this.width = width;
        hexes = new Hex[width + 1][height + 1];

        Canvas canvas = new Canvas(bitmap);
        boolean even = false;
        float offset = 200;
        float slip;
        int copywidth = width;

        for (int j = 1; j <= height; j++) {

            slip = even ? hexRadius * Hex.magicNumber : 0;
            width = even ? width + 1 : copywidth;
            for (int i = 0; i < width; i++) {

                Hex hex = new Hex(
                        offset + i * hexRadius * Hex.magicNumber * 2 - slip,
                        offset + (float) (j * hexRadius * 1.5),
                        hexRadius,
                        canvas,
                        borderPaint,
                        Color.LTGRAY,
                        5);
                hex.setMassXPos(i);
                hex.setMassYPos(j - 1);
                //hex.drawHex();
                hexes[i][j - 1] = hex;

            }
            even = !even;
        }

//        Hex hex = hexes[10][10];
//        hex.setFigure(Randomizer.getRandomFigure(), Randomizer.getRandomColor());
//        hex.drawHex();

        return hexes;
    }

    public Hex getCentralHex() {
        return hexes[width / 2][height / 2];
    }

    private List<Hex> getSurroindingHexes(Hex hex) {
        int x = hex.getMassXPos();
        int y = hex.getMassYPos();
        int rowOffset = y % 2 == 1 ? 1 : 0;
        List<Hex> hexList = new ArrayList<Hex>();
        try {
            hexList.add(hexes[x - rowOffset][y - 1]);
            hexList.add(hexes[x - 1][y]);
            hexList.add(hexes[x - rowOffset][y + 1]);
            hexList.add(hexes[x + 1 - rowOffset][y + 1]);
            hexList.add(hexes[x + 1][y]);
            hexList.add(hexes[x + 1 - rowOffset][y - 1]);
        } catch (ArrayIndexOutOfBoundsException exception) {
            // do nothing
        }
        return hexList;
    }

    public void drawSurroundingHexes(Hex hex) {
        for (Hex surHex : getSurroindingHexes(hex)) {
            surHex.drawHex();
        }
    }

    private static int mainHexPaths[] = {3 ,4 ,5 ,0 ,1 ,2};

    public boolean validateHex(Hex hex, List<Path> paths) {
        int pathPosition = 0;
        boolean overlapExist = false;
        for (Hex surHex : getSurroindingHexes(hex)){
            if (paths.get(mainHexPaths[pathPosition]).exist()){
                if (surHex.isDrawed() && surHex.getPaths()!=null && !surHex.getPaths().get(pathPosition).exist()){
                    return false;
                }
                if (surHex.isDrawed() && surHex.getPaths()!=null && surHex.getPaths().get(pathPosition).exist()){
                    overlapExist = true;
                }
            }
            if (surHex.getPaths()!=null && surHex.getPaths().get(pathPosition).exist() && !paths.get(mainHexPaths[pathPosition]).exist()){
                return false;
            }
            pathPosition++;
        }
        return overlapExist;
    }

}
