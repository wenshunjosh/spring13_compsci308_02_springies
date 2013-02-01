package simulation;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;


/**
 * XXX
 * 
 * @author Robert C. Duvall
 */
public class Factory {
    // data file keywords
    private static final String MASS_KEYWORD = "mass";
    private static final String SPRING_KEYWORD = "spring";
    private static final String GRAVITY_KEYWORD="gravity";
    private static final String VISCUOSITY_KEYWORD="viscosity";

    // mass IDs
    Map<Integer, Mass> myMasses = new HashMap<Integer, Mass>();


    /**
     * XXX.
     */
    public void loadModel (Model model, File modelFile, File modelFile2) {
        try {
            Scanner input = new Scanner(modelFile);
            while (input.hasNext()) {
                Scanner line = new Scanner(input.nextLine());
                if (line.hasNext()) {
                    String type = line.next();
                    if (MASS_KEYWORD.equals(type)) {
                        model.add(massCommand(line));
                    }
                    else if (SPRING_KEYWORD.equals(type)) {
                        model.add(springCommand(line));
                    }
                }
            }
            input.close();
        }
        catch (FileNotFoundException e) {
            // should not happen because File came from user selection
            e.printStackTrace();
        }
        try {
        	Scanner input =new Scanner(modelFile2);
        	while (input.hasNextLine())
        	{
        		String line=input.nextLine();
        		String[] commands=line.split("");
        		if (commands[0].equals(GRAVITY_KEYWORD))
        		{
        			GravityForce g=new GravityForce(Double.parseDouble(commands[2]), Double.parseDouble(commands[1]));
        			model.setGravity(g);
        		}
        		else if (commands[0].equals(VISCUOSITY_KEYWORD))
        		{
        			ViscuosityForce v=new ViscuosityForce(Double.parseDouble(commands[1]));
        			model.setViscuosity(v);
        		}
        	}
        }
        catch (FileNotFoundException e)
        {
        	e.printStackTrace();
        }
    }

    // create mass from formatted data
    private Mass massCommand (Scanner line) {
        int id = line.nextInt();
        double x = line.nextDouble();
        double y = line.nextDouble();
        double mass = line.nextDouble();
        Mass result = new Mass(x, y, mass);
        myMasses.put(id,  result);
        return result;
    }

    // create spring from formatted data
    private Spring springCommand (Scanner line) {
        Mass m1 = myMasses.get(line.nextInt());
        Mass m2 = myMasses.get(line.nextInt());
        double restLength = line.nextDouble();
        double ks = line.nextDouble();
        return new Spring(m1, m2, restLength, ks);
    }
}
