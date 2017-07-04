package torneos.estados

import participantes.Inscripto

trait Estado[T <: Inscripto]{
  val ganador: (List[T] => Option[T]) => Option[T]
}