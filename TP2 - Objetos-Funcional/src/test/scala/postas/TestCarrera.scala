package postas

import dragones.chimuelo
import org.scalatest.flatspec.AnyFlatSpec
import participantes.Vikingo._
import postas.requerimientos.MonturaRequerida

class TestCarrera extends AnyFlatSpec {

  "A Race Relay" should "be won by the viking with the highest speed" in {
    assertResult("PataPez")(Carrera(15).competir(List(astrid, patan, hipo, pataPez), List.empty).head.nombre)
  }

  it should "raise a viking's hunger by 1 for every unit of length" in {
    assertResult(16)(hipo.correr(Carrera(16)).get.hambre)
  }

  "A Race Relay with a mount requirement" should "not let a viking participate" in {
    assert(!hipo.puedeCorrer(Carrera(1, Some(MonturaRequerida()))))
  }

  it should "let a rider participate" in {
    assert(hipo.montar(chimuelo).get.puedeCorrer(Carrera(1, Some(MonturaRequerida()))))
  }

}
