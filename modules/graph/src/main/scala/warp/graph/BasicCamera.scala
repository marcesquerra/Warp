package warp.graph

import warp.maths.Vector
import org.lwjgl.opengl.GL11._

class BasicCamera extends Camera
{
	private var _position: Vector = (0, 0, 0)

	def position = _position
	def position_=(p: Vector) =
		if(p == null) throw new NullPointerException
		else _position = p

	var xRotation = 0.0
	var yRotation = 0.0
	var zRotation = 0.0

	def prepare
	{
		glLoadIdentity();                                                       // Reset The View

		glRotated(xRotation, 1.0, 0.0, 0.0)
		glRotated(zRotation, 0.0, 0.0, 1.0)
		glRotated(360.0f - yRotation, 0.0, 1.0, 0.0)

		glTranslated(- _position.x, - _position.y, - _position.z)
	}

}

object BasicCamera
{

	def apply() = new BasicCamera

}
