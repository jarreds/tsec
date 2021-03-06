package tsec.libsodium

import java.nio.charset.Charset

import cats.effect.Sync
import cats.evidence.Is
import tsec.common._
import tsec.libsodium.passwordhashers.PasswordHash$$
import tsec.libsodium.passwordhashers.internal.SodiumPasswordHasher

package object passwordhashers {

  final case class SodiumPasswordError(cause: String) extends TSecError

  private[passwordhashers] val asciiEncoder = Charset.forName("US-ASCII").newEncoder()

  final class PWStrengthParam[PTyp, Str](val opLimit: Int, val memLimit: Int)

  private[tsec] val PasswordHash$$ : HKStringNewt = new HKStringNewt {
    type Repr[A] = String

    def is[G] = Is.refl[String]
  }

  type PasswordHash[A] = PasswordHash$$.Repr[A]

  object PasswordHash {
    def apply[A: SodiumPasswordHasher](string: String): PasswordHash[A]  = is[A].coerce(string)
    @inline def is[A: SodiumPasswordHasher]: Is[String, PasswordHash[A]] = PasswordHash$$.is[A]
  }

  object PasswordStrength {
    object MinStrength
    object InteractiveStrength
    object ModerateStrength
    object SensitiveStrength
  }

  type MinStrength         = PasswordStrength.MinStrength.type
  type InteractiveStrength = PasswordStrength.InteractiveStrength.type
  type ModerateStrength    = PasswordStrength.ModerateStrength.type
  type SensitiveStrength   = PasswordStrength.SensitiveStrength.type

  implicit val argonMinstr: PWStrengthParam[Argon2, MinStrength] = new PWStrengthParam[Argon2, MinStrength](
    ScalaSodium.crypto_pwhash_argon2id_OPSLIMIT_MIN,
    ScalaSodium.crypto_pwhash_argon2id_MEMLIMIT_MIN
  )

  implicit val argonInteractiveStr: PWStrengthParam[Argon2, InteractiveStrength] =
    new PWStrengthParam[Argon2, InteractiveStrength](
      ScalaSodium.crypto_pwhash_argon2id_OPSLIMIT_INTERACTIVE,
      ScalaSodium.crypto_pwhash_argon2id_MEMLIMIT_INTERACTIVE
    )

  implicit val argonModerateStr: PWStrengthParam[Argon2, ModerateStrength] =
    new PWStrengthParam[Argon2, ModerateStrength](
      ScalaSodium.crypto_pwhash_argon2id_OPSLIMIT_MODERATE,
      ScalaSodium.crypto_pwhash_argon2id_MEMLIMIT_MODERATE
    )

  implicit val argonSensitiveStr: PWStrengthParam[Argon2, SensitiveStrength] =
    new PWStrengthParam[Argon2, SensitiveStrength](
      ScalaSodium.crypto_pwhash_argon2id_OPSLIMIT_SENSITIVE,
      ScalaSodium.crypto_pwhash_argon2id_MEMLIMIT_SENSITIVE
    )

  implicit val SodiumSCryptMinstr: PWStrengthParam[SodiumSCrypt, MinStrength] =
    new PWStrengthParam[SodiumSCrypt, MinStrength](
      ScalaSodium.crypto_pwhash_scryptsalsa208sha256_OPSLIMIT_MIN,
      ScalaSodium.crypto_pwhash_scryptsalsa208sha256_MEMLIMIT_MIN
    )

  implicit val SodiumSCryptInteractiveStr: PWStrengthParam[SodiumSCrypt, InteractiveStrength] =
    new PWStrengthParam[SodiumSCrypt, InteractiveStrength](
      ScalaSodium.crypto_pwhash_scryptsalsa208sha256_OPSLIMIT_INTERACTIVE,
      ScalaSodium.crypto_pwhash_scryptsalsa208sha256_MEMLIMIT_INTERACTIVE
    )

  implicit val SodiumSCryptSensitiveStr: PWStrengthParam[SodiumSCrypt, SensitiveStrength] =
    new PWStrengthParam[SodiumSCrypt, SensitiveStrength](
      ScalaSodium.crypto_pwhash_scryptsalsa208sha256_OPSLIMIT_SENSITIVE,
      ScalaSodium.crypto_pwhash_scryptsalsa208sha256_MEMLIMIT_SENSITIVE
    )

}
