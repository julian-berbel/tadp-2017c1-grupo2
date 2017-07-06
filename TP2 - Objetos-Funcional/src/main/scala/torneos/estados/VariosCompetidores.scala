package torneos.estados

import participantes.{Inscripto, Participante}
import postas.Posta
import torneos.Torneo

case class VariosCompetidores[T <: Inscripto](sobrevivientes: List[T]) extends Estado[T]{
  val ganador: (List[T] => T) => Estado[T] = { criterio =>
      Ganador(criterio(sobrevivientes))
    }

  def get: T = throw new Exception("Hay mas de un ganador!")

  override def continuar(posta: Posta, torneo: Torneo[T], participantes: List[T]): Estado[T] = {
    val _sobrevivientes = posta.competir(torneo.ordenDeEleccion(sobrevivientes.flatMap(_.prepararse)), torneo.dragonesDisponibles)
    val reagrupados = torneo.reagrupar(_sobrevivientes, participantes)
    reagrupados.length match {
      case 0 => TodosPerdedores()
      case 1 => Ganador(reagrupados.head)
      case _ => VariosCompetidores(torneo.reagrupar(torneo.criterioPasajeDeRonda(_sobrevivientes), participantes))
    }
  }
}