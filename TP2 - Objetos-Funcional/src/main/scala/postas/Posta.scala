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
    var dragonesYaElegidos: List[Dragon] = List.empty

    vikingos.map { vikingo =>

      val mejorParticipante: Option[Participante] =
        vikingo.mejorMontura(dragones.diff(dragonesYaElegidos), this)

      mejorParticipante.foreach{
        case Jinete(_, dragon) => dragonesYaElegidos = dragon :: dragonesYaElegidos
        case _ =>
      }

      mejorParticipante
    }.filter(_.isDefined).map(_.get)
  }
}