package bird;
import java.util.Arraylist;
public class Flock<S>{
    private ArrayList<S> flock = new ArrayList<>();

    public void join(S swallow){
        flock.add(swallow);
    }


    public double getAverageCruiseAirspeedVelocity() {
        double total = 0;

        if(flock.size() == 0) {
            return 0;
        }
        else if(flock.size() >= 2) {
            total -= 3;
        }
        else {
            total -= 2;
        }

        for(Swallow x : flock) {
            total += x.getAirspeedVelocity();
        }

        return total / flock.size();
    }

}