package de.endrullis.sta.exmaples

import de.endrullis.sta._

/**
 * Hello World example.
 *
 * @author Stefan Endrullis &lt;stefan@endrullis.de&gt;
 */
object HelloWorld extends ScalaTikzAni {

	// show "Hello World" only for one moment (0 seconds)
	add("\\node at (0,0) {Hello World};" stay 0)

	// generate the pdf
	animate()

}
