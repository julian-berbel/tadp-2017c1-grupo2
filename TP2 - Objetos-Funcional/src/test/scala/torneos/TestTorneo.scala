package torneos

import dragones._
import org.scalatest.flatspec.AnyFlatSpec
import participantes.Vikingo._
import postas.{Carrera, Combate, Pesca}
import postas.requerimientos.BarbaridadMinima
import torneos.estados.TodosPerdedores

class TestTorneo extends AnyFlatSpec {

  val nadder: Nadder = Nadder(10)
  val furiaNocturna: FuriaNocturna = FuriaNocturna(15,20)
  val gronckle: Gronckle = Gronckle(10, 30)
  val dragonConBarbaridadMinimaRequerida: FuriaNocturna = FuriaNocturna(150, 20, List(BarbaridadMinima(5)))

  val dragones: List[Dragon] = List(nadder, furiaNocturna, gronckle, chimuelo, dragonConBarbaridadMinimaRequerida)

  val vikingos = List(astrid, patan, hipo, pataPez)

  "A Tournament" should "be won by the who performs best in the last relay (and hasn't been previously eliminated)" in {
    assertResult("Patan")(TorneoEstandar(List(Pesca(), Combate(1)), dragones).competir(vikingos).get.nombre)
  }

  it should "be won by nobody if everyone is eliminated" in {
    assertResult(TodosPerdedores())(new TorneoEliminacion(List(Carrera(10), Pesca()), dragones, 5).competir(vikingos))
  }

}
