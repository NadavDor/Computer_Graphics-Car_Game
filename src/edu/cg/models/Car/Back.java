package edu.cg.models.Car;

import java.util.LinkedList;
import java.util.List;

import com.jogamp.opengl.GL2;
import com.jogamp.opengl.glu.GLU;
import com.jogamp.opengl.glu.GLUquadric;
import edu.cg.algebra.Point;
import edu.cg.models.BoundingSphere;
import edu.cg.models.IIntersectable;
import edu.cg.models.IRenderable;
import edu.cg.models.SkewedBox;

public class Back implements IRenderable, IIntersectable {
	private SkewedBox baseBox = new SkewedBox(Specification.B_BASE_LENGTH, Specification.B_BASE_HEIGHT,
			Specification.B_BASE_HEIGHT, Specification.B_BASE_DEPTH, Specification.B_BASE_DEPTH);
	private SkewedBox backBox = new SkewedBox(Specification.B_LENGTH, Specification.B_HEIGHT_1,
			Specification.B_HEIGHT_2, Specification.B_DEPTH_1, Specification.B_DEPTH_2);
	private PairOfWheels wheels = new PairOfWheels();
	private Spolier spoiler = new Spolier();

	@Override
	public void render(GL2 gl) {
		gl.glPushMatrix();
		Materials.SetBlackMetalMaterial(gl);
		gl.glTranslated(Specification.B_LENGTH / 2.0 - Specification.B_BASE_LENGTH / 2.0, 0.0, 0.0);
		baseBox.render(gl);
		Materials.SetRedMetalMaterial(gl);
		gl.glTranslated(-1.0 * (Specification.B_LENGTH / 2.0 - Specification.B_BASE_LENGTH / 2.0),
				Specification.B_BASE_HEIGHT, 0.0);
		backBox.render(gl);
		gl.glPopMatrix();
		gl.glPushMatrix();
		gl.glTranslated(-Specification.B_LENGTH / 2.0 + Specification.TIRE_RADIUS, 0.5 * Specification.TIRE_RADIUS,
				0.0);
		wheels.render(gl);
		gl.glPopMatrix();
		gl.glPushMatrix();
		gl.glTranslated(-Specification.B_LENGTH / 2.0 + 0.5 * Specification.S_LENGTH,
				0.5 * (Specification.B_HEIGHT_1 + Specification.B_HEIGHT_2), 0.0);
		spoiler.render(gl);
		gl.glPopMatrix();

		GLU glu = new GLU();
		GLUquadric quad = glu.gluNewQuadric();

		// adding exhausts
		gl.glPushMatrix();
		Materials.SetDarkGreyMetalMaterial(gl);
		gl.glTranslated(-0.29, 0.05, 0.03);
		gl.glRotated(90, 0.0, 1, 0.0);
		glu.gluCylinder(quad, 0.01, 0.01, 0.05, 20, 2);
		gl.glPopMatrix();

		gl.glPushMatrix();
		Materials.SetDarkGreyMetalMaterial(gl);
		gl.glTranslated(-0.29, 0.05, -0.03);
		gl.glRotated(90, 0.0, 1, 0.0);
		glu.gluCylinder(quad, 0.01, 0.01, 0.05, 20, 2);
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
		double x = Specification.B_LENGTH;
		double y = Specification.B_HEIGHT;
		double z = Specification.B_DEPTH;
		Point p = new Point(0,y/2.0,0);
		double radius = Math.sqrt(Math.pow((x/2), 2) + Math.pow((y/2.0), 2) + Math.pow((z/2), 2));
		BoundingSphere sphere = new BoundingSphere(radius, p);
		res.add(sphere);
		return res;
	}

}
