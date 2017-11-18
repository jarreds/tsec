package tsec

import cats.effect.Sync
import tsec.internal._
import tsec.jni._

///** Libsodium bindings using jni-ffi.
//  * Inspired from kalium's stuff.
//  *
//  */
//sealed trait ScalaSodium
//    extends Argon2
//    with GenericHash
//    with ShortHash
//    with HmacSha256
//    with HmacSha512
//    with HmacSha512256
//    with SCrypt
//    with SecretBox
//    with CryptoAEAD
//    with OriginalChacha20Poly1305
//    with Chacha20Poly1305IETF
//    with XChacha20Poly1305IETF {
//
//  def randombytes_buf(@Out buf: Array[Byte], @In @size_t size: Int): Unit
//
//  /**
//    * This function isn't thread safe. Be sure to call it once, and before
//    * performing other operations.
//    *
//    * Check libsodium's documentation for more info.
//    */
//  def sodium_init: Int
//
//  def sodium_version_string: String
//
//}

final class ScalaSodium {
  def sodium_init: Int = SodiumJNI.sodium_init

  def sodium_version_string: Array[Byte] = SodiumJNI.sodium_version_string

  def randombytes(dst_buf: Array[Byte], buf_len: Int): Unit =
    SodiumJNI.randombytes(dst_buf, buf_len)

  def randombytes_random: Int = SodiumJNI.randombytes_random

  def randombytes_uniform(upper_bound: Int): Int = SodiumJNI.randombytes_uniform(upper_bound)

  def randombytes_buf(buff: Array[Byte], buff_len: Int): Unit =
    SodiumJNI.randombytes_buf(buff, buff_len)

  def randombytes_close: Int = SodiumJNI.randombytes_close

  def randombytes_stir(): Unit =
    SodiumJNI.randombytes_stir

  def sodium_increment(src_dst_number: Array[Byte], number_len: Int): Unit =
    SodiumJNI.sodium_increment(src_dst_number, number_len)

  def crypto_secretbox_keybytes: Int = SodiumJNI.crypto_secretbox_keybytes

  def crypto_secretbox_noncebytes: Int = SodiumJNI.crypto_secretbox_noncebytes

  def crypto_secretbox_macbytes: Int = SodiumJNI.crypto_secretbox_macbytes

  def crypto_secretbox_zerobytes: Int = SodiumJNI.crypto_secretbox_zerobytes

  def crypto_secretbox_boxzerobytes: Int = SodiumJNI.crypto_secretbox_boxzerobytes

  def crypto_secretbox_primitive: Array[Byte] = SodiumJNI.crypto_secretbox_primitive

  def crypto_secretbox_easy(
      dst_cipher: Array[Byte],
      src_plain: Array[Byte],
      plain_len: Int,
      nonce: Array[Byte],
      secret_key: Array[Byte]
  ): Int = SodiumJNI.crypto_secretbox_easy(dst_cipher, src_plain, plain_len, nonce, secret_key)

  def crypto_secretbox_open_easy(
      dst_plain: Array[Byte],
      src_cipher: Array[Byte],
      cipher_len: Int,
      nonce: Array[Byte],
      secret_key: Array[Byte]
  ): Int = SodiumJNI.crypto_secretbox_open_easy(dst_plain, src_cipher, cipher_len, nonce, secret_key)

  def crypto_secretbox_detached(
      dst_cipher: Array[Byte],
      mac: Array[Byte],
      src_plain: Array[Byte],
      plain_len: Int,
      nonce: Array[Byte],
      secretkey: Array[Byte]
  ): Int = SodiumJNI.crypto_secretbox_detached(dst_cipher, mac, src_plain, plain_len, nonce, secretkey)

  def crypto_secretbox_open_detached(
      dst_plain: Array[Byte],
      src_cipher: Array[Byte],
      mac: Array[Byte],
      cipher_len: Int,
      nonce: Array[Byte],
      secretkey: Array[Byte]
  ): Int = SodiumJNI.crypto_secretbox_open_detached(dst_plain, src_cipher, mac, cipher_len, nonce, secretkey)

  def crypto_scalarmult_bytes: Int = SodiumJNI.crypto_scalarmult_bytes

  def crypto_scalarmult_scalarbytes: Int = SodiumJNI.crypto_scalarmult_scalarbytes

  def crypto_scalarmult_primitive: Array[Byte] = SodiumJNI.crypto_scalarmult_primitive

  def crypto_scalarmult_base(q: Array[Byte], n: Array[Byte]): Int = SodiumJNI.crypto_scalarmult_base(q, n)

  def crypto_scalarmult(q: Array[Byte], n: Array[Byte], p: Array[Byte]): Int = SodiumJNI.crypto_scalarmult(q, n, p)

  def crypto_box_seedbytes: Int = SodiumJNI.crypto_box_seedbytes

  def crypto_box_publickeybytes: Int = SodiumJNI.crypto_box_publickeybytes

  def crypto_box_secretkeybytes: Int = SodiumJNI.crypto_box_secretkeybytes

  def crypto_box_noncebytes: Int = SodiumJNI.crypto_box_noncebytes

  def crypto_box_macbytes: Int = SodiumJNI.crypto_box_macbytes

  def crypto_box_primitive: Array[Byte] = SodiumJNI.crypto_box_primitive

  def crypto_box_keypair(dst_public_Key: Array[Byte], dst_private_key: Array[Byte]): Int =
    SodiumJNI.crypto_box_keypair(dst_public_Key, dst_private_key)

  def crypto_box_seed_keypair(dst_public_key: Array[Byte], dst_private_key: Array[Byte], src_seed: Array[Byte]): Int =
    SodiumJNI.crypto_box_seed_keypair(dst_public_key, dst_private_key, src_seed)

  def crypto_box_easy(
      dst_cipher: Array[Byte],
      src_plain: Array[Byte],
      plain_len: Int,
      nonce: Array[Byte],
      remote_public_key: Array[Byte],
      local_private_key: Array[Byte]
  ): Int = SodiumJNI.crypto_box_easy(dst_cipher, src_plain, plain_len, nonce, remote_public_key, local_private_key)

  def crypto_box_open_easy(
      dst_plain: Array[Byte],
      src_cipher: Array[Byte],
      cipher_len: Int,
      nonce: Array[Byte],
      remote_public_key: Array[Byte],
      local_private_key: Array[Byte]
  ): Int =
    SodiumJNI.crypto_box_open_easy(dst_plain, src_cipher, cipher_len, nonce, remote_public_key, local_private_key)

  def crypto_box_detached(
      dst_cipher: Array[Byte],
      dst_mac: Array[Byte],
      src_plain: Array[Byte],
      plain_len: Int,
      nonces: Array[Byte],
      remote_public_key: Array[Byte],
      local_private_key: Array[Byte]
  ): Int =
    SodiumJNI.crypto_box_detached(
      dst_cipher,
      dst_mac,
      src_plain,
      plain_len,
      nonces,
      remote_public_key,
      local_private_key
    )

  def crypto_box_open_detached(
      dst_plain: Array[Byte],
      src_cipher: Array[Byte],
      src_mac: Array[Byte],
      cipher_len: Int,
      nonce: Array[Byte],
      remote_public_key: Array[Byte],
      local_private_key: Array[Byte]
  ): Int =
    SodiumJNI.crypto_box_open_detached(
      dst_plain,
      src_cipher,
      src_mac,
      cipher_len,
      nonce,
      remote_public_key,
      local_private_key
    )

  def crypto_box_beforenmbytes: Int = SodiumJNI.crypto_box_beforenmbytes

  def crypto_box_beforenm(
      dst_shared_key: Array[Byte],
      remote_public_key: Array[Byte],
      local_private_key: Array[Byte]
  ): Int = SodiumJNI.crypto_box_beforenm(dst_shared_key, remote_public_key, local_private_key)

  def crypto_box_easy_afternm(
      dst_cipher: Array[Byte],
      src_plain: Array[Byte],
      plain_len: Int,
      nonce: Array[Byte],
      shared_key: Array[Byte]
  ): Int = SodiumJNI.crypto_box_easy_afternm(dst_cipher, src_plain, plain_len, nonce, shared_key)

  def crypto_box_open_easy_afternm(
      dst_plain: Array[Byte],
      src_cipher: Array[Byte],
      cipher_len: Int,
      nonce: Array[Byte],
      shared_key: Array[Byte]
  ): Int = SodiumJNI.crypto_box_open_easy_afternm(dst_plain, src_cipher, cipher_len, nonce, shared_key)

  def crypto_box_detached_afternm(
      dst_cipher: Array[Byte],
      dst_mac: Array[Byte],
      src_plain: Array[Byte],
      plain_len: Int,
      nonce: Array[Byte],
      shared_key: Array[Byte]
  ): Int = SodiumJNI.crypto_box_detached_afternm(dst_cipher, dst_mac, src_plain, plain_len, nonce, shared_key)

  def crypto_box_open_detached_afternm(
      dst_plain: Array[Byte],
      src_cipher: Array[Byte],
      src_mac: Array[Byte],
      cipher_len: Int,
      nonce: Array[Byte],
      shared_key: Array[Byte]
  ): Int = SodiumJNI.crypto_box_open_detached_afternm(dst_plain, src_cipher, src_mac, cipher_len, nonce, shared_key)

  def crypto_box_sealbytes: Int = SodiumJNI.crypto_box_sealbytes

  def crypto_box_seal(
      dst_cipher: Array[Byte],
      src_plain: Array[Byte],
      plain_len: Int,
      remote_public_key: Array[Byte]
  ): Int = SodiumJNI.crypto_box_seal(dst_cipher, src_plain, plain_len, remote_public_key)

  def crypto_box_seal_open(
      dst_plain: Array[Byte],
      src_cipher: Array[Byte],
      cipher_len: Int,
      local_public_key: Array[Byte],
      local_private_key: Array[Byte]
  ): Int = SodiumJNI.crypto_box_seal_open(dst_plain, src_cipher, cipher_len, local_public_key, local_private_key)

  def crypto_box_zerobytes: Int = SodiumJNI.crypto_box_zerobytes

  def crypto_box_boxzerobytes: Int = SodiumJNI.crypto_box_boxzerobytes

  def crypto_box(
      dst_cipher: Array[Byte],
      src_msg: Array[Byte],
      msg_len: Int,
      src_nonce: Array[Byte],
      src_pub: Array[Byte],
      src_secret: Array[Byte]
  ): Int = SodiumJNI.crypto_box(dst_cipher, src_msg, msg_len, src_nonce, src_pub, src_secret)

  def crypto_box_open(
      dst_msg: Array[Byte],
      src_cipher: Array[Byte],
      cipher_len: Int,
      src_nonce: Array[Byte],
      src_pub: Array[Byte],
      src_secret: Array[Byte]
  ): Int = SodiumJNI.crypto_box_open(dst_msg, src_cipher, cipher_len, src_nonce, src_pub, src_secret)

  def crypto_box_afternm(
      dst_cipher: Array[Byte],
      src_msg: Array[Byte],
      msg_len: Int,
      src_nonce: Array[Byte],
      src_key: Array[Byte]
  ): Int = SodiumJNI.crypto_box_afternm(dst_cipher, src_msg, msg_len, src_nonce, src_key)

  def crypto_box_open_afternm(
      dst_msg: Array[Byte],
      src_cipher: Array[Byte],
      cipher_len: Int,
      src_nonce: Array[Byte],
      src_key: Array[Byte]
  ): Int = SodiumJNI.crypto_box_open_afternm(dst_msg, src_cipher, cipher_len, src_nonce, src_key)

  def crypto_sign_bytes: Int = SodiumJNI.crypto_sign_bytes

  def crypto_sign_seedbytes: Int = SodiumJNI.crypto_sign_seedbytes

  def crypto_sign_publickeybytes: Int = SodiumJNI.crypto_sign_publickeybytes

  def crypto_sign_secretkeybytes: Int = SodiumJNI.crypto_sign_secretkeybytes

  def crypto_sign_primitive: Array[Byte] = SodiumJNI.crypto_sign_primitive

  def crypto_sign_keypair(dst_public_Key: Array[Byte], dst_private_key: Array[Byte]): Int =
    SodiumJNI.crypto_sign_keypair(dst_public_Key, dst_private_key)

  def crypto_sign_seed_keypair(dst_public_Key: Array[Byte], dst_private_key: Array[Byte], src_seed: Array[Byte]): Int =
    SodiumJNI.crypto_sign_seed_keypair(dst_public_Key, dst_private_key, src_seed)

  def crypto_sign(
      dst_signed_msg: Array[Byte],
      signed_msg_len: Array[Int],
      src_msg: Array[Byte],
      msg_len: Int,
      local_private_key: Array[Byte]
  ): Int = SodiumJNI.crypto_sign(dst_signed_msg, signed_msg_len, src_msg, msg_len, local_private_key)

  def crypto_sign_open(
      dst_msg: Array[Byte],
      msg_len: Array[Int],
      src_signed_msg: Array[Byte],
      signed_msg_len: Int,
      remote_public_key: Array[Byte]
  ): Int = SodiumJNI.crypto_sign_open(dst_msg, msg_len, src_signed_msg, signed_msg_len, remote_public_key)

  def crypto_sign_detached(
      dst_signature: Array[Byte],
      signature_len: Array[Int],
      src_msg: Array[Byte],
      msg_len: Int,
      local_private_key: Array[Byte]
  ): Int = SodiumJNI.crypto_sign_detached(dst_signature, signature_len, src_msg, msg_len, local_private_key)

  def crypto_sign_verify_detached(
      src_signature: Array[Byte],
      src_msg: Array[Byte],
      msg_len: Int,
      remote_public_key: Array[Byte]
  ): Int = SodiumJNI.crypto_sign_verify_detached(src_signature, src_msg, msg_len, remote_public_key)

  def crypto_sign_ed25519_sk_to_seed(dst_seed: Array[Byte], src_private_key: Array[Byte]): Int =
    SodiumJNI.crypto_sign_ed25519_sk_to_seed(dst_seed, src_private_key)

  def crypto_sign_ed25519_sk_to_pk(dst_public_key: Array[Byte], src_private_key: Array[Byte]): Int =
    SodiumJNI.crypto_sign_ed25519_sk_to_pk(dst_public_key, src_private_key)

  def crypto_generichash_bytes: Int = SodiumJNI.crypto_generichash_bytes

  def crypto_generichash_bytes_min: Int = SodiumJNI.crypto_generichash_bytes_min

  def crypto_generichash_bytes_max: Int = SodiumJNI.crypto_generichash_bytes_max

  def crypto_generichash_keybytes: Int = SodiumJNI.crypto_generichash_keybytes

  def crypto_generichash_keybytes_min: Int = SodiumJNI.crypto_generichash_keybytes_min

  def crypto_generichash_keybytes_max: Int = SodiumJNI.crypto_generichash_keybytes_max

  def crypto_generichash_primitive: Array[Byte] = SodiumJNI.crypto_generichash_primitive

  def crypto_generichash(
      dst_hash: Array[Byte],
      dst_len: Int,
      src_input: Array[Byte],
      input_len: Int,
      src_key: Array[Byte],
      key_len: Int
  ): Int = SodiumJNI.crypto_generichash(dst_hash, dst_len, src_input, input_len, src_key, key_len)

  def crypto_generichash_statebytes: Int = SodiumJNI.crypto_generichash_statebytes

  def crypto_generichash_init(state: Array[Byte], src_key: Array[Byte], key_len: Int, out_len: Int): Int =
    SodiumJNI.crypto_generichash_init(state, src_key, key_len, out_len)

  def crypto_generichash_update(state: Array[Byte], src_input: Array[Byte], input_len: Int): Int =
    SodiumJNI.crypto_generichash_update(state, src_input, input_len)

  def crypto_generichash_final(state: Array[Byte], dst_out: Array[Byte], out_len: Int): Int =
    SodiumJNI.crypto_generichash_final(state, dst_out, out_len)

  def crypto_shorthash_bytes: Int = SodiumJNI.crypto_shorthash_bytes

  def crypto_shorthash_keybytes: Int = SodiumJNI.crypto_shorthash_keybytes

  def crypto_shorthash_primitive: Array[Byte] = SodiumJNI.crypto_shorthash_primitive

  def crypto_shorthash(dst_out: Array[Byte], src_input: Array[Byte], input_len: Int, src_key: Array[Byte]): Int =
    SodiumJNI.crypto_shorthash(dst_out, src_input, input_len, src_key)

  def crypto_auth_bytes: Int = SodiumJNI.crypto_auth_bytes

  def crypto_auth_keybytes: Int = SodiumJNI.crypto_auth_keybytes

  def crypto_auth_primitive: Array[Byte] = SodiumJNI.crypto_auth_primitive

  def crypto_auth(dst_mac: Array[Byte], src_input: Array[Byte], input_len: Int, src_key: Array[Byte]): Int =
    SodiumJNI.crypto_auth(dst_mac, src_input, input_len, src_key)

  def crypto_auth_verify(src_mac: Array[Byte], src_input: Array[Byte], input_len: Int, src_key: Array[Byte]): Int =
    SodiumJNI.crypto_auth_verify(src_mac, src_input, input_len, src_key)

  def crypto_onetimeauth_bytes: Int = SodiumJNI.crypto_onetimeauth_bytes

  def crypto_onetimeauth_keybytes: Int = SodiumJNI.crypto_onetimeauth_keybytes

  def crypto_onetimeauth_primitive: Array[Byte] = SodiumJNI.crypto_onetimeauth_primitive

  def crypto_onetimeauth(dst_out: Array[Byte], src_input: Array[Byte], input_len: Int, src_key: Array[Byte]): Int =
    SodiumJNI.crypto_onetimeauth(dst_out, src_input, input_len, src_key)

  def crypto_onetimeauth_verify(
      src_mac: Array[Byte],
      src_input: Array[Byte],
      input_len: Int,
      src_key: Array[Byte]
  ): Int = SodiumJNI.crypto_onetimeauth_verify(src_mac, src_input, input_len, src_key)

  def crypto_onetimeauth_statebytes: Int = SodiumJNI.crypto_onetimeauth_statebytes

  def crypto_onetimeauth_init(dst_state: Array[Byte], src_key: Array[Byte]): Int =
    SodiumJNI.crypto_onetimeauth_init(dst_state, src_key)

  def crypto_onetimeauth_update(dst_state: Array[Byte], src_input: Array[Byte], input_len: Int): Int =
    SodiumJNI.crypto_onetimeauth_update(dst_state, src_input, input_len)

  def crypto_onetimeauth_final(final_state: Array[Byte], dst_out: Array[Byte]): Int =
    SodiumJNI.crypto_onetimeauth_final(final_state, dst_out)

  def crypto_aead_chacha20poly1305_encrypt(
      c: Array[Byte],
      clen_p: Array[Int],
      m: Array[Byte],
      mlen: Int,
      ad: Array[Byte],
      adlen: Int,
      nsec: Array[Byte],
      npub: Array[Byte],
      k: Array[Byte]
  ): Int = SodiumJNI.crypto_aead_chacha20poly1305_encrypt(c, clen_p, m, mlen, ad, adlen, nsec, npub, k)

  def crypto_aead_chacha20poly1305_decrypt(
      m: Array[Byte],
      mlen_p: Array[Int],
      nsec: Array[Byte],
      c: Array[Byte],
      clen: Int,
      ad: Array[Byte],
      adlen: Int,
      npub: Array[Byte],
      k: Array[Byte]
  ): Int = SodiumJNI.crypto_aead_chacha20poly1305_decrypt(m, mlen_p, nsec, c, clen, ad, adlen, npub, k)

  def crypto_aead_chacha20poly1305_encrypt_detached(
      c: Array[Byte],
      mac: Array[Byte],
      maclen_p: Array[Int],
      m: Array[Byte],
      mlen: Int,
      ad: Array[Byte],
      adlen: Int,
      nsec: Array[Byte],
      npub: Array[Byte],
      k: Array[Byte]
  ): Int = SodiumJNI.crypto_aead_chacha20poly1305_encrypt_detached(c, mac, maclen_p, m, mlen, ad, adlen, nsec, npub, k)

  def crypto_aead_chacha20poly1305_decrypt_detached(
      m: Array[Byte],
      nsec: Array[Byte],
      c: Array[Byte],
      clen: Int,
      mac: Array[Byte],
      ad: Array[Byte],
      adlen: Int,
      npub: Array[Byte],
      k: Array[Byte]
  ): Int = SodiumJNI.crypto_aead_chacha20poly1305_decrypt_detached(m, nsec, c, clen, mac, ad, adlen, npub, k)

  def crypto_aead_chacha20poly1305_ietf_encrypt(
      c: Array[Byte],
      clen_p: Array[Int],
      m: Array[Byte],
      mlen: Int,
      ad: Array[Byte],
      adlen: Int,
      nsec: Array[Byte],
      npub: Array[Byte],
      k: Array[Byte]
  ): Int = SodiumJNI.crypto_aead_chacha20poly1305_ietf_encrypt(c, clen_p, m, mlen, ad, adlen, nsec, npub, k)

  def crypto_aead_chacha20poly1305_ietf_decrypt(
      m: Array[Byte],
      mlen_p: Array[Int],
      nsec: Array[Byte],
      c: Array[Byte],
      clen: Int,
      ad: Array[Byte],
      adlen: Int,
      npub: Array[Byte],
      k: Array[Byte]
  ): Int = SodiumJNI.crypto_aead_chacha20poly1305_ietf_decrypt(m, mlen_p, nsec, c, clen, ad, adlen, npub, k)

  def crypto_aead_chacha20poly1305_ietf_encrypt_detached(
      c: Array[Byte],
      mac: Array[Byte],
      maclen_p: Array[Int],
      m: Array[Byte],
      mlen: Int,
      ad: Array[Byte],
      adlen: Int,
      nsec: Array[Byte],
      npub: Array[Byte],
      k: Array[Byte]
  ): Int =
    SodiumJNI.crypto_aead_chacha20poly1305_ietf_encrypt_detached(c, mac, maclen_p, m, mlen, ad, adlen, nsec, npub, k)

  def crypto_aead_chacha20poly1305_ietf_decrypt_detached(
      m: Array[Byte],
      nsec: Array[Byte],
      c: Array[Byte],
      clen: Int,
      mac: Array[Byte],
      ad: Array[Byte],
      adlen: Int,
      npub: Array[Byte],
      k: Array[Byte]
  ): Int = SodiumJNI.crypto_aead_chacha20poly1305_ietf_decrypt_detached(m, nsec, c, clen, mac, ad, adlen, npub, k)

  def crypto_aead_xchacha20poly1305_ietf_encrypt(
      c: Array[Byte],
      clen_p: Array[Int],
      m: Array[Byte],
      mlen: Int,
      ad: Array[Byte],
      adlen: Int,
      nsec: Array[Byte],
      npub: Array[Byte],
      k: Array[Byte]
  ): Int = SodiumJNI.crypto_aead_xchacha20poly1305_ietf_encrypt(c, clen_p, m, mlen, ad, adlen, nsec, npub, k)

  def crypto_aead_xchacha20poly1305_ietf_decrypt(
      m: Array[Byte],
      mlen_p: Array[Int],
      nsec: Array[Byte],
      c: Array[Byte],
      clen: Int,
      ad: Array[Byte],
      adlen: Int,
      npub: Array[Byte],
      k: Array[Byte]
  ): Int = SodiumJNI.crypto_aead_xchacha20poly1305_ietf_decrypt(m, mlen_p, nsec, c, clen, ad, adlen, npub, k)

  def crypto_aead_xchacha20poly1305_ietf_encrypt_detached(
      c: Array[Byte],
      mac: Array[Byte],
      maclen_p: Array[Int],
      m: Array[Byte],
      mlen: Int,
      ad: Array[Byte],
      adlen: Int,
      nsec: Array[Byte],
      npub: Array[Byte],
      k: Array[Byte]
  ): Int =
    SodiumJNI.crypto_aead_xchacha20poly1305_ietf_encrypt_detached(c, mac, maclen_p, m, mlen, ad, adlen, nsec, npub, k)

  def crypto_aead_xchacha20poly1305_ietf_decrypt_detached(
      m: Array[Byte],
      nsec: Array[Byte],
      c: Array[Byte],
      clen: Int,
      mac: Array[Byte],
      ad: Array[Byte],
      adlen: Int,
      npub: Array[Byte],
      k: Array[Byte]
  ): Int = SodiumJNI.crypto_aead_xchacha20poly1305_ietf_decrypt_detached(m, nsec, c, clen, mac, ad, adlen, npub, k)

  def crypto_auth_hmacsha256_bytes: Int = SodiumJNI.crypto_auth_hmacsha256_bytes

  def crypto_auth_hmacsha256_keybytes: Int = SodiumJNI.crypto_auth_hmacsha256_keybytes

  def crypto_auth_hmacsha256(out: Array[Byte], in: Array[Byte], inlen: Int, k: Array[Byte]): Int =
    SodiumJNI.crypto_auth_hmacsha256(out, in, inlen, k)

  def crypto_auth_hmacsha256_verify(h: Array[Byte], in: Array[Byte], inlen: Int, k: Array[Byte]): Int =
    SodiumJNI.crypto_auth_hmacsha256_verify(h, in, inlen, k)

  def crypto_auth_hmacsha256_statebytes: Int = SodiumJNI.crypto_auth_hmacsha256_statebytes

  def crypto_auth_hmacsha256_init(state: Array[Byte], key: Array[Byte], keylen: Int): Int =
    SodiumJNI.crypto_auth_hmacsha256_init(state, key, keylen)

  def crypto_auth_hmacsha256_update(state: Array[Byte], in: Array[Byte], inlen: Int): Int =
    SodiumJNI.crypto_auth_hmacsha256_update(state, in, inlen)

  def crypto_auth_hmacsha256_final(state: Array[Byte], out: Array[Byte]): Int =
    SodiumJNI.crypto_auth_hmacsha256_final(state, out)

  def crypto_auth_hmacsha512_bytes: Int = SodiumJNI.crypto_auth_hmacsha512_bytes

  def crypto_auth_hmacsha512_keybytes: Int = SodiumJNI.crypto_auth_hmacsha512_keybytes

  def crypto_auth_hmacsha512(out: Array[Byte], in: Array[Byte], inlen: Int, k: Array[Byte]): Int =
    SodiumJNI.crypto_auth_hmacsha512(out, in, inlen, k)

  def crypto_auth_hmacsha512_verify(h: Array[Byte], in: Array[Byte], inlen: Int, k: Array[Byte]): Int =
    SodiumJNI.crypto_auth_hmacsha512_verify(h, in, inlen, k)

  def crypto_auth_hmacsha512_statebytes: Int = SodiumJNI.crypto_auth_hmacsha512_statebytes

  def crypto_auth_hmacsha512_init(state: Array[Byte], key: Array[Byte], keylen: Int): Int =
    SodiumJNI.crypto_auth_hmacsha512_init(state, key, keylen)

  def crypto_auth_hmacsha512_update(state: Array[Byte], in: Array[Byte], inlen: Int): Int =
    SodiumJNI.crypto_auth_hmacsha512_update(state, in, inlen)

  def crypto_auth_hmacsha512_final(state: Array[Byte], out: Array[Byte]): Int =
    SodiumJNI.crypto_auth_hmacsha512_final(state, out)

  def crypto_auth_hmacsha512256_bytes: Int = SodiumJNI.crypto_auth_hmacsha512256_bytes

  def crypto_auth_hmacsha512256_keybytes: Int = SodiumJNI.crypto_auth_hmacsha512256_keybytes

  def crypto_auth_hmacsha512256(out: Array[Byte], in: Array[Byte], inlen: Int, k: Array[Byte]): Int =
    SodiumJNI.crypto_auth_hmacsha512256(out, in, inlen, k)

  def crypto_auth_hmacsha512256_verify(h: Array[Byte], in: Array[Byte], inlen: Int, k: Array[Byte]): Int =
    SodiumJNI.crypto_auth_hmacsha512256_verify(h, in, inlen, k)

  def crypto_auth_hmacsha512256_statebytes: Int = SodiumJNI.crypto_auth_hmacsha512256_statebytes

  def crypto_auth_hmacsha512256_init(state: Array[Byte], key: Array[Byte], keylen: Int): Int =
    SodiumJNI.crypto_auth_hmacsha512256_init(state, key, keylen)

  def crypto_auth_hmacsha512256_update(state: Array[Byte], in: Array[Byte], inlen: Int): Int =
    SodiumJNI.crypto_auth_hmacsha512256_update(state, in, inlen)

  def crypto_auth_hmacsha512256_final(state: Array[Byte], out: Array[Byte]): Int =
    SodiumJNI.crypto_auth_hmacsha512256_final(state, out)

  def crypto_box_curve25519xsalsa20poly1305_seedbytes: Int = SodiumJNI.crypto_box_curve25519xsalsa20poly1305_seedbytes

  def crypto_box_curve25519xsalsa20poly1305_publickeybytes: Int =
    SodiumJNI.crypto_box_curve25519xsalsa20poly1305_publickeybytes

  def crypto_box_curve25519xsalsa20poly1305_secretkeybytes: Int =
    SodiumJNI.crypto_box_curve25519xsalsa20poly1305_secretkeybytes

  def crypto_box_curve25519xsalsa20poly1305_beforenmbytes: Int =
    SodiumJNI.crypto_box_curve25519xsalsa20poly1305_beforenmbytes

  def crypto_box_curve25519xsalsa20poly1305_noncebytes: Int = SodiumJNI.crypto_box_curve25519xsalsa20poly1305_noncebytes

  def crypto_box_curve25519xsalsa20poly1305_zerobytes: Int = SodiumJNI.crypto_box_curve25519xsalsa20poly1305_zerobytes

  def crypto_box_curve25519xsalsa20poly1305_boxzerobytes: Int =
    SodiumJNI.crypto_box_curve25519xsalsa20poly1305_boxzerobytes

  def crypto_box_curve25519xsalsa20poly1305_macbytes: Int = SodiumJNI.crypto_box_curve25519xsalsa20poly1305_macbytes

  def crypto_box_curve25519xsalsa20poly1305(
      c: Array[Byte],
      m: Array[Byte],
      mlen: Int,
      n: Array[Byte],
      pk: Array[Byte],
      sk: Array[Byte]
  ): Int = SodiumJNI.crypto_box_curve25519xsalsa20poly1305(c, m, mlen, n, pk, sk)

  def crypto_box_curve25519xsalsa20poly1305_open(
      m: Array[Byte],
      c: Array[Byte],
      clen: Int,
      n: Array[Byte],
      pk: Array[Byte],
      sk: Array[Byte]
  ): Int = SodiumJNI.crypto_box_curve25519xsalsa20poly1305_open(m, c, clen, n, pk, sk)

  def crypto_box_curve25519xsalsa20poly1305_seed_keypair(pk: Array[Byte], sk: Array[Byte], seed: Array[Byte]): Int =
    SodiumJNI.crypto_box_curve25519xsalsa20poly1305_seed_keypair(pk, sk, seed)

  def crypto_box_curve25519xsalsa20poly1305_keypair(pk: Array[Byte], sk: Array[Byte]): Int =
    SodiumJNI.crypto_box_curve25519xsalsa20poly1305_keypair(pk, sk)

  def crypto_box_curve25519xsalsa20poly1305_beforenm(k: Array[Byte], pk: Array[Byte], sk: Array[Byte]): Int =
    SodiumJNI.crypto_box_curve25519xsalsa20poly1305_beforenm(k, pk, sk)

  def crypto_box_curve25519xsalsa20poly1305_afternm(
      c: Array[Byte],
      m: Array[Byte],
      mlen: Int,
      n: Array[Byte],
      k: Array[Byte]
  ): Int = SodiumJNI.crypto_box_curve25519xsalsa20poly1305_afternm(c, m, mlen, n, k)

  def crypto_box_curve25519xsalsa20poly1305_open_afternm(
      m: Array[Byte],
      c: Array[Byte],
      clen: Int,
      n: Array[Byte],
      k: Array[Byte]
  ): Int = SodiumJNI.crypto_box_curve25519xsalsa20poly1305_open_afternm(m, c, clen, n, k)

  def crypto_core_hsalsa20_outputbytes: Int = SodiumJNI.crypto_core_hsalsa20_outputbytes

  def crypto_core_hsalsa20_inputbytes: Int = SodiumJNI.crypto_core_hsalsa20_inputbytes

  def crypto_core_hsalsa20_keybytes: Int = SodiumJNI.crypto_core_hsalsa20_keybytes

  def crypto_core_hsalsa20_constbytes: Int = SodiumJNI.crypto_core_hsalsa20_constbytes

  def crypto_core_hsalsa20(out: Array[Byte], in: Array[Byte], k: Array[Byte], c: Array[Byte]): Int =
    SodiumJNI.crypto_core_hsalsa20(out, in, k, c)

  def crypto_core_salsa20_outputbytes: Int = SodiumJNI.crypto_core_salsa20_outputbytes

  def crypto_core_salsa20_inputbytes: Int = SodiumJNI.crypto_core_salsa20_inputbytes

  def crypto_core_salsa20_keybytes: Int = SodiumJNI.crypto_core_salsa20_keybytes

  def crypto_core_salsa20_constbytes: Int = SodiumJNI.crypto_core_salsa20_constbytes

  def crypto_core_salsa20(out: Array[Byte], in: Array[Byte], k: Array[Byte], c: Array[Byte]): Int =
    SodiumJNI.crypto_core_salsa20(out, in, k, c)

  def crypto_generichash_blake2b_bytes_min: Int = SodiumJNI.crypto_generichash_blake2b_bytes_min

  def crypto_generichash_blake2b_bytes_max: Int = SodiumJNI.crypto_generichash_blake2b_bytes_max

  def crypto_generichash_blake2b_bytes: Int = SodiumJNI.crypto_generichash_blake2b_bytes

  def crypto_generichash_blake2b_keybytes_min: Int = SodiumJNI.crypto_generichash_blake2b_keybytes_min

  def crypto_generichash_blake2b_keybytes_max: Int = SodiumJNI.crypto_generichash_blake2b_keybytes_max

  def crypto_generichash_blake2b_keybytes: Int = SodiumJNI.crypto_generichash_blake2b_keybytes

  def crypto_generichash_blake2b_saltbytes: Int = SodiumJNI.crypto_generichash_blake2b_saltbytes

  def crypto_generichash_blake2b_personalbytes: Int = SodiumJNI.crypto_generichash_blake2b_personalbytes

  def crypto_generichash_blake2b(
      out: Array[Byte],
      outlen: Int,
      in: Array[Byte],
      inlen: Int,
      key: Array[Byte],
      keylen: Int
  ): Int = SodiumJNI.crypto_generichash_blake2b(out, outlen, in, inlen, key, keylen)

  def crypto_generichash_blake2b_salt_personal(
      out: Array[Byte],
      outlen: Int,
      in: Array[Byte],
      inlen: Int,
      key: Array[Byte],
      keylen: Int,
      salt: Array[Byte],
      personal: Array[Byte]
  ): Int = SodiumJNI.crypto_generichash_blake2b_salt_personal(out, outlen, in, inlen, key, keylen, salt, personal)

  def crypto_generichash_blake2b_init(state: Array[Byte], key: Array[Byte], keylen: Int, outlen: Int): Int =
    SodiumJNI.crypto_generichash_blake2b_init(state, key, keylen, outlen)

  def crypto_generichash_blake2b_init_salt_personal(
      state: Array[Byte],
      key: Array[Byte],
      keylen: Int,
      outlen: Int,
      salt: Array[Byte],
      personal: Array[Byte]
  ): Int = SodiumJNI.crypto_generichash_blake2b_init_salt_personal(state, key, keylen, outlen, salt, personal)

  def crypto_generichash_blake2b_update(state: Array[Byte], in: Array[Byte], inlen: Int): Int =
    SodiumJNI.crypto_generichash_blake2b_update(state, in, inlen)

  def crypto_generichash_blake2b_final(state: Array[Byte], out: Array[Byte], outlen: Int): Int =
    SodiumJNI.crypto_generichash_blake2b_final(state, out, outlen)

  def crypto_hash_sha256_bytes: Int = SodiumJNI.crypto_hash_sha256_bytes

  def crypto_hash_sha256(out: Array[Byte], in: Array[Byte], inlen: Int): Int =
    SodiumJNI.crypto_hash_sha256(out, in, inlen)

  def crypto_hash_sha256_statebytes: Int = SodiumJNI.crypto_hash_sha256_statebytes

  def crypto_hash_sha256_init(state: Array[Byte]): Int = SodiumJNI.crypto_hash_sha256_init(state)

  def crypto_hash_sha256_update(state: Array[Byte], in: Array[Byte], inlen: Int): Int =
    SodiumJNI.crypto_hash_sha256_update(state, in, inlen)

  def crypto_hash_sha256_final(state: Array[Byte], out: Array[Byte]): Int =
    SodiumJNI.crypto_hash_sha256_final(state, out)

  def crypto_hash_sha512_bytes: Int = SodiumJNI.crypto_hash_sha512_bytes

  def crypto_hash_sha512(out: Array[Byte], in: Array[Byte], inlen: Int): Int =
    SodiumJNI.crypto_hash_sha512(out, in, inlen)

  def crypto_hash_sha512_statebytes: Int = SodiumJNI.crypto_hash_sha512_statebytes

  def crypto_hash_sha512_init(state: Array[Byte]): Int = SodiumJNI.crypto_hash_sha512_init(state)

  def crypto_hash_sha512_update(state: Array[Byte], in: Array[Byte], inlen: Int): Int =
    SodiumJNI.crypto_hash_sha512_update(state, in, inlen)

  def crypto_hash_sha512_final(state: Array[Byte], out: Array[Byte]): Int =
    SodiumJNI.crypto_hash_sha512_final(state, out)

  def crypto_onetimeauth_poly1305_bytes: Int = SodiumJNI.crypto_onetimeauth_poly1305_bytes

  def crypto_onetimeauth_poly1305_keybytes: Int = SodiumJNI.crypto_onetimeauth_poly1305_keybytes

  def crypto_onetimeauth_poly1305(out: Array[Byte], in: Array[Byte], inlen: Int, k: Array[Byte]): Int =
    SodiumJNI.crypto_onetimeauth_poly1305(out, in, inlen, k)

  def crypto_onetimeauth_poly1305_verify(h: Array[Byte], in: Array[Byte], inlen: Int, k: Array[Byte]): Int =
    SodiumJNI.crypto_onetimeauth_poly1305_verify(h, in, inlen, k)

  def crypto_onetimeauth_poly1305_init(state: Array[Byte], key: Array[Byte]): Int =
    SodiumJNI.crypto_onetimeauth_poly1305_init(state, key)

  def crypto_onetimeauth_poly1305_update(state: Array[Byte], in: Array[Byte], inlen: Int): Int =
    SodiumJNI.crypto_onetimeauth_poly1305_update(state, in, inlen)

  def crypto_onetimeauth_poly1305_final(state: Array[Byte], out: Array[Byte]): Int =
    SodiumJNI.crypto_onetimeauth_poly1305_final(state, out)

  def crypto_pwhash_alg_argon2i13: Int = SodiumJNI.crypto_pwhash_alg_argon2i13

  def crypto_pwhash_alg_default: Int = SodiumJNI.crypto_pwhash_alg_default

  def crypto_pwhash_bytes_min: Int = SodiumJNI.crypto_pwhash_bytes_min

  def crypto_pwhash_bytes_max: Int = SodiumJNI.crypto_pwhash_bytes_max

  def crypto_pwhash_passwd_min: Int = SodiumJNI.crypto_pwhash_passwd_min

  def crypto_pwhash_passwd_max: Int = SodiumJNI.crypto_pwhash_passwd_max

  def crypto_pwhash_saltbytes: Int = SodiumJNI.crypto_pwhash_saltbytes

  def crypto_pwhash_strbytes: Int = SodiumJNI.crypto_pwhash_strbytes

  def crypto_pwhash_strprefix: Array[Byte] = SodiumJNI.crypto_pwhash_strprefix

  def crypto_pwhash_opslimit_min: Int = SodiumJNI.crypto_pwhash_opslimit_min

  def crypto_pwhash_opslimit_max: Int = SodiumJNI.crypto_pwhash_opslimit_max

  def crypto_pwhash_memlimit_min: Int = SodiumJNI.crypto_pwhash_memlimit_min

  def crypto_pwhash_memlimit_max: Int = SodiumJNI.crypto_pwhash_memlimit_max

  def crypto_pwhash_opslimit_interactive: Int = SodiumJNI.crypto_pwhash_opslimit_interactive

  def crypto_pwhash_memlimit_interactive: Int = SodiumJNI.crypto_pwhash_memlimit_interactive

  def crypto_pwhash_opslimit_moderate: Int = SodiumJNI.crypto_pwhash_opslimit_moderate

  def crypto_pwhash_memlimit_moderate: Int = SodiumJNI.crypto_pwhash_memlimit_moderate

  def crypto_pwhash_opslimit_sensitive: Int = SodiumJNI.crypto_pwhash_opslimit_sensitive

  def crypto_pwhash_memlimit_sensitive: Int = SodiumJNI.crypto_pwhash_memlimit_sensitive

  def crypto_pwhash(
      out: Array[Byte],
      outlen: Int,
      passwd: Array[Byte],
      passwdlen: Int,
      salt: Array[Byte],
      opslimit: Int,
      memlimit: Int,
      alg: Int
  ): Int = SodiumJNI.crypto_pwhash(out, outlen, passwd, passwdlen, salt, opslimit, memlimit, alg)

  def crypto_pwhash_str(out: Array[Byte], passwd: Array[Byte], passwdlen: Int, opslimit: Int, memlimit: Int): Int =
    SodiumJNI.crypto_pwhash_str(out, passwd, passwdlen, opslimit, memlimit)

  def crypto_pwhash_str_verify(str: Array[Byte], passwd: Array[Byte], passwdlen: Int): Int =
    SodiumJNI.crypto_pwhash_str_verify(str, passwd, passwdlen)

  def crypto_pwhash_primitive: Array[Byte] = SodiumJNI.crypto_pwhash_primitive

  def crypto_pwhash_scryptsalsa208sha256_saltbytes: Int = SodiumJNI.crypto_pwhash_scryptsalsa208sha256_saltbytes

  def crypto_pwhash_scryptsalsa208sha256_strbytes: Int = SodiumJNI.crypto_pwhash_scryptsalsa208sha256_strbytes

  def crypto_pwhash_scryptsalsa208sha256_strprefix: Array[Byte] = SodiumJNI.crypto_pwhash_scryptsalsa208sha256_strprefix

  def crypto_pwhash_scryptsalsa208sha256_opslimit_interactive: Int =
    SodiumJNI.crypto_pwhash_scryptsalsa208sha256_opslimit_interactive

  def crypto_pwhash_scryptsalsa208sha256_memlimit_interactive: Int =
    SodiumJNI.crypto_pwhash_scryptsalsa208sha256_memlimit_interactive

  def crypto_pwhash_scryptsalsa208sha256_opslimit_sensitive: Int =
    SodiumJNI.crypto_pwhash_scryptsalsa208sha256_opslimit_sensitive

  def crypto_pwhash_scryptsalsa208sha256_memlimit_sensitive: Int =
    SodiumJNI.crypto_pwhash_scryptsalsa208sha256_memlimit_sensitive

  def crypto_pwhash_scryptsalsa208sha256(
      out: Array[Byte],
      outlen: Int,
      passwd: Array[Byte],
      passwdlen: Int,
      salt: Array[Byte],
      opslimit: Int,
      memlimit: Int
  ): Int = SodiumJNI.crypto_pwhash_scryptsalsa208sha256(out, outlen, passwd, passwdlen, salt, opslimit, memlimit)

  def crypto_pwhash_scryptsalsa208sha256_str(
      out: Array[Byte],
      passwd: Array[Byte],
      passwdlen: Int,
      opslimit: Int,
      memlimit: Int
  ): Int = SodiumJNI.crypto_pwhash_scryptsalsa208sha256_str(out, passwd, passwdlen, opslimit, memlimit)

  def crypto_pwhash_scryptsalsa208sha256_str_verify(str: Array[Byte], passwd: Array[Byte], passwdlen: Int): Int =
    SodiumJNI.crypto_pwhash_scryptsalsa208sha256_str_verify(str, passwd, passwdlen)

  def crypto_pwhash_scryptsalsa208sha256_ll(
      passwd: Array[Byte],
      passwdlen: Int,
      salt: Array[Byte],
      saltlen: Int,
      N: Int,
      r: Int,
      p: Int,
      buf: Array[Byte],
      buflen: Int
  ): Int = SodiumJNI.crypto_pwhash_scryptsalsa208sha256_ll(passwd, passwdlen, salt, saltlen, N, r, p, buf, buflen)

  def crypto_pwhash_scryptsalsa208sha256_str_needs_rehash(str: Array[Byte], opslimit: Int, memlimit: Int): Int =
    SodiumJNI.crypto_pwhash_scryptsalsa208sha256_str_needs_rehash(str, opslimit, memlimit)

  def crypto_scalarmult_curve25519_bytes: Int = SodiumJNI.crypto_scalarmult_curve25519_bytes

  def crypto_scalarmult_curve25519_scalarbytes: Int = SodiumJNI.crypto_scalarmult_curve25519_scalarbytes

  def crypto_scalarmult_curve25519(q: Array[Byte], n: Array[Byte], p: Array[Byte]): Int =
    SodiumJNI.crypto_scalarmult_curve25519(q, n, p)

  def crypto_scalarmult_curve25519_base(q: Array[Byte], n: Array[Byte]): Int =
    SodiumJNI.crypto_scalarmult_curve25519_base(q, n)

  def crypto_secretbox_xsalsa20poly1305_keybytes: Int = SodiumJNI.crypto_secretbox_xsalsa20poly1305_keybytes

  def crypto_secretbox_xsalsa20poly1305_noncebytes: Int = SodiumJNI.crypto_secretbox_xsalsa20poly1305_noncebytes

  def crypto_secretbox_xsalsa20poly1305_zerobytes: Int = SodiumJNI.crypto_secretbox_xsalsa20poly1305_zerobytes

  def crypto_secretbox_xsalsa20poly1305_boxzerobytes: Int = SodiumJNI.crypto_secretbox_xsalsa20poly1305_boxzerobytes

  def crypto_secretbox_xsalsa20poly1305_macbytes: Int = SodiumJNI.crypto_secretbox_xsalsa20poly1305_macbytes

  def crypto_secretbox_xsalsa20poly1305(
      c: Array[Byte],
      m: Array[Byte],
      mlen: Int,
      n: Array[Byte],
      k: Array[Byte]
  ): Int = SodiumJNI.crypto_secretbox_xsalsa20poly1305(c, m, mlen, n, k)

  def crypto_secretbox_xsalsa20poly1305_open(
      m: Array[Byte],
      c: Array[Byte],
      clen: Int,
      n: Array[Byte],
      k: Array[Byte]
  ): Int = SodiumJNI.crypto_secretbox_xsalsa20poly1305_open(m, c, clen, n, k)

  def crypto_secretbox_xchacha20poly1305_easy(
      c: Array[Byte],
      m: Array[Byte],
      mlen: Int,
      n: Array[Byte],
      k: Array[Byte]
  ): Int = SodiumJNI.crypto_secretbox_xchacha20poly1305_easy(c, m, mlen, n, k)

  def crypto_secretbox_xchacha20poly1305_open_easy(
      m: Array[Byte],
      c: Array[Byte],
      clen: Int,
      n: Array[Byte],
      k: Array[Byte]
  ): Int = SodiumJNI.crypto_secretbox_xchacha20poly1305_open_easy(m, c, clen, n, k)

  def crypto_secretbox_xchacha20poly1305_detached(
      c: Array[Byte],
      mac: Array[Byte],
      m: Array[Byte],
      mlen: Int,
      n: Array[Byte],
      k: Array[Byte]
  ): Int = SodiumJNI.crypto_secretbox_xchacha20poly1305_detached(c, mac, m, mlen, n, k)

  def crypto_secretbox_xchacha20poly1305_open_detached(
      m: Array[Byte],
      c: Array[Byte],
      mac: Array[Byte],
      clen: Int,
      n: Array[Byte],
      k: Array[Byte]
  ): Int = SodiumJNI.crypto_secretbox_xchacha20poly1305_open_detached(m, c, mac, clen, n, k)

  def crypto_shorthash_siphash24_bytes: Int = SodiumJNI.crypto_shorthash_siphash24_bytes

  def crypto_shorthash_siphash24_keybytes: Int = SodiumJNI.crypto_shorthash_siphash24_keybytes

  def crypto_shorthash_siphash24(out: Array[Byte], in: Array[Byte], inlen: Int, k: Array[Byte]): Int =
    SodiumJNI.crypto_shorthash_siphash24(out, in, inlen, k)

  def crypto_sign_ed25519_bytes: Int = SodiumJNI.crypto_sign_ed25519_bytes

  def crypto_sign_ed25519_seedbytes: Int = SodiumJNI.crypto_sign_ed25519_seedbytes

  def crypto_sign_ed25519_publickeybytes: Int = SodiumJNI.crypto_sign_ed25519_publickeybytes

  def crypto_sign_ed25519_secretkeybytes: Int = SodiumJNI.crypto_sign_ed25519_secretkeybytes

  def crypto_sign_ed25519(sm: Array[Byte], smlen_p: Array[Int], m: Array[Byte], mlen: Int, sk: Array[Byte]): Int =
    SodiumJNI.crypto_sign_ed25519(sm, smlen_p, m, mlen, sk)

  def crypto_sign_ed25519_open(m: Array[Byte], mlen_p: Array[Int], sm: Array[Byte], smlen: Int, pk: Array[Byte]): Int =
    SodiumJNI.crypto_sign_ed25519_open(m, mlen_p, sm, smlen, pk)

  def crypto_stream_xsalsa20(c: Array[Byte], clen: Int, n: Array[Byte], k: Array[Byte]): Int =
    SodiumJNI.crypto_stream_xsalsa20(c, clen, n, k)

  def crypto_sign_ed25519_detached(
      sig: Array[Byte],
      siglen_p: Array[Int],
      m: Array[Byte],
      mlen: Int,
      sk: Array[Byte]
  ): Int = SodiumJNI.crypto_sign_ed25519_detached(sig, siglen_p, m, mlen, sk)

  def crypto_sign_ed25519_verify_detached(sig: Array[Byte], m: Array[Byte], mlen: Int, pk: Array[Byte]): Int =
    SodiumJNI.crypto_sign_ed25519_verify_detached(sig, m, mlen, pk)

  def crypto_sign_ed25519_keypair(pk: Array[Byte], sk: Array[Byte]): Int = SodiumJNI.crypto_sign_ed25519_keypair(pk, sk)

  def crypto_sign_ed25519_seed_keypair(pk: Array[Byte], sk: Array[Byte], seed: Array[Byte]): Int =
    SodiumJNI.crypto_sign_ed25519_seed_keypair(pk, sk, seed)

  def crypto_sign_ed25519_pk_to_curve25519(curve25519_pk: Array[Byte], ed25519_pk: Array[Byte]): Int =
    SodiumJNI.crypto_sign_ed25519_pk_to_curve25519(curve25519_pk, ed25519_pk)

  def crypto_sign_ed25519_sk_to_curve25519(curve25519_sk: Array[Byte], ed25519_sk: Array[Byte]): Int =
    SodiumJNI.crypto_sign_ed25519_sk_to_curve25519(curve25519_sk, ed25519_sk)

  def crypto_stream_chacha20_keybytes: Int = SodiumJNI.crypto_stream_chacha20_keybytes

  def crypto_stream_chacha20_noncebytes: Int = SodiumJNI.crypto_stream_chacha20_noncebytes

  def crypto_stream_chacha20(c: Array[Byte], clen: Int, n: Array[Byte], k: Array[Byte]): Int =
    SodiumJNI.crypto_stream_chacha20(c, clen, n, k)

  def crypto_stream_chacha20_xor(c: Array[Byte], m: Array[Byte], mlen: Int, n: Array[Byte], k: Array[Byte]): Int =
    SodiumJNI.crypto_stream_chacha20_xor(c, m, mlen, n, k)

  def crypto_stream_chacha20_xor_ic(
      c: Array[Byte],
      m: Array[Byte],
      mlen: Int,
      n: Array[Byte],
      ic: Int,
      k: Array[Byte]
  ): Int = SodiumJNI.crypto_stream_chacha20_xor_ic(c, m, mlen, n, ic, k)

  def crypto_stream_chacha20_ietf_noncebytes: Int = SodiumJNI.crypto_stream_chacha20_ietf_noncebytes

  def crypto_stream_chacha20_ietf(c: Array[Byte], clen: Int, n: Array[Byte], k: Array[Byte]): Int =
    SodiumJNI.crypto_stream_chacha20_ietf(c, clen, n, k)

  def crypto_stream_chacha20_ietf_xor(c: Array[Byte], m: Array[Byte], mlen: Int, n: Array[Byte], k: Array[Byte]): Int =
    SodiumJNI.crypto_stream_chacha20_ietf_xor(c, m, mlen, n, k)

  def crypto_stream_chacha20_ietf_xor_ic(
      c: Array[Byte],
      m: Array[Byte],
      mlen: Int,
      n: Array[Byte],
      ic: Int,
      k: Array[Byte]
  ): Int = SodiumJNI.crypto_stream_chacha20_ietf_xor_ic(c, m, mlen, n, ic, k)

  def crypto_stream_salsa20_keybytes: Int = SodiumJNI.crypto_stream_salsa20_keybytes

  def crypto_stream_salsa20_noncebytes: Int = SodiumJNI.crypto_stream_salsa20_noncebytes

  def crypto_stream_salsa20(c: Array[Byte], clen: Int, n: Array[Byte], k: Array[Byte]): Int =
    SodiumJNI.crypto_stream_salsa20(c, clen, n, k)

  def crypto_stream_salsa20_xor(c: Array[Byte], m: Array[Byte], mlen: Int, n: Array[Byte], k: Array[Byte]): Int =
    SodiumJNI.crypto_stream_salsa20_xor(c, m, mlen, n, k)

  def crypto_stream_salsa20_xor_ic(
      c: Array[Byte],
      m: Array[Byte],
      mlen: Int,
      n: Array[Byte],
      ic: Int,
      k: Array[Byte]
  ): Int = SodiumJNI.crypto_stream_salsa20_xor_ic(c, m, mlen, n, ic, k)

  def crypto_stream_xsalsa20_keybytes: Int = SodiumJNI.crypto_stream_xsalsa20_keybytes

  def crypto_stream_xsalsa20_noncebytes: Int = SodiumJNI.crypto_stream_xsalsa20_noncebytes

  def crypto_stream_xsalsa20_xor(c: Array[Byte], m: Array[Byte], mlen: Int, n: Array[Byte], k: Array[Byte]): Int =
    SodiumJNI.crypto_stream_xsalsa20_xor(c, m, mlen, n, k)

  def crypto_stream_xsalsa20_xor_ic(
      c: Array[Byte],
      m: Array[Byte],
      mlen: Int,
      n: Array[Byte],
      ic: Int,
      k: Array[Byte]
  ): Int = SodiumJNI.crypto_stream_xsalsa20_xor_ic(c, m, mlen, n, ic, k)
}

object ScalaSodium
    extends Argon2Constants
    with GenericHashConstants
    with ShortHashConstants
    with HmacSha256Constants
    with HmacSha512Constants
    with HmacSha512256Constants
    with SCryptConstants
    with SecretBoxConstants
    with CryptoAEADConstants
    with OriginalChacha20Poly1305Constants
    with Chacha20Poly1305IETFConstants
    with XChacha20Poly1305IETFConstants {

  val MIN_SUPPORTED_VERSION: Array[Integer] = Array[Integer](1, 0, 3)

  private var versionSupported = false

  private def libraryName = "sodiumjni"

  private[tsec] lazy val Sodium: ScalaSodium = {
    System.loadLibrary(libraryName)
    val sodium = new ScalaSodium()

    if (sodium.sodium_init < 0)
      throw new RuntimeException("ScalaSodium is not safe to use")
    sodium
  }

  def getSodiumUnsafe: ScalaSodium = Sodium

  def getSodium[F[_]](implicit F: Sync[F]): F[ScalaSodium] = F.delay(Sodium)

  def randombytes_buf(buff: Array[Byte], buff_len: Int): Unit =
    SodiumJNI.randombytes_buf(buff, buff_len)

  def randomBytes[F[_]](len: Int)(implicit F: Sync[F], S: ScalaSodium): F[Array[Byte]] = F.delay {
    val bytes = new Array[Byte](len)
    S.randombytes_buf(bytes, len)
    bytes
  }

}
