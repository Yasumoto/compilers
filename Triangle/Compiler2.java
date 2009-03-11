/*
 * @(#)Compiler2.java                        2.1 2003/10/07
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

package Triangle;

import Triangle.AbstractSyntaxTrees.Program;
import Triangle.CodeGenerator.Encoder;
import Triangle.ContextualAnalyzer.Checker;
import Triangle.SyntacticAnalyzer.Parser;
import Triangle.SyntacticAnalyzer.Scanner;
import Triangle.SyntacticAnalyzer.SourceFile;
import Triangle.TreeDrawer.Drawer;

/**
 * The main driver class for the Triangle compiler. 
 * Uses File Chooser dialog for source file.
 * Updated by A. Radenski, February 2007
 * 
 * @version  2.1 7 Oct 2003
 * @author  Deryck F. Brown
 */
public class Compiler2 {
  
  /** The filename for the object program, normally obj.tam. */
  static String objectName = "obj.tam";
  
  private static Scanner scanner;
  private static Parser parser;
  private static Checker checker;
  private static Encoder encoder;
  private static ErrorReporter reporter;
  private static Drawer drawer;
  
  /** The AST representing the source program. */
  private static Program theAST;
  
  /**
   * Compile the source program to TAM machine code.
   *
   * @param sourceName the name of the file containing the
   *    source program.
   * @param objectName the name of the file containing the
   *    object program.
   * @param showingAST true iff the AST is to be displayed after
   *    contextual analysis (not currently implemented).
   * @param showingTable true iff the object description details are to
   *    be displayed during code generation (not
   *    currently implemented).
   * @return true iff the source program is free of compile-time errors,
   *          otherwise false.
   */
  static boolean compileProgram (String sourceName, String objectName,
                                 boolean showingAST, boolean showingTable) {
    
    System.out.println("********** " +
                       "Triangle Compiler (Java Version 2.1)" +
                       " **********");
    
    System.out.println("Syntactic Analysis ...");
    SourceFile source = new SourceFile(sourceName);
    
    if (source == null) {
      System.out.println("Can't access source file " + sourceName);
      System.exit(1);
    }
    
    scanner  = new Scanner(source);
    reporter = new ErrorReporter();
    parser   = new Parser(scanner, reporter);
    checker  = new Checker(reporter);
    encoder  = new Encoder(reporter);
    drawer   = new Drawer();
    
    // scanner.enableDebugging();
    theAST = parser.parseProgram();    // 1st pass
    if (reporter.numErrors == 0) {
      //if (showingAST) {
      //    drawer.draw(theAST);
      //}
      System.out.println ("Contextual Analysis ...");
      checker.check(theAST);    // 2nd pass
      if (showingAST) {
        drawer.draw(theAST);
      }
      if (reporter.numErrors == 0) {
        System.out.println("Code Generation ...");
        encoder.encodeRun(theAST, showingTable); // 3rd pass
      }
    }
    
    boolean successful = (reporter.numErrors == 0);
    if (successful) {
      encoder.saveObjectProgram(objectName);
      System.out.println("Compilation was successful.");
    } else {
      System.out.println("Compilation was unsuccessful.");
    }
    return successful;
  }
  
  /**
   * Triangle compiler main program.
   *
   * @param args the only command-line argument to the program specifies
   *                  the source filename.
   */
  public static void main(String[] args) {
    boolean compiledOK;
    // Source file name as a command-line parameter:
     if (args.length != 1) {
     
    javax.swing.JFileChooser chooser = new javax.swing.JFileChooser();
    int returnVal = chooser.showOpenDialog(null);
    try {
      if(returnVal == javax.swing.JFileChooser.APPROVE_OPTION) {
        java.io.File sourceFile = chooser.getSelectedFile();
        String sourcePathName = sourceFile.getCanonicalPath();
        System.out.println("Compiling " + sourceFile.getName());
        compiledOK = compileProgram(sourcePathName, objectName, false, false);
      }
    } catch (java.io.IOException exception) {
      System.out.println(exception.toString());
    }
  }else{
     String sourceName = args[0];
  }
  }
}
