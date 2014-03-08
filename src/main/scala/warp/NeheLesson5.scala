package warp

import org.lwjgl.LWJGLException
import org.lwjgl.input.Mouse
import org.lwjgl.input.Keyboard
import org.lwjgl.opengl.GL11._
import org.lwjgl.util.glu.GLU._
import graph._
import graph.WG._
import maths._
import warp.mecha.Mecha
import warp.mecha.MechaDisplay
import org.lwjgl.util.glu.GLU


object NeheLesson5 extends MechaDisplay
{

	private var rtri  = 0.0                // Angle For The Triangle ( NEW )
	private var rquad = 0.0                // Angle For The Quad     ( NEW )

	def init() = 
	{
		glEnable       (GL_TEXTURE_2D);          // Enable Texture Mapping
		glShadeModel   (GL_SMOOTH);              // Enable Smooth Shading
		glClearColor   (0.0f, 0.0f, 0.0f, 0.0f); // Black Background
		glClearDepth   (1.0);                    // Depth Buffer Setup
		glEnable       (GL_DEPTH_TEST);          // Enables Depth Testing
		glDepthFunc    (GL_LEQUAL);              // The Type Of Depth Testing To Do
		glMatrixMode   (GL_PROJECTION);          // Select The Projection Matrix
		glLoadIdentity ();                       // Reset The Projection Matrix

		// Calculate The Aspect Ratio Of The Window
		gluPerspective(
				45.0f,
				aspectRatio,
				0.1f,
				100.0f);

		glMatrixMode(GL_MODELVIEW)               // Select The Modelview Matrix

		// Really Nice Perspective Calculations
		glHint(GL_PERSPECTIVE_CORRECTION_HINT, GL_NICEST);

		BasicCamera()
	}

	def render()
	{



		glLoadIdentity()

		glTranslatef(-1.5f, 0.0f, -6.0f)
		glRotated(rtri, 0.0f, 1.0f, 0.0f)


		glBegin(GL_TRIANGLES)

			glColor3f(1.0f, 0.0f, 0.0f); glVertex3f( 0.0f,  1.0f,  0.0f)
			glColor3f(0.0f, 1.0f, 0.0f); glVertex3f(-1.0f, -1.0f,  1.0f)
			glColor3f(0.0f, 0.0f, 1.0f); glVertex3f( 1.0f, -1.0f,  1.0f)

			glColor3f(1.0f, 0.0f, 0.0f); glVertex3f( 0.0f,  1.0f,  0.0f)
			glColor3f(0.0f, 0.0f, 1.0f); glVertex3f( 1.0f, -1.0f,  1.0f)
			glColor3f(0.0f, 1.0f, 0.0f); glVertex3f( 1.0f, -1.0f, -1.0f)

			glColor3f(1.0f, 0.0f, 0.0f); glVertex3f( 0.0f,  1.0f,  0.0f)
			glColor3f(0.0f, 1.0f, 0.0f); glVertex3f( 1.0f, -1.0f, -1.0f)
			glColor3f(0.0f, 0.0f, 1.0f); glVertex3f(-1.0f, -1.0f, -1.0f)

			glColor3f(1.0f, 0.0f, 0.0f); glVertex3f( 0.0f,  1.0f,  0.0f)
			glColor3f(0.0f, 0.0f, 1.0f); glVertex3f(-1.0f, -1.0f, -1.0f)
			glColor3f(0.0f, 1.0f, 0.0f); glVertex3f(-1.0f, -1.0f,  1.0f)

		glEnd()



		glLoadIdentity()

		glTranslatef(1.5f, 0.0f, -7.0f)
		glRotated(rquad, 1.0f, 1.0f, 1.0f)


		glBegin(GL_QUADS)

			glColor3f(0.0f, 1.0f, 0.0f)

			glVertex3f( 1.0f, 1.0f, -1.0f)
			glVertex3f(-1.0f, 1.0f, -1.0f)
			glVertex3f(-1.0f, 1.0f,  1.0f)
			glVertex3f( 1.0f, 1.0f,  1.0f)


			glColor3f(1.0f, 0.5f, 0.0f)

			glVertex3f( 1.0f, -1.0f,  1.0f)
			glVertex3f(-1.0f, -1.0f,  1.0f)
			glVertex3f(-1.0f, -1.0f, -1.0f)
			glVertex3f( 1.0f, -1.0f, -1.0f)


			glColor3f(1.0f, 0.0f, 0.0f)

			glVertex3f( 1.0f,  1.0f, 1.0f)
			glVertex3f(-1.0f,  1.0f, 1.0f)
			glVertex3f(-1.0f, -1.0f, 1.0f)
			glVertex3f( 1.0f, -1.0f, 1.0f)


			glColor3f(1.0f, 1.0f, 0.0f)

			glVertex3f( 1.0f, -1.0f, -1.0f)
			glVertex3f(-1.0f, -1.0f, -1.0f)
			glVertex3f(-1.0f,  1.0f, -1.0f)
			glVertex3f( 1.0f,  1.0f, -1.0f)


			glColor3f(0.0f, 0.0f, 1.0f)

			glVertex3f(-1.0f,  1.0f,  1.0f)
			glVertex3f(-1.0f,  1.0f, -1.0f)
			glVertex3f(-1.0f, -1.0f, -1.0f)
			glVertex3f(-1.0f, -1.0f,  1.0f)


			glColor3f(1.0f, 0.0f, 1.0f)

			glVertex3f(1.0f,  1.0f, -1.0f)
			glVertex3f(1.0f,  1.0f,  1.0f)
			glVertex3f(1.0f, -1.0f,  1.0f)
			glVertex3f(1.0f, -1.0f, -1.0f)

		glEnd()
	}


	def update()
	{
		rtri  += 0.2
		rquad -= 0.15
	}
}
