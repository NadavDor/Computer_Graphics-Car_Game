package edu.cg.models.Car;

import com.jogamp.opengl.GL2;

import com.jogamp.opengl.glu.GLU;
import com.jogamp.opengl.glu.GLUquadric;
import edu.cg.models.IRenderable;
import edu.cg.models.SkewedBox;

public class FrontBumber implements IRenderable {
	private static final SkewedBox bumperBase = new SkewedBox(Specification.F_BUMPER_LENGTH, Specification.F_BUMPER_HEIGHT_1, Specification.F_BUMPER_HEIGHT_2, Specification.F_BUMPER_DEPTH, Specification.F_BUMPER_DEPTH);
	private static final SkewedBox bumperWing = new SkewedBox(Specification.F_BUMPER_LENGTH, Specification.F_BUMPER_WINGS_HEIGHT_1, Specification.F_BUMPER_HEIGHT_2, Specification.F_BUMPER_WINGS_DEPTH, Specification.F_BUMPER_WINGS_DEPTH);


	@Override
	public void render(GL2 gl) {
		GLU glu = new GLU();

		Materials.SetBlackMetalMaterial(gl);
		bumperBase.render(gl);

		// translate for left wing
		gl.glPushMatrix();
		Materials.SetBlackMetalMaterial(gl);
		gl.glTranslated(0, 0, Specification.F_BUMPER_DEPTH / 2 + Specification.F_BUMPER_WINGS_DEPTH / 2);
		renderBumperWing(gl, glu);
		gl.glPopMatrix();

		// translate for right wing
		gl.glPushMatrix();
		Materials.SetBlackMetalMaterial(gl);
		gl.glTranslated(0, 0, - Specification.F_BUMPER_DEPTH / 2 - Specification.F_BUMPER_WINGS_DEPTH / 2);
		renderBumperWing(gl, glu);
		gl.glPopMatrix();
	}

	private void renderBumperWing(GL2 gl, GLU glu) {

		Materials.SetBlackMetalMaterial(gl);
		bumperWing.render(gl);

		gl.glPushMatrix();
		Materials.SetRedMetalMaterial(gl);
		gl.glTranslated(0, Specification.F_BUMPER_LENGTH / 3.5, 0);
		GLUquadric sphere = glu.gluNewQuadric();
		glu.gluSphere(sphere, 0.02, 18, 18);
		gl.glPopMatrix();
	}

	@Override
	public void init(GL2 gl) {
	}

	@Override
	public void destroy(GL2 gl) {

	}

	@Override
	public String toString() {
		return "FrontBumper";
	}

}
