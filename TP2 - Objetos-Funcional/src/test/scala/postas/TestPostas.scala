package postas

import org.scalatest._
import participantes.Vikingo._

class TestPostas extends FlatSpec {
  "A Fishing Relay" should "be won by the (now hungry) viking who can carry the most" in {
    assertResult(pataPez)(Pesca().competir(List(astrid, patan, hipo, pataPez), List.empty).head)
  }

  it should "raise a viking's hunger by 5" in {
    assertResult(5)(hipo.correr(Pesca()).get.hambre)
  }

  "A Combat Relay" should "be won by the (now hungry) viking with the highest damage" in {
    assertResult(patan.deltaHambre(10))(Combate(0).competir(List(astrid, patan, hipo, pataPez), List.empty).head)
  }

  it should "raise a viking's hunger by 10" in {
    assertResult(10)(hipo.correr(Combate(0)).get.hambre)
  }

  "A Race Relay" should "be won by the (now hungry) viking with the highest speed" in {
    assertResult(pataPez.deltaHambre(4))(Carrera(15).competir(List(astrid, patan, hipo, pataPez), List.empty).head)
  }

  it should "raise a viking's hunger by 1 for every unit of length" in {
    assertResult(16)(hipo.correr(Carrera(16)).get.hambre)
  }
}
