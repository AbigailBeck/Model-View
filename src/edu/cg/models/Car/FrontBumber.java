package edu.cg.models.Car;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;

import com.jogamp.opengl.GL2;
import com.jogamp.opengl.glu.GLU;
import com.jogamp.opengl.glu.GLUquadric;

import edu.cg.algebra.Point;
import edu.cg.models.BoundingSphere;
import edu.cg.models.IRenderable;
import edu.cg.models.SkewedBox;

public class FrontBumber implements IRenderable {
	// TODO: Add fields as you like (and methods if you think they are necessary).
	private SkewedBox bumber = new SkewedBox(Specification.F_BUMPER_LENGTH,Specification.F_BUMPER_HEIGHT_1,Specification.F_BUMPER_HEIGHT_2,Specification.F_BUMPER_DEPTH,Specification.F_BUMPER_DEPTH);
	private SkewedBox bumperWing = new SkewedBox(Specification.F_BUMPER_LENGTH,Specification.F_BUMPER_WINGS_HEIGHT_1,Specification.F_BUMPER_HEIGHT_2,Specification.F_BUMPER_WINGS_DEPTH,Specification.F_BUMPER_WINGS_DEPTH);
	
	
	@Override
	public void render(GL2 gl) {
		// TODO: Render the front bumper relative to it's local coordinate system.
		// Remember the dimensions of the bumper, this is important when you
		// combine the bumper with the hood.
		GLU glu = new GLU();
		GLUquadric quad = glu.gluNewQuadric();

		gl.glPushMatrix();
		gl.glTranslated(Specification.F_HOOD_LENGTH_2/2 + Specification.F_BUMPER_LENGTH/2, 0 , 0);
		Materials.SetRedMetalMaterial(gl);
		bumber.render(gl);
		
		gl.glPushMatrix();
		gl.glTranslated(0, 0.03 , -0.08);
		gl.glColor3d(0, 0, 0);
		glu.gluCylinder(quad, 0.03, 0.03, 0.15, 8, 2);
		gl.glPopMatrix();
		
		
		gl.glPushMatrix();
		gl.glTranslated(0,0, Specification.F_BUMPER_DEPTH/2 + Specification.F_BUMPER_WINGS_DEPTH/2);
		Materials.SetRedMetalMaterial(gl);
		bumperWing.render(gl);
		gl.glPushMatrix();
		gl.glTranslated(0,0.05, 0.005*(Specification.F_BUMPER_DEPTH/2 + Specification.F_BUMPER_WINGS_DEPTH/2));
		gl.glColor3d(255, 255, 0);
		glu.gluSphere(quad, 0.04, 10, 10);
		
		gl.glPopMatrix();
		gl.glPopMatrix();
		gl.glTranslated(0,0, -Specification.F_BUMPER_DEPTH/2 - Specification.F_BUMPER_WINGS_DEPTH/2);
		Materials.SetRedMetalMaterial(gl);
		bumperWing.render(gl);
		gl.glPushMatrix();
		gl.glTranslated(0,0.05, 0.005*(-Specification.F_BUMPER_DEPTH/2 - Specification.F_BUMPER_WINGS_DEPTH/2));
		gl.glColor3d(255, 255, 0);
		glu.gluSphere(quad, 0.04, 10, 10);
		
		gl.glPopMatrix();	
		gl.glPopMatrix();
		
		glu.gluDeleteQuadric(quad);
		
	}

	@Override
	public void init(GL2 gl) {
	}

	@Override
	public String toString() {
		return "FrontBumper";
	}

}
