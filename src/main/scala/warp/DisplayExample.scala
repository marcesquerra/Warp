package warp

import org.lwjgl.LWJGLException
import org.lwjgl.opengl.Display
import org.lwjgl.opengl.DisplayMode
import org.lwjgl.input.Mouse
import org.lwjgl.input.Keyboard
import org.lwjgl.opengl.GL11._

object DisplayExample extends App
{

	new DisplayExample().start()

}

class DisplayExample
{

	def start()
	{
		try
		{
			Display.setDisplayMode(new DisplayMode(800, 600))
			Display.create()
		}
		catch
		{
			case e: LWJGLException =>
				e.printStackTrace()
				System.exit(0)

		}

		// init OpenGL here
		glMatrixMode(GL_PROJECTION)
		glLoadIdentity()
		glOrtho(0, 800, 0, 600, 1, -1)
		glMatrixMode(GL_MODELVIEW)

		while (!Display.isCloseRequested)
		{

			// Clear the screen and depth buffer
			glClear(GL_COLOR_BUFFER_BIT | GL_DEPTH_BUFFER_BIT)

			// set the color of the quad (R,G,B,A)
			glColor3f(0.5f, 0.5f, 1.0f)

			// draw quad
			glBegin(GL_QUADS)
			glVertex2f(100, 100)
			glVertex2f(100 + 200, 100)
			glVertex2f(100 + 200, 100 + 200)
			glVertex2f(100, 100 + 200)
			glEnd()
 
			pollInput()
			Display.update()
		}

		Display.destroy()
	}

	def pollInput()
	{

		if (Mouse.isButtonDown(0))
		{
			val x = Mouse.getX
			val y = Mouse.getY

			println("MOUSE DOWN @ X: " + x + " Y: " + y)
		}

		if (Keyboard.isKeyDown(Keyboard.KEY_SPACE))
			println("SPACE KEY IS DOWN")

		while (Keyboard.next())
		{
			if (Keyboard.getEventKeyState)
			{
				if (Keyboard.getEventKey == Keyboard.KEY_A)
					println("A Key Pressed")


				if (Keyboard.getEventKey == Keyboard.KEY_S)
					println("S Key Pressed")


				if (Keyboard.getEventKey == Keyboard.KEY_D)
					println("D Key Pressed")

			}
			else
			{

				if (Keyboard.getEventKey == Keyboard.KEY_A)
					println("A Key Released")


				if (Keyboard.getEventKey == Keyboard.KEY_S)
					println("S Key Released")

				if (Keyboard.getEventKey == Keyboard.KEY_D)
					println("D Key Released")

			}
		}
	}
}
