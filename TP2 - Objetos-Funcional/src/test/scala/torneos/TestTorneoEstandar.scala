package torneos

import org.scalatest._
import participantes.Vikingo._

class TestTorneoEstandar extends FlatSpec {

  val vikingos = List(astrid, patan, hipo, pataPez)

  val torneo = TorneoEstandar(List.empty, List.empty)

  "A Standard Tournament" should "choose the first participant as its winner" in {
    assertResult("Astrid")(torneo.criterioGanador(vikingos).get.nombre)
  }

  it should "eliminate the bottom half of the participants each round" in {
    assertResult(List(astrid, patan))(torneo.criterioPasajeDeRonda(vikingos))
  }
}