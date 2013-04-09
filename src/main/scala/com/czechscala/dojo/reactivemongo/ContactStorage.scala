package com.czechscala.dojo.reactivemongo

import reactivemongo.api.{DefaultDB, MongoConnection}
import reactivemongo.bson.{BSONDocument, BSONString}
import scala.concurrent.Await
import scala.concurrent.duration._
import scala.util.{Failure, Success}
import reactivemongo.core.commands.{GetLastError, CollStatsResult}
import reactivemongo.bson._
import reactivemongo.bson.handlers.DefaultBSONHandlers._

/**
 * Created with IntelliJ IDEA.
 * User: drashko
 * Date: 4/9/13
 * Time: 8:13 PM
 * To change this template use File | Settings | File Templates.
 */

import reactivemongo.api._

object ContactStorage {

  import scala.concurrent.ExecutionContext.Implicits.global

  val collectionName: String = "contacts"

  var db: DefaultDB = null

  def connect(dbname: String) = {
    val connection = MongoConnection(List("127.0.0.1:27017"))
    db = connection(dbname)
  }

  def connected = db != null

  def clean {
    val res1 = db(collectionName).drop
    Await.ready(res1, 10 second)

    val res2 = db(collectionName).create(true)
    Await.ready(res2, 10 second)
  }

  def count: Int = {

    var count = 0
    val res = db(collectionName).stats()
    res.onSuccess {
      case cr: CollStatsResult => count = cr.count
    }
    Await.ready(res, 10 second)
    count
  }

  def insert(contact: Contact) {
    val doc = BSONDocument("name" -> BSONString(contact.name), "email" -> BSONString(contact.email))
    val res = db(collectionName).insert(doc, GetLastError())
    Await.ready(res, 10 second)
  }
}
