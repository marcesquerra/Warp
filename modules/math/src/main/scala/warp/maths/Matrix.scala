package warp.maths

class Matrix(
		val m11: Real, val m12: Real, val m13: Real, val m14: Real, 
		val m21: Real, val m22: Real, val m23: Real, val m24: Real, 
		val m31: Real, val m32: Real, val m33: Real, val m34: Real, 
		val m41: Real, val m42: Real, val m43: Real, val m44: Real) {

	override def toString() =
		if(!is3D)
		s"""
			.⎡$m11    $m12    $m13    $m14⎤
			.⎢$m21    $m22    $m23    $m24⎥
			.⎢$m31    $m32    $m33    $m34⎥
			.⎣$m41    $m42    $m43    $m44⎦
			""".trim.stripMargin('.')
		else
		s"""
			.⎡$m11    $m12    $m13⎤
			.⎢$m21    $m22    $m23⎥
			.⎣$m31    $m32    $m33⎦
			""".trim.stripMargin('.')

	def is3D = m14 == 0 && m24 == 0 && m34 == 0 && m41 == 0 && m42 == 0 && m43 == 0 && m44 == 0

	def row1 = Vector(m11, m12, m13, m14)
	def row2 = Vector(m21, m22, m23, m24)
	def row3 = Vector(m31, m32, m33, m34)
	def row4 = Vector(m41, m42, m43, m44)

	def col1 = Vector(m11, m21, m31, m41)
	def col2 = Vector(m12, m22, m32, m42)
	def col3 = Vector(m13, m23, m33, m43)
	def col4 = Vector(m14, m24, m34, m44)

	def * (m: Matrix) = new Matrix (
				row1 * m.col1,   row1 * m.col2,   row1 * m.col3,   row1 * m.col4,
				row2 * m.col1,   row2 * m.col2,   row2 * m.col3,   row2 * m.col4,
				row3 * m.col1,   row3 * m.col2,   row3 * m.col3,   row3 * m.col4,
				row4 * m.col1,   row4 * m.col2,   row4 * m.col3,   row4 * m.col4)

	def * (v: Vector) = new Vector (
				row1 * v,
				row2 * v,
				row3 * v,
				row4 * v)

	def transposed: Matrix = (col1, col2, col3, col4)

}

object Matrix
{
	def apply[M11, M12, M13, M14,
	          M21, M22, M23, M24,
	          M31, M32, M33, M34,
	          M41, M42, M43, M44]
					(
							m11: M11,   m12: M12,   m13: M13,   m14: M14,
							m21: M21,   m22: M22,   m23: M23,   m24: M24,
							m31: M31,   m32: M32,   m33: M33,   m34: M34,
							m41: M41,   m42: M42,   m43: M43,   m44: M44)
					(implicit
							num11: Numeric[M11],   num12: Numeric[M12],   num13: Numeric[M13],   num14: Numeric[M14],
							num21: Numeric[M21],   num22: Numeric[M22],   num23: Numeric[M23],   num24: Numeric[M24],
							num31: Numeric[M31],   num32: Numeric[M32],   num33: Numeric[M33],   num34: Numeric[M34],
							num41: Numeric[M41],   num42: Numeric[M42],   num43: Numeric[M43],   num44: Numeric[M44]) =
			new Matrix(
					num11.toReal(m11),   num12.toReal(m12),   num13.toReal(m13),   num14.toReal(m14),
					num21.toReal(m21),   num22.toReal(m22),   num23.toReal(m23),   num24.toReal(m24),
					num31.toReal(m31),   num32.toReal(m32),   num33.toReal(m33),   num34.toReal(m34),
					num41.toReal(m41),   num42.toReal(m42),   num43.toReal(m43),   num44.toReal(m44))


	def apply[M11, M12, M13,
	          M21, M22, M23,
	          M31, M32, M33]
					(
							m11: M11,   m12: M12,   m13: M13, 
							m21: M21,   m22: M22,   m23: M23, 
							m31: M31,   m32: M32,   m33: M33)
					(implicit
							num11: Numeric[M11],   num12: Numeric[M12],   num13: Numeric[M13],
							num21: Numeric[M21],   num22: Numeric[M22],   num23: Numeric[M23],
							num31: Numeric[M31],   num32: Numeric[M32],   num33: Numeric[M33]) =
			new Matrix(
					num11.toReal(m11),   num12.toReal(m12),   num13.toReal(m13),   0.0,
					num21.toReal(m21),   num22.toReal(m22),   num23.toReal(m23),   0.0,
					num31.toReal(m31),   num32.toReal(m32),   num33.toReal(m33),   0.0,
					0.0,                 0.0,                 0.0,                 0.0)

	def apply(row1: Vector, row2: Vector, row3: Vector, row4: Vector) =
		new Matrix(
				row1.x, row1.y, row1.z, row1.w,
				row2.x, row2.y, row2.z, row2.w,
				row3.x, row3.y, row3.z, row3.w,
				row4.x, row4.y, row4.z, row4.w)

	def apply(row1: Vector, row2: Vector, row3: Vector) =
		new Matrix(
				row1.x, row1.y, row1.z, 0.0,
				row2.x, row2.y, row2.z, 0.0,
				row3.x, row3.y, row3.z, 0.0,
				   0.0,    0.0,    0.0, 0.0)

	val I = new Matrix(1.0, 0.0, 0.0, 0.0,
	                   0.0, 1.0, 0.0, 0.0,
	                   0.0, 0.0, 1.0, 0.0,
	                   0.0, 0.0, 0.0, 1.0)
}


