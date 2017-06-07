package main.scala
import items.item._
import vikingos.Vikingo
  
//falta: hacer covariante la lista, por los jinetes

  object hambrePosta
  {
    def apply(vikingo: Vikingo, porcentaje: Int) = vikingo.hambre(porcentaje)
  }

  def pesca(participantes: List[Vikingo]): List[Vikingo] = 
  {
    participantes.map(hambrePosta(_,5))
    participantes.sortWith(_.puedeCargar > _.puedeCargar)
  } 
  
  def carrera(participantes: List[Vikingo], km:Int): List[Vikingo] = 
  {
    participantes.map(hambrePosta(_,km))
    participantes.sortWith(_.velocidad > _.velocidad)
  } 
  
  def combate(participantes: List[Vikingo]): List[Vikingo] = ???