package warp.mecha

import org.lwjgl.LWJGLException
import org.lwjgl.opengl.Display
import org.lwjgl.opengl.DisplayMode
import org.lwjgl.input.Mouse
import org.lwjgl.input.Keyboard
//import org.lwjgl.opengl.GL11._


trait MechaDisplay
{
	private var fullScreen = false
	private var _width     = 800
	private var _height    = 600

	final def withSize(width: Int, height: Int):this.type = {
		fullScreen = false
		_width = width
		_height = height
		this
	}

	final def width  = _width
	final def height = _height

	def onFullScreen():this.type = {fullScreen = true; this}

	private var _fpsTarget = 60
	final def withTargetFps(fpsTarget: Int):this.type = {
		_fpsTarget = fpsTarget
		this
	}

	final def targetFps = _fpsTarget

	final def setTitle(title: String) = Display.setTitle(title)

	final def start()
	{
		try
		{
			if(fullScreen)
			{
				val displayMode = Display.getDesktopDisplayMode()
				_width  = displayMode.getWidth()
				_height = displayMode.getHeight()
				Display.setDisplayMode(displayMode)
				Display.setFullscreen(true)
				Display.setVSyncEnabled(true)
			}
			else
				Display.setDisplayMode(new DisplayMode(_width, _height))
			Display.create()
		}
		catch
		{
			case e: LWJGLException =>
				e.printStackTrace()
				System.exit(0)

		}

		init()

		while (!Display.isCloseRequested)
		{
			Mecha.preFrame

			update()
			render()

			Display.update()
			if(!fullScreen)
				Display.sync(_fpsTarget); // cap fps to 60fps
		}

		Display.destroy()
	}

	def init(): Unit

	def render(): Unit

	def update(): Unit
}
