package edu.cg.models.Car;


import java.util.Collection;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import com.jogamp.opengl.*;

import edu.cg.algebra.Point;
import edu.cg.algebra.Vec;
import edu.cg.models.BoundingSphere;
import edu.cg.models.IIntersectable;
import edu.cg.models.IRenderable;

/**
 * A F1 Racing Car.
 *
 */
public class F1Car implements IRenderable, IIntersectable {
	// TODO : Add new design features to the car.
	// Remember to include a ReadMe file specifying what you implemented.
	
	Center carCenter = new Center();
	Back carBack = new Back();
	Front carFront = new Front();

	@Override
	public void render(GL2 gl) {
		carCenter.render(gl);
		gl.glPushMatrix();
		gl.glTranslated(-Specification.B_LENGTH / 2.0 - Specification.C_BASE_LENGTH / 2.0, 0.0, 0.0);
		carBack.render(gl);
		gl.glPopMatrix();
		gl.glPushMatrix();
		gl.glTranslated(Specification.F_LENGTH / 2.0 + Specification.C_BASE_LENGTH / 2.0, 0.0, 0.0);
		carFront.render(gl);
		gl.glPopMatrix();

	}

	@Override
	public String toString() {
		return "F1Car";
	}

	@Override
	public void init(GL2 gl) {

	}
	
	
		

	@Override
	public List<BoundingSphere> getBoundingSpheres() {
		// TODO: Return a list of bounding spheres the list structure is as follow:
		// s1 -> s2 -> s3 -> s4
		// where:
		// s1 - sphere bounding the whole car
		// s2 - sphere bounding the car front
		// s3 - sphere bounding the car center
		// s4 - sphere bounding the car back
		//
		// * NOTE:
		// All spheres should be adapted so that they are place relative to
		// the car model coordinate system.
		
		//Basic List Implementation
		LinkedList<BoundingSphere> res = new LinkedList<BoundingSphere>();
		Point center = new Point(0.0, 0.0, 0.0);
		double radius = (Specification.F_LENGTH + Specification.C_LENGTH + Specification.B_LENGTH + Specification.S_WINGS_HEIGHT)/2;
		BoundingSphere s1 = new BoundingSphere(radius, center);
		s1.setSphereColore3d(0,0,0);
		res.add(s1);
		
		List<BoundingSphere> s2 = carFront.getBoundingSpheres();
		for (BoundingSphere i : s2) {
			i.translateCenter(Specification.F_LENGTH / 2.0 + Specification.C_BASE_LENGTH / 2.0, 0.0, 0.0);
			res.add(i);
		}
		
		List<BoundingSphere> s3 = carCenter.getBoundingSpheres();
		for (BoundingSphere j : s3) {	
			//j.translateCenter(-1*(Specification.F_LENGTH / 2.0 + Specification.C_BASE_LENGTH / 2.0), 0.0, 0.0);
			res.add(j);
		}
		
		List<BoundingSphere> s4 = carBack.getBoundingSpheres();
		for (BoundingSphere k : s4) {
			k.translateCenter(-Specification.B_LENGTH / 2.0 - Specification.C_BASE_LENGTH / 2.0, 0.0, 0.0);
			res.add(k);
		}
		
		
		return res;
	}
	
	
}
