package com.fillta.dropwizard

import conf.Config
import providers.AbstractInjectableProvider
import resources.BaseResource

/**
 * @author Courtney Robinson <courtney@crlog.info>
 */

object MyFilltaService extends FilltaService[Config]("MyFilltaService") {

  override protected def initializeService {
    //initialize
    //all resources in the package will be added
    addResourcePackage(classOf[BaseResource].getPackage.getName)
    //add all providers
    addProviderPackage(classOf[AbstractInjectableProvider[_]].getPackage.getName)
  }

}