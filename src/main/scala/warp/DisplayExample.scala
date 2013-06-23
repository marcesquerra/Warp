package warp

import org.lwjgl.LWJGLException
import org.lwjgl.input.Mouse
import org.lwjgl.input.Keyboard
import org.lwjgl.opengl.GL11._
import graph._
import graph.WG._
import maths._
import warp.mecha.Mecha
import warp.mecha.MechaDisplay

object DisplayApp extends App
{

	DisplayExample
//		.onFullScreen()
		.withTargetFps(60)
		.start()

}

object DisplayExample extends MechaDisplay
{
	/** position of quad */
	var x = 400; var y = 300

	/** angle of quad rotation */
	var rotation = 0.0;

	def init() {
		println("OpenGL version: " + glGetString(GL_VERSION))

		// init OpenGL here
		glMatrixMode(GL_PROJECTION)
		glLoadIdentity()
		glOrtho(0, width, 0, height, 1, -1)
		glMatrixMode(GL_MODELVIEW)

		// Callbacks
		Mecha onFpsUpdate { fps =>
			setTitle("FPS: " + fps)
		}
	}

	def render()
	{
		// Clear The Screen And The Depth Buffer
		glClear(GL_COLOR_BUFFER_BIT | GL_DEPTH_BUFFER_BIT);


		// draw quad
		glPushMatrix();
			glTranslatef(x, y, 0);
			glRotated(rotation, 0f, 0f, 1f);
			glTranslatef(-x, -y, 0);

			glBegin(GL_QUADS);
				glColor3b(126, 0, 0)
				glVertex2f(x - 50, y - 50);
				glColor3b(0, 126, 0)
				glVertex2f(x + 50, y - 50);
				glColor3b(0, 0, 126)
				glVertex2f(x + 50, y + 50);
				glColor3b(126, 126, 0)
				glVertex2f(x - 50, y + 50);
			glEnd();
		glPopMatrix();
	}

	def update()
	{
		// rotate quad
		rotation += 0.05f * Mecha.delta;

		if (Keyboard.isKeyDown(Keyboard.KEY_LEFT))  x -= (0.35f * Mecha.delta).toInt
		if (Keyboard.isKeyDown(Keyboard.KEY_RIGHT)) x += (0.35f * Mecha.delta).toInt

		if (Keyboard.isKeyDown(Keyboard.KEY_UP))    y += (0.35f * Mecha.delta).toInt
		if (Keyboard.isKeyDown(Keyboard.KEY_DOWN))  y -= (0.35f * Mecha.delta).toInt

		// keep quad on the screen
		if (x < 0) x = 0;
		if (x > width) x = width;
		if (y < 0) y = 0;
		if (y > height) y = height;
	}
}
