package com.dont_code.cibil_score_indicator_library;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Path;
import android.util.AttributeSet;
import android.view.View;

public class ArcProgressBar extends View {

    private Paint arcPaint;
    private Paint arrowPaint;
    private float progress;
    private int[] sectionColors = {0xFFFF5652, 0xFFF8BA5A, 0xFF45B334,0xFF3A8738}; // Red, Orange, Green, Dark Green

    public ArcProgressBar(Context context) {
        super(context);
        init(null);
    }

    public ArcProgressBar(Context context, AttributeSet attrs) {
        super(context, attrs);
        init(attrs);
    }

    public ArcProgressBar(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init(attrs);
    }

    private void init(AttributeSet attrs) {
        arcPaint = new Paint(Paint.ANTI_ALIAS_FLAG);
        arcPaint.setStyle(Paint.Style.STROKE);

        arrowPaint = new Paint(Paint.ANTI_ALIAS_FLAG);
        arrowPaint.setStyle(Paint.Style.FILL);
        arrowPaint.setColor(getResources().getColor(android.R.color.holo_blue_light)); // Default color

    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);

        int width = getWidth();
        int height = getHeight();
        int diameter = Math.min(width, height);
        int strokeWidth = diameter / 7;
        float anglePerSection = (270f - (sectionColors.length - 1) * 2) / sectionColors.length; // Adjusted angle per section

        arcPaint.setStrokeWidth(strokeWidth);

        // Draw the colored segments with gaps
        for (int i = 0; i < sectionColors.length; i++) {
            arcPaint.setColor(sectionColors[i]);
            float startAngle = -225 + (anglePerSection + 2) * i; // Start angle adjusted with gap
            float sweepAngle = anglePerSection;
            canvas.drawArc(
                    strokeWidth / 2f, strokeWidth / 2f,
                    width - strokeWidth / 2f, height - strokeWidth / 2f,
                    startAngle, sweepAngle, false, arcPaint);
        }

        // Draw arrow at the progressing point
        float arrowAngle = progress * 270f - 225; // Adjusting for clockwise direction and start angle
        drawArrow(canvas, arrowAngle, diameter / 2, diameter - strokeWidth / 2); // Bottom aligned
    }

    private void drawArrow(Canvas canvas, float angle, float centerX, float centerY) {
        float arrowLength = 0.009f * getWidth(); // Length of the arrow
        float arrowWidth = 0.06f * getWidth(); // Width of the arrow

        // Calculate the endpoint of the arrow based on the center of the arc progress bar
        float endX = centerX + (float) Math.cos(Math.toRadians(angle)) * (centerX - arcPaint.getStrokeWidth() - arrowLength);
        float endY = centerY -200 + (float) Math.sin(Math.toRadians(angle)) * (centerX - arcPaint.getStrokeWidth() - arrowLength);

        // Calculate the points for the arrow
        float arrowPoint1X = endX + (float) Math.cos(Math.toRadians(angle + 225)) * arrowWidth;
        float arrowPoint1Y = endY + (float) Math.sin(Math.toRadians(angle + 225)) * arrowWidth;

        float arrowPoint2X = endX + (float) Math.cos(Math.toRadians(angle - 225)) * arrowWidth;
        float arrowPoint2Y = endY + (float) Math.sin(Math.toRadians(angle - 225)) * arrowWidth;

        // Draw the arrow
        Path arrowPath = new Path();
        arrowPath.moveTo(endX, endY);
        arrowPath.lineTo(arrowPoint1X, arrowPoint1Y);
        arrowPath.lineTo(arrowPoint2X, arrowPoint2Y);
        arrowPath.close();

        arrowPaint.setColor(Color.BLACK);
        // Draw the arrow
        canvas.drawPath(arrowPath, arrowPaint);
    }


    public void setProgress(float progress) {
        // Ensure progress is between 0 and 1
        this.progress = Math.max(0, Math.min(progress, 1));
        invalidate(); // Request a redraw
    }

    public float getProgress() {
        return progress;
    }
}
