package com.fillta.dropwizard.providers

import com.sun.jersey.api.core.HttpContext
import com.yammer.dropwizard.config.Configuration
import javax.ws.rs.ext.Provider

/**
 * Courtney Robinson <courtney@crlog.info>
 */
@Provider
class ConfigProvider(config: Configuration)
  extends AbstractInjectableProvider[Configuration](classOf[Configuration]) {


  def getValue(c: HttpContext): Configuration = {
    return config
  }
}