package warp.maths

class Vector(val x: Real, val y: Real, val z: Real, val w: Real = 0.0)
{

	def **[T] (other: T)(implicit num: Numeric[T]): Vector =
	{
		val a = num.toReal(other)
		new Vector(x * a, y * a, z * a, w * a)
	}

	def :/ [T] (other: T)(implicit num: Numeric[T]): Vector =
	{
		val a = num.toReal(other)
		new Vector(x / a, y / a, z / a, w / a)
	}

	def + (v: Vector): Vector =	new Vector(x + v.x, y + v.y, z + v.z, w + v.w)
	def - (v: Vector): Vector =	new Vector(x - v.x, y - v.y, z - v.z, w - v.w)
	def unary_- : Vector = new Vector(-x, -y, -z, -w)
	def unary_+ : Vector = this

	override def toString() = if(w == 0) s"($x, $y, $z)" else s"($x, $y, $z, $w)"

	override lazy val hashCode = (x, y, z, w).hashCode

	override def equals(other: Any) = other match {
	  case v: Vector => v.x == x && v.y == y && v.z == z && v.w == w
	  case _ => false
	}

	def ===(v: Vector) = v.x == x && v.y == y && v.z == z && v.w == w
	def ===[A, B, C]
			(v: (A, B, C))
			(implicit
					aNum: Numeric[A],
					bNum: Numeric[B],
					cNum: Numeric[C]) =
					  aNum.toReal(v._1) == x &&
					  bNum.toReal(v._2) == y &&
					  cNum.toReal(v._3) == z &&
					  w == 0.0

	def ===[A, B, C, D]
			(v: (A, B, C, D))
			(implicit
					aNum: Numeric[A],
					bNum: Numeric[B],
					cNum: Numeric[C],
					dNum: Numeric[D]) =
					  aNum.toReal(v._1) == x &&
					  bNum.toReal(v._2) == y &&
					  cNum.toReal(v._3) == z &&
					  dNum.toReal(v._4) == w


	def mod = Math.sqrt(x*x + y*y + z*z + w*w)
	def *(v: Vector): Real = x * v.x + y * v.y + z * v.z + w * v.w
	def ^(v: Vector) = new Vector(y * v.z -  z * v.y, z * v.x - x * v.z, x * v.y - y * v.x)
	def to(v: Vector) = new VectorTravel(this, v)

	def * (m: Matrix): Vector = new Vector (
				this * m.col1,   this * m.col2,   this * m.col3,   this * m.col4)

	def normalized = this :/ mod

	def mapX(f: Real => Real) = new Vector(f(x),   y ,   z , f(w))
	def mapY(f: Real => Real) = new Vector(  x , f(y),   z , f(w))
	def mapZ(f: Real => Real) = new Vector(  x ,   y , f(z), f(w))
	def mapW(f: Real => Real) = new Vector(  x ,   y ,   z , f(w))

}

class VectorTravel(val a: Vector, val b: Vector)
{
	def lerp(i: Real):Vector = new Vector(
	    (1.0 - i) * a.x   +   i * b.x,
	    (1.0 - i) * a.y   +   i * b.y,
	    (1.0 - i) * a.z   +   i * b.z,
	    (1.0 - i) * a.w   +   i * b.w)

	def lerp[T](i: T)(implicit num: Numeric[T]):Vector =
	  lerp(num.toReal(i))
}

object Vector
{
	def unapply(v: Vector) = Some((v.x, v.y, v.z))
	def apply[A, B, C]
			(x: A, y: B, z: C)
			(implicit aNum: Numeric[A], bNum: Numeric[B], cNum: Numeric[C]): Vector =
				new Vector(
						aNum.toReal(x),
						bNum.toReal(y),
						cNum.toReal(z))

	def apply[A, B, C, D]
			(x: A, y: B, z: C, w: D)
			(implicit aNum: Numeric[A], bNum: Numeric[B], cNum: Numeric[C], dNum: Numeric[D]): Vector =
				new Vector(
						aNum.toReal(x),
						bNum.toReal(y),
						cNum.toReal(z),
						dNum.toReal(w))

	val i:Vector = Vector(1.0, 0.0, 0.0, 0.0)
	val j:Vector = Vector(0.0, 1.0, 0.0, 0.0)
	val k:Vector = Vector(0.0, 0.0, 1.0, 0.0)
}
