package com.fillta.dropwizard.providers

import com.sun.jersey.api.core.HttpContext

/**
 * Courtney Robinson <courtney@crlog.info>
 */
class StandardProvider[T](clazz:Class[T],value:T)
  extends AbstractInjectableProvider[T](clazz){
  def getValue(p1: HttpContext): T = value
}
