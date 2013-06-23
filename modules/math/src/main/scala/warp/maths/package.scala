package warp

package object maths
{
	type Real = Double

	implicit class NumericOperations[T](val num: Numeric[T]) extends AnyVal {
		def toReal(in: T): Real = num.toDouble(in)
	}

	implicit def tripleToVector[A, B, C]
			(in: (A, B, C))
			(implicit aNum: Numeric[A], bNum: Numeric[B], cNum: Numeric[C]): Vector =
				new Vector(
						aNum.toReal(in._1),
						bNum.toReal(in._2),
						cNum.toReal(in._3))

	implicit def quadToVector[A, B, C, D]
			(in: (A, B, C, D))
			(implicit aNum: Numeric[A], bNum: Numeric[B], cNum: Numeric[C], dNum: Numeric[D]): Vector =
				new Vector(
						aNum.toReal(in._1),
						bNum.toReal(in._2),
						cNum.toReal(in._3),
						dNum.toReal(in._4))

	implicit class RealOperations[T <% Real](val in: T)(implicit num: Numeric[T]) {

		def ** (v: Vector): Vector = {
			val a = num.toReal(in)
			new Vector(v.x * a, v.y * a, v.z * a, v.w * a)
		}

		def :/ (v: Vector): Vector = {
			val a = num.toReal(in)
			new Vector(a / v.x,  a / v.y, a / v.z, a / v.w)
		}

	}




	implicit def tupleToMatrix[M11, M12, M13, M14,
	                           M21, M22, M23, M24,
	                           M31, M32, M33, M34,
	                           M41, M42, M43, M44]
					(m: (
							M11,   M12,   M13,   M14,
							M21,   M22,   M23,   M24,
							M31,   M32,   M33,   M34,
							M41,   M42,   M43,   M44))
					(implicit
							num11: Numeric[M11],   num12: Numeric[M12],   num13: Numeric[M13],   num14: Numeric[M14],
							num21: Numeric[M21],   num22: Numeric[M22],   num23: Numeric[M23],   num24: Numeric[M24],
							num31: Numeric[M31],   num32: Numeric[M32],   num33: Numeric[M33],   num34: Numeric[M34],
							num41: Numeric[M41],   num42: Numeric[M42],   num43: Numeric[M43],   num44: Numeric[M44]) =
			new Matrix(
					num11.toReal(m._1 ),   num12.toReal(m._2 ),   num13.toReal(m._3 ),   num14.toReal(m._4 ),
					num21.toReal(m._5 ),   num22.toReal(m._6 ),   num23.toReal(m._7 ),   num24.toReal(m._8 ),
					num31.toReal(m._9 ),   num32.toReal(m._10),   num33.toReal(m._11),   num34.toReal(m._12),
					num41.toReal(m._13),   num42.toReal(m._14),   num43.toReal(m._15),   num44.toReal(m._16))


	implicit def tupleToMatrix[M11, M12, M13,
	                           M21, M22, M23,
	                           M31, M32, M33]
					(m: (
							M11,   M12,   M13,
							M21,   M22,   M23,
							M31,   M32,   M33))
					(implicit
							num11: Numeric[M11],   num12: Numeric[M12],   num13: Numeric[M13],
							num21: Numeric[M21],   num22: Numeric[M22],   num23: Numeric[M23],
							num31: Numeric[M31],   num32: Numeric[M32],   num33: Numeric[M33]) =
			new Matrix(
					num11.toReal(m._1),   num12.toReal(m._2),   num13.toReal(m._3),   0.0,
					num21.toReal(m._4),   num22.toReal(m._5),   num23.toReal(m._6),   0.0,
					num31.toReal(m._7),   num32.toReal(m._8),   num33.toReal(m._9),   0.0,
					               0.0,                  0.0,                  0.0,   0.0)





	implicit def tuplesToMatrix[M11, M12, M13, M14,
	                            M21, M22, M23, M24,
	                            M31, M32, M33, M34,
	                            M41, M42, M43, M44]
					(m: (
							(M11,   M12,   M13,   M14),
							(M21,   M22,   M23,   M24),
							(M31,   M32,   M33,   M34),
							(M41,   M42,   M43,   M44)))
					(implicit
							num11: Numeric[M11],   num12: Numeric[M12],   num13: Numeric[M13],   num14: Numeric[M14],
							num21: Numeric[M21],   num22: Numeric[M22],   num23: Numeric[M23],   num24: Numeric[M24],
							num31: Numeric[M31],   num32: Numeric[M32],   num33: Numeric[M33],   num34: Numeric[M34],
							num41: Numeric[M41],   num42: Numeric[M42],   num43: Numeric[M43],   num44: Numeric[M44]) =
			new Matrix(
					num11.toReal(m._1._1),   num12.toReal(m._1._2),   num13.toReal(m._1._3),   num14.toReal(m._1._4),
					num21.toReal(m._2._1),   num22.toReal(m._2._2),   num23.toReal(m._2._3),   num24.toReal(m._2._4),
					num31.toReal(m._3._1),   num32.toReal(m._3._2),   num33.toReal(m._3._3),   num34.toReal(m._3._4),
					num41.toReal(m._4._1),   num42.toReal(m._4._2),   num43.toReal(m._4._3),   num44.toReal(m._4._4))


	implicit def tuplesToMatrix[M11, M12, M13,
	                            M21, M22, M23,
	                            M31, M32, M33]
					(m: (
							(M11,   M12,   M13),
							(M21,   M22,   M23),
							(M31,   M32,   M33)))
					(implicit
							num11: Numeric[M11],   num12: Numeric[M12],   num13: Numeric[M13],
							num21: Numeric[M21],   num22: Numeric[M22],   num23: Numeric[M23],
							num31: Numeric[M31],   num32: Numeric[M32],   num33: Numeric[M33]) =
			new Matrix(
					num11.toReal(m._1._1),   num12.toReal(m._1._2),   num13.toReal(m._1._3),   0.0,
					num21.toReal(m._2._1),   num22.toReal(m._2._2),   num23.toReal(m._2._3),   0.0,
					num31.toReal(m._3._1),   num32.toReal(m._3._2),   num33.toReal(m._3._3),   0.0,
					                  0.0,                     0.0,                     0.0,   0.0)


	implicit def vectorsToMatrix(m: (Vector, Vector, Vector, Vector)) =
		new Matrix(
				m._1.x, m._1.y, m._1.z, m._1.w,
				m._2.x, m._2.y, m._2.z, m._2.w,
				m._3.x, m._3.y, m._3.z, m._3.w,
				m._4.x, m._4.y, m._4.z, m._4.w)

	implicit def vectorsToMatrix(m: (Vector, Vector, Vector)) =
		new Matrix(
				m._1.x, m._1.y, m._1.z, 0.0,
				m._2.x, m._2.y, m._2.z, 0.0,
				m._3.x, m._3.y, m._3.z, 0.0,
				   0.0,    0.0,    0.0, 0.0)
}

