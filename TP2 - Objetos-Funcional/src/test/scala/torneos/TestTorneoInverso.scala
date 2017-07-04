package torneos

import dragones.chimuelo
import org.scalatest._
import participantes.Vikingo._
import postas.requerimientos.MonturaRequerida

class TestTorneoInverso extends FlatSpec {
  val vikingos = List(astrid, patan, hipo, pataPez)

  val torneo = TorneoInverso(List.empty, List.empty)

  "An Inverse Tournament" should "choose the last participant as its winner" in {
    assertResult("PataPez")(torneo.criterioGanador(vikingos).get.nombre)
  }

  it should "eliminate the top half of the participants each round" in {
    assertResult(List(hipo, pataPez))(torneo.criterioPasajeDeRonda(vikingos))
  }
}
