package main.scala
import items.item._
import vikingos.Vikingo
  
type Posta = List[Nordico] => List[Nordico]
  
  object hambrePosta
  {
    def apply(nordico: Nordico, porcentaje: Int) = 
    {
      nordico match 
      {
        case _:Vikingo => nordico.hambre(porcentaje)
        case _:Jinete => nordico.hambre(5)
      }
    }
  }
  
  def pesca: Posta = 
  {
    participantes:List[Nordico] =>
    participantes.map(hambrePosta(_,5))
                 .sortWith(_.puedeCargar > _.puedeCargar)
                 
  } 
  
  def carrera(km: Int): Posta = 
  {
    participantes:List[Nordico] =>
    participantes.map(hambrePosta(_,km))
                 .sortWith(_.velocidad > _.velocidad)
  } 
  
  def combate:Posta =  
  {
    participantes:List[Nordico] =>
    participantes.map(hambrePosta(_,10))
                 .sortWith(_.danio > _.danio)
  }