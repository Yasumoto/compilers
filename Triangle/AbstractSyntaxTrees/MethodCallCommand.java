/*
 * @(#)DashVname.java                        2.1 2003/10/07
 *
 * Copyright (C) 1999, 2003 D.A. Watt and D.F. Brown
 * Dept. of Computing Science, University of Glasgow, Glasgow G12 8QQ Scotland
 * and School of Computer and Math Sciences, The Robert Gordon University,
 * St. Andrew Street, Aberdeen AB25 1HG, Scotland.
 * All rights reserved.
 *
 * This software is provided free for educational use only. It may
 * not be used for commercial purposes without the prior written permission
 * of the authors.
 */

package Triangle.AbstractSyntaxTrees;

import Triangle.SyntacticAnalyzer.SourcePosition;

public class MethodCallCommand extends Command {

  // actually converting this should work.
  public MethodCallCommand (Identifier iAST, Identifier iAST2, ActualParameterSequence apsAST, SourcePosition thePosition) {
    super (thePosition);
    I = iAST;
    I2 = iAST2;
    APS = apsAST;
  }

  public Object visit (Visitor v, Object o) {
    return v.visitMethodCallCommand(this, o);
  }

  public Identifier I;
  public Identifier I2;
  public Vname V;
  public ActualParameterSequence APS;
}