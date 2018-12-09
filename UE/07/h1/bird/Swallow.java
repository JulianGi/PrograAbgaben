package bird;
import bird.swallos.AfricanSwallow;
import cargo.*;
public abstract class Swallow{

    private Object Fracht;

    public Swallow(Object Fracht){
        this.Fracht = Fracht;
    }

    public Object getCargo(){
        return this.Fracht;
    }

    public boolean isLadden(){
        return Fracht != null;
    }

    protected abstract int getAirspeedVelocity();


    static Swallow createAfricanSwallow(Object cargo){
        return new AfricanSwallow(cargo);
    }

    protected int getSpeed(){
        if(getCargo() == null){
            return getAirspeedVelocity();
        }
        else if(getCargo() instanceof Nut){
            if(getAirspeedVelocity() - ((Nut) getCargo()).getWeight() < 0){
                return 0;
            }
            else{
                return getAirspeedVelocity() - ((Nut) getCargo()).getWeight();
            }
        }
        else{
            return getAirspeedVelocity()/2;
        }


    }

}