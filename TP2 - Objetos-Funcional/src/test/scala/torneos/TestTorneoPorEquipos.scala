package torneos

import items.Hacha
import org.scalatest._
import participantes.{Caracteristicas, Equipo}
import participantes.Vikingo._
import postas.Pesca

class TestTorneoPorEquipos extends FlatSpec {
  val caracteristicasDebiles = Caracteristicas(peso = 1, item = Hacha(1), barbarosidad = 1, hambre = 0, velocidad = 1)
  val caracteristicasFuertes = Caracteristicas(peso = 10, item = Hacha(1), barbarosidad = 10, hambre = 0, velocidad = 10)

  val vikingo1: Vikingo = Vikingo("Vikingo 1", caracteristicasFuertes)
  val vikingo2: Vikingo = Vikingo("Vikingo 2", caracteristicasFuertes)
  val vikingo3: Vikingo = Vikingo("Vikingo 3", caracteristicasDebiles)

  val vikingo4: Vikingo = Vikingo("Vikingo 4", caracteristicasDebiles)
  val vikingo5: Vikingo = Vikingo("Vikingo 5", caracteristicasDebiles)
  val vikingo6: Vikingo = Vikingo("Vikingo 6", caracteristicasFuertes)

  val equipos = List(Equipo(List(vikingo1, vikingo2, vikingo3)), Equipo(List(vikingo4, vikingo5, vikingo6)))

  val torneo = TorneoPorEquipos(List(Pesca()), List.empty)

  "A Team Tournament" should "choose the first team (as is at the moment of winning) as its winner" in {
    assertResult(List("Vikingo 2", "Vikingo 1"))(torneo.competir(equipos).get.miembros.map(_.nombre))
  }
}
