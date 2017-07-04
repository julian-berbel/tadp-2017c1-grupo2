package postas

import items.Arma
import org.scalatest._
import participantes.Caracteristicas
import participantes.Vikingo._

class TestCombate extends FlatSpec {

  "A Combat Relay" should "be won by the viking with the highest damage" in {
    assertResult("Patan")(Combate(0).competir(List(astrid, patan, hipo, pataPez), List.empty).head.nombre)
  }

  it should "raise a viking's hunger by 10" in {
    assertResult(10)(hipo.correr(Combate(0)).get.hambre)
  }

  it should "not let a viking who has a barbarity lower than de minimum participate" in {
    assert(!hipo.puedeCorrer(Combate(10)))
  }

  it should "let a viking who has a barbarity higher than de minimum participate" in {
    assert(hipo.puedeCorrer(Combate(1)))
  }

  it should "let a viking who has a barbarity lower than de minimum but has a weapon participate" in {
    val vikingoArmado: Vikingo = Vikingo("", Caracteristicas(0,new Arma(10),0,0,0))
    assert(vikingoArmado.puedeCorrer(Combate(1)))
  }

}
