package Triangle.AbstractSyntaxTrees;

import Triangle.SyntacticAnalyzer.SourcePosition;

public class ClassTypeDenoter extends Declaration {
	public ClassTypeDenoter ( Identifier iAST, Declaration dAST, 
					Source thePosition ) {
		super(thePosition);
		I = iAST;
		D = dAST;
	}

	public Object visit (Visitor v, Object o) {
		  return v.visitRecordTypeDenoter(this, o);
	}
	
	public boolean equals (Object obj) {
		if (obj != null && obj instanceof ClassTypeDenoter) {
			ClassTypeDenoter ctd = (ClassTypeDenoter) obj;
			return (this.I.spelling.compareTo(ctd.I.spelling) == 0) &&
    				this.D.equals(ctd.D);
		} else
			return false;
	}

	public Identifier I;
	public Declaration D;
}
