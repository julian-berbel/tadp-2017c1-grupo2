package postas

import dragones.Dragon
import participantes.Vikingo.Vikingo
import participantes.{Jinete, Participante}
import requerimientos._

trait Posta {
  val requerimiento: Option[Requerimiento]
  val cuantaHambreDa: Int

  def correr(participantes: List[Participante]): List[Participante] =
    participantes.sortBy(evaluar).reverse.map(_.correr(this).get)

  def competir(participantes: List[Vikingo], dragones: List[Dragon]): List[Vikingo] =
    correr(emparejar(participantes, dragones)).map{
        case participante: Vikingo => participante
        case participante: Jinete => participante.vikingo
    }

  def evaluar(participante: Participante): Int

  def cumpleRequisitos(participante: Participante): Boolean =
    requerimiento.forall(_.esCumplidoPor(participante))

  def darHambre(vikingo: Vikingo): Vikingo =
    vikingo.deltaHambre(this.cuantaHambreDa)

  def emparejar(vikingos: List[Vikingo], dragones: List[Dragon]): List[Participante] = {
    vikingos.foldLeft((List.empty, List.empty): List[Option[Participante]] Tuple2 List[Dragon]){ (tuplaParticipantesDragones, vikingo) =>
      val participantes = tuplaParticipantesDragones._1
      val dragonesYaElegidos = tuplaParticipantesDragones._2

      val mejorParticipante: Option[Participante] =
        vikingo.mejorMontura(dragones.diff(dragonesYaElegidos), this)

      val _dragones = mejorParticipante.map{
        case Jinete(_, dragon) => dragon :: dragonesYaElegidos
        case _ => dragonesYaElegidos
      }.getOrElse(dragonesYaElegidos)

      (mejorParticipante::participantes, _dragones)
    }._1.filter(_.isDefined).map(_.get)
  }
}