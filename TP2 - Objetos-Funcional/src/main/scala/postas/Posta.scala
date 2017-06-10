package main.scala
import items.item._
import vikingos.Vikingo
  
//falta: hacer covariante la lista, por los jinetes
// el nivel de hambre que aumenta para un jinete es siempre 5 

  type Posta = List[Vikingo] => List[Vikingo]
  
  object hambrePosta
  {
    def apply(vikingo: Vikingo, porcentaje: Int) = {
      if (vikingo.hambre + porcentaje > 100) {
        vikingo.descalificar
      } else {
        vikingo.hambre(porcentaje)
      }
    }
  }
  
  def pesca: Posta = 
  {
    participantes:List[Vikingo] =>
    participantes.map(hambrePosta(_,5))
    participantes.filter(!_.descalificado)
    participantes.sortWith(_.puedeCargar > _.puedeCargar)
  } 
  
  def carrera(km: Int): Posta = 
  {
    participantes:List[Vikingo] =>
    participantes.map(hambrePosta(_,km))
    participantes.filter(!_.descalificado)
    participantes.sortWith(_.velocidad > _.velocidad)
  } 
  
  def combate:Posta =  
  {
    participantes:List[Vikingo] =>
    participantes.map(hambrePosta(_,10))
    participantes.filter(!_.descalificado)
    participantes.sortWith(_.danio > _.danio)
  }