package torneos.estados

import participantes.Inscripto

case class VariosCompetidores[T <: Inscripto](ganadores: List[T]) extends Estado[T]{
  val ganador: (List[T] => Option[T]) => Option[T] = { criterio =>
      criterio(ganadores)
    }
}