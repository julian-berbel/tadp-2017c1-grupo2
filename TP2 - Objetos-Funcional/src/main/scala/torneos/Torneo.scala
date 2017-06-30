package torneos

import dragones.Dragon
import participantes.{Equipo, Inscripto, Vikingo}
import postas.Posta

trait Estado[T <: Inscripto]{
  val ganador: (List[T] => Option[T]) => Option[T]
}

case class Ganador[T <: Inscripto](participante: T) extends Estado[T]{
  val ganador: (List[T] => Option[T]) => Option[T] = { _ =>
    Some(participante)
  }
}

case class TodosPerdedores[T <: Inscripto]() extends Estado[T]{
  val ganador: (List[T] => Option[T]) => Option[T] = { _ =>
    None
  }
}

case class VariosCompetidores[T <: Inscripto](ganadores: List[T]) extends Estado[T]{
  val ganador: (List[T] => Option[T]) => Option[T] = { criterio =>
      criterio(ganadores)
    }
}

trait Torneo[T <: Inscripto] {
  val postas: List[Posta]

  val dragones: List[Dragon]

  def competir(participantes: List[T]): Option[T] = {

    val resultado =
      postas.foldLeft(VariosCompetidores(participantes): Estado[T]) { (estado, posta) =>
        estado match {
          case VariosCompetidores(sobrevivientes) =>
            val _sobrevivientes = reagrupar(posta.competir(sobrevivientes.flatMap(_.prepararse), dragonesDisponibles))
            _sobrevivientes.length match {
              case 0 => TodosPerdedores()
              case 1 => Ganador(_sobrevivientes.head)
              case _ => VariosCompetidores(criterioPasajeDeRonda(_sobrevivientes))
            }
          case _estado => _estado
        }
      }

    resultado.ganador(criterioGanador)
  }

  val criterioGanador: List[T] => Option[T]

  def reagrupar(vikingos: List[Vikingo]): List[T]

  def criterioPasajeDeRonda: List[T] => List[T]

  def ordenDeEleccion(vikingos: List[Vikingo]): List[Vikingo] = vikingos

  val dragonesDisponibles: List[Dragon] = dragones
}

trait ReglasEstandar[T <: Inscripto]{
  def criterioPasajeDeRonda: (List[T]) => List[T] = {competidores => competidores.take(competidores.length / 2)}
}

case class TorneoEstandar(postas: List[Posta], dragones: List[Dragon]) extends Torneo[Vikingo] with ReglasEstandar[Vikingo]{
  val criterioGanador: List[Vikingo] => Option[Vikingo] = {_.headOption}

  def reagrupar(vikingos: List[Vikingo]): List[Vikingo] = vikingos
}

class TorneoEliminacion(postas: List[Posta], dragones: List[Dragon], cuantosCaen: Int) extends TorneoEstandar(postas, dragones){
  override def criterioPasajeDeRonda: (List[Vikingo]) => List[Vikingo] = {vikingos => vikingos.dropRight(cuantosCaen)}
}

case class TorneoInverso(postas: List[Posta], dragones: List[Dragon]) extends Torneo[Vikingo]{
  val criterioGanador: List[Vikingo] => Option[Vikingo] = {_.lastOption}

  def criterioPasajeDeRonda: (List[Vikingo]) => List[Vikingo] = {vikingos => vikingos.takeRight(vikingos.length / 2)}

  def reagrupar(vikingos: List[Vikingo]): List[Vikingo] = vikingos
}

class TorneoConVeto(postas: List[Posta], dragones: List[Dragon], condicionVeto: Dragon => Boolean) extends TorneoEstandar(postas, dragones){
  override val dragonesDisponibles: List[Dragon] = dragones.filter(condicionVeto)
}

class TorneoConHandicap(postas: List[Posta], dragones: List[Dragon], condicionVeto: Dragon => Boolean) extends TorneoEstandar(postas, dragones){
  override def ordenDeEleccion(vikingos: List[Vikingo]): List[Vikingo] = vikingos.reverse
}

case class TorneoPorEquipos(postas: List[Posta], dragones: List[Dragon]) extends Torneo[Equipo] with ReglasEstandar[Equipo]{
  override def competir(equipos: List[Equipo]): Option[Equipo] =
    super.competir(equipos)

  val criterioGanador: List[Equipo] => Option[Equipo] = {equipos =>
    Some(equipos.maxBy(_.miembros.length))
  }

  def reagrupar(vikingos: List[Vikingo]): List[Equipo] =
    vikingos.grouped(3).map(Equipo).toList // No dice como reagruparlos
}