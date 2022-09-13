package com.met622.util;

public class PhysicsFormula {
    public boolean circleRect(float circleX, float circleY, float radius, float rectX, float rectY, float rectWidth, float rectHeight) {

        // temporary variables to set edges for testing
        float testX = circleX;
        float testY = circleY;

        if (circleX < rectX)         testX = rectX;      // test left edge
        else if (circleX > rectX+rectWidth) testX = rectX+rectWidth;   // right edge
        if (circleY < rectY)         testY = rectY;      // top edge
        else if (circleY > rectY+rectHeight) testY = rectY+rectHeight;   // bottom edge

        // get distance from the closest edges
        float distX = circleX-testX;
        float distY = circleY-testY;
        float distance = (float)Math.sqrt( (distX*distX) + (distY*distY) );

        // if the distance is less than the radius, collision!
        if (distance < radius) {
            return true;
        }
        return false;
    }
}
