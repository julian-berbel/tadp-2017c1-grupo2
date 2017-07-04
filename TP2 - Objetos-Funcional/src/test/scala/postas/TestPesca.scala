package postas

import org.scalatest._
import participantes.Vikingo._
import postas.requerimientos.PesoMinimo

class TestPesca extends FlatSpec {

  "A Fishing Relay" should "be won by the viking who can carry the most" in {
    assertResult("PataPez")(Pesca().competir(List(astrid, patan, hipo, pataPez), List.empty).head.nombre)
  }

  it should "raise a viking's hunger by 5" in {
    assertResult(5)(hipo.correr(Pesca()).get.hambre)
  }

  "A Fishing Relay with a minimum weight requirement" should "not let a viking who can't lift said minimum participate" in {
    assert(!hipo.puedeCorrer(Pesca(Some(PesoMinimo(100)))))
  }

  it should "let a viking who can lift said minimum participate" in {
    assert(hipo.puedeCorrer(Pesca(Some(PesoMinimo(1)))))
  }

}
