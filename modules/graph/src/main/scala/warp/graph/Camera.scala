package warp.graph

import org.lwjgl.opengl.GL11._
import org.lwjgl.util.glu.GLU._

import warp.maths._

trait Camera
{
	def prepare()

	private var specs = CameraSpecs(45f, 0.1, 100)
	private var aspectRatio: Float = 0

	final def init(aspectRatio: Float)
	{
		this.aspectRatio = aspectRatio
		specs.load()
	}

	final def updateSpecs(f: CameraSpecs => CameraSpecs) {
		specs = f(specs)
		specs.load()
	}

	sealed case class CameraSpecs(
		fieldOfView: Float,    // Specifies the field of view angle, in degrees, in the y direction.
		      zNear: Real,     // Specifies the distance from the viewer to the near clipping plane (always positive).
		       zFar: Real)     // Specifies the distance from the viewer to the far clipping plane (always positive).
	{
		if(zNear < 0 || zFar < 0) throw new IllegalArgumentException()
		if(zNear >= zFar) throw new IllegalArgumentException()


		final private[Camera] def load()
		{
			glMatrixMode(GL_PROJECTION); // Select The Projection Matrix
			glLoadIdentity(); // Reset The Projection Matrix

			// Calculate The Aspect Ratio Of The Window
			gluPerspective(
					fieldOfView,
					aspectRatio,
					zNear.toFloat,
					zFar.toFloat)

			glMatrixMode(GL_MODELVIEW)
		}

	}


}

