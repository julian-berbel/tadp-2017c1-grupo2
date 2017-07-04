package postas

import org.scalatest._
import participantes.Vikingo._

class TestPosta extends FlatSpec {

  "A Relay" should "not let a viking whose hunger would be over 100 after running participate" in {
    assert(!hipo.puedeCorrer(Carrera(150)))
  }

}
