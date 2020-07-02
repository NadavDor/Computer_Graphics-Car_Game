package edu.cg.models.Car;

import com.jogamp.opengl.*;
import edu.cg.models.IRenderable;

/**
 * A simple axes dummy
 *
 */
public class Empty implements IRenderable {

	public void render(GL2 gl) {
	}

	@Override
	public String toString() {
		return "Empty";
	}

	@Override
	public void init(GL2 gl) {

	}

	@Override
	public void destroy(GL2 gl) {

	}
}
