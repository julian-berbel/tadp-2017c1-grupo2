package participantes

import Item._
import org.scalatest._
import Vikingo._

class TestVikingos extends FlatSpec {

  "Hipo" should "have a flight system" in {
    assert(hipo.tieneUn[SistemaDeVuelo])
  }

  "Astrid" should "have an axe" in {
    assert(astrid.tieneUn[Hacha])
  }

  "Patan" should "have a mace" in {
    assert(astrid.tieneUn[Maza])
  }

  "Patapez" should "have food" in {
    assert(astrid.tieneUn[Comestible])
  }

  "A Viking" should "have a damage equal to its barbarity plus the damage of its item" in {
    assertResult(35)(astrid.danio)
  }

  it should "be able to carry as much as half its weight plus twice its barbarity" in {
    assertResult(19)(patan.cuantoPuedeCargar)
  }

  it should "have a speed specific to itself" in {
    assertResult(6)(astrid.velocidad)
  }
}