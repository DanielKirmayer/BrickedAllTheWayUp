import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;

public class BrickLayout {

    public ArrayList<Brick> getBricks() {
        return bricks;
    }

    private ArrayList<Brick> bricks;
    private int[][] brickLayout;
    private int cols;
    private Brick fallingBrick;
    private int height = 0;
    private boolean isFalling = false;

    public BrickLayout(String fileName, int cols, boolean dropAllBricks) {
        this.cols = cols;
        ArrayList<String> fileData = getFileData(fileName);
        bricks = new ArrayList<Brick>();
        for (String line : fileData) {
            String[] points = line.split(",");
            int start = Integer.parseInt(points[0]);
            int end = Integer.parseInt(points[1]);
            Brick b = new Brick(start, end);
            bricks.add(b);
        }
        brickLayout = new int[30][cols];
    }

    public void doOneBrick() {
        if (!isFalling && !bricks.isEmpty()) {
            fallingBrick = bricks.removeFirst();
            height = 0;
            isFalling = true;
        }

        if (isFalling) {
            boolean canFall = true;
            for (int i = fallingBrick.getStart() - 1; i <= fallingBrick.getEnd(); i++) {
                if (height + 1 >= brickLayout.length || brickLayout[height + 1][i] == 1) {
                    canFall = false;
                    break;
                }
            }

            if (canFall) {
                height++;
            } else {
                for (int i = fallingBrick.getStart() - 1; i <= fallingBrick.getEnd(); i++) {
                    brickLayout[height][i] = 1;
                }
                isFalling = false;
                fallingBrick = null;
            }
        }
    }

    public ArrayList<String> getFileData(String fileName) {
        File f = new File(fileName);
        Scanner s = null;
        try {
            s = new Scanner(f);
        } catch (FileNotFoundException e) {
            System.out.println("File not found.");
            System.exit(1);
        }
        ArrayList<String> fileData = new ArrayList<String>();
        while (s.hasNextLine())
            fileData.add(s.nextLine());

        return fileData;
    }

    public void printBrickLayout() {
        for (int r = 0; r < brickLayout.length; r++) {
            for (int c = 0; c < brickLayout[0].length; c++) {
                System.out.print(brickLayout[r][c] + " ");
            }
            System.out.println();
        }
    }

    public boolean checkBrickSpot(int r, int c) {
        return brickLayout[r][c] == 1;
    }

    public int[][] getBrickLayout() {
        int[][] copy = new int[brickLayout.length][brickLayout[0].length];
        for (int i = 0; i < brickLayout.length; i++) {
            System.arraycopy(brickLayout[i], 0, copy[i], 0, brickLayout[0].length);
        }

        if (isFalling) {
            for (int i = fallingBrick.getStart() - 1; i <= fallingBrick.getEnd(); i++) {
                if (height < copy.length) {
                    copy[height][i] = 1;
                }
            }
        }

        return copy;
    }
}
