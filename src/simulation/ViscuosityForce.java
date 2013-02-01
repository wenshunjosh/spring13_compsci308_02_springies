package simulation;

public class ViscuosityForce {
	private double forceMagnitude;
	private double forceDirection;

	public ViscuosityForce(double magnitude, double direction) 
	{
		forceMagnitude=-1*magnitude;
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
