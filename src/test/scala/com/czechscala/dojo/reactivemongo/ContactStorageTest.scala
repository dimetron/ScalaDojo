package com.czechscala.dojo.reactivemongo

import org.scalatest.FunSuite

class ContactStorageTest extends FunSuite {

  test("Test Storage") {
    ContactStorage.connect("contacts")
    assert(ContactStorage.connected)

    ContactStorage.clean
    assert(0 === ContactStorage.count)

    ContactStorage.insert(Contact("Bohumír Zámečník", "Bohumir.Zamecnik@gmail.com"))
    assert(1 === ContactStorage.count)

    ContactStorage.insert(Contact("Dmitriy Rashko", "drashko@me.com"))
    assert(2 === ContactStorage.count)
  }
}
