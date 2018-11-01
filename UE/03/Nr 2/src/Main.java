public class Main {

    public static void main(String[] args) {
        int zeilen = -1;
        int spalten = -1;
        while (zeilen < 1)
        {
            zeilen = SimpleIO.getInt("Wie viele Zeilen hat die Matrix? (>= 1)");
        }

        while (spalten   < 1)
        {
            spalten= SimpleIO.getInt("Wie viele Spalten hat die Matrix? (>= 1)");
        }


        int[][] matrix = new int[zeilen][spalten];

        for(int i = 1; i <= zeilen; i++ )
        {
            for(int x = 1; x <= spalten; x++)
            {
                matrix[i -1][x -1] = SimpleIO.getInt("Wie lautet die Zahl für Position (" + i + "," + x + ")");
            }
        }



        System.out.println("Matrix:");

        for(int[] x : matrix)
        {
            for(int y : x)
            {
                System.out.print(y + ", ");
            }
            System.out.print("\n");
        }

        System.out.println("Transpornierte Matrix:");


        int[][] transMatrix = new int[spalten][zeilen];


        for(int i = 0; i < matrix.length; i++)
        {
            for(int j = 0; j < matrix[0].length; j++)
            {
                transMatrix[j][i] = matrix[i][j];
            }
        }

        for(int[] x : transMatrix)
        {
            for(int y : x)
            {
                System.out.print(y + ", ");
            }
            System.out.print("\n");
        }



    }
}
