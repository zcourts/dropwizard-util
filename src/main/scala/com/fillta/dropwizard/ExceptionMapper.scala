package com.fillta.dropwizard

import javax.ws.rs.ext.Provider
import javax.ws.rs.WebApplicationException
import javax.ws.rs.core.{MediaType, Response}
import com.codahale.jerkson.Json.generate
import org.slf4j.LoggerFactory

/**
 * @author Courtney Robinson <courtney@crlog.info> @ 15/02/12
 */
@Provider
class ExceptionMapper extends javax.ws.rs.ext.ExceptionMapper[Throwable] {
  private val LOG = LoggerFactory.getLogger(this.getClass)

  override def toResponse(e: Throwable): Response = e match {
    case we: WebApplicationException => {
      // try to handle jersey exceptions ourselves
      Response.fromResponse(we.getResponse)
        .`type`(MediaType.APPLICATION_JSON_TYPE)
        .entity(generate(Map("error" -> we.getResponse.getEntity)))
        .build
    }
    case _ => {
      //if its not a web application exception then something has gone severely wrong
      LOG.error("Something's gone horribly wrong and we kinda don't know why, so here you go...its your problem now :-)", e)
      Response
        .status(Response.Status.INTERNAL_SERVER_ERROR)
        .`type`(MediaType.APPLICATION_JSON_TYPE)
        .entity(generate(Map("error" -> ("Error processing request due to " + e))))
        .build
    }

  }
}

//
//import com.yammer.dropwizard.logging.Log
//import errors.ErrorHandler
//import javax.ws.rs.core.{MediaType, Response}
//import javax.ws.rs.ext.Provider
//import com.fillta.dropwizard.conf.Config
//import com.sun.jersey.api.NotFoundException
//
///**
// * Courtney Robinson <courtney@crlog.info>
// */
//@Provider
//class ExceptionMapper(config: Config) extends javax.ws.rs.ext.ExceptionMapper[Throwable] {
//  protected var handler: ErrorHandler = new ErrorHandler(config)
//  protected var log: Log = Log.forClass(classOf[ExceptionMapper])
//
//  def toResponse(throwableCause: Throwable): Response = {
//    handler.setError(throwableCause)
//    handler.handleException
//    val r: Response = handler.getResponse
//    throwableCause match {
//      case ex: NotFoundException => {
//        if (r == null) {
//          Response.status(404).`type`(MediaType.APPLICATION_JSON).build()
//        } else {
//          r
//        }
//      }
//      case _ => {
//        if (handler.doLog) {
//          val trace: Array[StackTraceElement] = throwableCause.getStackTrace
//          val b: StringBuilder = new StringBuilder
//          for (traceElement <- trace) b.append(traceElement)
//          if (r != null) {
//            this.log.warn(throwableCause, "Error, handled returning status %s with view %s Stacktrace %s".format(r.getStatus, r.getEntity.getClass.getName, b.toString))
//          } else {
//            log.warn(throwableCause, "Stacktrace : %s".format(b))
//          }
//        }
//        r
//      }
//    }
//  }
//
//  /**
//   * Sets the handler used to manage errors
//   *
//   * @param handler
//   */
//  def setHandler(handler: ErrorHandler) {
//    this.handler = handler
//  }
//
//}
//
