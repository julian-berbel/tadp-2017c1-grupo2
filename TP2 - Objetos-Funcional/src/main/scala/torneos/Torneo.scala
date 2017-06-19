package torneos

import dragones.Dragon
import participantes.{Vikingo}
import postas.Posta

trait Torneo {
  val postas: List[Posta]

  val dragones: List[Dragon]

  def competir(vikingos: List[Vikingo]): Option[Vikingo] = {
    val ganadores =
      postas.foldLeft(ordenDeEleccion(vikingos)){ (sobrevivientes, posta) =>
        sobrevivientes.length match{
          case n if n <= 1 => sobrevivientes
          case _ => criterioPasajeDeRonda(posta.competir(sobrevivientes, dragonesDisponibles))
        }
      }
    criterioGanador(ganadores)
  }

  def criterioGanador: List[Vikingo] => Option[Vikingo]

  def criterioPasajeDeRonda: List[Vikingo] => List[Vikingo]
  
  def ordenDeEleccion(vikingos: List[Vikingo]): List[Vikingo] = vikingos

  val dragonesDisponibles: List[Dragon] = dragones
}

case class TorneoEstandar(postas: List[Posta], dragones: List[Dragon]) extends Torneo{
  def criterioGanador: List[Vikingo] => Option[Vikingo] = {_.headOption}

  def criterioPasajeDeRonda: (List[Vikingo]) => List[Vikingo] = {vikingos => vikingos.take(vikingos.length / 2)}
}

case class TorneoEliminacion(postas, dragones, cuantosCaen: Int) extends TorneoEstandar(postas, dragones){
  override def criterioPasajeDeRonda: (List[Vikingo]) => List[Vikingo] = {vikingos => vikingos.dropRight(cuantosCaen)}
}

case class TorneoInverso(postas: List[Posta], dragones: List[Dragon]) extends Torneo{
  def criterioGanador: List[Vikingo] => Option[Vikingo] = {_.lastOption}

  def criterioPasajeDeRonda: (List[Vikingo]) => List[Vikingo] = {vikingos => vikingos.takeRight(vikingos.length / 2)}
}

case class TorneoConVeto(postas, dragones, condicionVeto: Dragon => Boolean) extends TorneoEstandar(postas, dragones){
  override val dragonesDisponibles: List[Dragon] = dragones.filter(condicionVeto)
}

case class TorneoConHandicap(postas, dragones, condicionVeto: Dragon => Boolean) extends TorneoEstandar(postas, dragones){
  override def ordenDeEleccion(vikingos: List[Vikingo]): List[Vikingo] = vikingos.reverse
}