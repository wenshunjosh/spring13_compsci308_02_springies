package simulation;

public class GravityForce {
	private double forceMagnitude;
	private double forceDirection;

	public GravityForce(double magnitude, double direction) 
	{
		forceMagnitude=magnitude;
		forceDirection=direction;
	}
	public double getMagnitude()
	{
		return forceMagnitude;
	}
	public double getAngle()
	{
		return forceDirection;
	}

}
