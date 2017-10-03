package tsec.jwt.algorithms

import cats.MonadError
import tsec.common.{ByteEV, JKeyGenerator}
import tsec.jwt.algorithms.JWTSigAlgo.MT
import tsec.jwt.util.ParseEncodedKeySpec
import tsec.mac.imports._
import tsec.signature.core.{SigAlgoTag, SignerPrograms}
import tsec.signature.imports._

sealed trait JWA[A] {
  val jwtRepr: String
}

object JWA {

  implicit case object HS256 extends JWTMacAlgo[HMACSHA256] {
    val jwtRepr: String = "HS256"
  }

  implicit case object HS384 extends JWTMacAlgo[HMACSHA384] {
    val jwtRepr: String = "HS384"
  }

  implicit case object HS512 extends JWTMacAlgo[HMACSHA512] {
    val jwtRepr: String = "HS512"
  }

  implicit case object NoAlg extends JWA[NoSigningAlgorithm] {
    val jwtRepr: String = "none"
  }

  implicit case object ES256 extends JWTECSig[SHA256withECDSA] {
    val jwtRepr: String = "ES256"
  }

  implicit case object ES384 extends JWTECSig[SHA384withECDSA] {
    val jwtRepr: String = "ES384"
  }

  implicit case object ES512 extends JWTECSig[SHA512withECDSA] {
    val jwtRepr: String = "ES512"
  }

  implicit case object RS256 extends JWTRSASig[SHA256withRSA] {
    val jwtRepr: String = "RS256"
  }

  implicit case object RS384 extends JWTRSASig[SHA384withRSA] {
    val jwtRepr: String = "RS384"
  }

  implicit case object RS512 extends JWTRSASig[SHA512withRSA] {
    val jwtRepr: String = "RS512"
  }

}

abstract class JWTMacAlgo[A](implicit val keyGen: MacKeyGenerator[A]) extends JWA[A]

abstract class JWTSigAlgo[A: SigAlgoTag](implicit gen: ByteEV[A]) extends JWA[A] { //todo: Get rid of this tire fire
  def concatToJCA[F[_]](bytes: Array[Byte])(implicit me: MT[F]): F[Array[Byte]]
  def jcaToConcat[F[_]](bytes: Array[Byte])(implicit me: MT[F]): F[Array[Byte]]
}

abstract class JWTECSig[A: SigAlgoTag: ECKFTag](implicit gen: ByteEV[A]) extends JWTSigAlgo[A] {
  def concatToJCA[F[_]](bytes: Array[Byte])(implicit me: MT[F]): F[Array[Byte]] =
    ParseEncodedKeySpec.concatSignatureToDER[F, A](bytes)
  def jcaToConcat[F[_]](bytes: Array[Byte])(implicit me: MT[F]): F[Array[Byte]] =
    ParseEncodedKeySpec.derToConcat[F, A](bytes)

  @inline def keyGen(implicit keyGen: ECKFTag[A]): ECKFTag[A] = keyGen
}

abstract class JWTRSASig[A: SigAlgoTag](implicit gen: ByteEV[A]) extends JWTSigAlgo[A] {
  def concatToJCA[F[_]](bytes: Array[Byte])(implicit me: MT[F]): F[Array[Byte]] = me.pure(bytes)
  def jcaToConcat[F[_]](bytes: Array[Byte])(implicit me: MT[F]): F[Array[Byte]] = me.pure(bytes)

  @inline def keyGen(implicit keyGen: RSAKFTag[A]): RSAKFTag[A] = keyGen
}

object JWTSigAlgo {
  type MT[F[_]] = MonadError[F, Throwable]
  def fromString[A](alg: String)(implicit o: JWTSigAlgo[A]): Option[JWTSigAlgo[A]] = alg match {
    case o.jwtRepr => Some(o)
    //While we work on signatures, this can be none.
    case _ => None
  }
}

object JWTMacAlgo {
  def fromString[A](alg: String)(implicit o: JWTMacAlgo[A]): Option[JWTMacAlgo[A]] = alg match {
    case o.jwtRepr => Some(o)
    //While we work on signatures, this can be none.
    case _ => None
  }
}
