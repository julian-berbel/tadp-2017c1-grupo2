package torneos.estados

import participantes.Inscripto

case class VariosCompetidores[T <: Inscripto](ganadores: List[T]) extends Estado[T]{
  val ganador: (List[T] => T) => Estado[T] = { criterio =>
      Ganador(criterio(ganadores))
    }

  def get: T = throw new Exception("Hay mas de un ganador!")
}