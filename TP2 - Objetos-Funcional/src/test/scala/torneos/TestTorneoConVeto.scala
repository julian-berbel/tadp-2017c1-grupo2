package torneos

import dragones.Nadder
import org.scalatest.flatspec.AnyFlatSpec
import participantes.Vikingo._
import postas.Combate

class TestTorneoConVeto extends AnyFlatSpec {
  val vikingos = List(hipo, patan)

  val torneo = new TorneoConVeto(List(Combate(0)), List(Nadder(10)), {_ => false})

  "A Ban Tournament" should "not let vikings choose dragons that don't meet a given criteria" in {
    assertResult("Patan")(torneo.competir(vikingos).get.nombre)
  }
}
