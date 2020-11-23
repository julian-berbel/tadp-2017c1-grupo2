package torneos

import org.scalatest.flatspec.AnyFlatSpec
import participantes.Vikingo._

class TestTorneoEstandar extends AnyFlatSpec {

  val vikingos = List(astrid, patan, hipo, pataPez)

  val torneo = TorneoEstandar(List.empty, List.empty)

  "A Standard Tournament" should "choose the first participant as its winner" in {
    assertResult("Astrid")(torneo.criterioGanador(vikingos).nombre)
  }

  it should "eliminate the bottom half of the participants each round" in {
    assertResult(List(astrid, patan))(torneo.criterioPasajeDeRonda(vikingos))
  }
}
