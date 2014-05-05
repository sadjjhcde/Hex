package ru.game.Hex.Core;

import android.graphics.Color;
import android.widget.ImageButton;

public class HexButtonsContainer {

    private ImageButton rotateButton;
    private ImageButton hexImage;
    private Hex hex;

    public HexButtonsContainer(ImageButton rotateButton, ImageButton hexImage){
        this.rotateButton = rotateButton;
        this.hexImage = hexImage;
        this.hex = hex;
    }


    public ImageButton getRotateButton() {
        return rotateButton;
    }

    public ImageButton getHexImage() {
        return hexImage;
    }

    public Hex getHex() {
        return hex;
    }

    public void setHex(Hex hex) {
        this.hex = hex;
    }

    public void  select(){
        hexImage.setBackgroundColor(Color.GREEN);
    }

    public void unselect(){
        hexImage.setBackgroundColor(Color.LTGRAY);
    }
}
