package dragones

import org.scalatest.flatspec.AnyFlatSpec

class TestFuriaNocturna extends AnyFlatSpec {

  val furiaNocturna: FuriaNocturna = FuriaNocturna(15,20)

  "A Night Fury" should "have a base speed three times the regular" in {
    assertResult(180)(furiaNocturna.velocidadBasica)
  }

  it should "have a damage specific to itself" in {
    assertResult(20)(furiaNocturna.danio)
  }

}
