public class Statistics {
    int [] array = new int[100];
    int counter = -1;

    public void addValue(int value) {
        if(counter < 99)
        {
            counter++;
            array[counter] = value;
        }
        else{
            SimpleIO.output("Error reached limit of 100 integers.");
        }
    }

    public int getMinimum() {
        if(counter == -1)
        {
            SimpleIO.output("Es wurden keine Werte eingegeben.");
            return 0;
        }
        int min = Integer.MAX_VALUE;
        for(int i = 0; i <= counter; i++)
        {
            if (array[i] < min)
            {
                min = array[i];
            }
        }

        return min;
    }

    public int getMaximum() {
        if(counter == -1)
        {
            SimpleIO.output("Es wurden keine Werte eingegeben.");
            return 0;
        }
        int max = Integer.MIN_VALUE;
        for(int i = 0; i <= counter; i++)
        {
            if (array[i] > max)
            {
                max = array[i];
            }
        }
        return max;
    }

    public double getAverage(){
        if(counter == -1)
        {
            SimpleIO.output("Es wurden keine Werte eingegeben.");
            return 0;
        }
        double total = 0;
        for(int i = 0; i <= counter; i++)
        {
            total += array[i];
        }
        double divider = counter +1;
        return total/divider;

    }

    public static void main(String[] args) {
            Statistics statistics = new Statistics();
            statistics.addValue(2);
            statistics.addValue(105);
            statistics.addValue(-366);
            statistics.addValue(44);
            statistics.addValue(11);
            SimpleIO.output("Minimum: " + statistics.getMinimum());
            SimpleIO.output("Maximum: " + statistics.getMaximum());
            SimpleIO.output("Durchschnitt: " + statistics.getAverage());
    }
}
