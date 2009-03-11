package Triangle.AbstractSyntaxTrees;

import Triangle.SyntacticAnalyzer.SourcePosition;

public class ClassTypeDenoter extends TypeDenoter {
	public ClassTypeDenoter ( Identifier iAST, InstanceTypeDenoter itAST, 
					Source thePosition ) {
		super(thePosition);
		I = iAST;
		IT = itAST;
	}

	public Object visit (Visitor v, Object o) {
		  return v.visitRecordTypeDenoter(this, o);
	}
	
	public boolean equals (Object obj) {
		if (obj != null && obj instanceof ClassTypeDenoter) {
			ClassTypeDenoter ctd = (ClassTypeDenoter) obj;
			return (this.I.spelling.compareTo(ctd.I.spelling) == 0) &&
    				this.IT.equals(ctd.IT);
		} else
			return false;
	}

	public Identifier I;
	public InstanceTypeDenoter iT;
}
