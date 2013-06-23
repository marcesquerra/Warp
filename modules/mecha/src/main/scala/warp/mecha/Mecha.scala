package warp.mecha

import org.lwjgl.Sys

object Mecha
{

	def now: Long = (Sys.getTime * 1000) / Sys.getTimerResolution

	private var lastFrame: Long = now
	private var _delta: Int = 0
	private def updateDelta() {
		val time = now
		_delta = (time - lastFrame).toInt
		lastFrame = time
	}

	def delta = _delta

	def preFrame() {
		updateDelta()
		updateFps()
	}

	private var fpsCallback: Int => _ = {fps:Int => ()}

	def onFpsUpdate[T](cb: Int => T) {
		fpsCallback = cb
		cb(0)
	}

	private var lastFpsTime = now
	private var _frameCount = 0
	private var _fps = 0
	private def updateFps() {
		val time = now
		if(time - lastFpsTime > 1000) {
			_fps = _frameCount
			fpsCallback(_frameCount)
			_frameCount = 0
			lastFpsTime = time
		}
		_frameCount += 1
	}

	def fps = _fps
}