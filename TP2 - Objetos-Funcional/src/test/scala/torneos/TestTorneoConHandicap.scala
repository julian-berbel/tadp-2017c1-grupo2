package torneos

import dragones.Nadder
import items.Comestible
import org.scalatest.flatspec.AnyFlatSpec
import participantes.Caracteristicas
import participantes.Vikingo._
import postas.Combate

class TestTorneoConHandicap extends AnyFlatSpec {
  val vikingos = List(Vikingo("UnVikingo", Caracteristicas(1,Comestible(1),1,1,1)), Vikingo("OtroVikingo", Caracteristicas(1,Comestible(1),1,1,1)))

  val torneo = new TorneoConHandicap(List(Combate(0)), List(Nadder(10)))

  "A Handicap Tournament" should "let the last participant choose a dragon first" in {
    assertResult("OtroVikingo")(torneo.competir(vikingos).get.nombre)
  }
}
