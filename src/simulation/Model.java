package simulation;

import java.awt.Dimension;
import java.awt.Graphics2D;
import java.util.List;
import java.util.ArrayList;
import view.Canvas;


/**
 * XXX.
 * 
 * @author Robert C. Duvall
 */
public class Model {
    // bounds and input for game
    private Canvas myView;
    // simulation state
    private List<Mass> myMasses;
    private List<Spring> mySprings;
    private GravityForce myGravity;
    private ViscuosityForce myViscuosity;

    /**
     * Create a game of the given size with the given display for its shapes.
     */
    public Model (Canvas canvas) {
        myView = canvas;
        myMasses = new ArrayList<Mass>();
        mySprings = new ArrayList<Spring>();
    }

    public void setGravity(GravityForce g)
    {
    	myGravity=g;
    }
    public void setViscuosity(ViscuosityForce v)
    {
    	myViscuosity=v;
    }
    /**
     * Draw all elements of the simulation.
     */
    public void paint (Graphics2D pen) {
        for (Spring s : mySprings) {
            s.paint(pen);
        }
        for (Mass m : myMasses) {
            m.paint(pen);
        }
    }

    /**
     * Update simulation for this moment, given the time since the last moment.
     */
    public void update (double elapsedTime) {
        Dimension bounds = myView.getSize();
        for (Spring s : mySprings) {
            s.update(elapsedTime, bounds);
        }
        for (Mass m : myMasses) {
            m.update(elapsedTime, bounds, myGravity, myViscuosity);
        }
    }

    /**
     * Add given mass to this simulation.
     */
    public void add (Mass mass) {
        myMasses.add(mass);
    }

    /**
     * Add given spring to this simulation.
     */
    public void add (Spring spring) {
    	spring.setGravity(myGravity);
    	spring.setViscuosity(myViscuosity);
        mySprings.add(spring);
    }
}
