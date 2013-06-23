package warp

import org.lwjgl.opengl.GL11._
import maths._

package graph
{

object WG
{

	def glVertex(v: Vector) = glVertex4d(v.x, v.y, v.z, 1.0)

}


}