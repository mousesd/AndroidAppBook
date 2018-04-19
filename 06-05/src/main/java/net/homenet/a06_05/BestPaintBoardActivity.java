package net.homenet.a06_05;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;

public class BestPaintBoardActivity extends AppCompatActivity {

    private int color;
    private int pen = 2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_best_paint_board);

        final BestPaintBoard board = this.findViewById(R.id.bestPaintBoard);

        Button colorButton = this.findViewById(R.id.colorButton);
        colorButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ColorPaletteDialog.listener = new ColorPaletteDialog.OnColorSelectedListener() {
                    @Override
                    public void onColorSelected(int color) {
                        BestPaintBoardActivity.this.color = color;
                        board.updatePaintProperty(BestPaintBoardActivity.this.color, pen);
                    }
                };

                Intent intent = new Intent(BestPaintBoardActivity.this, ColorPaletteDialog.class);
                startActivity(intent);
            }
        });

        Button penButton = this.findViewById(R.id.penButton);
        penButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                PenPaletteDialog.listener = new PenPaletteDialog.OnPenSelectedListener() {
                    @Override
                    public void onPenSelected(int pen) {
                        BestPaintBoardActivity.this.pen = pen;
                        board.updatePaintProperty(color, BestPaintBoardActivity.this.pen);
                    }
                };

                Intent intent = new Intent(BestPaintBoardActivity.this, PenPaletteDialog.class);
                startActivity(intent);
            }
        });

        Button eraserButton = this.findViewById(R.id.eraserButton);
        eraserButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                color = Color.WHITE;
                pen = 15;
                board.updatePaintProperty(color, pen);
            }
        });

        Button undoButton = this.findViewById(R.id.undoButton);
        undoButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                board.undo();
            }
        });
    }
}
