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
import java.io.BufferedReader
import java.io.InputStreamReader
import java.util.StringTokenizer
import java.io.IOException
import warp.graph.BasicCamera


object NeheLesson10 extends MechaDisplay
{
	private val piover180     = 0.0174532925f
	private var blend         = false                                           // Blending ON/OFF
	private var bp            = false                                           // B Pressed?
	private var heading       = 0.0

	private var walkbias      = 0.0
	private var walkbiasangle = 0.0

	private var z             = 0.0                                             // Depth Into The Screen
	private val cam           = BasicCamera()

	var list:  Int = _

	override def init(): Camera =
	{
		setupWorld();
		initGL();
		cam
	}

	def render()
	{

		glCallList(list)

	}


	def update()
	{
		if (!Keyboard.isKeyDown(Keyboard.KEY_B))
			bp = false;

		if (Keyboard.isKeyDown(Keyboard.KEY_PRIOR))
			z -= 0.02

		if (Keyboard.isKeyDown(Keyboard.KEY_NEXT))
			z += 0.02

		if (Keyboard.isKeyDown(Keyboard.KEY_UP))
		{

			cam.position = cam.position mapX {_ - Math.sin(heading * piover180) * 0.05}
			cam.position = cam.position mapZ {_ - Math.cos(heading * piover180) * 0.05}

			if (walkbiasangle >= 359.0)
				walkbiasangle = 0.0
			else
				walkbiasangle += 10

			walkbias = Math.sin(walkbiasangle * piover180) / 20.0
		}

		if (Keyboard.isKeyDown(Keyboard.KEY_DOWN))
		{
			cam.position = cam.position mapX {_ + Math.sin(heading * piover180) * 0.05}
			cam.position = cam.position mapZ {_ + Math.cos(heading * piover180) * 0.05}

			if (walkbiasangle <= 1.0)
				walkbiasangle = 359.0
			else
				walkbiasangle -= 10

			walkbias = Math.sin(walkbiasangle * piover180) / 20.0
		}

		if (Keyboard.isKeyDown(Keyboard.KEY_RIGHT))
		{
			heading -= 1.0
			cam.yRotation = heading
		}

		if (Keyboard.isKeyDown(Keyboard.KEY_LEFT))
		{
			heading += 1.0
			cam.yRotation = heading
		}

		if (Keyboard.isKeyDown(Keyboard.KEY_PRIOR))
			cam.xRotation -= 1.0

		if (Keyboard.isKeyDown(Keyboard.KEY_NEXT))
			cam.xRotation += 1.0

		if (Keyboard.isKeyDown(Keyboard.KEY_Q))
			camera.updateSpecs{specs => specs.copy(fieldOfView = specs.fieldOfView + 1)}

		if (Keyboard.isKeyDown(Keyboard.KEY_A))
			camera.updateSpecs{specs => specs.copy(fieldOfView = specs.fieldOfView - 1)}

//		var ytrans = -walkbias-0.25f
		cam.position = cam.position mapY (y => walkbias+0.25)
	}


	private def initGL()
	{
//		glEnable(GL_TEXTURE_2D); // Enable Texture Mapping
		glEnable(GL_DEPTH_TEST)
		glShadeModel(GL_SMOOTH); // Enable Smooth Shading
		glClearColor(0.0f, 0.0f, 0.0f, 0.0f); // Black Background
		glClearDepth(1.0f); // Depth Buffer Setup
		// Really Nice Perspective Calculations
		glHint(GL_PERSPECTIVE_CORRECTION_HINT, GL_NICEST);
	}

	private def setupWorld()
	{
		createLists(loadWorld())
	}

	private def createLists(sector1: Sector) =
	{
		list = glGenLists(1)
		glNewList(list, GL_COMPILE)
			for (t <- sector1.triangle)
				t.render()
		glEndList()
	}

	private def loadWorld(): Sector =
	{
		var x, y, z, u, v = 0.0
		var numtriangles  =   0
		var sector1: Sector = null

		try
		{

			var line = ""
			val dis = new BufferedReader(new InputStreamReader(this.getClass().getResourceAsStream("data/world.txt")))

			import scala.util.control.Breaks._

			breakable
			{
				while ((line = dis.readLine()) != null)
				{
					if (line.trim().length() != 0 && !line.trim().startsWith("//"))
					{
						if (line.startsWith("NUMPOLLIES"))
						{
							var numTriangles = 0
	
							numTriangles = Integer.parseInt(line.substring(line.indexOf("NUMPOLLIES") + "NUMPOLLIES".length() + 1))
							sector1 = new Sector(numTriangles)
		
							break
						}
					}
				}
			}

			for (i <- 0 until sector1.numTriangles)
			{

				for (vert <- 0 until 3)
				{

					breakable
					{
						while ((line = dis.readLine()) != null)
						{
							if (line.trim().length() != 0 && !line.trim().startsWith("//"))
								break;
						}
					}

					if (line != null)
					{
						val st = new StringTokenizer(line, " ");

						sector1.triangle(i).vertex(vert).x = st.nextToken().toDouble
						sector1.triangle(i).vertex(vert).y = st.nextToken().toDouble
						sector1.triangle(i).vertex(vert).z = st.nextToken().toDouble
						sector1.triangle(i).vertex(vert).u = st.nextToken().toDouble
						sector1.triangle(i).vertex(vert).v = st.nextToken().toDouble
					}
				}
			}

			dis.close()

			sector1
		}
		catch
		{
			case ioe: IOException =>
				ioe.printStackTrace()
				throw ioe
		}
	}
}

class Vertex {

	var x = 0.0
	var y = 0.0
	var z = 0.0

	var u = 0.0
	var v = 0.0

	def paint() {
		glColor3d(u,v,0)
		glVertex3d(x, y, z)
	}
}

class Triangle {

	var vertex: Array[Vertex] = new Array(3)

	for (i <- 0 until 3)
		vertex(i) = new Vertex()

	def render() =
	{
		glBegin(GL_TRIANGLES);
			glNormal3f( 0.0f, 0.0f, 1.0f);

			for(v <- vertex) v.paint

		glEnd();
	}
}

class Sector(var numTriangles: Int)
{

	var triangle: Array[Triangle] = new Array(numTriangles)

	for (i <- 0 until numTriangles)
		triangle(i) = new Triangle()

}
