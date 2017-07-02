package postas

import Item.Arma
import dragones.chimuelo
import org.scalatest._
import participantes.Caracteristicas
import participantes.Vikingo._
import postas.requerimientos.{MonturaRequerida, PesoMinimo}

class TestPostas extends FlatSpec {
  "A Relay" should "not let a viking whose hunger would be over 100 after running participate" in {
    assert(!hipo.puedeCorrer(Carrera(150)))
  }

  "A Fishing Relay" should "be won by the (now hungry) viking who can carry the most" in {
    assertResult(pataPez)(Pesca().competir(List(astrid, patan, hipo, pataPez), List.empty).head)
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

  "A Combat Relay" should "be won by the (now hungry) viking with the highest damage" in {
    assertResult(patan.deltaHambre(10))(Combate(0).competir(List(astrid, patan, hipo, pataPez), List.empty).head)
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
    val vikingoArmado: Vikingo = Vikingo(Caracteristicas(0,new Arma(10),0,0,0))
    assert(vikingoArmado.puedeCorrer(Combate(1)))
  }

  "A Race Relay" should "be won by the (now hungry) viking with the highest speed" in {
    assertResult(pataPez.deltaHambre(4))(Carrera(15).competir(List(astrid, patan, hipo, pataPez), List.empty).head)
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
