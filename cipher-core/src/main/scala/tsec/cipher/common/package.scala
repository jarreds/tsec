package tsec.cipher

import java.security.spec.AlgorithmParameterSpec

import shapeless.tag
import shapeless.tag.@@
import tsec.cipher.common.mode.ModeKeySpec
import tsec.common._

package object common {

  final case class PlainText(content: Array[Byte]) extends AnyVal
  final case class CipherText[A, M, P](content: Array[Byte], iv: Array[Byte]) {
    def toSingleArray: Array[Byte] = content ++ iv
  }

  object CipherText {
    def fromSingleArray[A2, M2, P2](
      bytes: Array[Byte]
    )(implicit keySpec: ModeKeySpec[M2]): Either[CipherTextError, CipherText[A2, M2, P2]] =
      if (bytes.length < keySpec.ivLength + 1)
        Left(CipherTextError("Array must be nonEmpty"))
      else {
        val ivIx         = bytes.length - keySpec.ivLength
        val ivArray      = new Array[Byte](keySpec.ivLength)
        val contentArray = new Array[Byte](ivIx)
        System.arraycopy(bytes, 0, contentArray, 0, ivIx)
        System.arraycopy(bytes, ivIx, ivArray, 0, keySpec.ivLength)

        Right(CipherText[A2, M2, P2](contentArray, ivArray))
      }
  }

  final case class AAD(aad: Array[Byte]) extends AnyVal

  object AAD {
    def buildFromStringUTF8(string: String) = AAD(string.utf8Bytes)
  }

  protected[tsec] def tagSpec[T](a: AlgorithmParameterSpec): AlgorithmParameterSpec @@ T = tag[T](a)

  type NoSuchInstanceError = NoSuchInstanceError.type

}
