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
    private int max;
    private boolean maxDone = false;
    private ArrayList<Integer> places = new ArrayList<Integer>();

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
        brickLayout = new int[bricks.size()][cols];

    }

    public void doOneBrick() {
        if (bricks.size() != 0) {
            Brick b = bricks.remove(0);


            b.setHeight(brickLayout.length - 1);


            for (int i = b.getStart() - 1; i <= b.getEnd(); i++) {
                for (int j = 0; j < brickLayout.length; j++) {
                    if (checkBrickSpot(j, i))
                    {
                        if (j <= b.getHeight())
                        {
                            b.setHeight(j - 1);
                        }
                    }
                }
            }


            for (int i = b.getStart() - 1; i < b.getEnd(); i++) {
                brickLayout[b.getHeight()][i+1] = 1;
            }
        }
    }




    public ArrayList<String> getFileData(String fileName) {
        File f = new File(fileName);
        Scanner s = null;
        try {
            s = new Scanner(f);
        }
        catch (FileNotFoundException e) {
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
        if (brickLayout[r][c] == 1) {
            return true;
        }
        else {
            return false;
        }
    }

    public int[][] getBrickLayout() {
        return brickLayout;
    }
}