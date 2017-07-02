package postas

import dragones.Dragon
import participantes.Vikingo.Vikingo
import participantes.{Jinete, Participante}
import requerimientos._

trait Posta {
  val requerimiento: Option[Requerimiento]
  val cuantaHambreDa: Int

  def correr(participantes: List[Participante]): List[Participante] =
    participantes.filter(_.puedeCorrer(this)).sortBy(evaluar).reverse.map(_.correr(this).get)

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

      var dragonElegido: Option[Dragon] = None

      val mejorParticipante: Option[Participante] =
        dragones.diff(dragonesYaElegidos).foldLeft(Some(vikingo): Option[Participante]) { (mejorPorAhora, dragon) =>
          val otroParticipante: Option[Participante] = vikingo.montar(dragon)

          otroParticipante match {
            case None => mejorPorAhora
            case Some(otro) =>
              if (otro.esMejorQue(mejorPorAhora.get)(this)){
                dragonElegido = Some(dragon)
                otroParticipante
              }
              else mejorPorAhora
          }
        }

      dragonElegido.foreach(dragon => dragonesYaElegidos = dragon :: dragonesYaElegidos)

      mejorParticipante.get
    }
  }
}