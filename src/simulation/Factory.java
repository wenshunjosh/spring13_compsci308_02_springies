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
    private static final String CENTER_OF_MASS_KEYWORD="centermass";
    private static final String WALL_REPULSION_KEYWORD="wall";

    // mass IDs
    Map<Integer, Mass> myMasses = new HashMap<Integer, Mass>();


    /**
     * XXX.
     */
    public void loadModel (Model model, File modelFile) {
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
                    else
                    {
                    	//check for cm and wr, set them up in the model
                    }
                }
            }
            input.close();
        }
        catch (FileNotFoundException e) {
            // should not happen because File came from user selection
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
