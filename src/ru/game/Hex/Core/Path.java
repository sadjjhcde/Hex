package ru.game.Hex.Core;

import android.graphics.Color;
import android.graphics.Paint;

import java.util.ArrayList;
import java.util.List;

public class Path {

    public static Paint NONE;

    static {
        NONE = new Paint();
        NONE.setColor(Color.TRANSPARENT);
    }

    private Paint paint;

    public Path(Paint paint) {
        this.paint = paint;
    }

    public Paint getPaint() {
        return paint;
    }

    public boolean exist(){
        return !(paint.getColor() == Color.TRANSPARENT);
    }

    public static List<Path> getFullPathsSet(Paint paint){
        List<Path> paths = new ArrayList<Path>();
        for (int i=0; i<6; i++){
            paths.add(new Path(paint));
        }
        return paths;
    }

    public static void rotate(List<Path> paths){
        Path temp = paths.get(0);
        paths.set(0, paths.get(1));
        paths.set(1, paths.get(2));
        paths.set(2, paths.get(3));
        paths.set(3, paths.get(4));
        paths.set(4, paths.get(5));
        paths.set(5, temp);
    }
}
