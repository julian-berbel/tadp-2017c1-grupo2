package dragones

case class Gronckle(peso: Int) extends Dragon(peso) {
  override def velocidadBasica: Int = super.velocidadBasica / 2
  
  val danio: Int = peso * 5
}