package ru.game.Hex.Util;

import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import ru.game.Hex.Core.Figure.Circle;
import ru.game.Hex.Core.Figure.Figure;
import ru.game.Hex.Core.Figure.Square;
import ru.game.Hex.Core.Figure.Triangle;
import ru.game.Hex.Core.Path;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class Randomizer {

    private static Random random;

    static {
        random = new Random();
    }

    public static int getRandomColor(){
        int check = random.nextInt(3);
        int color;
        if (check == 0){
            color = Color.TRANSPARENT;
        }else if (check == 1){
            color = Color.RED;
        }else{
            color = Color.BLUE;
        }
        return color;
    }

    public static Figure getRandomFigure(){
        Figure figure;
        int check = random.nextInt(3);
        if (check == 0){
            figure = new Square();
        }else if (check == 1){
            figure = new Triangle();
        }else{
            figure = new Circle();
        }

        return figure;
    }

    public static List<Path> getRandomPaths(Paint paint){
        List<Path> paths = new ArrayList<Path>();
        for (int i=0; i<6; i++){
            Paint color;
            int check = random.nextInt(2);
            if (check == 0){
                color = paint;
            }else{
                color = Path.NONE;
            }
            paths.add(new Path(color));
        }
        return paths;
    }

    public static List<Path> getRangedPaths(){
        List<Path> paths = new ArrayList<Path>();

        int opacity = 30;
        for (int i=0; i<6; i++){
            Paint paint = new Paint();
            paint.setColor(Color.RED);
            paint.setAlpha(opacity+=30);
            paths.add(new Path(paint));
        }
        return paths;
    }

}
