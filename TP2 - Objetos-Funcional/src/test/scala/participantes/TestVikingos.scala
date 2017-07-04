package participantes

import items._
import org.scalatest._
import Vikingo._

class TestVikingos extends FlatSpec {

  "Hipo" should "have a flight system" in {
    assert(hipo.tieneUn(tipoSistemaDeVuelo))
  }

  "Astrid" should "have a weapon" in {
    assert(astrid.tieneUn(tipoArma))
  }

  "Patan" should "have a weapon" in {
    assert(astrid.tieneUn(tipoArma))
  }

  "Patapez" should "have food" in {
    assert(pataPez.tieneUn(tipoComestible))
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