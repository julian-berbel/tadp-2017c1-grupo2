package postas

import dragones.chimuelo
import org.scalatest.flatspec.AnyFlatSpec
import participantes.Jinete
import participantes.Vikingo._

class TestPosta extends AnyFlatSpec {

  "A Relay" should "not let a viking whose hunger would be over 100 after running participate" in {
    assert(!hipo.puedeCorrer(Carrera(150)))
  }

  it should "raise a rider's hunger by 5 regardless of the kind of relay" in {
    assertResult(5)(hipo.montar(chimuelo).flatMap(_.correr(Carrera(16)).toOption).get.hambre)
  }

  it should "only assign a dragon once when zipping dragons and vikings" in {
    assertResult(List(Jinete(hipo, chimuelo), hipo))(Pesca().emparejar(List(hipo, hipo), List(chimuelo)))
  }

}
