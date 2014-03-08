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

object DisplayApp extends App
{

	NeheLesson10
		.onFullScreen()
//		.withTargetFps(60)
		.start()

}
