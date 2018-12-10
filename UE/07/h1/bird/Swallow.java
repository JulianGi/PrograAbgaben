package bird;
import bird.swallows.*;
import cargo.*;
public abstract class Swallow{

    private Object cargo;

    public Swallow(Object cargo){
        this.cargo = cargo;
    }
    public Swallow(){
        this.cargo = null;  
    }

    public Object getCargo(){
        return this.cargo;
    }

    public void setCargo(Object cargo){
        this.cargo = cargo;    
    }

    public boolean isLadden(){
        return cargo != null;
    }

    protected abstract int getAirspeedVelocity();


    static Swallow createEuropeanSwallow(Object cargo){
        EuropeanSwallow a = new EuropeanSwallow();
        a.setCargo(cargo);
        return a;
    }

    static Swallow createAfricanSwallow(Object cargo){
        AfricanSwallow a = new AfricanSwallow();
        a.setCargo(cargo);
        return a;
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