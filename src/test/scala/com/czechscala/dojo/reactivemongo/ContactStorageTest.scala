package com.czechscala.dojo.reactivemongo

import org.scalatest.FunSuite

class ContactStorageTest extends FunSuite {

  test("Test Storage") {
    ContactStorage.connect("contacts")
    assert(ContactStorage.connected)

    ContactStorage.clean

    val contactsInStorageBefore = ContactStorage.count
    val c = Contact("Bohumír Zámečník", "Bohumir.Zamecnik@gmail.com")
    ContactStorage.insert(c)

    println(ContactStorage.count)

    assert(contactsInStorageBefore + 1 == ContactStorage.count)
  }
}
