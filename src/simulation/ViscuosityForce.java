package simulation;

public class ViscuosityForce {
	private double forceMagnitude;

	public ViscuosityForce(double magnitude) 
	{
		forceMagnitude=-1*magnitude;
	}
	public double getMagnitude()
	{
		return forceMagnitude;
	}
}
