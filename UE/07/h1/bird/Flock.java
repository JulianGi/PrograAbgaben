package bird;
import java.util.ArrayList;
public class Flock<S extends Swallow> implements FlockInterface{
    private ArrayList<S> flock = new ArrayList<>();

	public Flock () {}

    public void join(S swallow){
        flock.add(swallow);
    }


    public double getAverageCruiseAirspeedVelocity() {
        double total = 0;

        for(Swallow x : flock) {
            total += x.getAirspeedVelocity();
        }

        if(flock.size() == 0) {
            return 0;
        }
        else if(flock.size() == 1) {
            total -= 2;
        }
        else {
            total -= 3;
        }

        return total / flock.size();
    }

}