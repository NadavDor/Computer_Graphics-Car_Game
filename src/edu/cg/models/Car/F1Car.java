package edu.cg.models.Car;

import java.util.LinkedList;
import java.util.List;

import com.jogamp.opengl.*;

import com.jogamp.opengl.glu.GLU;
import com.jogamp.opengl.glu.GLUquadric;
import edu.cg.algebra.Point;
import edu.cg.models.BoundingSphere;
import edu.cg.models.IIntersectable;
import edu.cg.models.IRenderable;

/**
 * A F1 Racing Car.
 *
 */
public class F1Car implements IRenderable, IIntersectable {
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
	public void destroy(GL2 gl) {

	}

	@Override
	public List<BoundingSphere> getBoundingSpheres() {
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
		double x;
		LinkedList<BoundingSphere> res = new LinkedList<BoundingSphere>();
		// add sphere for F1 car
		BoundingSphere carBoundingSphere = createCarBoundingSphere();
		res.add(carBoundingSphere);

		//Spheres for front of the F1 car
		for (BoundingSphere sphere : carFront.getBoundingSpheres()){
			x = (Specification.F_LENGTH / 2) + (Specification.C_LENGTH / 2);
			sphere.translateCenter(x, 0.0, 0);
			res.add(sphere);
		}
		//Spheres for center of the F1 car
		res.addAll(carCenter.getBoundingSpheres());
		//Spheres for back of the F1 car
		x = -1 * ((Specification.B_LENGTH / 2) + (Specification.C_LENGTH / 2));
		for (BoundingSphere sphere : carBack.getBoundingSpheres()) {
			sphere.translateCenter(x, 0, 0);
			System.out.println(sphere.getCenter());
			res.add(sphere);
		}
		return res;
	}

	public static BoundingSphere createCarBoundingSphere() {
		Double x = Specification.B_LENGTH + Specification.C_LENGTH + Specification.F_LENGTH;
		Double y = Math.max(Specification.B_HEIGHT, Math.max(Specification.C_HIEGHT, Specification.F_HEIGHT));
		Double z = Math.max(Specification.B_DEPTH, Math.max(Specification.F_DEPTH, Specification.F_DEPTH));
		Point center = new Point(0, y / 2, 0);
		Double radius = Math.sqrt(Math.pow(x / 2, 2) + Math.pow(y / 2, 2) + Math.pow(z / 2, 2));
		BoundingSphere carBoundingSphere = new BoundingSphere(radius, center);
		carBoundingSphere.setSphereColore3d(0, 0, 0);
		return carBoundingSphere;
	}
}

