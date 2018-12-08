public abstract class Swallow{

    Object Fracht;

    public Swallow(Object Fracht){
        this.Fracht = Fracht;
    }

    public Object getCargo(){
        return this.Fracht;
    }

    public boolean isLadden(){
        return Fracht != null;
    }

    public abstract int getAirspeedVelocity();

}