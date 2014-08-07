package com.example.Kalambury;

import android.graphics.Path;
import android.graphics.RectF;

/**
 * Created by macie_000 on 2014-07-27.
 */
public class DrawingObject{
    Path path;
    int color;
    int size;

    public DrawingObject(Path path, int color, int size) {
        this.path = path;
        this.color = color;
        this.size = size;
    }
}
