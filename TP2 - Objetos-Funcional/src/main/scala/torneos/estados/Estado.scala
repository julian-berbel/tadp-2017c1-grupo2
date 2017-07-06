package torneos.estados

import participantes.Inscripto
import postas.Posta
import torneos.Torneo

trait Estado[T <: Inscripto]{
  val ganador: (List[T] => T) => Estado[T]

  def get: T

  def continuar(posta: Posta, torneo: Torneo[T], participantes: List[T]): Estado[T] = this
}