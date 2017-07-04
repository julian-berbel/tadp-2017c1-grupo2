package torneos

import org.scalatest._
import participantes.Vikingo.{astrid, hipo, pataPez, patan}

class TestTorneoEliminacion extends FlatSpec {
  val vikingos = List(astrid, patan, hipo, pataPez)

  val torneo = new TorneoEliminacion(List.empty, List.empty,1)

  "An Elimination Tournament" should "eliminate the last N participants each round" in {
    assertResult(List(astrid, patan, hipo))(torneo.criterioPasajeDeRonda(vikingos))
  }
}
