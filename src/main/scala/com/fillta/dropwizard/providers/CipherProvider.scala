package com.fillta.dropwizard.providers

import com.sun.jersey.api.core.HttpContext
import javax.ws.rs.ext.Provider
import com.fillta.dropwizard.auth.AESEncrypter

/**
 * Courtney Robinson <courtney@crlog.info>
 */

@Provider
class CipherProvider(password: String, salt: String)
  extends AbstractInjectableProvider[AESEncrypter](classOf[AESEncrypter]) {

  def getValue(c: HttpContext) = new AESEncrypter(password, salt)
}