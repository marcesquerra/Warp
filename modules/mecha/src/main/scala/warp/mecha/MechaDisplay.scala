package warp.mecha

import org.lwjgl.LWJGLException
import org.lwjgl.opengl.Display
import org.lwjgl.opengl.DisplayMode
import org.lwjgl.input.Mouse
import org.lwjgl.input.Keyboard
import org.lwjgl.opengl.GL11._
import warp.graph.Camera


trait MechaDisplay
{
	private var fullScreen = false
	private var _width     = 800
	private var _height    = 600
	private var _aspect    = _width / _height

	final def withSize(width: Int, height: Int):this.type = {
		fullScreen = false
		_width = width
		_height = height
		this
	}

	final def width        = _width
	final def height       = _height
	final def aspectRatio  = _width / _height

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

		_camera = init()

		if(_camera == null) throw new NullPointerException()

		_camera.init(aspectRatio)

		while (!Display.isCloseRequested)
		{
			Mecha.preFrame()

			update()

			glClear(GL_COLOR_BUFFER_BIT | GL_DEPTH_BUFFER_BIT)

			camera.prepare()
			render()

			Display.update()
			if(!fullScreen)
				Display.sync(_fpsTarget)
		}

		Display.destroy()
	}

	private var _camera: Camera = _
	def camera = _camera
	def camera_=(c: Camera) =
		if(c == null) throw new NullPointerException()
		else _camera = c

	def init(): Camera

	def render(): Unit

	def update(): Unit
}
