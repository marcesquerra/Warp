package warp

import maths._

object Sample extends App
{

	val v2 = 3 ** (1, 2, 3)
	val v3 = 1 ** Vector(3, 6, 9)
	val v4 = 1 ** (3, 6, 10)
	val v5 = -v4
	val v6 = +v4
	val v7 = (1, 2, 3) + (3, 5, 6)
	val v8 = v6 to v7 lerp 0.7
	val v9 = 3 ** Vector.i

	val Vector(x, y, z) = v2
	val tmp = v7.mod

	println(v6)
	println(v2.hashCode)
	println((3, 6, 9).hashCode)

	println(v2 === (3, 6, 9))
	println((3, 6, 9) === v2)
	println(v2 === v3)

	println(v2 === (3, 6, 91))
	println((3, 62, 9) === v2)
	println(v2 === v4)

	v2 match {
	  case Vector(x, y, z) => println("x: " + x)
	}

	val m: Matrix = (1, 2, 3,
	                 4, 5, 6,
	                 7, 8, 9)

	val m2: Matrix = ((1,2,3),
	                  (1,2,3),
	                  (1,2,3))

	println(m)
	println(m2)
	println(m2.transposed)
	println(m.transposed)

}