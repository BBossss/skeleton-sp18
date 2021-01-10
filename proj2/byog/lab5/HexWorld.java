package byog.lab5;
import org.junit.Test;
import static org.junit.Assert.*;

import byog.TileEngine.TERenderer;
import byog.TileEngine.TETile;
import byog.TileEngine.Tileset;

import java.util.Random;

/**
 * Draws a world consisting of hexagonal regions.
 */
public class HexWorld {

    public void drawHexagon(int length) {
        drawUp(length);
        drawDown(length);
    }

    private void drawUp(int length) {
        for (int i = 0; i < length; i++) {
            drawLine(numberOfWhitespace(i + 1, length), ' ');
            drawLine(numberOfTile(i + 1, length), 'a');
            System.out.println();
        }
    }

    private void drawDown(int length) {
        for (int i = length; i > 0; i--) {
            drawLine(numberOfWhitespace(i, length), ' ');
            drawLine(numberOfTile(i, length), 'a');
            System.out.println();
        }
    }

    private int numberOfWhitespace(int row, int length) {
        return length - row;
    }

    private int numberOfTile(int row, int length) {
        return length + 2 * (row - 1);
    }

    private void drawLine(int length, char ch) {
        for (int i = 0; i < length; i++) {
            System.out.print(ch);
        }
    }

    public static void main(String[] args) {
        HexWorld hw = new HexWorld();
        hw.drawHexagon(6);
    }
}
