package torneos


import org.scalatest.flatspec.AnyFlatSpec

import dragones.chimuelo
import participantes.Vikingo._
import postas.requerimientos.MonturaRequerida

class TestTorneoInverso extends AnyFlatSpec {
  val vikingos = List(astrid, patan, hipo, pataPez)

  val torneo = TorneoInverso(List.empty, List.empty)

  "An Inverse Tournament" should "choose the last participant as its winner" in {
    assertResult("PataPez")(torneo.criterioGanador(vikingos).nombre)
  }

  it should "eliminate the top half of the participants each round" in {
    assertResult(List(hipo, pataPez))(torneo.criterioPasajeDeRonda(vikingos))
  }
}
