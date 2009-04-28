/*
 * Class Type Denoter
 *
 * Used to add Classes to the Triangle AST implementation
 *
 * Joe Smith
 */
package Triangle.AbstractSyntaxTrees;

import Triangle.SyntacticAnalyzer.SourcePosition;

public class ClassTypeDenoter extends TypeDenoter {
	public ClassTypeDenoter ( Declaration dAST, 
					SourcePosition thePosition ) {
		super(thePosition);
		D = dAST;
	}

	
	public Object visit (Visitor v, Object o) {
		  return v.visitClassTypeDenoter(this, o);
	}
	
	public boolean equals (Object obj) {
		if (obj != null && obj instanceof ClassTypeDenoter) {
			ClassTypeDenoter ctd = (ClassTypeDenoter) obj;
			return (this.D.equals(ctd.D));
		} else
			return false;
	}

	public Declaration D;
}
