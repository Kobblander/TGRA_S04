/*
 * Date: Mar 1, 2008
 * Time: 6:38:11 PM
 * (c) 2008 Shawn Silverman
 */
package com.tgra.client.game.maze;

import java.io.PrintStream;
import java.util.Arrays;

/**
 * Implements the basic requirements of a rectangular maze generator.
 * Subclasses provide a specific generation algorithm.
 *
 * @author Shawn Silverman
 */
public abstract class MazeGenerator {
    /*public static void main(String[] args) {
        MazeGenerator maze = new HuntAndKillMazeGenerator(10, 10, 0, 9);
        maze.generate();
        maze.print(System.out);
    }*/

    static {
        // This forces the compiler in Greenfoot to compile these classes too

        Class[] c = new Class[] {
                HuntAndKillMazeGenerator.class,
                PrimMazeGenerator.class,
                RecursiveBacktrackerMazeGenerator.class
        };
    }

    /** Represents UP. */
    public static final int UP    = 0;

    /** Represents RIGHT. */
    public static final int RIGHT = 1;

    /** Represents DOWN. */
    public static final int DOWN  = 2;

    /** Represents LEFT. */
    public static final int LEFT  = 3;

    private int width;
    private int height;

    // Stores whether the walls exist or not

    private boolean[] horizWalls;
    private boolean[] vertWalls;

    public boolean[] getHorizWalls() {
        return horizWalls;
    }

    public boolean[] getVertWalls() {
        return vertWalls;
    }

    /**
     * A convenience structure that represents one cell.  It contains a cell's
     * coordinates.
     *
     * @author Shawn Silverman
     */
    protected static class Cell {
        protected int x;
        protected int y;

        /**
         * Creates a new cell object having the given coordinates.
         *
         * @param x the cell's X-coordinate
         * @param y the cell's Y-coordinate
         */
        protected Cell(int x, int y) {
            this.x = x;
            this.y = y;
        }

        public String toString() {
            return "(" + x + ", " + y + ")";
        }
    }

    /**
     * Create a new maze generator.  The height and width in cells is
     * specified.
     *
     * @param width the maze width, in cells
     * @param width the maze height, in cells
     * @throws IllegalArgumentException if either size non-positive.
     */
    protected MazeGenerator(int width, int height) {
        if (width <= 0 || height <= 0) {
            throw new IllegalArgumentException("Size must be positive");
        }

        this.width = width;
        this.height = height;

        // Create the walls

        horizWalls = new boolean[width * (height + 1)];
        vertWalls  = new boolean[(width + 1) * height];

        reset();
    }

    /**
     * Resets the maze.
     */
    public final void reset() {
        // Fill the walls

        Arrays.fill(horizWalls, true);
        Arrays.fill(vertWalls, true);
    }

    /**
     * Generates the maze.  This first resets the maze by calling
     * {@link #reset()}.
     */
    public final void generate() {
        reset();
        generateMaze();
    }

    /**
     * Generates the maze using a specific algorithm.  Subclasses implement
     * this.
     */
    protected abstract void generateMaze();

    /**
     * Checks the direction, and throws an <code>IllegalArgumentException</code>
     * if it is invalid.
     *
     * @param direction the direction value to check
     * @throws IllegalArgumentException if the direction value is invalid.
     */
    private static void checkDirection(int direction) {
        switch (direction) {
            case UP:
            case RIGHT:
            case DOWN:
            case LEFT:
                break;
            default:
                throw new IllegalArgumentException("Bad direction: " + direction);
        }
    }

    /**
     * Checks that the given cell location is valid.
     *
     * @param x the cell's X-coordinate
     * @param y the cell's Y-coordinate
     * @throws IndexOutOfBoundsException if the coordinate is out of range.
     */
    protected void checkLocation(int x, int y) {
        if (x < 0 || width <= x) {
            throw new IndexOutOfBoundsException("X out of range: " + x);
        }
        if (y < 0 || height <= y) {
            throw new IndexOutOfBoundsException("Y out of range: " + y);
        }
    }

    /**
     * Carves a path in the given direction from the given cell.
     *
     * @param x the starting cell's X-coordinate
     * @param y the starting cell's Y-coordinate
     * @param direction the direction to carve
     * @return whether the wall existed and was removed.  If the wall was
     *         already gone, then this returns <code>false</code>.
     * @throws IllegalArgumentException if the direction value is invalid.
     * @throws IndexOutOfBoundsException if the coordinate is out of range.
     * @see #UP
     * @see #RIGHT
     * @see #DOWN
     * @see #LEFT
     */
    protected boolean carve(int x, int y, int direction) {
        // Check the arguments

        checkDirection(direction);
        checkLocation(x, y);

        int index = -1;
        boolean[] array = null;

        switch (direction) {
            case UP:
                index = y*width + x;
                array = horizWalls;
                break;
            case DOWN:
                index = (y + 1)*width + x;
                array = horizWalls;
                break;
            case LEFT:
                index = y*(width + 1) + x;
                array = vertWalls;
                break;
            case RIGHT:
                index = y*(width + 1) + (x + 1);
                array = vertWalls;
                break;
        }

        // Set the wall to 'false' and return what it was before

        boolean b = array[index];
        array[index] = false;
        return b;
    }

    /**
     * Checks if the specified wall is present.
     *
     * @param x the starting cell's X-coordinate
     * @param y the starting cell's Y-coordinate
     * @param direction the direction to carve
     * @return whether the specified wall is present.
     * @throws IllegalArgumentException if the direction value is invalid.
     * @throws IndexOutOfBoundsException if the coordinate is out of range.
     * @see #UP
     * @see #RIGHT
     * @see #DOWN
     * @see #LEFT
     */
    public boolean isWallPresent(int x, int y, int direction) {
        // Check the arguments

        checkDirection(direction);
        checkLocation(x, y);

        int index = -1;
        boolean[] array = null;

        switch (direction) {
            case UP:
                index = y*width + x;
                array = horizWalls;
                break;
            case DOWN:
                index = (y + 1)*width + x;
                array = horizWalls;
                break;
            case LEFT:
                index = y*(width + 1) + x;
                array = vertWalls;
                break;
            case RIGHT:
                index = y*(width + 1) + (x + 1);
                array = vertWalls;
                break;
        }

        // Set the wall to 'false' and return what it was before

        return array[index];
    }

    /**
     * Gets the maze width, in cells.
     *
     * @return the maze width in cells.
     */
    public int getWidth() {
        return width;
    }

    /**
     * Gets the maze height, in cells.
     *
     * @return the maze height in cells.
     */
    public int getHeight() {
        return height;
    }

    /**
     * Prints the maze.  The following characters are used for each part.
     * <ul>
     * <li><code>'-'</code> for horizontal walls</li>
     * <li><code>'|'</code> for vertical walls</li>
     * <li><code>'*'</code> for the corner fillers</li>
     * </ul>
     *
     * @param out the target {@link java.io.PrintStream}
     */
    public void print(PrintStream out) {
        for (int y = 0; y < height; y++) {
            // Print a row of horizontal walls

            int rowBase = y * width;
            for (int x = 0; x < width; x++) {
                out.print('*'/*'.'*/);
                out.print(horizWalls[rowBase + x] ? '-' : ' ');
            }
            out.println('*'/*'.'*/);

            // Print a row of vertical walls

            rowBase = y*(width + 1);
            for (int x = 0; x < width; x++) {
                out.print(vertWalls[rowBase + x] ? '|' : ' ');
                out.print(' ');
            }
            out.println(vertWalls[rowBase + width] ? '|' : ' ');
        }

        // Print the last row of horizontal walls

        int rowBase = height * width;
        for (int x = 0; x < width; x++) {
            out.print('*'/*'.'*/);
            out.print(horizWalls[rowBase + x] ? '-' : ' ');
        }
        out.println('*'/*'.'*/);
    }
}
