package bird.swallows;
import bird.*;

public class EuropeanSwallow extends Swallow{
    private int AirspeedVelocity = 11;

    public int getAirspeedVelocity(){
        return adjustSpeed(AirspeedVelocity);
    }
}