package torneos.estados

import participantes.Inscripto

trait Estado[T <: Inscripto]{
  val ganador: (List[T] => T) => Estado[T]

  def get: T
}