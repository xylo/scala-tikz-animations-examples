package de.endrullis.sta.exmaples

import de.endrullis.sta.{Pos, Generator}
import de.endrullis.sta.BaseVarIC._

/**
 * @author Stefan Endrullis &lt;stefan@endrullis.de&gt;
 */
object Text {

	def splitIntoLetters(s: String) = {
		s.indices.toList.map(i => "\\phantom{"+s.substring(0,i)+"}"+s.charAt(i)+"\\phantom{"+s.substring(i+1)+"}")
	}

	def node(text: Generator[_], pos: Generator[Pos] = Pos(0,0)) = "\\node at $pos {$text};" << ("pos" -> pos, "text" -> text)

}
