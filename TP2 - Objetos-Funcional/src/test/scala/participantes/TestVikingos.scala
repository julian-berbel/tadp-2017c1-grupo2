package participantes

import items._
import org.scalatest.flatspec.AnyFlatSpec
import Vikingo._
import dragones._
import postas.{Carrera, Combate, Pesca}
import postas.requerimientos.BarbaridadMinima

class TestVikingos extends AnyFlatSpec {

  val nadder: Nadder = Nadder(10)
  val furiaNocturna: FuriaNocturna = FuriaNocturna(15,20)
  val gronckle: Gronckle = Gronckle(10, 30)
  val dragonConBarbaridadMinimaRequerida: FuriaNocturna = FuriaNocturna(150, 20, List(BarbaridadMinima(5)))
  val dragones: List[Dragon] = List(nadder, furiaNocturna, gronckle, chimuelo, dragonConBarbaridadMinimaRequerida)

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

  it should "choose the way for it to participate that will maximize its carrying weight in a fishing relay" in {
    assertResult(Some(Jinete(hipo, chimuelo)))(hipo.mejorMontura(dragones, Pesca()))
  }

  it should "choose the way for it to participate that will maximize its damage in a combat relay" in {
    assertResult(Some(Jinete(hipo, nadder)))(hipo.mejorMontura(dragones, Combate(1)))
  }

  it should "choose the way for it to participate that will maximize its speed in a race relay" in {
    assertResult(Some(Jinete(hipo, furiaNocturna)))(hipo.mejorMontura(dragones, Carrera(1)))
  }

  it should "choose to participate by himself if running with a dragon will worsen his score" in {
    assertResult(Some(pataPez))(pataPez.mejorMontura(dragones, Pesca()))
  }

  it should "not be able to participate if he doesn't meet the criteria" in {
    assertResult(None)(hipo.mejorMontura(dragones, Combate(1000)))
  }

}
