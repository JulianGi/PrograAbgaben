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

	public static EuropeanSwallow createEuropeanSwallow(Object cargo) {
		EuropeanSwallow a = new EuropeanSwallow();
		a.setCargo(cargo);
		return a;
	}

	public static AfricanSwallow createAfricanSwallow(Object cargo) {
		AfricanSwallow a = new AfricanSwallow();
		a.setCargo(cargo);
		return a;
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

    protected int adjustSpeed(int speed){
		// Protected shared function for subclasses to adjust their base speed based on cargo
        if (getCargo() != null) {
			if (getCargo() instanceof Nut) {
				return Math.max(0, speed - ((Nut) getCargo()).getWeight());
			}
			return Math.floorDiv(speed, 2);
		}
		return speed;
    }

}