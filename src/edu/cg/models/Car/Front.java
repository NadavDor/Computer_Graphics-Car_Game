package edu.cg.models.Car;

import java.util.LinkedList;
import java.util.List;

import com.jogamp.opengl.GL2;
import edu.cg.algebra.Point;
import edu.cg.models.BoundingSphere;
import edu.cg.models.IIntersectable;
import edu.cg.models.IRenderable;

public class Front implements IRenderable, IIntersectable {
	private FrontHood hood = new FrontHood();
	private PairOfWheels wheels = new PairOfWheels();
	private FrontBumber bumper = new FrontBumber();

	@Override
	public void render(GL2 gl) {
		// the car.
		gl.glPushMatrix();
		// Render hood - Use Red Material.
		gl.glTranslated(-Specification.F_LENGTH / 2.0 + Specification.F_HOOD_LENGTH / 2.0, 0.0, 0.0);
		hood.render(gl);
		// Render the wheels.
		gl.glTranslated(Specification.F_HOOD_LENGTH / 2.0 - 1.25 * Specification.TIRE_RADIUS,
				0.5 * Specification.TIRE_RADIUS, 0.0);
		wheels.render(gl);
		gl.glPopMatrix();

		// Render the bumper
		gl.glPushMatrix();
		gl.glTranslated(Specification.TIRE_RADIUS * 2 + Specification.F_BUMPER_LENGTH / 2.0, 0.0, 0.0);
		bumper.render(gl);
		gl.glPopMatrix();
	}

	@Override
	public void init(GL2 gl) {
	}

	@Override
	public void destroy(GL2 gl) {

	}

	@Override
	public List<BoundingSphere> getBoundingSpheres() {
		// s1
		// where:
		// s1 - sphere bounding the car front
		LinkedList<BoundingSphere> res = new LinkedList<BoundingSphere>();
		double x = Specification.F_LENGTH;
		double y = Specification.F_HEIGHT;
		double z = Specification.F_DEPTH;
		Point p = new Point(0,y/2,0);
		double radius = Math.sqrt(Math.pow((x/2), 2) + Math.pow((y/2), 2) + Math.pow((z/2), 2));
		BoundingSphere sphere = new BoundingSphere(radius, p);
		res.add(sphere);
		return res;
	}

	@Override
	public String toString() {
		return "CarFront";
	}
}
