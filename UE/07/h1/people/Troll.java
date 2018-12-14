package people;

import bird.*;

public class Troll {
	
	private boolean confused = false;

	public void pass () 
	{
		if (!confused)
			java.lang.System.exit(-1);
	}

	public double askAboutAirspeedVelocity (Object object) throws UnspecificQuestionException
	{
		if (object instanceof FlockInterface)
		{
			return ((FlockInterface)object).getAverageCruiseAirspeedVelocity();
		}
		if (object instanceof Swallow)
		{
			Flock<Swallow> tempFlock = new Flock<Swallow> ();
			tempFlock.join((Swallow)object); 
			return tempFlock.getAverageCruiseAirspeedVelocity()+2;
		}
		if (object instanceof String)
		{
			String s = (String)object;
			if (s.equals("Swallow") || s.equals("Unladden Swallow") || s.equals("European Swallow") || s.equals("African Swallow")) {
				confused = true;
				throw new UnspecificQuestionException ();
			}
		}
		return 0;
	}
}