package edu.cg.models;

import com.jogamp.opengl.GL2;
import com.jogamp.opengl.glu.GLU;
import com.jogamp.opengl.glu.GLUquadric;

import edu.cg.algebra.Point;
import edu.cg.algebra.Vec;
import edu.cg.models.Car.Specification;

public class BoundingSphere implements IRenderable {
	private double radius = 0.0;
	private Point center;
	private double color[];

	public BoundingSphere(double radius, Point center) {
		color = new double[3];
		this.setRadius(radius);
		this.setCenter(new Point(center.x, center.y, center.z));
	}

	public void setSphereColore3d(double r, double g, double b) {
		this.color[0] = r;
		this.color[1] = g;
		this.color[2] = b;
	}

	/**
	 * Given a sphere s - check if this sphere and the given sphere intersect.
	 * 
	 * @return true if the spheres intersects, and false otherwise
	 */
	public boolean checkIntersection(BoundingSphere s) {
		// TODO: Check if two spheres intersect.
		double sumOfRadius = this.radius + s.radius;
		float distanceBetweenCenters = this.center.dist(s.center);
		
		return sumOfRadius > distanceBetweenCenters;
	}

	public void translateCenter(double dx, double dy, double dz) {
		// TODO: Translate the sphere center by (dx,dy,dz).
		this.setCenter(this.center.add(new Vec(dx, dy, dz)));
		
	}

	@Override
	public void render(GL2 gl) {
		// TODO: Render a sphere with the given radius and center.
		// NOTE : Use the specified color when rendering.
		GLU glu = new GLU();
		GLUquadric quad = glu.gluNewQuadric();
		gl.glPushMatrix();
		gl.glTranslated(this.center.x, this.center.y, this.center.z);
		gl.glColor3d(this.color[0], this.color[1], this.color[2]);
		glu.gluSphere(quad, this.radius, 10, 10);
		gl.glPopMatrix();
		glu.gluDeleteQuadric(quad);
	}

	@Override
	public void init(GL2 gl) {
	}

	public double getRadius() {
		return radius;
	}

	public void setRadius(double radius) {
		this.radius = radius;
	}

	public Point getCenter() {
		return center;
	}

	public void setCenter(Point center) {
		this.center = center;
	}

}
