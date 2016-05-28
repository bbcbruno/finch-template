package finchtemplate.util.hawk.validate

import cats.data.Xor
import cats.data.Xor._
import finchtemplate.util.hawk.TaggedTypesFunctions._
import finchtemplate.util.hawk._
import finchtemplate.util.hawk.params.{PayloadContext, RequestContext}
import finchtemplate.util.hawk.validate.NormalisedRequest._

object Maccer {

  // TODO TJA If the payload is available at the time of authentication, the server uses the hash value provided by the
  // client to construct the normalized string and validates the MAC. If the MAC is valid, the server calculates the
  // payload hash and compares the value with the provided payload hash in the header. In many cases, checking the MAC
  // first is faster than calculating the payload hash.
  //
  // It is important to note that MAC validation does not mean the hash value provided by the client is valid, only
  // that the value included in the header was not modified. Without calculating the payload hash on the server and
  // comparing it to the value provided by the client, the payload may be modified by an attacker.

  def requestMac(credentials: Credentials, context: RequestContext, method: ValidationMethod): Xor[Error, MAC] =
    method match {
      case HeaderValidationMethod => validateHeader(credentials, context)
      case PayloadValidationMethod => validatePayload(credentials, context)
    }

  private def validateHeader(credentials: Credentials, context: RequestContext): Xor[Error, MAC] =
    right(normalisedHeaderMac(credentials, context, None))

  private def validatePayload(credentials: Credentials, context: RequestContext): Xor[Error, MAC] = {
    context.payload.map { payloadContext =>
      // TODO TJA Pull the optional hash out of the header, when it's been made optional.
      val payloadHash: Option[PayloadHash] = Some(context.clientAuthHeader.payloadHash)
      payloadHash match {
        case Some(clientProvidedHash) => {
          val macFromClientProvidedHash = normalisedHeaderMac(credentials, context, Some(MAC(Base64Encoded(clientProvidedHash))))
          if (macFromClientProvidedHash == context.clientAuthHeader.mac) {
            right(completePayloadMac(credentials, context, payloadContext))
          } else {
            left(new Error("MAC provided in request does not match the computed MAC (possible invalid payload hash)"))
          }
        }
        case None => right(completePayloadMac(credentials, context, payloadContext))
      }
    }.getOrElse(left(new Error("No payload provided for payload validation")))
  }

  private def completePayloadMac(credentials: Credentials, context: RequestContext, payloadContext: PayloadContext): MAC = {
    val computedPayloadMac = normalisedPayloadMac(credentials, payloadContext)
    normalisedHeaderMac(credentials, context, Some(computedPayloadMac))
  }
}
